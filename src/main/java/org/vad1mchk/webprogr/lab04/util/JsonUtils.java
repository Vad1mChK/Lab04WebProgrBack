package org.vad1mchk.webprogr.lab04.util;

import jakarta.json.*;
import org.vad1mchk.webprogr.lab04.model.request.UserRequestDto;
import org.vad1mchk.webprogr.lab04.model.response.UserResponseDto;
import java.util.List;

public final class JsonUtils {
    public static UserRequestDto deserializeUserRequestDto(JsonObject userRequestJson) {
        if (userRequestJson == null) return null;
        UserRequestDto userRequestDto = new UserRequestDto();
        try {
            userRequestDto.setUsername(userRequestJson.getString("username"));
            userRequestDto.setPassword(userRequestJson.getString("password"));
        } catch (NullPointerException | ClassCastException e) {
            throw new IllegalArgumentException(
                "Cannot deserialize user: Field `username` or `password` does not exist or is not string in `user`"
            );
        }

        return userRequestDto;
    }

    public static JsonObject serializeUserResponseDto(UserResponseDto userResponseDto) {
        if (userResponseDto == null) return null;

        return Json.createObjectBuilder()
                .add("id", userResponseDto.getId())
                .add("username", userResponseDto.getUsername())
                .add("loggedIn", userResponseDto.isLoggedIn())
                .build();
    }

    public static JsonArray serializeListOfUserResponseDto(List<UserResponseDto> list) {
        if (list == null) return null;

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        try {
            list.forEach((userResponseDto) -> {
                arrayBuilder.add(serializeUserResponseDto(userResponseDto));
            });
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(
                    "Cannot serialize list of users: One or more users serialized are null."
            );
        }

        return arrayBuilder.build();
    }
}
