package com.example.githubsearcher.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubsearcher.R
import com.example.githubsearcher.model.UserParcelable
import com.example.githubsearcher.viewmodel.CustomViewModel
import com.example.githubsearcher.viewmodel.RepoCustomAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_detail.*



class UserDetailActivity : AppCompatActivity() {
    lateinit var viewModel: CustomViewModel
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val userParcelable: UserParcelable = intent.getParcelableExtra("userParcelable")!!
        listView = findViewById(R.id.lv_detail_repo_list)

        viewModel = ViewModelProvider(this)
            .get(CustomViewModel::class.java)
        viewModel!!.loadUserRepos(userParcelable.login)

        tv_detail_username.text = "Username: " + userParcelable.login
        if(userParcelable.email == null){
            tv_detail_email.text = "Email: null"
        }
        else{
            tv_detail_email.text = "Email: " + userParcelable.email
        }

        if(userParcelable.location == null){
            tv_detail_location.text = "Location: null"
        }
        else{
            tv_detail_location.text = "Location: " + userParcelable.location
        }

        if(userParcelable.bio == null){
            tv_detail_bio.text = "This author has no bio..."
        }
        else{
            tv_detail_bio.text = "Bio: " + userParcelable.bio
        }

        var date = userParcelable.created_at.split("T".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        date = date.replace("-".toRegex(), "/")
        tv_detail_join_date.text = "Join Date: " + date

        tv_detail_follower.text = userParcelable.followers.toString() + " Followers"
        tv_detail_following.text = "Following " + userParcelable.following.toString()
        Picasso.get().load(userParcelable.avatar_url).into(iv_detail_avatar)

        sv_detail_repo_search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel!!.loadUserRepos(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //    adapter.getFilter().filter(newText);
                return false
            }
        })

        viewModel?.getUserRepo()!!.observe(this, Observer {
            it?.let {
                val adapter = RepoCustomAdapter(this, it)
                listView.adapter = adapter
            }})


    }
}
