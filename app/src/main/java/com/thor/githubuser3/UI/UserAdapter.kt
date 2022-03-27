package com.thor.githubuser3.UI

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thor.githubuser3.R
import com.thor.githubuser3.Repository.User.User
import com.thor.githubuser3.databinding.ItemEmptyBinding
import com.thor.githubuser3.databinding.ItemUserBinding


class UserAdapter(private val itemClickListener: (User) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: MutableList<User> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<User>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> ItemViewHolder(
                ItemUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> EmptyViewHolder(
                ItemEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                holder.bindItem(list[position])
                holder.binding.root.setOnClickListener {
                    itemClickListener.invoke(list[position])
                }
            }
            is EmptyViewHolder -> {
            }
        }
    }

    override fun getItemViewType(position: Int) = if (list.isEmpty()) 0 else 1

    override fun getItemCount() = if (list.isEmpty()) 1 else list.size

    class ItemViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: User) {
            binding.tvCompany.text = item.htmlUrl
//            binding.name.text = item.username


            Glide.with(binding.root.context)
                .load(item.avatar)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(binding.imgItemPhoto)

//            binding.info.setOnClickListener {
//                CustomTabsIntent.Builder().build()
//                    .launchUrl(binding.root.context, Uri.parse(item.htmlUrl))
//            }
        }
    }

    class EmptyViewHolder(binding: ItemEmptyBinding) : RecyclerView.ViewHolder(binding.root)
}