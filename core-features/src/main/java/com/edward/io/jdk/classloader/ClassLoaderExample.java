package com.edward.io.jdk.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * Java utilize classloader to load byte code file from disk to memory.
 *
 * <pre>
 *     ClassLoader hierarchical structure:
 *
 *     BootStrapClassLoader : Responsible for loading ${JavaHome}/jre/lib
 *        |
 *      ExtClassLoader : Responsible for loading ${JavaHome}/jre/ext
 *          |
 *         AppClassLoader : Responsible for loading application classes and thirdparty libs
 * </pre>
 *
 * Created by edwardcheng on 2017/9/19.
 */
public class ClassLoaderExample {

    public static void main(String[] args) throws Exception{

        //ClassLoader hierarchical structure
        ClassLoader appLoader = ClassLoaderExample.class.getClassLoader();
        System.out.println(appLoader);
        ClassLoader extLoader = appLoader.getParent();
        System.out.println(extLoader);
        ClassLoader bootLoader = extLoader.getParent();
        System.out.println(bootLoader); // bootLoader will be null, it exists in c++ level.

        //load class through URLClassLoader, class won't be initialized
        String packagePath = System.getProperty("user.dir") + "/core-features/target/classes/com/edward/io/jdk/classloader/";
        URLClassLoader urlLoader = new URLClassLoader(new URL[]{new URL("file:/"+packagePath)});
        Class sample = urlLoader.loadClass("com.edward.io.jdk.classloader.SampleClass");
        System.out.println(sample);

        //load class through AppClassLoader, class won't be initialized
        appLoader.loadClass("com.edward.io.jdk.classloader.SampleClass");

        //load class through default class loader, this method will lead to initialize class by default
        Class.forName("com.edward.io.jdk.classloader.SampleClass");

    }
}
