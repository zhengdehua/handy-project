package com.edward.io.base.controller;

import com.edward.io.base.dto.BaseDto;

import java.io.Serializable;

/**
 * Created by lori on 2017/8/3.
 * This class is not permitted to instance directly.
 */
public abstract class ControllerBean<T extends BaseDto> implements Serializable{

    public T transform() {
        return null;
    }

    public Serializable getPrimaryKey() {
        return null;
    }

    public abstract String getEntityName();
}
