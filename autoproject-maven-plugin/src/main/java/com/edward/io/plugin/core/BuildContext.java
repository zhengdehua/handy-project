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

import java.util.ArrayList;
import java.util.Collections;
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


public class BuildContext {

    private static BuildContext instance;

    private String groupId;
    private String artifactId;
    private String author;
    private String basedir;
    private String date;
    private String ormReverse;

    public BuildContext() {
        synchronized (BuildContext.class) {
            if (instance == null) {
                instance = this;
            }
        }
    }

    public static BuildContext getInstance() {
        return instance;
    }

    private String calcArtifactId() {
        String str = artifactId.replaceAll("[^\\w]", "|");
        String result = str;
        String[] groups = str.split("\\|");
        List<String> list = new ArrayList<String>();
        String grp = "";
        for(int i=groups.length-1; i>=0; i--) {
            grp = groups[i];
            if (grp == null || grp.trim().length() == 0) {
                continue;
            }
            if (grp.matches("^\\d+$")) {
                list.add(grp);
                continue;
            }
            list.add(grp);
            break;
        }
        Collections.reverse(list);
        StringBuilder sb = new StringBuilder();
        for (String item : list) {
            sb.append(item);
        }
        result = sb.toString();
        return result;
    }

    public String getBasePackage() {
        return groupId + "." + calcArtifactId();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBasedir() {
        return basedir;
    }

    public void setBasedir(String basedir) {
        this.basedir = basedir;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrmReverse() {
        return ormReverse;
    }

    public void setOrmReverse(String ormReverse) {
        this.ormReverse = ormReverse;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("basedir --> ").append(basedir).append("\n");
        sb.append("groupId --> ").append(groupId).append("\n");
        sb.append("artifactId --> ").append(artifactId).append("\n");
        sb.append("author --> ").append(author).append("\n");
        sb.append("date --> ").append(date).append("\n");
        sb.append("ormReverse --> ").append(ormReverse).append("\n");
        return sb.toString();
    }
}
