package com.edward.io.demo.domain;

import java.io.Serializable;

public class WpPostsKey implements Serializable {
    private Long id;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}