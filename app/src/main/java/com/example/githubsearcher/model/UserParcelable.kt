package com.example.githubsearcher.model

import android.os.Parcel
import android.os.Parcelable

class UserParcelable(
    val login: String,
    val avatar_url: String,
    val location: String?,
    val email: String?,
    val bio: String?,
    val followers: Int,
    val following: Int,
    val created_at: String) : Parcelable {

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<UserParcelable> {
            override fun createFromParcel(parcel: Parcel) = UserParcelable(parcel)
            override fun newArray(size: Int) = arrayOfNulls<UserParcelable>(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        login = parcel.readString()!!,
        avatar_url = parcel.readString()!!,
        location = parcel.readString(),
        email = parcel.readString(),
        bio = parcel.readString(),
        followers = parcel.readInt(),
        following = parcel.readInt(),
        created_at = parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeString(avatar_url)
        parcel.writeString(location)
        parcel.writeString(email)
        parcel.writeString(bio)
        parcel.writeInt(followers)
        parcel.writeInt(following)
        parcel.writeString(created_at)
    }

    override fun describeContents() = 0
}