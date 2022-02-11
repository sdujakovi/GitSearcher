package com.example.gitsearcher.util

import java.lang.Math.round
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration.Companion.milliseconds

class LastUpdateCalculater {
    companion object {
        fun CalculateTime(stringDate: String?):String{

            //Formating DateTime stamp obtained from jason
            val inDateTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(stringDate)
            val nowDateTime = Date()

            //Difference of current DateTime and obtained DateTime
            val differenceDateTime = (nowDateTime.time.milliseconds - inDateTime.time.milliseconds)

            //Conversion of DateTime format to Date format for use when last update is above 12 months ago
            val outDateTime = StringBuilder()
            val outDate = SimpleDateFormat("dd/MM/yyyy").format(inDateTime)

            /***
             * Calculating displayed last update time format based on differenceDateTime
             * if between 0 and 60 seconds --> display in seconds ago
             * if between 0 and 60 minutes  --> display in minnuts ago
             * if between 0 and 24 hours   --> display in hours ago
             * if between 0 and 30 days    --> display in days ago
             * if between 0 and 12 months  --> display in months ago
             * if more than 12 moths       --> display actual date
             */
            if((differenceDateTime.inWholeSeconds > 0) and (differenceDateTime.inWholeSeconds < 60)){
                outDateTime.append(differenceDateTime.inWholeSeconds.toString())
                outDateTime.append(" sec ago")
            }else if((differenceDateTime.inWholeMinutes > 0) and(differenceDateTime.inWholeMinutes < 60)){
                outDateTime.append(differenceDateTime.inWholeMinutes.toString())
                outDateTime.append(" min ago")
            }else if((differenceDateTime.inWholeHours > 0) and(differenceDateTime.inWholeHours < 24)){
                outDateTime.append(differenceDateTime.inWholeHours.toString())
                outDateTime.append(" h ago")
            }else if((differenceDateTime.inWholeDays > 0) and (differenceDateTime.inWholeDays < 30)){
                outDateTime.append(differenceDateTime.inWholeDays.toString())
                outDateTime.append(" day ago")
            }else if((differenceDateTime.inWholeDays/30) <= 12){
                outDateTime.append(round((differenceDateTime.inWholeDays/30).toDouble()).toString())
                outDateTime.append(" mth ago")
            }else{
                outDateTime.append("at ").append(outDate.toString())
            }
            return outDateTime.toString()
        }

        //Calculating expanded text of last update data
        fun CalculateTimeExtended(stringDate: String?):String{
            val outDateTime = StringBuilder()
            val formatDateTime = CalculateTime(stringDate)
            outDateTime.append("updated ").append(formatDateTime)
            return outDateTime.toString()
        }
    }
}