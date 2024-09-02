@file:OptIn(FormatStringsInDatetimeFormats::class)

package domain

import data.Constants.DateFormat.DEFAULT_FORMAT
import io.fluidsonic.country.Country
import io.fluidsonic.i18n.name
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime

/**
 * Creates a _cold_ flow that produces values from the given Throwable.
 */
fun <T> Throwable.asFlow(): Flow<T> {
    return flow {
        emit(
            suspendCancellableCoroutine { continuation ->
                continuation.cancel(this@asFlow)
            }
        )
    }
}

/**
 * Creates a _cold_ flow that produces values from the given T type.
 */
fun <T> T.asFlow(): Flow<T> = flow {
    emit(this@asFlow)
}

fun Long.toDateTimeAsEEEE_dd_MMMMStringFormat(zone: TimeZone? = null): String {
    val date = Clock.System.now().apply {
        Instant.fromEpochMilliseconds(this@toDateTimeAsEEEE_dd_MMMMStringFormat)
    }.toLocalDateTime(zone ?: TimeZone.currentSystemDefault())

    val dayOfWeek = date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
    val dayOfMonth = date.dayOfMonth.toString().padStart(2, '0')
    val month = date.month.name.lowercase().replaceFirstChar { it.uppercase() }

    return "$dayOfWeek, $dayOfMonth $month"
}

fun Long.toDateTimeString(format: String = DEFAULT_FORMAT, zone: TimeZone? = null): String {
    val date = Clock.System.now().apply {
        Instant.fromEpochMilliseconds(this@toDateTimeString)
    }.toLocalDateTime(zone ?: TimeZone.currentSystemDefault())

    return LocalDateTime.Format {
        byUnicodePattern(format)
    }.format(date)
}

fun Long.toDateTimeString(format: String = DEFAULT_FORMAT, zoneId: Int? = null): String {

    val date = Clock.System.now().apply {
        Instant.fromEpochMilliseconds(this@toDateTimeString)
    }.toLocalDateTime(TimeZone.currentSystemDefault()/*TimeZone.of(zoneId.toString())*/)

    return LocalDateTime.Format {
        byUnicodePattern(format)
    }.format(date)/*try {
        SimpleDateFormat(format, Locale.ENGLISH)
            .apply { timeZone = TimeZone.getDefault().apply { zoneId?.let { rawOffset = it } } }
            .format(date)
    } catch (e: Exception) {
        SimpleDateFormat(Constants.DateFormat.DEFAULT_FORMAT, Locale.ENGLISH)
            .apply { timeZone = TimeZone.getDefault().apply { zoneId?.let { rawOffset = it } } }
            .format(date)
    }*/
}

fun String.toCountryName(): String {
    return Country.forCode(this).name
}
