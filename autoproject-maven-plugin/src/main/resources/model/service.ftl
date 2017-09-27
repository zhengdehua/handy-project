/*
* This code is generated by shch-maven-plugin.
*
*/
package ${basePackage}.service;

import com.edward.io.base.service.BaseService;
import ${basePackage}.domain.${domain};
import ${basePackage}.domain.${domain}Key;
import ${basePackage}.dto.${domain}Dto;
import ${basePackage}.mapper.${domain}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* Created by ${author} on ${date}.
*/
@Component
public class ${domain}Service extends BaseService<${domain},${domain}Key,${domain}Dto> {

    @Autowired
    private ${domain}Mapper ${domain?uncap_first}Mapper;

    @Override
    protected ${domain}Dto transform(${domain} entity) {
        ${domain}Dto dto = new ${domain}Dto();
        //you can write code here for transfering from entity to dto
        return dto;
    }
}
