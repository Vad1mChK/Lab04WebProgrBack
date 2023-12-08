package org.vad1mchk.webprogr.lab04.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.vad1mchk.webprogr.lab04.database.UserDao;
import org.vad1mchk.webprogr.lab04.model.entity.User;
import org.vad1mchk.webprogr.lab04.model.response.UserResponseDto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class UserService {
    @EJB
    private UserDao userDao;

    // Method just for debug purposes.
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userDao.selectAll();
        return users.stream()
                .filter(Objects::nonNull)
                .map((user) -> new UserResponseDto(user.getId(), user.getUsername(), user.isLoggedIn()))
                .collect(Collectors.toList());
    }
}
