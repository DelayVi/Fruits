package ru.delayvi.fruits.domain.accounts.entity

data class SignUpData(
    val username: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)