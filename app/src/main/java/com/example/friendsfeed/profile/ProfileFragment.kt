package com.example.friendsfeed.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.friendsfeed.databinding.ProfileFragmentBinding
import com.example.friendsfeed.utils.Coroutine
import com.example.friendsfeed.utils.hide
import com.example.friendsfeed.utils.show
import kotlinx.android.synthetic.main.profile_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProfileFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: ProfileViewModelFactory by instance()


    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return inflater.inflate(R.layout.profile_fragment, container, false)

        return ProfileFragmentBinding.inflate(inflater, container, false)
                .apply {
                    lifecycleOwner = viewLifecycleOwner
//                    Coroutine.main {
//                        progress_bar_profile.show()
//                        viewModel.profile.await().observe(viewLifecycleOwner, Observer {
//                            progress_bar_profile.hide()
//                            profile = it       // Attaching view model to the xml layout variable
//                        })
//                    }
                    //profileViewModel = viewModel
                }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
    }
}