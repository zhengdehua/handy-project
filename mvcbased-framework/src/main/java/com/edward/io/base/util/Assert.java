package com.edward.io.base.util;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * Created by lori on 2017/8/15.
 */
public final class Assert {

    public static void notNull(Object object, String message) {
        if (object == null) throw new IllegalArgumentException(message);
    }

    public static void notNull(Object object) {
        notNull(object, "argument can not be null");
    }

    public static void hasText(String str, String message) {
        if (str == null || str.trim().length() == 0) throw new IllegalArgumentException(message);
    }

    public static void hasText(String str) {
        hasText(str, "argument can not be empty");
    }

    public static void isTrue(Boolean expression, String message) {
        if (!expression) throw new IllegalArgumentException(message);
    }

    public static void isTrue(Boolean expression) {
        isTrue(expression, "argument can not be false");
    }

    public static void notEmpty(Object object, String message) {
        notNull(object, message);
        if (object instanceof Collection && ((Collection)object).size() == 0)
            throw new IllegalArgumentException(message);
        if (object.getClass().isArray() && Array.getLength(object) == 0)
            throw new IllegalArgumentException(message);
    }

    public static void notEmpty(Object object) {
        notEmpty(object, "argument can not be empty");
    }

}
