package com.example.friendsfeed.postpackage.allpost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.friendsfeed.R
import com.example.friendsfeed.auth.AuthListener
import com.example.friendsfeed.fragment.CreatePostFragment
import com.example.friendsfeed.utils.Coroutine
import com.example.friendsfeed.utils.log
import com.example.friendsfeed.utils.toast
import org.kodein.di.android.x.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import kotlin.math.log


class AllPostFragment : Fragment(), AuthListener, KodeinAware {

    companion object {
        //fun newInstance() = AllPostFragment()
        @JvmStatic
        fun newInstance() = AllPostFragment()
    }

    override val kodein by kodein()

    private val factory: AllPostViewModelFactory by instance()

    private lateinit var viewModel: AllPostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.all_post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(AllPostViewModel::class.java)

        Coroutine.main {
            val posts = viewModel.posts.await()
            posts.observe(viewLifecycleOwner, Observer {
                context?.toast(it.size.toString())

            })
        }
    }

    override fun onStarted() {
        context?.log("AllPost Fragment onStart() ")
    }

    override fun onSuccess(message: String) {
        context?.log("AllPost Fragment onSuccess() ")
    }

    override fun onFailure(message: String) {
        context?.log("AllPost Fragment onFailure() ")
    }


}