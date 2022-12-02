package ru.delayvi.fruits.domain.accounts

open class AccountsExceptions: RuntimeException()

class AccountAlreadyExistException: AccountsExceptions()

class AuthException: AccountsExceptions()

class InvalidEmailException: AccountsExceptions()
