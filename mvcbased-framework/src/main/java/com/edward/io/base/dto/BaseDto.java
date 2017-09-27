package com.edward.io.base.dto;

import com.edward.io.base.domain.BaseEntity;

/**
 * Created by lori on 2017/8/2.
 */
public interface BaseDto<T extends BaseEntity> {

    T transform();

}
