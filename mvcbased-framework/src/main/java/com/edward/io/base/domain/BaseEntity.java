package com.edward.io.base.domain;

import java.io.Serializable;

/**
 * Created by lori on 2017/8/2.
 */
public interface BaseEntity<PK extends Serializable> {

    PK getPrimayKey();
}
