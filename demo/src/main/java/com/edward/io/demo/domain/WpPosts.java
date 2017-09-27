package com.edward.io.demo.domain;

import com.edward.io.base.domain.BaseEntity;
import com.edward.io.demo.domain.WpPostsKey;
import java.util.Date;

public class WpPosts extends WpPostsKey implements BaseEntity<WpPostsKey> {
    private Long postAuthor;

    private Date postDate;

    private Date postDateGmt;

    private String postStatus;

    private String commentStatus;

    private String pingStatus;

    private String postPassword;

    private String postName;

    private Date postModified;

    private Date postModifiedGmt;

    private Long postParent;

    private String guid;

    private Integer menuOrder;

    private String postType;

    private String postMimeType;

    private Long commentCount;

    public Long getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(Long postAuthor) {
        this.postAuthor = postAuthor;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getPostDateGmt() {
        return postDateGmt;
    }

    public void setPostDateGmt(Date postDateGmt) {
        this.postDateGmt = postDateGmt;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus == null ? null : postStatus.trim();
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus == null ? null : commentStatus.trim();
    }

    public String getPingStatus() {
        return pingStatus;
    }

    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus == null ? null : pingStatus.trim();
    }

    public String getPostPassword() {
        return postPassword;
    }

    public void setPostPassword(String postPassword) {
        this.postPassword = postPassword == null ? null : postPassword.trim();
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    public Date getPostModified() {
        return postModified;
    }

    public void setPostModified(Date postModified) {
        this.postModified = postModified;
    }

    public Date getPostModifiedGmt() {
        return postModifiedGmt;
    }

    public void setPostModifiedGmt(Date postModifiedGmt) {
        this.postModifiedGmt = postModifiedGmt;
    }

    public Long getPostParent() {
        return postParent;
    }

    public void setPostParent(Long postParent) {
        this.postParent = postParent;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType == null ? null : postType.trim();
    }

    public String getPostMimeType() {
        return postMimeType;
    }

    public void setPostMimeType(String postMimeType) {
        this.postMimeType = postMimeType == null ? null : postMimeType.trim();
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public WpPostsKey getPrimayKey() {
        com.edward.io.demo.domain.WpPostsKey key = new com.edward.io.demo.domain.WpPostsKey();
        key.setId(getId());
        return key;
    }
}