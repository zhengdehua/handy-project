package com.edward.io.base.dao;

import com.edward.io.base.domain.BaseEntity;
import com.edward.io.base.support.mybatis.OrmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by lori on 2017/8/2.
 */
public abstract class DaoAdapter<T extends BaseEntity<PK>, PK extends Serializable> implements IDao<T, PK> {

    //The third party dao implementation
    @Autowired
    private OrmMapper<T, PK> delegate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public int insert(T entity) throws SQLException {
        return delegate.insert(entity);
    }

    public int update(T entity) throws SQLException {
        return delegate.updateByPrimaryKeySelective(entity);
    }

    public int delete(PK pk) throws SQLException {
        return delegate.deleteByPrimaryKey(pk);
    }

    public T get(PK pk) throws SQLException {
        return delegate.selectByPrimaryKey(pk);
    }

    @Override
    public <V> V executeInTransaction(TransactionCallback<V> action) throws TransactionException {
        return transactionTemplate.execute(action);
    }
}
