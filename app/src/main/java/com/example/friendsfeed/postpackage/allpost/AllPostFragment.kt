package com.example.friendsfeed.postpackage.allpost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendsfeed.R
import com.example.friendsfeed.auth.AuthListener
import com.example.friendsfeed.auth.data.db.entities.HomePosts
import com.example.friendsfeed.utils.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.all_post_fragment.*
import org.kodein.di.android.x.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance


class AllPostFragment : Fragment(), AuthListener, KodeinAware {

    companion object {
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

        bindUI()
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

    private fun bindUI() = Coroutine.main {
        all_post_pb.show()
        viewModel.posts.await()?.observe(viewLifecycleOwner, Observer {

            all_post_pb.hide()
            initRecyclerView(it.toHomePostItem())

        })
    }

    private fun initRecyclerView(postItem: List<HomePostsItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(postItem)
        }

        all_post_rv.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }

    private fun List<HomePosts>.toHomePostItem(): List<HomePostsItem> {
        return this.map {
            HomePostsItem(it)
        }
    }


}