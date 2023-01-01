package com.example.remotejobapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.remotejobapp.databinding.ItemlayoutremotejobsBinding
import com.example.remotejobapp.fragment.MainFragmentDirections
import com.example.remotejobapp.model.Job


class RemoteJobAdapter():ListAdapter<Job,RemoteJobAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view=ItemlayoutremotejobsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val remotejob=getItem(position)

        holder.itemView.apply {
            Glide.with(this)
                .load(remotejob.companyLogo)
                .into(holder.binding.ivLogo)
        }.setOnClickListener {
            val action=MainFragmentDirections.actionMainFragmentToDetailsViewsFragment(remotejob)
            it.findNavController().navigate(action)
        }

        holder.binding.apply {
            tvCompanyname.text=remotejob.companyName
            tvJobtype.text=remotejob.jobType
            tvLocation.text=remotejob.candidateRequiredLocation
            tvCategory.text=remotejob.category
            val date = remotejob.publicationDate?.split("T")
            tvDate.text= date?.get(0) ?:""
        }

    }


    class ViewHolder(itemBinding: ItemlayoutremotejobsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        internal val binding: ItemlayoutremotejobsBinding = itemBinding
    }


    private class DiffCallback : DiffUtil.ItemCallback<Job>() {
        override fun areItemsTheSame(oldItem: Job, newItem: Job): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Job, newItem: Job): Boolean {
            return true
        }
    }


    interface Action{
   //     fun delete(responseActivity: ResponseActivity)
    }


}