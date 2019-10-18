package com.example.githubsearcher.model

class UserDetail (
    var login: String,
    var avatar_url: String,
    var location: String,
    var email: String?,
    var bio: String?,
    var public_repos: Int,
    var followers: Int,
    var following: Int,
    var created_at: String
)