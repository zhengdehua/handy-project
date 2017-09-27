package com.edward.io.jdk.classloader;

/**
 * Created by edwardcheng on 2017/9/11.
 */
public class ClassInitExample {

    private static class InstanceHolder {

        //reference this constant won't lead to initialize class object
        private static final String VERSION = "1.0.0.0";

        //however, reference this non-constant will lead to initialize class object
        private static String GROUP;

        static {
            System.out.println("InstanceHolder static code block");
        }
    }

    public static void main(String[] args) {

        //System.out.println(InstanceHolder.VERSION);
        System.out.println(InstanceHolder.GROUP);;
    }

}

