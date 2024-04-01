package com.kapozzz.domain.model

data class User(
    val name: String,
    val surname: String,
    val mobilePhone: String
) {
    companion object {
        fun getEmpty(): User {
            return User(
                name = "empty",
                surname = "empty",
                mobilePhone = "empty"
            )
        }
    }
}