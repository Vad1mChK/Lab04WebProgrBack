package org.vad1mchk.webprogr.lab04.service;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.JwtException;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.security.auth.message.AuthException;
import org.vad1mchk.webprogr.lab04.database.ShotDao;
import org.vad1mchk.webprogr.lab04.database.UserDao;
import org.vad1mchk.webprogr.lab04.model.entity.Shot;
import org.vad1mchk.webprogr.lab04.model.entity.User;
import org.vad1mchk.webprogr.lab04.model.request.ShotRequestDto;
import org.vad1mchk.webprogr.lab04.model.response.ShotDeletionResponseDto;
import org.vad1mchk.webprogr.lab04.model.response.ShotResponseDto;
import org.vad1mchk.webprogr.lab04.util.JwtTokenProvider;
import org.vad1mchk.webprogr.lab04.util.ShotHitChecker;
import org.vad1mchk.webprogr.lab04.util.ZonedDateTimeFormatter;

@Stateless
public class ShotService {
    @EJB
    private ShotDao shotDao;

    @EJB
    private UserDao userDao;

    @EJB
    private JwtTokenProvider jwtProvider;

    public List<ShotResponseDto> getShotsByOwner(String jwt) throws AuthException {
        validateToken(jwt);
        ZonedDateTimeFormatter formatter = new ZonedDateTimeFormatter();

        User user = getUserByJwt(jwt);

        return shotDao
                .selectByOwner(user)
                .stream()
                .map(ShotResponseDto::fromShot)
                .collect(Collectors.toList());
    }

    public ShotResponseDto addShot(String jwt, ShotRequestDto shotRequestDto) throws AuthException {
        validateToken(jwt);

        String xString = shotRequestDto.getX();
        String yString = shotRequestDto.getY();
        String rString = shotRequestDto.getR();
        int zoneOffsetSeconds = shotRequestDto.getZone();

        Shot shot = new Shot();
        try {
            long startTime = System.nanoTime();

            BigDecimal x = new BigDecimal(xString);
            BigDecimal y = new BigDecimal(yString);
            BigDecimal r = new BigDecimal(rString);

            shot.setX(x);
            shot.setY(y);
            shot.setR(r);
            shot.setZoneOffsetSeconds(zoneOffsetSeconds);
            shot.setCreationDateTime(ZonedDateTime.now(ZoneOffset.ofTotalSeconds(zoneOffsetSeconds)).toLocalDateTime());
            shot.setOwner(getUserByJwt(jwt));
            shot.setHit(ShotHitChecker.check(x, y, r));

            shot.setTimeElapsedNanoseconds(System.nanoTime() - startTime);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Одно из значений X, Y, R — не число");
        }

        shotDao.insert(shot);

        return ShotResponseDto.fromShot(shot);
    }

    public ShotDeletionResponseDto deleteShotsByOwner(String jwt) throws AuthException {
        validateToken(jwt);

        User user = getUserByJwt(jwt);

        int deletedCount = shotDao.deleteByOwner(user);
        ShotDeletionResponseDto responseDto = new ShotDeletionResponseDto();
        responseDto.setDeletedCount(deletedCount);
        return responseDto;
    }

    private void validateToken(String jwt) throws AuthException {
        if (!jwtProvider.validateToken(jwt)) {
            throw new AuthException("JWT-токен не валиден.");
        }
    }

    private User getUserByJwt(String jwt) throws AuthException {
        if (jwtProvider.validateToken(jwt)) {
            String username = jwtProvider.getSubject(jwt);
            if (username == null) {
                throw new JwtException("Невозможно получить имя пользователя из JWT-токена.");
            }
            User user = userDao.selectByUsername(username);
            if (user == null) {
                throw new AuthException("Пользователя с именем, указанном в JWT-токене, не существует.");
            }
            return user;
        } else {
            throw new AuthException("JWT-токен не валиден.");
        }
    }
}
