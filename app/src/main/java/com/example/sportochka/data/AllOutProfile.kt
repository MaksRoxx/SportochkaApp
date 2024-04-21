package com.example.sportochka.data

data class AllOutProfile(
    val username: String,
    val photo: String,
    val longtitude: Double,
    val latitude: Double,
    val friendList: List<FriendOutProfile>
)
