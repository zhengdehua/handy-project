/*
* This code is generated by shch-maven-plugin.
*
*/
package com.edward.io.demo.controller;

import com.edward.io.base.controller.ControllerBean;
import com.edward.io.demo.domain.WpPostsKey;
import com.edward.io.demo.dto.WpPostsDto;

import java.io.Serializable;

/**
* Created by edwardcheng on 2017/09/10.
*/
public class WpPostsControllerBean extends ControllerBean<WpPostsDto> {

    @Override
    public WpPostsDto transform() {
        WpPostsDto dto = new WpPostsDto();
        //you can write code here for transfering from request object to dto
        return dto;
    }

    @Override
    public Serializable getPrimaryKey() {
        WpPostsKey key = new WpPostsKey();
        //you can write code here for transfering from request object domain key
        return key;
    }

    @Override
    public String getEntityName() {
        return "WpPosts";
    }
}