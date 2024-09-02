package domain.exception

import domain.model.Dialog
import domain.model.Redirect
import domain.model.Tag
import domain.annotation.ExceptionType

sealed class BaseException(
    open val code: Int,
    @ExceptionType val type: Int,
    override val message: String?,
    val hashCode: String? = "test${String.hashCode()}"//"${System.nanoTime()}"
) : Throwable(message) {

    data class AlertException(
        override val code: Int,
        override val message: String,
        val title: String? = null
    ) : BaseException(code, ExceptionType.ALERT, message)

    data class InlineException(
        override val code: Int,
        val tags: List<Tag>
    ) : BaseException(code, ExceptionType.INLINE, null)

    data class RedirectException(
        override val code: Int,
        val redirect: Redirect
    ) : BaseException(code, ExceptionType.REDIRECT, null)

    data class SnackBarException(
        override val code: Int,
        override val message: String
    ) : BaseException(code, ExceptionType.SNACK, message)

    data class ToastException(
        override val code: Int,
        override val message: String
    ) : BaseException(code, ExceptionType.TOAST, message)

    data class DialogException(
        override val code: Int,
        val dialog: Dialog
    ) : BaseException(code, ExceptionType.DIALOG, null)

    data class OnPageException(
        override val code: Int,
        override val message: String
    ) : BaseException(code, ExceptionType.ON_PAGE, message)
}
