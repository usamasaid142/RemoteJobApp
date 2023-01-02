package com.example.remotejobapp.fragment

import android.content.DialogInterface
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
import com.example.remotejobapp.R
import com.example.remotejobapp.adapter.FavoriteJobAdapter
import com.example.remotejobapp.databinding.SavedJobfragmentBinding
import com.example.remotejobapp.model.FavoriteJob
import com.example.remotejobapp.viewmodel.RemoteJobFavoriteViewmodel
import com.example.runningapp.alertdialog.Alert
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedJobFragment : Fragment(),FavoriteJobAdapter.Action {

    private lateinit var binding: SavedJobfragmentBinding
    private val viewmodel: RemoteJobFavoriteViewmodel by viewModels()
    private lateinit var favoriteJobAdapter: FavoriteJobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = SavedJobfragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycleview()
    }


    private fun setUpRecycleview() {
        favoriteJobAdapter = FavoriteJobAdapter(this)
        binding.rvFavJob.apply {
            adapter = favoriteJobAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(object : DividerItemDecoration(activity, LinearLayout.VERTICAL) {})
        }
        getAllFavoriteJob()

    }

    private fun getAllFavoriteJob() {
        viewmodel.allFavoriteJob.observe(viewLifecycleOwner, Observer {
            favoriteJobAdapter.submitList(it)
            favoriteJobAdapter.notifyDataSetChanged()
            updateUi(it)
        })
    }

    private fun updateUi(it: List<FavoriteJob>?) {
        if (it.isNullOrEmpty()) {
            binding.crdView.visibility = View.VISIBLE
        } else {
            binding.crdView.visibility = View.GONE
        }
    }

    override fun delete(favjob: FavoriteJob) {
        Alert.showMessage(android.R.drawable.ic_dialog_alert,
            requireContext(),R.string.msg,R.string.del,R.string.ok,
            { dialog, which ->
                viewmodel.delete(favjob)
                favoriteJobAdapter.notifyDataSetChanged()
                dialog.dismiss()
            },R.string.no,
            { dialog, which ->
                dialog.dismiss()
            },true
        )
    }


}