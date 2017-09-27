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
import com.edward.io.plugin.core.DataBaseConfig;
import com.edward.io.plugin.core.Execute;
import com.edward.io.plugin.core.ProcessException;
import com.edward.io.plugin.util.Assert;
import com.edward.io.plugin.util.Logger;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
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
public class MybatisReverseByJdbcExecutor implements Execute {

    private DataBaseConfig dataBaseConfig;
    private BuildContext buildContext;

    public MybatisReverseByJdbcExecutor(DataBaseConfig dataBaseConfig, BuildContext buildContext) {
        this.dataBaseConfig = dataBaseConfig;
        this.buildContext = buildContext;
    }

    public Object execute() throws ProcessException {
        Logger.tip("Begin to execute mybatis reverse generate");

        try {
            Assert.hasText(dataBaseConfig.getDriverClass(), "lacks of jdbc driver class");
            Assert.hasText(dataBaseConfig.getUrl(), "lacks of jdbc url");
            Assert.hasText(dataBaseConfig.getUserId(), "lacks of jdbc userId");
            Assert.hasText(dataBaseConfig.getPassword(), "lacks of jdbc password");
            Assert.notEmpty(dataBaseConfig.getTables(), "There is not any effective tables specified");

            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            Configuration config = new Configuration();
            config.addContext(initContext(dataBaseConfig));
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            return null;
        } catch (Exception e) {
            throw new ProcessException(e);
        }
    }

    private Context initContext(DataBaseConfig dataBaseConfig) {
        Context context = new Context(ModelType.HIERARCHICAL);
        context.setId("testTables");
        context.setTargetRuntime("MyBatis3");

        //comment generator
        CommentGeneratorConfiguration cgc = new CommentGeneratorConfiguration();
        cgc.addProperty("suppressAllComments", "true");
        context.setCommentGeneratorConfiguration(cgc);

        //jdbc connection
        JDBCConnectionConfiguration jcc = new JDBCConnectionConfiguration();
        jcc.setDriverClass(dataBaseConfig.getDriverClass());
        jcc.setConnectionURL(dataBaseConfig.getUrl());
        jcc.setUserId(dataBaseConfig.getUserId());
        jcc.setPassword(dataBaseConfig.getPassword());
        context.setJdbcConnectionConfiguration(jcc);

        //java type resolver
        JavaTypeResolverConfiguration jrc = new JavaTypeResolverConfiguration();
        jrc.addProperty("forceBigDecimals", "false");
        context.setJavaTypeResolverConfiguration(jrc);

        //java model generator
        JavaModelGeneratorConfiguration jgc = new JavaModelGeneratorConfiguration();
        jgc.setTargetPackage(buildContext.getBasePackage() + ".domain");
        jgc.setTargetProject("./src/main/java");
        jgc.addProperty("enableSubPackages", "false");
        jgc.addProperty("trimStrings", "true");
        context.setJavaModelGeneratorConfiguration(jgc);

        //sql map generator
        SqlMapGeneratorConfiguration sgc = new SqlMapGeneratorConfiguration();
        sgc.setTargetPackage(buildContext.getBasePackage() + ".mapper");
        File sqlmapDir = new File(buildContext.getBasedir() + "/src/main/resources/sqlmap");
        if (!sqlmapDir.exists()) sqlmapDir.mkdirs();
        sgc.setTargetProject("./src/main/resources/sqlmap");
        sgc.addProperty("enableSubPackages", "false");
        context.setSqlMapGeneratorConfiguration(sgc);

        //java client generator
        JavaClientGeneratorConfiguration jcgc = new JavaClientGeneratorConfiguration();
        jcgc.setConfigurationType("XMLMAPPER");
        jcgc.setTargetPackage(buildContext.getBasePackage() + ".mapper");
        jcgc.setTargetProject("./src/main/java");
        jcgc.addProperty("enableSubPackages", "false");
        context.setJavaClientGeneratorConfiguration(jcgc);

        //tables
        for (String table : dataBaseConfig.getTables()) {
            TableConfiguration tc = new TableConfiguration(context);
            tc.setTableName(table);
            if (dataBaseConfig.fromOracle()) {
                tc.setSchema(dataBaseConfig.getUserId());
            }
            context.addTableConfiguration(tc);
        }

        //plugins
        PluginConfiguration pc = new PluginConfiguration();
        pc.setConfigurationType("com.edward.io.plugin.support.plugins.BaseEntityPlugin");
        context.addPluginConfiguration(pc);

        return context;
    }
}
