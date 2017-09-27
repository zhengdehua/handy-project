package com.edward.io.base.service;

import com.edward.io.base.dto.BaseDto;
import com.edward.io.base.exception.BusinessException;

import java.io.Serializable;

/**
 * Created by lori on 2017/8/2.
 */
public interface IService<R extends BaseDto, PK extends Serializable> {

    void save(R dto) throws BusinessException;

    void remove(PK pk) throws BusinessException;

    void modify(R dto) throws BusinessException;

    R find(PK pk) throws BusinessException;
}
