package com.edward.io.base.service;

import com.edward.io.base.domain.BaseEntity;
import com.edward.io.base.dao.IDao;
import com.edward.io.base.dto.BaseDto;
import com.edward.io.base.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by lori on 2017/8/3.
 */
public abstract class BaseService<T extends BaseEntity, PK extends Serializable, R extends BaseDto<T>> implements IService<R, PK> {

    private static final Logger log = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    private IDao<T, PK> dao;

    @Override
    public void save(R dto) throws BusinessException {
        try {
            if (dto != null) {
                dao.insert(dto.transform());
            }
        } catch (SQLException e) {
            log.error("save failed", e);
            throw BusinessException.wrapFor(e);
        }
    }

    @Override
    public void remove(PK pk) throws BusinessException {
        try {
            if (pk != null) {
                dao.delete(pk);
            }
        } catch (SQLException e) {
            log.error("remove failed", e);
            throw BusinessException.wrapFor(e);
        }
    }

    @Override
    public void modify(R dto) throws BusinessException {
        try {
            if (dto != null) {
                dao.update(dto.transform());
            }
        } catch (SQLException e) {
            log.error("modify failed", e);
            throw BusinessException.wrapFor(e);
        }
    }

    @Override
    public R find(PK pk) throws BusinessException {
        try {
            if (pk != null) {
                return transform(dao.get(pk));
            }
            throw new BusinessException("lacks of pk");
        } catch (SQLException e) {
            log.error("remove failed", e);
            throw BusinessException.wrapFor(e);
        }
    }

    protected abstract R transform(T entity);
}
