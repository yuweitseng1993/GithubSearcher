package com.example.githubsearcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubsearcher.model.*
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class CustomViewModel : ViewModel() {
    private var userList: MutableList<String>? = null
    private var userDetailList: MutableList<UserDetail>? = null
    private var userDetailData: MutableLiveData<ArrayList<UserDetail>> = MutableLiveData()
    private var userRepoData: MutableLiveData<ArrayList<UserRepo>> = MutableLiveData()

    fun getUserDetail(): MutableLiveData<ArrayList<UserDetail>>? {
        return userDetailData
    }

    fun getUserRepo(): MutableLiveData<ArrayList<UserRepo>>? {
        return userRepoData
    }

    fun loadUsers(userName: String){
        System.out.println("loadUsers")
        ApiInterface.create().getUsers(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<UserResult>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: UserResult) {
                    System.out.println("loadUsers onNext()")
                    userDetailList = ArrayList()
                    generateUserList(t.items)
                }

                override fun onError(e: Throwable) {
                    System.out.println("loadUsers onError() " + e.message)
                }

            })
    }

    fun loadUserDetail(userName: String){
        System.out.println("loadUserDetail userName -> " + userName)
        ApiInterface.create().getUserDetail(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<UserDetail>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: UserDetail) {
                    System.out.println("loadUserDetail onNext()")
                    System.out.println("t.login -> " + t.login)
                    userDetailList!!.add(t)
                    userDetailData!!.value = userDetailList as ArrayList<UserDetail>
                }

                override fun onError(e: Throwable) {
                    System.out.println("loadUsers onError() " + e.message)
                }

            })

    }

    fun generateUserList(list: ArrayList<User>){
        System.out.println("generateUserList")
        System.out.println("list.size -> " + list.size)
        userList = ArrayList()
        for(x in list){
            loadUserDetail(x.login)
        }
    }

    fun loadUserRepos(userName: String){
        ApiInterface.create().getUserRepos(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<UserRepo>>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: List<UserRepo>) {
                    userRepoData.value = t as ArrayList<UserRepo>
                }

                override fun onError(e: Throwable) {

                }

            })
    }
}