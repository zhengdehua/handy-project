package com.edward.io.base.support.mybatis;

import com.edward.io.base.domain.BaseEntity;

import java.io.Serializable;

/**
 * Created by lori on 2017/8/22.
 */
public interface OrmMapper<T extends BaseEntity<PK>, PK extends Serializable> {

    int insert(T record);

    int deleteByPrimaryKey(PK pk);

    int updateByPrimaryKeySelective(T record);

    T selectByPrimaryKey(PK pk);
}
