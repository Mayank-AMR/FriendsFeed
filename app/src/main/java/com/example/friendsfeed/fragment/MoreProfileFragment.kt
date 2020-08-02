package com.example.friendsfeed.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.friendsfeed.R
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MoreProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoreProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_more_profile, container, false)

        view.findViewById<BottomNavigationView>(R.id.bottombar_more_profile).setOnNavigationItemSelectedListener(navListener)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MoreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MoreProfileFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    // Initialising BottomNavigationView Listener under fragment
    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
        when (item.itemId) {
            R.id.bb_ViewTypeGrid -> {
                //ToDO: Show all media in grid
                Toast.makeText(context, "Show all media in grid", Toast.LENGTH_LONG).show()

                true
            }
            R.id.bb_ViewTypeList -> {
                //TODO: Show all media in List
                Toast.makeText(context, "Show all media in Lis", Toast.LENGTH_LONG).show()

                true
            }
            R.id.bb_OnlyMy -> {
                //TODO: Show only my Media
                Toast.makeText(context, "Show only my Mediar", Toast.LENGTH_LONG).show()

                true
            }
            R.id.bb_MyFavorite -> {
                //TODO: Show only favorite media
                Toast.makeText(context, "Show only favorite media", Toast.LENGTH_LONG).show()

                true
            }
            else ->
                false
        }

    }

}