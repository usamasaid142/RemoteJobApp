package com.example.remotejobapp.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.remotejobapp.databinding.ItemlayoutremotejobsBinding
import com.example.remotejobapp.fragment.MainFragmentDirections
import com.example.remotejobapp.model.FavoriteJob
import kotlinx.coroutines.Job


class FavoriteJobAdapter(private val onclick:Action):ListAdapter<FavoriteJob,FavoriteJobAdapter.ViewHolder>(DiffCallback()) {

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
            val favoriteJob= com.example.remotejobapp.model.Job(remotejob.candidateRequiredLocation,remotejob.category,remotejob.companyLogo,
            remotejob.companyLogoUrl,remotejob.companyName,remotejob.description,remotejob.id,remotejob.jobType,
            remotejob.publicationDate,remotejob.salary,null,remotejob.title,remotejob.url)
            val action=MainFragmentDirections.actionMainFragmentToDetailsViewsFragment(favoriteJob)
            it.findNavController().navigate(action)
        }

        holder.binding.apply {
            ivDelete.visibility=View.VISIBLE
            tvCompanyname.text=remotejob.companyName
            tvJobtype.text=remotejob.jobType
            tvLocation.text=remotejob.candidateRequiredLocation
            tvCategory.text=remotejob.category
            val date = remotejob.publicationDate?.split("T")
            tvDate.text= date?.get(0) ?:""
        }

        holder.binding.ivDelete.setOnClickListener {
            onclick.delete(remotejob)
        }

    }


    class ViewHolder(itemBinding: ItemlayoutremotejobsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        internal val binding: ItemlayoutremotejobsBinding = itemBinding
    }


    private class DiffCallback : DiffUtil.ItemCallback<FavoriteJob>() {
        override fun areItemsTheSame(oldItem: FavoriteJob, newItem: FavoriteJob): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: FavoriteJob, newItem: FavoriteJob): Boolean {
            return true
        }
    }


    interface Action{
      fun delete(responseActivity: FavoriteJob)
    }


}