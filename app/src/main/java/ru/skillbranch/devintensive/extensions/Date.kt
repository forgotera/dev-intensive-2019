package ru.skillbranch.devintensive.extensions


import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int,units: TimeUnits = TimeUnits.SECOND):Date{
    var time = this.time

    time +=when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

//FIXME
fun Date.humanizeDiff(date:Date = Date()): String {
     val check =date.time - time
     val second = abs(check) / 1000L
     val minute = second / 60
     val hour =  minute / 60
     val day = hour / 24
     if(check > 0) {
         return when (second) {
             in 0..1 -> "только что"
             in 2..45 -> "несколько секунд назад"
             in 45..75 -> "минуту назад"
             in 75..2700 -> {
                 when (minute % 10) {
                     in 2..4 -> "$minute минуты назад"
                     else -> "$minute минут назад"
                 }
             }
             in 2700..4500 -> "час назад"
             in 4500..79200 -> {
                 when (hour) {
                     in 2..4 -> "$hour часа назад"
                     else -> "$hour часов назад"
                 }
             }
             //22ч-26ч
             in 79200..93600 -> "день назад"
             //26ч - 360д
             in 93600..31104000 -> {
                 when (day % 10) {
                     in 2..4 -> "$day дня назад"
                     else -> "$day дней назад"
                 }

             }
             else -> {
                 return "более года назад"
             }
         }
     }else{
         return when (second) {
             in 0..1 -> "только что"
             in 2..45 -> " через несколько секунд"
             in 45..75 -> "через минуту"
             in 75..2700 -> {
                 when (minute % 10) {
                     in 2..4 -> "через $minute минуты"
                     else -> "через $minute минут"
                 }
             }
             in 2700..4500 -> "через час"
             in 4500..79200 -> {
                 when (hour) {
                     in 2..4 -> "через $hour часа"
                     else -> "через $hour часов"
                 }
             }
             //22ч-26ч
             in 79200..93600 -> "через день"
             //26ч - 360д
             in 93600..31104000 -> {
                 when (day % 10) {
                     in 2..4 -> "через $day дня"
                     else -> "через $day дней"
                 }

             }
             else -> {
                 return "более чем через года"
             }
         }
     }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
