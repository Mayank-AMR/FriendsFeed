package com.example.friendsfeed.postpackage.createpost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.friendsfeed.R

class CreateNewPostFragment : Fragment() {

    companion object {
        fun newInstance() = CreateNewPostFragment()
    }

    private lateinit var viewModel: CreateNewPostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.create_new_post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateNewPostViewModel::class.java)
        // TODO: Use the ViewModel
    }

}