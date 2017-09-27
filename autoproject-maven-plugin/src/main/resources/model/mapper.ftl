/*
* This code is generated by shch-maven-plugin.
*
*/
package ${basePackage}.mapper;

import ${basePackage}.domain.${domain};
import ${basePackage}.domain.${domain}Example;
import ${basePackage}.domain.${domain}Key;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.edward.io.base.support.OrmMapper;

/**
* Created by ${author} on ${date}.
*/
@Mapper
public interface ${domain}Mapper extends OrmMapper<${domain},${domain}Key> {
    int countByExample(${domain}Example example);

    int deleteByExample(${domain}Example example);

    int insertSelective(${domain} record);

    List<${domain}> selectByExample(${domain}Example example);

    int updateByExampleSelective(@Param("record") ${domain} record, @Param("example") ${domain}Example example);

    int updateByExample(@Param("record") ${domain} record, @Param("example") ${domain}Example example);

    int updateByPrimaryKey(${domain} record);
}