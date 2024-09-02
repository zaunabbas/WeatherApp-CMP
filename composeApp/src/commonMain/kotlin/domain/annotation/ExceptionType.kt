package domain.annotation

import domain.annotation.ExceptionType.Companion.ALERT
import domain.annotation.ExceptionType.Companion.DIALOG
import domain.annotation.ExceptionType.Companion.INLINE
import domain.annotation.ExceptionType.Companion.ON_PAGE
import domain.annotation.ExceptionType.Companion.REDIRECT
import domain.annotation.ExceptionType.Companion.SNACK
import domain.annotation.ExceptionType.Companion.TOAST

/**
 * Clear exception from Throwable
 * @param SNACK is type of show message via Snack bar
 * @param TOAST is type of show message via Toast
 * @param INLINE is type of show or hide view warning, example: password in correct hint of password field
 * @param ALERT is type of show message type Alert Dialog, but only message & button `OK`
 * @param DIALOG is type of show Alert Dialog, with multiple attributes: title, message, positive, negative & action
 * @param REDIRECT is type of auto-redirect with view, action or finished, ...
 * @param ON_PAGE is type of show message on center screen, maybe show retry button
 */
//@IntDef(SNACK, TOAST, INLINE, ALERT, DIALOG, REDIRECT, ON_PAGE)
annotation class ExceptionType {
    companion object {
        const val SNACK = 1
        const val TOAST = 2
        const val INLINE = 3
        const val ALERT = 4
        const val DIALOG = 5
        const val REDIRECT = 6
        const val ON_PAGE = 7
    }
}
