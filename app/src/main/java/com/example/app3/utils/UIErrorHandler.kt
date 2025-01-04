package com.example.app3.utils

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.app3.views.OnAlertClickAction
import com.example.domain.exceptions.ChangePasswordFailedException
import com.example.domain.exceptions.UnauthorizedException
import com.example.domain.exceptions.WarningException
import com.example.domain.exceptions.WrongCredentialsException
import kotlinx.coroutines.CancellationException
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

private lateinit var DEFAULT_ERROR_TITLE: String
private lateinit var DEFAULT_ERROR_DESCRIPTION: String
sealed class Error(val title: String?,
            val message: String?,
            val buttonText: String?,
            val stackTrace: String? = null){
    class WrongCredential(
        message: String? = null,
        buttonText: String?
    ) : Error(null, message, buttonText)
    class Unauthorized(
        title: String? = null,
        message: String? = null,
        buttonText: String? = null
    ) : Error(title, message, buttonText)
    class Warning(title: String?,
                  message: String? = null,
                  buttonText: String?
    ): Error(title = title,message = message, buttonText = buttonText)

    }
interface UIErrorHandler {
    val error: LiveData<Error>
    fun handleSessionError(exception: Exception)
    fun handleError(exception: Exception)
}
@Singleton
class DefaultUIErrorHandler @Inject constructor( ) : UIErrorHandler {
    init {
        DEFAULT_ERROR_TITLE = "Eroare titlu"
        DEFAULT_ERROR_DESCRIPTION = "Eroare descriere"
    }
    private val mutableError = MutableLiveData<Error>()
    override val error: LiveData<Error> = mutableError
    override fun handleSessionError(exception: Exception) {
        when(exception){
            is UnauthorizedException -> mutableError.postValue(Error.Unauthorized(
                title = "Autorizare Gresita",
                message = "Nume/Parola este Gresita",
                buttonText = "Mai incerca"))
            is ChangePasswordFailedException -> mutableError.postValue(Error.WrongCredential(
                message = "Nume/Parola este Gresita",
                buttonText = "Mai incerca"))
            is WarningException -> mutableError.postValue(Error.Warning(
                title = "Atentie",
                message = "Nume/Parola este Gresita",
                buttonText = "Mai incerca"))
            else -> handleAppError(exception)

        }
    }

    override fun handleError(exception: Exception) {
        if (exception is CancellationException){
            Timber.i(exception)
            return
        }
        handleSessionError(exception)
    }

    private fun handleAppError(exception: Exception) {
        val exceptionMessage = exception.message?.lowercase(Locale.ROOT)
        val errorType = when (exception) {
            is WrongCredentialsException ->{
                Error.WrongCredential(
                    message = exceptionMessage,
                    buttonText = "Try"
                )
            }
            is WarningException ->{
                Error.Warning(
                    title = "Atentie",
                    message = exceptionMessage,
                    buttonText = "Try")
            }

            else -> {Error.Warning("","","")}
        }
        mutableError.postValue(errorType)

    }
}
fun FragmentActivity.handleError(
//        requestAuth: ((AuthType) -> Unit)? = null,
//        passwordChange: (() -> Unit)? = null,
//        secretQuestionsChange: ((String?) -> Unit)? = null,
//        resetUserLicense: (() -> Unit)? = null,
//        pinChangeFailed: (() -> Unit)? = null,
//        otherError: ((AchilleError) -> Boolean)? = null,
//        afterPopup: ((error: AchilleError, dialog: AlertDialog?) -> Unit)? = null,
//        dp310DispositiveAuth: ((AuthType) -> Unit)? = null,
//        offline: (() -> Unit)? = null,
//        initializationFailed: (() -> Unit)? = null
) = Observer<Error> { error ->
    var dialog: AlertDialog? = null
    when(error){
        is Error.WrongCredential ->{
            dialog = createDialogMessageWithSingleButton(
                title = "Eroare",
                titleButton = "Ok",
                description = error.message.toString(),
                onCDSAlertClickAction = object : OnAlertClickAction {
                    override fun onConfirmAction() {
                            dialog?.dismiss()
                            dialog = null
                    }
                    override fun onResetAction() {}
                }
            ).show()
        }
        is Error.Warning -> {
            println(error.stackTrace)
            dialog = createDialogMessageWithSingleButton(
                title = "Eroare",
                titleButton = "Ok",
                description = error.message.toString(),
                onCDSAlertClickAction = object : OnAlertClickAction {
                    override fun onConfirmAction() {
                        dialog?.dismiss()
                        dialog = null
                    }
                    override fun onResetAction() {}
                }
            ).show()
        }

        else -> {}
    }

}
