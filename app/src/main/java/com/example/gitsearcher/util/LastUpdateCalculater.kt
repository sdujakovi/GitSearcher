package com.example.gitsearcher.util

import java.lang.Math.round
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.time.Duration.Companion.milliseconds

class LastUpdateCalculater {
    companion object {
        fun CalculateTime(stringDate: String?):String{
            val inDateTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(stringDate)
            val nowDateTime = Date()

            val diferenceDateTime = (nowDateTime.time.milliseconds - inDateTime.time.milliseconds)

            val outDateTime = StringBuilder()


            if((diferenceDateTime.inWholeSeconds > 0) and (diferenceDateTime.inWholeSeconds < 60)){
                outDateTime.append(diferenceDateTime.inWholeSeconds.toString())
            }else if((diferenceDateTime.inWholeMinutes > 0) and(diferenceDateTime.inWholeMinutes < 60)){
                outDateTime.append(diferenceDateTime.inWholeMinutes.toString())
            }else if((diferenceDateTime.inWholeHours > 0) and(diferenceDateTime.inWholeHours < 60)){
                outDateTime.append(diferenceDateTime.inWholeHours.toString())
            }else if((diferenceDateTime.inWholeDays > 0) and (diferenceDateTime.inWholeDays < 30)){
                outDateTime.append(diferenceDateTime.inWholeDays.toString())
            }else{
                outDateTime.append(round((diferenceDateTime.inWholeDays/30).toDouble()).toString())
            }
            return outDateTime.toString()
        }
    }
}