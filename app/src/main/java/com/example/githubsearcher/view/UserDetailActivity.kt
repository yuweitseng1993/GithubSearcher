package com.example.githubsearcher.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.githubsearcher.R
import com.example.githubsearcher.viewmodel.CustomViewModel

class UserDetailActivity : AppCompatActivity() {
    lateinit var userName: String
    lateinit var viewModel: CustomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val intent = intent
        userName = intent.getStringExtra("userName")
        viewModel = ViewModelProvider(this)
            .get(CustomViewModel::class.java)

    }
}
