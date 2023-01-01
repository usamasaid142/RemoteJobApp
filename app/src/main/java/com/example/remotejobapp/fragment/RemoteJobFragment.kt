package com.example.remotejobapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.remotejobapp.adapter.RemoteJobAdapter
import com.example.remotejobapp.databinding.RemoteJobfragmentBinding
import com.example.remotejobapp.utils.Resource
import com.example.remotejobapp.viewmodel.RemotejobViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RemoteJobFragment : Fragment() {

 private lateinit var binding:RemoteJobfragmentBinding
 private lateinit var remoteJobAdapter: RemoteJobAdapter
 private val viewModel:RemotejobViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= RemoteJobfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupResyclerview()
        callback()
    }

    private fun setupResyclerview(){
        remoteJobAdapter= RemoteJobAdapter()
        binding.rvJobs.apply {
            adapter=remoteJobAdapter
            layoutManager= LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(object :DividerItemDecoration(activity,LinearLayout.VERTICAL){})
        }

    }


    private fun callback(){
        viewModel.jobresponse.observe(viewLifecycleOwner, Observer {

        if (it!=null){
            when(it){

                is Resource.Loading->{
                    showprogtessbar()
                }

                is Resource.Success->{
                    hideprogressbar()
                    remoteJobAdapter.submitList(it.data?.jobs)
                    remoteJobAdapter.notifyDataSetChanged()
                }
                is Resource.Error->{
                    hideprogressbar()
                }
            }
        }

        })

        viewModel.getAlljobs()
    }

    private fun showprogtessbar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideprogressbar() {
        binding.progressBar.visibility = View.GONE
    }

}