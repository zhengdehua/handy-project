package com.edward.io.plugin.util;

import java.io.File;

/**
 * Created by lori on 2017/9/4.
 */
public final class FileUtil {

    public static boolean deleteDirectory(File file) {
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File childFile : files) {
                    forceDelete(childFile);
                }
            }
            file.delete();
        }
        return true;
    }

    public static boolean forceDelete(File file) {
        boolean isPresent = file.exists();
        if (isPresent && file.isDirectory()) {
            deleteDirectory(file);
        }else {
            if (isPresent) {
                file.delete();
            }
        }
        return true;
    }

    public static void main(String[] args) {

        String path = "D:\\SVN\\shClearingV2.0\\Sm@rtGalaxy\\gaea_poc\\gaea-core\\src\\main\\resources\\META-INF\\mapper\\com";
        forceDelete(new File(path));
    }
}
