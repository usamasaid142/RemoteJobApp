package com.example.remotejobapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.remotejobapp.R
import com.example.remotejobapp.databinding.MainfragmentBinding
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

 private lateinit var binding:MainfragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= MainfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabBar()
    }

    private fun setupTabBar() {
        val adapter=FragmentPagerItemAdapter(
            childFragmentManager,
            FragmentPagerItems.with(activity)
                .add("Jobs",RemoteJobFragment::class.java)
                .add("Search",SearchJobFragment::class.java)
                .add("Saved jobs",SavedJobFragment::class.java)
                .create()
        )
        binding.viewpager.adapter=adapter
        binding.viewpagertab.setViewPager(binding.viewpager)
    }


}