package org.vad1mchk.webprogr.lab04.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.security.auth.message.AuthException;
import org.vad1mchk.webprogr.lab04.database.UserDao;
import org.vad1mchk.webprogr.lab04.model.entity.User;
import org.vad1mchk.webprogr.lab04.model.request.UserRequestDto;
import org.vad1mchk.webprogr.lab04.model.response.UserResponseDto;
import org.vad1mchk.webprogr.lab04.security.Hasher;
import org.vad1mchk.webprogr.lab04.security.Sha512Hasher;
import org.vad1mchk.webprogr.lab04.util.CredentialsValidator;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Stateless
public class UserService {
    @EJB
    private UserDao userDao;
    @EJB
    private CredentialsValidator credentialsValidator;

    // Method just for debug purposes.
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userDao.selectAll();
        return users.stream()
                .filter(Objects::nonNull)
                .map((user) -> new UserResponseDto(user.getId(), user.getUsername(), user.isLoggedIn()))
                .collect(Collectors.toList());
    }

    public UserResponseDto registerUser(UserRequestDto request) throws AuthException {
        if (userDao.selectByUsername(request.getUsername()) != null) {
            throw new AuthException("Пользователь с таким именем уже существует.");
        }

        validateCredentials(request.getUsername(), request.getPassword());

        User user = new User();

        Hasher hasher = new Sha512Hasher(getPepper());
        byte[] salt = hasher.generateSalt();

        user.setUsername(request.getUsername());
        user.setPasswordHashed(hasher.hash(request.getPassword(), salt));
        user.setSalt(salt);

        userDao.insert(user);

        return new UserResponseDto(user.getId(), user.getUsername(), user.isLoggedIn());
    }

    public UserResponseDto logInUser(UserRequestDto request) throws AuthException {
        User user = userDao.selectByUsername(request.getUsername());

        if (user == null) {
            throw new AuthException("Пользователя с таким именем не существует.");
        }

        byte[] salt = user.getSalt();
        byte[] passwordHashed = user.getPasswordHashed();

        Hasher hasher = new Sha512Hasher(getPepper());

        if (!Arrays.equals(
                hasher.hash(request.getPassword(), salt),
                passwordHashed
        )) {
            throw new AuthException("Неверный пароль.");
        }

        user.setLoggedIn(true);

        return new UserResponseDto(user.getId(), user.getUsername(), user.isLoggedIn());
    }

    private void validateCredentials(String username, String password) throws AuthException {
        String usernameValidationMessage = credentialsValidator.validateUsername(username);
        String passwordValidationMessage = credentialsValidator.validatePassword(password);

        if (usernameValidationMessage != null) {
            throw new AuthException(usernameValidationMessage);
        }

        if (passwordValidationMessage != null) {
            throw new AuthException(passwordValidationMessage);
        }
    }

    private byte[] getPepper() {
        String pepperString = System.getenv("pepper");
        return pepperString != null ? pepperString.getBytes(StandardCharsets.UTF_8) : null;
    }
}
