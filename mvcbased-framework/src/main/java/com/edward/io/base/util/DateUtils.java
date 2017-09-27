package com.edward.io.base.util;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtils
{
  public static String parseDate(Date date, String formatStr)
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);

    if (date == null)
      return null;

    return dateFormat.format(date);
  }

  public static Date getDate(Date date, int hour, int min, int sec)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(10, hour);
    cal.set(12, min);
    cal.set(13, sec);
    return cal.getTime();
  }

  public static Date toDate(String strDateTime, String dateTimeFormat)
  {
    if ((strDateTime == null) || (strDateTime.length() == 0) || 
      (dateTimeFormat == null) || (dateTimeFormat.length() == 0)) {
      return null;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
    Date date = dateFormat.parse(strDateTime, new ParsePosition(0));

    if (date == null) {
      return null;
    }

    String dateStr = parseDate(date, dateTimeFormat);

    if (!(strDateTime.equals(dateStr))) {
      return null;
    }

    return date;
  }

  public static Timestamp toTimestamp(String dateTimeStr, String dateTimeFormat)
  {
    return toTimestamp(toDate(dateTimeStr, dateTimeFormat));
  }

  public static Timestamp toTimestamp(Date date)
  {
    if (date == null)
      return null;

    return new Timestamp(date.getTime());
  }

  public static boolean isSmallerThan(Date startTime, Date endTime)
  {
    if ((startTime == null) || (endTime == null)) {
      return true;
    }

    return (startTime.getTime() <= endTime.getTime());
  }

  public static final Date addDate(int type, Date date, int num)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(type, num);
    return cal.getTime();
  }

  public static Date addDaysToDate(Date date, int numDays)
  {
    if (date == null)
      return null;

    return addDate(5, date, numDays);
  }

  public static Date addHoursToDate(Date date, int numHours)
  {
    if (date == null)
      return null;

    return addDate(11, date, numHours);
  }

  public static Date addMinutesToDate(Date date, int numMins)
  {
    if (date == null)
      return null;

    return addDate(12, date, numMins);
  }

  public static Date addMonthsToDate(Date date, int numMonths)
  {
    if (date == null)
      return null;

    return addDate(2, date, numMonths);
  }

  public static Date addYearsToDate(Date date, int numYears)
  {
    if (date == null)
      return null;

    return addDate(1, date, numYears);
  }

  public static int compareMonth(Date st, Date end)
  {
    int y = Math.abs(((getYear(end) < 0) ? 0 : getYear(end)) - 
      ((getYear(st) < 0) ? 0 : getYear(st)));
    int m = 0;
    if (y > 0) {
      --y;
      m = Math.abs(12 - getMonth(st) + getMonth(end));
    } else {
      m = Math.abs(getMonth(end) - getMonth(st));
    }
    return (y * 12 + m);
  }

  public static long compare(Date start, Date end)
  {
    if ((start != null) && (end != null))
      return (end.getTime() - start.getTime());

    return -1738881375949291520L;
  }

  public static int getYear()
  {
    return getYear(new Date());
  }

  public static int getYear(Date date)
  {
    if (date == null)
      return -1;

    return DateToCalendar(date).get(1);
  }

  public static int getMonth()
  {
    return getMonth(new Date());
  }

  public static int getMonth(Date date)
  {
    if (date == null)
      return 0;

    return (DateToCalendar(date).get(2) + 1);
  }

  public static int getDay()
  {
    return getDay(new Date());
  }

  public static int getDay(Date da)
  {
    if (da == null)
      return 0;

    return DateToCalendar(da).get(5);
  }

  public static Calendar DateToCalendar(Date date)
  {
    Calendar cc = Calendar.getInstance();
    cc.setTime(date);
    return cc;
  }

  public static Date getUpWeekDay(Date date)
  {
    if (date == null)
      return null;

    Calendar cc = Calendar.getInstance();
    cc.setTime(date);
    int week = cc.get(7);
    return addDaysToDate(date, 1 - week);
  }

  public static Date getMonday(Date date)
  {
    if (date == null)
      return null;

    Calendar cc = Calendar.getInstance();
    cc.setTime(date);
    int week = cc.get(7);
    return addDaysToDate(date, 2 - week);
  }

  public static int getWeek(Date date)
  {
    if (date == null)
      return -1;

    Calendar cc = Calendar.getInstance();
    cc.setTime(date);
    int week = cc.get(7);
    if (week == 1)
      week = 7;
    else
      --week;

    return week;
  }

  public static String getRandNum(int lo)
  {
    if (lo < 1)
      lo = 4;

    StringBuffer temp = new StringBuffer();
    Random rand = new Random();
    for (int i = 0; i < lo; ++i)
      temp.append(String.valueOf(rand.nextInt(10)));

    return temp.toString();
  }

  public static String getDataName()
  {
    return parseDate(new Date(), "yyyyMMddHHmmss") + getRandNum(4);
  }

  public static String formatDS(String date)
  {
    if (date == null)
      return "";

    return date.replace("-", "").replace(":", "").replace(" ", "");
  }
}