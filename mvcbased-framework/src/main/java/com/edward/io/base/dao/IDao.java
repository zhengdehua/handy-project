package com.edward.io.base.dao;

import com.edward.io.base.domain.BaseEntity;
import com.edward.io.base.domain.PageParam;
import com.edward.io.base.domain.PageResult;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.TransactionCallback;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

public interface IDao<T extends BaseEntity, PK extends Serializable> {

    int insert(T entity) throws SQLException;

    int update(T entity) throws SQLException;

    int delete(PK pk) throws SQLException;

    T get(PK pk) throws SQLException;

    //PageResult<T> getByPage(Map<String, Object> condition, PageParam param) throws SQLException;

    <V> V executeInTransaction(TransactionCallback<V> action) throws TransactionException;

}
