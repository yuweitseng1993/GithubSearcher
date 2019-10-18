package com.example.githubsearcher.viewmodel

import android.util.Log
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
    private var userDetailList: MutableList<UserDetail> = ArrayList()
//    private lateinit var tempUserDetail: UserDetail
    private var userDetailData: MutableLiveData<ArrayList<UserDetail>> = MutableLiveData()

    fun getUserDetail(): MutableLiveData<ArrayList<UserDetail>>? {
//        userDetailData.value = userDetailList as ArrayList<UserDetail>
        return userDetailData
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
//                    System.out.println("t.items.size -> " + t.items.size)
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
//                    setUserDetail()
//                    tempUserDetail = t
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
//        userDetailList = ArrayList()
//        for(u in list){
//            loadUserDetail(u.login)
//        }
        for(x in 0..1){
            loadUserDetail(list[x].login)
        }
//        setUserDetail()
    }

    fun setUserDetail(){
        System.out.println("setUserDetail")
        System.out.println("userDetailList.size -> " + userDetailList!!.size)
        userDetailData.value = userDetailList as ArrayList<UserDetail>
    }
}