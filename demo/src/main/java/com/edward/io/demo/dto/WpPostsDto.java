/*
* This code is generated by shch-maven-plugin.
*
*/
package com.edward.io.demo.dto;

import com.edward.io.base.dto.BaseDto;
import com.edward.io.demo.domain.WpPosts;

/**
* Created by edwardcheng on 2017/09/10.
*/
public class WpPostsDto implements BaseDto<WpPosts> {

    public WpPosts transform() {
        WpPosts entity = new WpPosts();
        //you can write code here for transfering from dto to domain
        return entity;
    }
}
