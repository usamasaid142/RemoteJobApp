package com.example.remotejobapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.remotejobapp.adapter.RemoteJobAdapter
import com.example.remotejobapp.databinding.SearchJobfragmentBinding
import com.example.remotejobapp.utils.Resource
import com.example.remotejobapp.viewmodel.RemotejobViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SearchJobFragment : Fragment() {

    private lateinit var binding: SearchJobfragmentBinding
    private lateinit var remoteJobAdapter: RemoteJobAdapter
    private val viewModel: RemotejobViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = SearchJobfragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupresyclerview()
        getSearchnews()
    }

    fun setupresyclerview() {
        remoteJobAdapter = RemoteJobAdapter()
        binding.rvSearchjobs.apply {
            adapter = remoteJobAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(object : DividerItemDecoration(activity, LinearLayout.VERTICAL){})
            remoteJobAdapter.notifyDataSetChanged()

        }
    }

    private fun getSearchnews() {

        var job: Job? = null
        binding.etSerach.addTextChangedListener {
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                it?.let {
                    if (it.isNotEmpty()) {
                        viewModel.searchAlljobs(it.toString())
                    }else{
                        viewModel.searchAlljobs("os")
                    }

                }

            }
        }

        viewModel.jobSearchresponse.observe(viewLifecycleOwner, Observer { response->

            when(response){
                is Resource.Success->{
                    hideprogressbar()
                    response.data?.jobs.let {
                        remoteJobAdapter.submitList(it)
                        remoteJobAdapter.notifyDataSetChanged()
                    }

                }

                is Resource.Error->{
                    hideprogressbar()
                    response.message?.let {
                        Log.e("tag",response.message)
                    }

                }

                is Resource.Loading->{
                    showprogtessbar()
                }
            }


        })

    }


    private fun showprogtessbar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideprogressbar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

}