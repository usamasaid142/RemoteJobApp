package com.example.remotejobapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.remotejobapp.R
import com.example.remotejobapp.databinding.SavedJobfragmentBinding
import com.example.remotejobapp.viewmodel.RemoteJobFavoriteViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedJobFragment : Fragment() {

    private lateinit var binding:SavedJobfragmentBinding
    private val viewmodel:RemoteJobFavoriteViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= SavedJobfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }




}