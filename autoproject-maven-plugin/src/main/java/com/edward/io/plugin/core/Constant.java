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
package com.edward.io.plugin.core;

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
public class Constant {

    public static final String MYBATIS_REVERSE = "mybatis-reverse";
    public static final String HIBERNATE_REVERSE = "hibernate-reverse";

    public interface Author {
        String NAME = "Edward Cheng";
        String GENDER = "male";
        String AGE = "27";
        String DEGREE = "undergraduate";
        String CAREER = "Java software engineer";
        String WORK_TIME = "5 years";
        String MASTER_DEV_LANGUAGE = "Java, JavaScript, HTML, SQL";
        String GROWING_DEV_LANGUAGE = "C, Scala, Go, Python";
        String MASTER_DEV_COMPONENT = "spring-framework, spring-boot, spring-cloud, spring-security, jquery, bootstrap, angularjs";
        String EMAIL = "dwarcheng@163.com";
        String SLOGAN = "Interest always comes first.";
    }

    public static final String LICENSE = "/*\n" +
            " * Copyright 2002-2016 the original author or authors.\n" +
            " *\n" +
            " * Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
            " * you may not use this file except in compliance with the License.\n" +
            " * You may obtain a copy of the License at\n" +
            " *\n" +
            " *      http://www.apache.org/licenses/LICENSE-2.0\n" +
            " *\n" +
            " * Unless required by applicable law or agreed to in writing, software\n" +
            " * distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            " * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            " * See the License for the specific language governing permissions and\n" +
            " * limitations under the License.\n" +
            " */\n";

    public static String authorInfo() {
        StringBuilder sb = new StringBuilder();
        String line = "*************************************************************";
        String halfLine = "*";

        String[] content = {
                "                 name: " + Author.NAME + " ",
                "               gender: " + Author.GENDER + " ",
                "                  age: " + Author.AGE + " ",
                "               degree: " + Author.DEGREE + " ",
                "               career: " + Author.CAREER + " ",
                "            work time: " + Author.WORK_TIME + " ",
                "  master dev language: " + Author.MASTER_DEV_LANGUAGE + " ",
                " master dev component: " + Author.MASTER_DEV_COMPONENT + " ",
                " growing dev language: " + Author.GROWING_DEV_LANGUAGE + " ",
                "                email: " + Author.EMAIL + " ",
                "               slogan: " + Author.SLOGAN + " "
        };

        sb.append("\n\n").append('/').append(line);

        for (String item : content) {
            sb.append('\n').append(halfLine).append(item);
        }

        sb.append('\n').append(line).append('/');

        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(LICENSE);
        System.out.println(authorInfo());
    }

}
