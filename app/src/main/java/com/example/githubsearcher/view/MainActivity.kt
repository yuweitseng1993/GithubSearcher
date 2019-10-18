package com.example.githubsearcher.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearcher.R
import com.example.githubsearcher.viewmodel.CustomAdapter
import com.example.githubsearcher.viewmodel.CustomViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var viewModel: CustomViewModel? = null
    private var adapter: CustomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sv_search_bar.onActionViewExpanded()
        rv_user_list.layoutManager = LinearLayoutManager(this)

        adapter = CustomAdapter(this)
        rv_user_list.adapter = adapter

        viewModel = ViewModelProvider(this)
            .get(CustomViewModel::class.java)

        sv_search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
//                System.out.println("sv_search_bar.setOnQueryTextListener")
                viewModel!!.loadUsers(query)
//                Thread.sleep(1000)
//                presentData()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //    adapter.getFilter().filter(newText);
                return false
            }
        })

//        viewModel.getUserDetail()!!.observe(this,
//            Observer{
//                adapter.setData(it)
//            })
//        viewModel.getUserDetail()!!.observe(this, Observer {
//            it?.let {
//                adapter.setData(it)
//            }})
        viewModel?.getUserDetail()!!.observe(this, Observer {
            it?.let {
                adapter?.setData(it)
            }})
    }

    fun presentData(){
        viewModel?.getUserDetail()!!.observe(this, Observer {
            it?.let {
                adapter?.setData(it)
            }})
    }
}
