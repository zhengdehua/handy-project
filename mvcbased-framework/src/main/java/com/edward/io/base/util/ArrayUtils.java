package com.edward.io.base.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayUtils {

    public static long[] toLong(Object[] array) {
        if (array == null) {
            return null;
        }
        long[] values = new long[array.length];
        for (int i = 0; i < values.length; i++) {
            Object object = array[i];
            if (object instanceof Number) {
                values[i] = ((Number) object).longValue();
            } else {
                values[i] = Long.valueOf(object.toString());
            }
        }
        return values;
    }

    public static int[] toInt(Object[] array) {
        if (array == null) {
            return null;
        }
        int[] values = new int[array.length];
        for (int i = 0; i < values.length; i++) {
            Object object = array[i];
            if (object instanceof Number) {
                values[i] = ((Number) object).intValue();
            } else {
                values[i] = Integer.valueOf(object.toString());
            }
        }
        return values;
    }

    public static boolean isInArray(Object obj, Object... array) {
        if (array == null || array.length == 0) {
            return false;
        }
        for (Object item : array) {
            if (item == null && obj == null) {
                return true;
            } else if (item != null && item.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public static <T> T[] mixConcate(T[] slice, T... slices) {
        T[] _slices = slices;
        return concate(slice, slices);
    }

    public static <T> T[] concate(T[]... slices) {
        List<T> list = null;
        int size = 0;

        if (slices != null) {
            for (T[] slice : slices) {
                size += slice == null ? 0 : slice.length;
            }
        }

        if (size == 0) {
            return null;
        }

        list = new ArrayList<T>(size);

        for (T[] slice : slices) {
            Collections.addAll(list, slice);
        }

        Class clazz = slices[0].getClass().getComponentType();
        T[] arrays = (T[]) Array.newInstance(clazz, list.size());
        return list.toArray(arrays);
    }

}
