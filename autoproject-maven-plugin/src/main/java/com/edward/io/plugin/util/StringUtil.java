package com.edward.io.plugin.util;

/**
 * Created by lori on 2017/9/5.
 */
public final class StringUtil {

    public static String upperCaseFirstLetter(String word) {
        String result = word;
        if (word != null) {
            char first = word.charAt(0);
            if (first >= 97 && first <= 122) {
                first -= 32;
            }
            result = first + word.substring(1);
        }
        return result;
    }
}
