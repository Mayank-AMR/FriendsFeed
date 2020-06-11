package com.example.friendsfeed.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.friendsfeed.model.MyPostsContainer;

import java.util.ArrayList;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 22-04-2020 11:24 AM
 */
public class MyPostModel extends ViewModel {
    private MutableLiveData<ArrayList<MyPostsContainer>> rawDataList = new MutableLiveData<>();

    public void setRawData(ArrayList<MyPostsContainer> dataList) {
        rawDataList.postValue(dataList);
    }

    public LiveData<ArrayList<MyPostsContainer>> getRawDataList() {
        return rawDataList;
    }
}
