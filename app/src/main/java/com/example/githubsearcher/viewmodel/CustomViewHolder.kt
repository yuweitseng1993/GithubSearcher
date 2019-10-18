package com.example.githubsearcher.viewmodel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearcher.R

class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivUserAvatar: ImageView = itemView.findViewById(R.id.iv_user_avatar)
    val tvUsername: TextView = itemView.findViewById(R.id.tv_username)
    val tvRepoNum: TextView = itemView.findViewById(R.id.tv_repo_num)
    val cvUserCard: CardView = itemView.findViewById(R.id.user_card_view)
}