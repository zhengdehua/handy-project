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
package com.edward.io.plugin.support.plugins;

import com.edward.io.plugin.util.Logger;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;
import java.util.Properties;

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
public class BaseEntityPlugin extends PluginAdapter {

    private FullyQualifiedJavaType serializable;
    private FullyQualifiedJavaType gwtSerializable;
    private boolean addGWTInterface;
    private boolean suppressJavaInterface;

    public BaseEntityPlugin() {
        super();
        serializable = new FullyQualifiedJavaType("java.io.Serializable"); //$NON-NLS-1$
        gwtSerializable = new FullyQualifiedJavaType("com.google.gwt.user.client.rpc.IsSerializable"); //$NON-NLS-1$
    }

    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        addGWTInterface = Boolean.valueOf(properties.getProperty("addGWTInterface")); //$NON-NLS-1$
        suppressJavaInterface = Boolean.valueOf(properties.getProperty("suppressJavaInterface")); //$NON-NLS-1$
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        Logger.info("Adapt base record class to BaseEntity interface");
        implementBaseEntity(topLevelClass, introspectedTable);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        makeSerializable(topLevelClass, introspectedTable);
        return true;
    }

    protected void implementBaseEntity(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String pk = introspectedTable.getPrimaryKeyType();
        FullyQualifiedJavaType pkType = new FullyQualifiedJavaType(pk);
        Logger.info("implement BaseEntity<"+pk+">");
        FullyQualifiedJavaType baseEntity = new FullyQualifiedJavaType("com.edward.io.base.domain.BaseEntity");
        baseEntity.addTypeArgument(pkType);
        topLevelClass.addImportedType(baseEntity);
        topLevelClass.addSuperInterface(baseEntity);

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(pkType);
        method.setName("getPrimayKey");
        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
        if (columns != null && columns.size() > 0) {
            method.addBodyLine(pk + " key = new " + pk + "();");
            for (IntrospectedColumn column : columns) {
                String property = column.getJavaProperty();
                String methodName = upperCaseFirstLetter(property);
                method.addBodyLine("key.set" + methodName + "(get"+methodName+"());");
            }
            method.addBodyLine("return key;");
        }

        topLevelClass.addMethod(method);
    }

    protected void makeSerializable(TopLevelClass topLevelClass,
                                    IntrospectedTable introspectedTable) {
        if (addGWTInterface) {
            topLevelClass.addImportedType(gwtSerializable);
            topLevelClass.addSuperInterface(gwtSerializable);
        }

        if (!suppressJavaInterface) {
            topLevelClass.addImportedType(serializable);
            topLevelClass.addSuperInterface(serializable);

            Field field = new Field();
            field.setFinal(true);
            field.setInitializationString("1L"); //$NON-NLS-1$
            field.setName("serialVersionUID"); //$NON-NLS-1$
            field.setStatic(true);
            field.setType(new FullyQualifiedJavaType("long")); //$NON-NLS-1$
            field.setVisibility(JavaVisibility.PRIVATE);
            context.getCommentGenerator().addFieldComment(field, introspectedTable);

            topLevelClass.addField(field);
        }
    }

    private String upperCaseFirstLetter(String word) {
        String result = word;
        if (word != null) {
            char first = word.charAt(0);
            if (first >= 97 && first <= 122) {
                first -= 32;
            }
            result = first + word.substring(1);
        }
        return result;
    }

}
