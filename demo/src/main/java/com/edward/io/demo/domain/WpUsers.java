package com.edward.io.demo.domain;

import com.edward.io.base.domain.BaseEntity;
import com.edward.io.demo.domain.WpUsersKey;
import java.util.Date;

public class WpUsers extends WpUsersKey implements BaseEntity<WpUsersKey> {
    private String userLogin;

    private String userPass;

    private String userNicename;

    private String userEmail;

    private String userUrl;

    private Date userRegistered;

    private String userActivationKey;

    private Integer userStatus;

    private String displayName;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin == null ? null : userLogin.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public String getUserNicename() {
        return userNicename;
    }

    public void setUserNicename(String userNicename) {
        this.userNicename = userNicename == null ? null : userNicename.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl == null ? null : userUrl.trim();
    }

    public Date getUserRegistered() {
        return userRegistered;
    }

    public void setUserRegistered(Date userRegistered) {
        this.userRegistered = userRegistered;
    }

    public String getUserActivationKey() {
        return userActivationKey;
    }

    public void setUserActivationKey(String userActivationKey) {
        this.userActivationKey = userActivationKey == null ? null : userActivationKey.trim();
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public WpUsersKey getPrimayKey() {
        com.edward.io.demo.domain.WpUsersKey key = new com.edward.io.demo.domain.WpUsersKey();
        key.setId(getId());
        return key;
    }
}