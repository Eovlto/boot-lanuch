package com.mingyang.bootlaunch.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class DateUtil {
    public static String DATE_FORMAT_TIMESTAMP = "yyyyMMddHHmmssSSS";
    public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static String DATE_FORMAT_YMDH = "yyyy-MM-dd HH";
    public static String DATE_FORMAT_YMD = "yyyy-MM-dd";
    public static String DATE_FORMAT_YM = "yyyy-MM";

    public DateUtil() {
    }

    public static String format(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static String format(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static Date parse(String date) {
        if (date != null && !date.equals("")) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
                return simpleDateFormat.parse(date);
            } catch (ParseException var2) {
                var2.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static Date parse(String date, String format) {
        if (date != null && !date.equals("")) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                return simpleDateFormat.parse(date);
            } catch (ParseException var3) {
                var3.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(5);
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(2) + 1;
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(1);
    }

    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(7) - 1;
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        return addDay(getFirstDayOfMonth(addMonth(date, 1)), -1);
    }

    public static boolean isLeapYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(1);
        return year % 4 == 0 && year % 100 != 0 | year % 400 == 0;
    }

    public static Date getDateByYmd(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    public static Date getYearCycleOfDate(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(1, year);
        return calendar.getTime();
    }

    public static Date getMonthCycleOfDate(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, month);
        return calendar.getTime();
    }

    public static int getYearSpace(Date fromDate, Date toDate) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(fromDate);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(toDate);
        return calendar2.get(1) - calendar1.get(1);
    }

    public static int getMonthSpace(Date fromDate, Date toDate) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(fromDate);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(toDate);
        return calendar2.get(1) * 12 + calendar2.get(2) - (calendar1.get(1) * 12 + calendar1.get(2));
    }

    public static long getDaySpace(Object fromDate, Object toDate) {
        Date date1 = changeObject(fromDate);
        Date date2 = changeObject(toDate);
        long datel1 = date1.getTime();
        long datel2 = date2.getTime();
        return (datel2 - datel1) / 86400000L;
    }

    public static long getHourSpace(Object fromDate, Object toDate) {
        Date date1 = changeObject(fromDate);
        Date date2 = changeObject(toDate);
        long datel1 = date1.getTime();
        long datel2 = date2.getTime();
        return (datel2 - datel1) / 3600000L;
    }

    public static long getMinuteSpace(Object fromDate, Object toDate) {
        Date date1 = changeObject(fromDate);
        Date date2 = changeObject(toDate);
        long datel1 = date1.getTime();
        long datel2 = date2.getTime();
        return (datel2 - datel1) / 60000L;
    }

    public static long getSecondSpace(Object fromDate, Object toDate) {
        Date date1 = changeObject(fromDate);
        Date date2 = changeObject(toDate);
        long datel1 = date1.getTime();
        long datel2 = date2.getTime();
        return (datel2 - datel1) / 1000L;
    }

    public static int getAge(Date birthday, Date date) {
        int year = getYear(date);
        int month = getMonth(date);
        int day = getDay(date);
        int _year = getYear(birthday);
        int _month = getMonth(birthday);
        int _day = getDay(birthday);
        return month <= _month && (month != _month || day <= _day) ? year - 1 - _year : year - _year;
    }

    public static String getBirthDayFromIdCard(String idCard) {
        Calendar calendar = Calendar.getInstance();
        if (idCard.length() == 15) {
            calendar.set(1, Integer.valueOf("19" + idCard.substring(6, 8)));
            calendar.set(2, Integer.valueOf(idCard.substring(8, 10)) - 1);
            calendar.set(5, Integer.valueOf(idCard.substring(10, 12)));
        } else if (idCard.length() == 18) {
            calendar.set(1, Integer.valueOf(idCard.substring(6, 10)));
            calendar.set(2, Integer.valueOf(idCard.substring(10, 12)) - 1);
            calendar.set(5, Integer.valueOf(idCard.substring(12, 14)));
        }

        return format(calendar.getTime());
    }

    public static Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, day);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, month);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(1, year);
        return calendar.getTime();
    }

    public static Date changeObject(Object date) {
        if (date != null && date instanceof Date) {
            return (Date)date;
        } else {
            return date != null && date instanceof String ? parse((String)date) : null;
        }
    }

    public static long getAgeByBirthday(String date) {
        Date birthday = parse(date, DATE_FORMAT_YMD);
        long sec = (new Date()).getTime() - birthday.getTime();
        long age = sec / 86400000L / 365L;
        return age;
    }

    public static String getFormat(String str) {
        boolean year = false;
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        if (pattern.matcher(str.substring(0, 4)).matches()) {
            year = true;
        }

        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        if (!year) {
            if (!str.contains("月") && !str.contains("-") && !str.contains("/")) {
                index = 3;
            } else if (Character.isDigit(str.charAt(0))) {
                index = 1;
            }
        }

        for(int i = 0; i < str.length(); ++i) {
            char chr = str.charAt(i);
            if (Character.isDigit(chr)) {
                switch(index) {
                    case 0:
                        stringBuilder.append("y");
                        break;
                    case 1:
                        stringBuilder.append("M");
                        break;
                    case 2:
                        stringBuilder.append("d");
                        break;
                    case 3:
                        stringBuilder.append("H");
                        break;
                    case 4:
                        stringBuilder.append("m");
                        break;
                    case 5:
                        stringBuilder.append("s");
                        break;
                    case 6:
                        stringBuilder.append("S");
                }
            } else {
                if (i > 0 && Character.isDigit(str.charAt(i - 1))) {
                    ++index;
                }

                stringBuilder.append(chr);
            }
        }

        return stringBuilder.toString();
    }

    public static boolean compare(Date date1, Date date2) {
        return date1.compareTo(date2) <= -1;
    }
}