package com.vectorinc.stockappmockup.data.mapper

import com.vectorinc.stockappmockup.data.remote.dto.IntraDayInfoDto
import com.vectorinc.stockappmockup.domain.model.IntraDayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun IntraDayInfoDto.toIntraDayInfo()  : IntraDayInfo{
    val pattern = "yyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timeStamp,formatter)
    return IntraDayInfo(
        date = localDateTime,
        close = close
    )
}