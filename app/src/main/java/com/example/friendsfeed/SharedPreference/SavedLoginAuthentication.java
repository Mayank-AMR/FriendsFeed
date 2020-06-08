package com.example.friendsfeed.SharedPreference;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 11-04-2020 01:39 PM
 */
public class SavedLoginAuthentication {
    private String userId;
    private String fullName;
    private String userName;
    private String userEmail;
    private String email_verified_at;
    private String picURL;
    private String profileCover;
    private String following;
    private String followers;
    private String bio;
    private String country;
    private String website;
    private String created_at;
    private String updated_at;

    public SavedLoginAuthentication(String userId, String fullName, String userName, String userEmail,
                                    String email_verified_at, String picURL, String profileCover,
                                    String following, String followers, String bio, String country,
                                    String website, String created_at, String updated_at) {
        this.userId = userId;
        this.fullName = fullName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.email_verified_at = email_verified_at;
        this.picURL = picURL;
        this.profileCover = profileCover;
        this.following = following;
        this.followers = followers;
        this.bio = bio;
        this.country = country;
        this.website = website;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public String getPicURL() {
        return picURL;
    }

    public String getProfileCover() {
        return profileCover;
    }

    public String getFollowing() {
        return following;
    }

    public String getFollowers() {
        return followers;
    }

    public String getBio() {
        return bio;
    }

    public String getCountry() {
        return country;
    }

    public String getWebsite() {
        return website;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }



}
