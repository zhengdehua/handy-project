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


import com.edward.io.plugin.core.*;
import com.edward.io.plugin.util.Assert;
import com.edward.io.plugin.util.FileUtil;
import com.edward.io.plugin.util.Logger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class CodeGenExecutor implements Execute {

    private MetaData metaData;
    private BuildContext buildContext;

    public CodeGenExecutor(MetaData metaData, BuildContext buildContext) {
        this.metaData = metaData;
        this.buildContext = buildContext;
    }

    public Object execute() throws ProcessException {
        Logger.tip("Begin to execute code generator");

        try {
            Configuration cfg = loadTemplates();

            generateDto(cfg);
            Logger.info("Successfully generate dto java beans");

            generateDao(cfg);
            Logger.info("Successfully generate dao java beans");

            if (Constant.MYBATIS_REVERSE.equals(BuildContext.getInstance().getOrmReverse())) {
                generateMapper();
                Logger.info("Successfully generate ibatis mapper java beans");
            }

            generateService(cfg);
            Logger.info("Successfully generate service java beans");

            generateControllerBean(cfg);
            Logger.info("Successfully generate controller-bean java beans");

            generateController(cfg);
            Logger.info("Successfully generate controller java beans");

            generateSpringBootApplication(cfg);
            Logger.info("Successfully generate spring boot entry");

            generateApplicationProperties(cfg);
            Logger.info("Successfully generate spring boot properties");

            moveMapperResources();

            return null;
        } catch (Exception e) {
            throw new ProcessException(e);
        }
    }

    private void generateDto(Configuration cfg) throws Exception {
        FileOutputStream fos = null;
        Writer out = null;

        Template template = cfg.getTemplate("dto.ftl");
        String filePath = metaData.getTargetPath() + "/dto/";
        File dtoDir = new File(filePath);
        if (!dtoDir.exists()) {
            dtoDir.mkdirs();
        }

        List<MetaData.Item> items = metaData.getItems();
        Assert.notEmpty(items, "There is no dto items to handle");

        for (MetaData.Item dtoItem : items) {
            try {
                fos = new FileOutputStream(filePath + dtoItem.getDomain() + "Dto.java");
                out = new OutputStreamWriter(fos);
                template.process(dtoItem, out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fos != null) fos.close();
                if (out != null) out.close();
            }
        }

    }

    private void generateDao(Configuration cfg) throws Exception{
        FileOutputStream fos = null;
        Writer out = null;

        Template template = cfg.getTemplate("dao.ftl");
        String filePath = metaData.getTargetPath() + "/dao/";
        File dtoDir = new File(filePath);
        if (!dtoDir.exists()) {
            dtoDir.mkdirs();
        }

        List<MetaData.Item> items = metaData.getItems();
        Assert.notEmpty(items, "There is no dao items to handle");

        for (MetaData.Item dtoItem : items) {
            try {
                fos = new FileOutputStream(filePath + dtoItem.getDomain() + "Dao.java");
                out = new OutputStreamWriter(fos);
                template.process(dtoItem, out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fos != null) fos.close();
                if (out != null) out.close();
            }
        }
    }

    private void generateMapper() throws Exception{
        String mapperDir = metaData.getTargetPath() + "/mapper/";
        String[] imports = {
                "import org.apache.ibatis.annotations.Mapper;",
                "import com.edward.io.base.support.mybatis.OrmMapper;"
        };

        String importLineRegx = "[\\s\\t]*import[\\s\\t\\w\\.\\;]+$";
        String interfaceLineRegx = "[\\s\\t]*public interface [\\s\\t\\w\\{]+$";

        List<MetaData.Item> items = metaData.getItems();

        for (MetaData.Item item : items) {
            String domain = item.getDomain();
            String mapper = domain + "Mapper";
            String filepath = mapperDir + mapper + ".java";

            boolean hasImport = false;
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            String line = reader.readLine();
            while (line != null) {
                if (!hasImport && line.matches(importLineRegx)) {
                    for (String s : imports) {
                        sb.append(s).append('\n');
                    }
                    hasImport = true;
                }

                if (line.matches(interfaceLineRegx)) {
                    line = line.replace(mapper, mapper+" extends OrmMapper<"+domain+","+domain+"Key>");
                    sb.append("@Mapper").append('\n');
                }

                sb.append(line).append('\n');
                line = reader.readLine();
            }

            File file = new File(filepath);
            file.delete();

            FileOutputStream fos = new FileOutputStream(filepath);
            fos.write(sb.toString().getBytes("utf-8"));
            fos.flush();
            fos.close();
        }

    }

    private void generateService(Configuration cfg) throws Exception{
        FileOutputStream fos = null;
        Writer out = null;

        Template template = cfg.getTemplate("service.ftl");
        String filePath = metaData.getTargetPath() + "/service/";
        File dtoDir = new File(filePath);
        if (!dtoDir.exists()) {
            dtoDir.mkdirs();
        }

        List<MetaData.Item> items = metaData.getItems();
        Assert.notEmpty(items, "There is no service items to handle");

        for (MetaData.Item dtoItem : items) {
            try {
                fos = new FileOutputStream(filePath + dtoItem.getDomain() + "Service.java");
                out = new OutputStreamWriter(fos);
                template.process(dtoItem, out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fos != null) fos.close();
                if (out != null) out.close();
            }
        }
    }

    private void generateControllerBean(Configuration cfg) throws Exception{
        FileOutputStream fos = null;
        Writer out = null;

        Template template = cfg.getTemplate("controller-bean.ftl");
        String filePath = metaData.getTargetPath() + "/controller/";
        File dtoDir = new File(filePath);
        if (!dtoDir.exists()) {
            dtoDir.mkdirs();
        }

        List<MetaData.Item> items = metaData.getItems();
        Assert.notEmpty(items, "There is no controller bean items to handle");

        for (MetaData.Item dtoItem : items) {
            try {
                fos = new FileOutputStream(filePath + dtoItem.getDomain() + "ControllerBean.java");
                out = new OutputStreamWriter(fos);
                template.process(dtoItem, out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fos != null) fos.close();
                if (out != null) out.close();
            }
        }
    }

    private void generateController(Configuration cfg) throws Exception{
        FileOutputStream fos = null;
        Writer out = null;

        Template template = cfg.getTemplate("controller.ftl");
        String filePath = metaData.getTargetPath() + "/controller/";
        File dtoDir = new File(filePath);
        if (!dtoDir.exists()) {
            dtoDir.mkdirs();
        }

        List<MetaData.Item> items = metaData.getItems();
        Assert.notEmpty(items, "There is no controller items to handle");

        for (MetaData.Item dtoItem : items) {
            try {
                fos = new FileOutputStream(filePath + dtoItem.getDomain() + "Controller.java");
                out = new OutputStreamWriter(fos);
                template.process(dtoItem, out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fos != null) fos.close();
                if (out != null) out.close();
            }
        }
    }

    private void generateSpringBootApplication(Configuration cfg) throws Exception {
        FileOutputStream fos = null;
        Writer out = null;

        try {
            Template template = cfg.getTemplate("spring-boot.ftl");
            String filePath = metaData.getTargetPath();
            File dtoDir = new File(filePath);
            if (!dtoDir.exists()) {
                dtoDir.mkdirs();
            }

            Map<String,String> model = new HashMap<String, String>();
            model.put("basePackage", buildContext.getBasePackage());
            model.put("author", buildContext.getAuthor());
            model.put("date", buildContext.getDate());

            fos = new FileOutputStream(filePath + "/Application.java");
            out = new OutputStreamWriter(fos);
            template.process(model, out);

        } finally {
            if (fos != null) fos.close();
            if (out != null) out.close();
        }
    }

    private void generateApplicationProperties(Configuration cfg) throws Exception {
        FileOutputStream fos = null;
        Writer out = null;

        try {
            Template template = cfg.getTemplate("application.ftl");
            String filePath = buildContext.getBasedir() + "/src/main/resources";
            File dtoDir = new File(filePath);
            if (!dtoDir.exists()) {
                dtoDir.mkdirs();
            }

            Map<String,String> model = new HashMap<String, String>();
            DataBaseConfig dataBaseConfig = DataBaseConfig.getInstance();
            if (dataBaseConfig != null) {
                model.put("driverClass", dataBaseConfig.getDriverClass());
                model.put("url", dataBaseConfig.getUrl());
                model.put("userId", dataBaseConfig.getUserId());
                model.put("password", dataBaseConfig.getPassword());
            }

            String basePackage = buildContext.getBasePackage();
            String appContext = basePackage;

            if (basePackage.indexOf('.') != -1) {
                appContext = basePackage.substring(basePackage.lastIndexOf('.') + 1);
            }

            if (Constant.MYBATIS_REVERSE.equals(BuildContext.getInstance().getOrmReverse())) {
                model.put("typeAliasesPackage", basePackage + ".domain");
            }

            model.put("appContext", appContext);
            model.put("author", buildContext.getAuthor());
            model.put("date", buildContext.getDate());

            fos = new FileOutputStream(filePath + "/application.yml");
            out = new OutputStreamWriter(fos);
            template.process(model, out);

        } finally {
            if (fos != null) fos.close();
            if (out != null) out.close();
        }
    }

    private void moveMapperResources() throws Exception {
        String resourcePath = buildContext.getBasedir() + "/src/main/resources/sqlmap/";
        String mapperPath = resourcePath + (buildContext.getBasePackage() + ".mapper").replaceAll("\\.", "\\/");
        File mapperDir = new File(mapperPath);
        if (mapperDir.exists() && mapperDir.isDirectory()) {
            File[] files = mapperDir.listFiles();
            if (files != null && files.length > 0) {
                FileOutputStream fos = null;
                FileInputStream fis = null;

                for (File file : files) {
                    try {
                        fos = new FileOutputStream(resourcePath + file.getName());
                        fis = new FileInputStream(file);
                        byte[] bytes = new byte[fis.available()];
                        fis.read(bytes);
                        fos.write(updateSqlMap(bytes));
                        fos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fis != null) {
                            fis.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    }
                }

                FileUtil.forceDelete(new File(resourcePath
                        + buildContext.getBasePackage().substring(0, buildContext.getBasePackage().indexOf("."))));

            }
        }
    }

    private byte[] updateSqlMap(byte[] bytes) throws Exception {
        String oracleSchemaRegx = ".*(PRESS.).*";
        Pattern oracleSchemaPattern = Pattern.compile(oracleSchemaRegx);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
        String line = null;
        Matcher matcher = null;
        DataBaseConfig dataBaseConfig = DataBaseConfig.getInstance();
        try {
            while ((line = br.readLine()) != null) {
                if (dataBaseConfig.fromOracle()) {
                    matcher = oracleSchemaPattern.matcher(line);
                    if (matcher.matches()) {
                        line = line.replaceAll(matcher.group(1), "");
                    }
                }
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }

        return sb.toString().getBytes();
    }

    private Configuration loadTemplates() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setClassForTemplateLoading(CodeGenExecutor.class, "/model/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg;
    }

}
