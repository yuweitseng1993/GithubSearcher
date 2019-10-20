package com.example.githubsearcher.viewmodel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearcher.model.UserDetail
import com.squareup.picasso.Picasso
import android.widget.Toast
import android.view.View
import com.example.githubsearcher.model.UserParcelable
import com.example.githubsearcher.view.UserDetailActivity


class CustomAdapter(private val context: Context) : RecyclerView.Adapter<CustomViewHolder>() {
    var userDetailList: List<UserDetail> = ArrayList()
    fun setData(udl: List<UserDetail>){
        userDetailList = udl
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(com.example.githubsearcher.R.layout.recyclerview_item, parent, false))
    }

    override fun getItemCount(): Int {
        return if (userDetailList != null) userDetailList!!.size else 0
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        var tempUser = userDetailList!![position]
        holder.tvUsername.text = tempUser.login
        holder.tvRepoNum.text = "Repo: " + tempUser.public_repos
        Picasso.get().load(tempUser.avatar_url).into(holder.ivUserAvatar)

        holder.cvUserCard.setOnClickListener {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra("userParcelable", UserParcelable(tempUser.login,
                                                    tempUser.avatar_url,
                                                    tempUser.location,
                                                    tempUser.email,
                                                    tempUser.bio,
                                                    tempUser.followers,
                                                    tempUser.following,
                                                    tempUser.created_at))
            context.startActivity(intent)
        }
    }

}
