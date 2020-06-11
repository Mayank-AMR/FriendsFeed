package com.example.friendsfeed.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.friendsfeed.adapter.MyPostAdapter;
import com.example.friendsfeed.util.MyPostJSONParser;
import com.example.friendsfeed.viewModel.MyPostModel;
import com.example.friendsfeed.model.MyPostsContainer;
import com.example.friendsfeed.R;
import com.example.friendsfeed.SharedPreference.SharedPrefManager;

import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPostFragment extends Fragment {
    public static final String TAG = "HomeMainFragment";
    //private static final String TAG = "HomeMainFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private MyPostAdapter myPostAdapter;
    private MyPostModel myPostModel;
    private ArrayList<MyPostsContainer> containerArrayList;
    private RequestQueue mRequestQueue;


    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public MyPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * <p>
     * // @param param1 Parameter 1.
     * //@param param2 Parameter 2.
     *
     * @return A new instance of fragment MainHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    /*
    public static MyPostFragment newInstance(String param1, String param2) {
        MyPostFragment fragment = new MyPostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    */
    // Instead of above Instance method...........
    public static MyPostFragment newInstance() {
        Log.d(TAG, "newInstance: ");
        MyPostFragment fragment = new MyPostFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);


//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

        containerArrayList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(getActivity());
        parseJSON();


        Log.d(TAG, "onCreate: end");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: start");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_post, container, false);
        mRecyclerView = view.findViewById(R.id.my_post_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Log.d(TAG, "onCreateView: end");
        return view;
    }

    //---------------------------------------------------------------------------------------

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: start");
        super.onActivityCreated(savedInstanceState);


        myPostModel = ViewModelProviders.of(getActivity()).get(MyPostModel.class);
        myPostModel.getRawDataList().observe(getViewLifecycleOwner(), new Observer<ArrayList<MyPostsContainer>>() {
            @Override
            public void onChanged(ArrayList<MyPostsContainer> list) {
                Log.d(TAG, "onChanged: " + list.size());

                myPostAdapter = new MyPostAdapter(getActivity(), list);
                mRecyclerView.setAdapter(myPostAdapter);

            }
        });

        Log.d(TAG, "onActivityCreated: end");
    }

    private void parseJSON() {
        Log.d(TAG, "parseJSON: start");
        String id = "";
        if (SharedPrefManager.getInstance(getActivity()).isSignIn()) {
            SharedPrefManager sp = SharedPrefManager.getInstance(getActivity());
            id = sp.getLoginAuthentication().getUserId();
        }
        //final String url = "https://diready.co/api/userpost?user_id="+id;
        final String url = "https://diready.co/api/userpost?user_id=" + 1;
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        /*
                        try {
                            if (response.getInt("status") == 200) {
                                JSONArray messageArray = response.getJSONArray("message");
                                JSONArray likeArray = response.getJSONArray("like");
                                if (messageArray.length() == likeArray.length()) {
                                    for (int i = 0; i < messageArray.length(); i++) {
                                        JSONObject message = messageArray.getJSONObject(i);
                                        JSONObject like = likeArray.getJSONObject(i);

                                        String postId = String.valueOf(message.getInt("post_id"));
                                        String post = message.getString("post");
                                        String userId = message.getString("user_id");
                                        String postImage = message.getString("post_image");
                                        String likesCount = message.getString("likes_count");
                                        String commentsCount = message.getString("comments_count");
                                        String createdAt = message.getString("created_at");
                                        String updatedAt = message.getString("updated_at");

                                        boolean selfLikeStatus = Boolean.parseBoolean(like.getString("status"));

                                        containerArrayList.add(new MyPostsContainer(postId, post, userId,
                                                postImage, likesCount, commentsCount, createdAt, updatedAt, selfLikeStatus));
                                    }

                                    mainModel.setRawData(containerArrayList);
//                                    myPostAdapter = new MyPostAdapter(getActivity(), containerArrayList);
//                                    mRecyclerView.setAdapter(myPostAdapter);
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        */
                        containerArrayList = MyPostJSONParser.postJsonParser(response);
                        if (containerArrayList != null) {
                            myPostModel.setRawData(containerArrayList);
                        }

                        Log.d(TAG, "onResponse: \n" + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
                error.printStackTrace();
            }
        });
        mRequestQueue.add(objectRequest);
        Log.d(TAG, "parseJSON: end");
    }
}
