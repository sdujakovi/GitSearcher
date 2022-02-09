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
            val outDate = SimpleDateFormat("dd/MM/yyyy").format(inDateTime)


            if((diferenceDateTime.inWholeSeconds > 0) and (diferenceDateTime.inWholeSeconds < 60)){

                outDateTime.append(diferenceDateTime.inWholeSeconds.toString())
                outDateTime.append(" sec ago")
            }else if((diferenceDateTime.inWholeMinutes > 0) and(diferenceDateTime.inWholeMinutes < 60)){
                outDateTime.append(diferenceDateTime.inWholeMinutes.toString())
                outDateTime.append(" min ago")
            }else if((diferenceDateTime.inWholeHours > 0) and(diferenceDateTime.inWholeHours < 24)){
                outDateTime.append(diferenceDateTime.inWholeHours.toString())
                outDateTime.append(" hrs ago")
            }else if((diferenceDateTime.inWholeDays > 0) and (diferenceDateTime.inWholeDays < 30)){
                outDateTime.append(diferenceDateTime.inWholeDays.toString())
                outDateTime.append(" days ago")
            }else if((diferenceDateTime.inWholeDays/30) <= 12){
                outDateTime.append(round((diferenceDateTime.inWholeDays/30).toDouble()).toString())
                outDateTime.append(" mth ago")
            }else{
                outDateTime.append("at ").append(outDate.toString())
            }
            return outDateTime.toString()
        }
    }
}