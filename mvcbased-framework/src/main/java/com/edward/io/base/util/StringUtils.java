package com.edward.io.base.util;

import java.util.Iterator;
import java.util.List;

/**
 * Created by edwardcheng on 2017/9/14.
 */
public class StringUtils {

    public static boolean isNotBlank(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static String[] split(String str, String delimit) {
        String[] array = null;
        if (isNotBlank(str) && isNotBlank(delimit)) {
            array = str.split(delimit);
        }
        if (array == null) {
            array = new String[]{str};
        }
        return array;
    }

    public static String join(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        separator = (separator == null ? "" : separator.trim());
        if (list != null && list.size() > 0) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                if (object != null) {
                    sb.append(object.toString()).append(separator);
                }
            }
            int separatorTailPos = -1;
            if ((separatorTailPos = sb.lastIndexOf(separator)) == sb.length() - 1) {
                sb.delete(separatorTailPos, separatorTailPos + separator.length());
            }
        }
        return sb.toString();
    }
}
