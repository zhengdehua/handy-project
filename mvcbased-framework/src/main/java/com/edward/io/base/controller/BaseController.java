package com.edward.io.base.controller;

import com.edward.io.base.dto.BaseDto;
import com.edward.io.base.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lori on 2017/8/3.
 */
public abstract class BaseController<S extends IService, R extends ControllerBean> implements IController<R> {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private S service;

    public void setService(S service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Override
    public ResponseEntity get(R request) {
        try {
            validateRequest(request);
            BaseDto result = service.find(request.getPrimaryKey());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
           log.error("get failed", e);
           return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(request.getEntityName(), "getfailed", e.getMessage())).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    @Override
    public ResponseEntity update(@RequestBody R request) {
        try {
            validateRequest(request);
            service.modify(request.transform());
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            log.error("update failed", e);
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(request.getEntityName(), "updatefailed", e.getMessage())).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Override
    public ResponseEntity create(@RequestBody R request) {
        try {
            validateRequest(request);
            service.save(request.transform());
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            log.error("create failed", e);
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(request.getEntityName(), "createfailed", e.getMessage())).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    @Override
    public ResponseEntity delete(R request) {
        try {
            validateRequest(request);
            service.remove(request.getPrimaryKey());
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            log.error("delete failed", e);
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(request.getEntityName(), "deletefailed", e.getMessage())).body(null);
        }
    }

    protected abstract void validateRequest(R request);

}
