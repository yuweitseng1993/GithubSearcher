package com.example.githubsearcher.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.githubsearcher.R
import com.example.githubsearcher.model.UserRepo
import com.example.githubsearcher.view.WebLinkActivity




class RepoCustomAdapter(private val context: Context,
                        private val dataSource: ArrayList<UserRepo>) : BaseAdapter(), Filterable{

    private var dataHolder: ArrayList<UserRepo> = ArrayList()
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    init{
        dataHolder.addAll(dataSource)
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.listview_item, parent, false)

        val repoName: TextView = rowView.findViewById(R.id.tv_repo_name)
        val forkNum: TextView = rowView.findViewById(R.id.tv_fork_num)
        val starNum: TextView = rowView.findViewById(R.id.tv_star_num)
        val cl_repo_holder: ConstraintLayout = rowView.findViewById(R.id.cl_repo_holder)

        val userRepo = getItem(position) as UserRepo
        repoName.text = userRepo.name
        forkNum.text = userRepo.forks_count.toString()
        starNum.text = userRepo.stargazers_count.toString()

        cl_repo_holder.setOnClickListener(object : View.OnClickListener{
            override fun onClick(position: View?) {
                val intent = Intent(context, WebLinkActivity::class.java)
                intent.putExtra("url", userRepo.html_url)
                context.startActivity(intent)
            }
        })
        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataHolder[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataHolder.size
    }

    override fun getFilter(): Filter {
        return exampleFilter
    }

    private val exampleFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            System.out.println("dataSource.size -> " + dataSource.size)
            var filteredList: ArrayList<UserRepo> = ArrayList()

            if (constraint == null || constraint.isEmpty()) {
                filteredList = dataSource
                System.out.println("wo/ constraint: filteredList.size -> " + filteredList.size)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }

                for (item in dataSource) {
                    if (item.name.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
                System.out.println("w/ constraint: filteredList.size -> " + filteredList.size)
            }

            val results = FilterResults()
            results.values = filteredList

            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            dataHolder.clear()
            dataHolder.addAll(results.values as ArrayList<UserRepo>)
            notifyDataSetChanged()
        }
    }
}
