package com.example.friendsfeed;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 22-04-2020 12:28 PM
 */
public class MyPostsContainer {
    private String post_id;
    private String post;
    private String user_id;
    private String post_image;
    private String likes_count;
    private String comments_count;
    private String created_at;
    private String updated_at;
    private boolean self_like_status;


    public MyPostsContainer(String post_id, String post, String user_id, String post_image,
                            String likes_count, String comments_count, String created_at,
                            String updated_at, boolean self_like_status) {
        this.post_id = post_id;
        this.post = post;
        this.user_id = user_id;
        this.post_image = post_image;
        this.likes_count = likes_count;
        this.comments_count = comments_count;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.self_like_status = self_like_status;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }

    public String getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(String likes_count) {
        this.likes_count = likes_count;
    }

    public String getComments_count() {
        return comments_count;
    }

    public void setComments_count(String comments_count) {
        this.comments_count = comments_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isSelf_like_status() {
        return self_like_status;
    }

    public void setSelf_like_status(boolean self_like_status) {
        this.self_like_status = self_like_status;
    }

}
