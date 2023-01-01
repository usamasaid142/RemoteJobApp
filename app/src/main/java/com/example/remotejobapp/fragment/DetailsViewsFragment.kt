package com.example.remotejobapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.remotejobapp.databinding.DetailsViewsfragmentBinding
import com.example.remotejobapp.model.FavoriteJob
import com.example.remotejobapp.model.Job
import com.example.remotejobapp.viewmodel.RemoteJobFavoriteViewmodel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsViewsFragment : Fragment() {

    private lateinit var binding:DetailsViewsfragmentBinding
    private val args:DetailsViewsFragmentArgs by navArgs()
    private lateinit var currentjob:Job
    private val viewmodel:RemoteJobFavoriteViewmodel by viewModels()

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
        initButton()
    }

    private fun initButton(){
        binding.fab.setOnClickListener {
            insert()
            Snackbar.make(it,"Job saved successfully",Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setUpWebView() {
       binding.webviwew.apply {
           webViewClient= WebViewClient()

               currentjob.let {
                   it.url?.let { it1 -> loadUrl(it1) }
               }


       }
    }

    private fun insert(){
        val favoriteJob=FavoriteJob(0,args.job!!.candidateRequiredLocation,
            args.job!!.category, args.job!!.companyLogo, args.job!!.companyLogoUrl, args.job!!.companyName,
            args.job!!.description, args.job!!.id, args.job!!.jobType, args.job!!.publicationDate,
            args.job!!.salary, args.job!!.title, args.job!!.url)

        favoriteJob?.let {
            viewmodel.insert(it)
        }
    }
}