package com.example.userapp.data.api.model


import com.example.userapp.domain.model.User
import com.google.gson.annotations.SerializedName

data class UsersResponseDto(
    @SerializedName("data")
    val `data`: List<UserDto>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("support")
    val support: SupportDto,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)

data class UserDto(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String
)

data class SupportDto(
    @SerializedName("text")
    val text: String,
    @SerializedName("url")
    val url: String
)

fun UserDto.toDomainModel(): User {
    return User(
        avatar = this.avatar,
        email = this.email,
        firstName = this.firstName,
        id = this.id,
        lastName = this.lastName
    )
}


