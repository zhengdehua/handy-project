/*
* This code is generated by shch-maven-plugin.
*
*/
package ${basePackage}.controller;

import com.edward.io.base.controller.ControllerBean;
import ${basePackage}.domain.${domain}Key;
import ${basePackage}.dto.${domain}Dto;

import java.io.Serializable;

/**
* Created by ${author} on ${date}.
*/
public class ${domain}ControllerBean extends ControllerBean<${domain}Dto> {

    @Override
    public ${domain}Dto transform() {
        ${domain}Dto dto = new ${domain}Dto();
        //you can write code here for transfering from request object to dto
        return dto;
    }

    @Override
    public Serializable getPrimaryKey() {
        ${domain}Key key = new ${domain}Key();
        //you can write code here for transfering from request object domain key
        return key;
    }

    @Override
    public String getEntityName() {
        return "${domain}";
    }
}