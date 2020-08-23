package com.example.ankit.advisr.utils;

import lombok.NonNull;

public class QuestionUtils {

    private static final String PERCENT = "%";

    private QuestionUtils() { }

    public static String createLikeQuery(@NonNull final String string) {
        if (string.length() != 0) {
            return PERCENT + string + PERCENT;
        } else {
            return string;
        }
    }

}
