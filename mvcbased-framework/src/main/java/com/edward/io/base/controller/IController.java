package com.edward.io.base.controller;

import org.springframework.http.ResponseEntity;

/**
 * Created by lori on 2017/8/3.
 */
public interface IController<R extends ControllerBean> {

    ResponseEntity get(R r);

    ResponseEntity update(R r);

    ResponseEntity create(R r);

    ResponseEntity delete(R r);
}
