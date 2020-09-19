package com.example.friendsfeed.postpackage.allpost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.friendsfeed.R
import com.example.friendsfeed.auth.AuthListener


class AllPostFragment : Fragment(), AuthListener {

    companion object {
        fun newInstance() = AllPostFragment()
    }

    private lateinit var viewModel: AllPostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.all_post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllPostViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStarted() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(message: String) {
        TODO("Not yet implemented")
    }

    override fun onFailure(message: String) {
        TODO("Not yet implemented")
    }


}