package com.example.domain.exceptions

sealed class AppExceptions(val messageKey: String? = null, val stackTrace: String? = null) :
    Exception(messageKey)
class UnauthorizedException : AppExceptions()
class ChangePasswordFailedException : AppExceptions()
class WarningException(messageKey: String?) : AppExceptions(messageKey)
class WrongCredentialsException(messageKey: String?) : AppExceptions(messageKey)

