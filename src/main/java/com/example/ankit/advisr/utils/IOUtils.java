package com.example.ankit.advisr.utils;

import com.example.ankit.advisr.model.User;
import lombok.NonNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

    private static final String PERCENT = "%";

    private IOUtils() { }

    public static String createLikeQuery(@NonNull final String string) {
        if (string.length() != 0) {
            return PERCENT + string + PERCENT;
        } else {
            return string;
        }
    }

    public static List<User> convertToUserList(@NonNull final List<List<Object>> lists) {
        List<User> users = new ArrayList<>();
        lists.forEach(userArray -> {
            final User user = User.builder()
                    .id(Long.parseLong((new BigInteger(String.valueOf(userArray.get(0)))).toString()))
                    .firstName(String.valueOf(userArray.get(1)))
                    .lastName(String.valueOf(userArray.get(2)))
                    .email(String.valueOf(userArray.get(3)))
                    .build();
            users.add(user);
        });
        return users;
    }

}
