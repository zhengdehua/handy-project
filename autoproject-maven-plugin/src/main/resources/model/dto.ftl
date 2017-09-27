/*
* This code is generated by shch-maven-plugin.
*
*/
package ${basePackage}.dto;

import com.edward.io.base.dto.BaseDto;
import ${basePackage}.domain.${domain};

/**
* Created by ${author} on ${date}.
*/
public class ${domain}Dto implements BaseDto<${domain}> {

    public ${domain} transform() {
        ${domain} entity = new ${domain}();
        //you can write code here for transfering from dto to domain
        return entity;
    }
}
