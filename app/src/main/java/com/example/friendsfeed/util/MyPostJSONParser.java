package com.example.friendsfeed.util;

import com.example.friendsfeed.model.MyPostsContainer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 11-06-2020 06:01 PM
 */
public class MyPostJSONParser {
    private static final String KEY_STATUS = "status";

    public static final String KEY_postId = "post_id";
    public static final String KEY_post = "post";
    public static final String KEY_userId = "user_id";
    public static final String KEY_postImage = "post_image";
    public static final String KEY_likesCount = "likes_count";
    public static final String KEY_commentsCount = "comments_count";
    public static final String KEY_createdAt = "created_at";
    public static final String KEY_updatedAt = "updated_at";

    public static ArrayList<MyPostsContainer> postJsonParser(JSONObject response) {
        ArrayList<MyPostsContainer> containerArrayList = new ArrayList<>();
        try {
            if (response.getInt(KEY_STATUS) == 200) {
                JSONArray messageArray = response.getJSONArray("message");
                JSONArray likeArray = response.getJSONArray("like");
                if (messageArray.length() == likeArray.length()) {
                    for (int i = 0; i < messageArray.length(); i++) {
                        JSONObject message = messageArray.getJSONObject(i);
                        JSONObject like = likeArray.getJSONObject(i);

                        String postId = String.valueOf(message.optInt(KEY_postId));
                        String post = message.optString(KEY_post);
                        String userId = message.optString(KEY_userId);
                        String postImage = message.optString(KEY_postImage);
                        String likesCount = message.optString(KEY_likesCount);
                        String commentsCount = message.optString(KEY_commentsCount);
                        String createdAt = message.optString(KEY_createdAt);
                        String updatedAt = message.optString(KEY_updatedAt);

                        boolean selfLikeStatus = Boolean.parseBoolean(like.getString("status"));

                        containerArrayList.add(new MyPostsContainer(postId, post, userId,
                                postImage, likesCount, commentsCount, createdAt, updatedAt, selfLikeStatus));
                    }

//                    mainModel.setRawData(containerArrayList);
//                                    myPostAdapter = new MyPostAdapter(getActivity(), containerArrayList);
//                                    mRecyclerView.setAdapter(myPostAdapter);
                    return containerArrayList;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
