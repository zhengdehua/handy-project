/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.edward.io.plugin;

import com.edward.io.plugin.core.Constant;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/*************************************************************
*                 name: Edward Cheng 
*               gender: male 
*                  age: 27 
*               degree: undergraduate 
*               career: Java software engineer 
*            work time: 5 years 
*  master dev language: Java, JavaScript, HTML, SQL 
* master dev component: spring-framework, spring-boot, spring-cloud, spring-security, jquery, bootstrap, angularjs 
* growing dev language: C, Scala, Go, Python 
*                email: dwarcheng@163.com 
*               slogan: Interest always comes first. 
*************************************************************/

@Mojo(name = "Comment")
public class Comment {

    private static void findFile(File file, List<File> list) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File _file : files) {
                findFile(_file, list);
            }
        }else {
            list.add(file);
        }
    }

    private static void findFile(File[] files, List<File> list) {
        for (File file : files) {
            findFile(file, list);
        }
    }

    private static void addComment(String srcPath) {
        File[] files = new File(srcPath).listFiles();
        List<File> fileList = new ArrayList<File>();
        findFile(files, fileList);

        for (File thisFile : fileList) {

            String content = getFileContent(thisFile);
            int classIndex = content.indexOf("public class");
            if (classIndex == -1) classIndex = content.indexOf("public abstract class");
            if (classIndex == -1) classIndex = content.indexOf("public final class");
            if (classIndex == -1) classIndex = content.indexOf("public interface");
            if (classIndex == -1) continue;
            String beforeClassDef = content.substring(0, classIndex);
            int lastImportIndex = beforeClassDef.lastIndexOf("import");
            if (lastImportIndex == -1) lastImportIndex = beforeClassDef.indexOf("package");
            if (lastImportIndex == -1) continue;
            int lastClassCommentIndex = beforeClassDef.lastIndexOf("*/");
            if (lastClassCommentIndex == -1) continue;
            String classComment = beforeClassDef.substring(lastImportIndex, lastClassCommentIndex+2)
                    .replaceAll("import .*", "")
                    .replaceAll("package .*", "");

            content = content.replace(classComment, Constant.authorInfo());
            content = Constant.LICENSE + content.substring(content.indexOf("package"));

            writeFile(thisFile, content);
        }

    }

    private static String getFileContent(File file) {
        try {
            String content = "";
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            content = new String(bytes, "utf-8");
            return content;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeFile(File file, String content) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir");
        String srcPath = basePath + "/autoproject-maven-plugin/src/main/java/com/edward/io/plugin/";

        addComment(srcPath);
    }
}
