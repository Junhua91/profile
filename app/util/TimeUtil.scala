package com.junhua.profile.util

import java.text.SimpleDateFormat
import java.util.{Calendar, Date, TimeZone}

object TimeUtil {


  def formatTime(timeStamp: Date, simpleDateFormat: SimpleDateFormat): String = {
    simpleDateFormat.format(timeStamp)
  }

  def changeTime(calendar: Calendar, field: Int, amount: Int): Calendar = {
    calendar.add(field, amount)
    calendar
  }

  def convertDateToCalendar(date: Date): Calendar = {
    val calendar = Calendar.getInstance()
    calendar.setTime(date)
    calendar
  }

  def changeTimeFromDate(date: Date, field: Int, amount: Int): Date = {
    val calendar = convertDateToCalendar(date)
    val changedTime = changeTime(calendar, field, amount)
    changedTime.getTime
  }

  def getCurrentTimeFromTimeZone(timeZone: TimeZone): String = {
    val calendar = Calendar.getInstance(timeZone)
    val formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    formatter.setTimeZone(timeZone)
    formatter.format(calendar.getTime)
  }

}
