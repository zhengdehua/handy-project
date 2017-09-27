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
package com.edward.io.plugin.execute;

import com.edward.io.plugin.core.BuildContext;
import com.edward.io.plugin.core.Execute;
import com.edward.io.plugin.core.ProcessException;
import com.edward.io.plugin.util.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
public class MybatisRuntimeConfigExecutor implements Execute {

    private BuildContext buildContext;

    public MybatisRuntimeConfigExecutor(BuildContext buildContext) {
        this.buildContext = buildContext;
    }

    public Object execute() throws ProcessException {
        Logger.tip("Begin to execute mybatis runtime config file generation");

        InputStream is = null;
        FileOutputStream fos = null;
        try {
            String path = buildContext.getBasedir() + "/src/main/resources/mybatis.xml";
            fos = new FileOutputStream(path);
            is = MybatisRuntimeConfigExecutor.class.getResourceAsStream("/mybatis/mybatis.xml");
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            fos.write(bytes);
            fos.flush();
            return null;
        } catch (IOException e) {
            throw new ProcessException(e);
        } finally {
            try {
                if (is != null) is.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
