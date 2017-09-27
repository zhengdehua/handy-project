package com.edward.io.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.edward.io.base.support.mybatis.OrmMapper;
import com.edward.io.demo.domain.WpUsers;
import com.edward.io.demo.domain.WpUsersExample;
import com.edward.io.demo.domain.WpUsersKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WpUsersMapper extends OrmMapper<WpUsers,WpUsersKey> {
    int countByExample(WpUsersExample example);

    int deleteByExample(WpUsersExample example);

    int deleteByPrimaryKey(WpUsersKey key);

    int insert(WpUsers record);

    int insertSelective(WpUsers record);

    List<WpUsers> selectByExample(WpUsersExample example);

    WpUsers selectByPrimaryKey(WpUsersKey key);

    int updateByExampleSelective(@Param("record") WpUsers record, @Param("example") WpUsersExample example);

    int updateByExample(@Param("record") WpUsers record, @Param("example") WpUsersExample example);

    int updateByPrimaryKeySelective(WpUsers record);

    int updateByPrimaryKey(WpUsers record);
}
