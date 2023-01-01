package com.example.remotejobapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.remotejobapp.databinding.DetailsViewsfragmentBinding
import com.example.remotejobapp.model.Job
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsViewsFragment : Fragment() {

    private lateinit var binding:DetailsViewsfragmentBinding
    private val args:DetailsViewsFragmentArgs by navArgs()
    private lateinit var currentjob:Job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DetailsViewsfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       currentjob= args.job!!
        setUpWebView()
    }

    private fun setUpWebView() {
       binding.webviwew.apply {
           webViewClient= WebViewClient()

               currentjob.let {
                   it.url?.let { it1 -> loadUrl(it1) }
               }


       }
    }
}