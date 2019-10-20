package com.example.githubsearcher.model

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @Headers(
        "Accept: application/vnd.github.inertia-preview+json"
    )

    //https://api.github.com/search/users?q=userName
    @GET("search/users?")
    fun getUsers(@Query("q") userName: String): Observable<UserResult>

    //https://api.github.com/users/userName
    @GET("users/{username}")
    fun getUserDetail(@Path("username") userName: String): Observable<UserDetail>

    //https://api.github.com/users/userName/repos
    @GET("users/{username}/repos")
    fun getUserRepos(@Path("username") userName: String) : Observable<List<UserRepo>>

    companion object {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}