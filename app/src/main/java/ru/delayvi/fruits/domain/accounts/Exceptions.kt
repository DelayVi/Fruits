package ru.delayvi.fruits.domain.accounts

open class AccountsExceptions: RuntimeException()

class AccountAlreadyExistException: AccountsExceptions()

class InvalidPasswordException: AccountsExceptions()