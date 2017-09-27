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

import com.edward.io.plugin.util.Logger;
import com.edward.io.plugin.core.BuildContext;
import com.edward.io.plugin.core.Execute;
import com.edward.io.plugin.core.ProcessException;

import java.io.*;

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
public class HelpGenerateConfigExecutor implements Execute {

    private BuildContext buildContext;

    public HelpGenerateConfigExecutor(BuildContext buildContext) {
        this.buildContext = buildContext;
    }

    public Object execute() throws ProcessException {
        Logger.tip("Begin to execute help[generateConfig]");

        InputStream is = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            String path = buildContext.getBasedir() + "/src/main/resources/generateConfig.xml";
            fos = new FileOutputStream(path);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            is = HelpGenerateConfigExecutor.class.getResourceAsStream("/help/generateConfig.xml");
            br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = br.readLine()) != null) {
                Logger.info(line);
                bw.write(line);
                bw.write("\n");
            }
            fos.flush();
            bw.flush();
            return null;
        } catch (IOException e) {
            throw new ProcessException(e);
        } finally {
            try {
                if (is != null) is.close();
                if (br != null) br.close();
                if (fos != null) fos.close();
                if (bw != null) bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
