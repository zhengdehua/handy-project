package com.edward.io.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.edward.io.base.support.mybatis.OrmMapper;
import com.edward.io.demo.domain.WpPosts;
import com.edward.io.demo.domain.WpPostsExample;
import com.edward.io.demo.domain.WpPostsKey;
import com.edward.io.demo.domain.WpPostsWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WpPostsMapper extends OrmMapper<WpPosts,WpPostsKey> {
    int countByExample(WpPostsExample example);

    int deleteByExample(WpPostsExample example);

    int deleteByPrimaryKey(WpPostsKey key);

    int insert(WpPostsWithBLOBs record);

    int insertSelective(WpPostsWithBLOBs record);

    List<WpPostsWithBLOBs> selectByExampleWithBLOBs(WpPostsExample example);

    List<WpPosts> selectByExample(WpPostsExample example);

    WpPostsWithBLOBs selectByPrimaryKey(WpPostsKey key);

    int updateByExampleSelective(@Param("record") WpPostsWithBLOBs record, @Param("example") WpPostsExample example);

    int updateByExampleWithBLOBs(@Param("record") WpPostsWithBLOBs record, @Param("example") WpPostsExample example);

    int updateByExample(@Param("record") WpPosts record, @Param("example") WpPostsExample example);

    int updateByPrimaryKeySelective(WpPostsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(WpPostsWithBLOBs record);

    int updateByPrimaryKey(WpPosts record);
}
