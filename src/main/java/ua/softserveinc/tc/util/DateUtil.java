package ua.softserveinc.tc.util;

import ua.softserveinc.tc.constants.DateConstants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Demian on 07.06.2016.
 */
public final class DateUtil {
    private static DateFormat dateFormat = new SimpleDateFormat(DateConstants.SHORT_DATE_FORMAT);
    private static DateFormat dateAndTimeFormat = new SimpleDateFormat(DateConstants.DATE_AND_TIME_FORMAT);
    private static DateFormat isoDateFormat = new SimpleDateFormat(DateConstants.DATE_FORMAT);

    // Suppresses default constructor, ensuring non-instantiability.
    private DateUtil() {
    }

    public static Date toDate(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            //TODO: log
            return null;
        }
    }

    public static Date toDateAndTime(String date) {
        try {
            return dateAndTimeFormat.parse(date);
        } catch (ParseException e) {
            //TODO: log
            return null;
        }
    }
    public static Date toDateISOFormat(String dateToParse) {
        try {
            return isoDateFormat.parse(dateToParse);
        } catch (ParseException e) {
            //TODO: log
            return null;
        }
    }

    public static String toIsoString(Date date){
        return isoDateFormat.format(date);
    }

    public static Date toDate(Calendar calendar) {
        return calendar.getTime();
    }

    public static Calendar dateNow() {
        return Calendar.getInstance();
    }

    public static Calendar dateMonthAgo() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar;
    }

    public static String getStringDate(Calendar calendar) {
        String result = calendar.get(Calendar.YEAR) + "-";
        result += String.format("%02d", calendar.get(Calendar.MONTH) + 1) + "-";
        result += String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH));
        return result;
    }

    private static int getHoursFromMilliseconds(long milliseconds) {
        return (int) TimeUnit.MILLISECONDS.toHours(milliseconds);
    }

    private static int getMinutesFromMilliseconds(long milliseconds) {
        int hours = getHoursFromMilliseconds(milliseconds);
        return (int) TimeUnit.MILLISECONDS.toMinutes(milliseconds - TimeUnit.HOURS.toMillis(hours));
    }

    public static int getRoundedHours(long milliseconds) {
        int hours = getHoursFromMilliseconds(milliseconds);
        int minutes = getMinutesFromMilliseconds(milliseconds);

        // 02:10 hours - 2 hours; 02:10 hours - 3 hours
        if (minutes > 11) {
            hours++;
        }
        return hours;
    }

    public static String toHoursAndMinutes(long milliseconds) {
        int hours = getHoursFromMilliseconds(milliseconds);
        int minutes = getMinutesFromMilliseconds(milliseconds);

        String hoursAndMinutes = String.format("%02d", hours) + ":";
        hoursAndMinutes += String.format("%02d", minutes);

        return hoursAndMinutes;
    }
    public static Date setTime(String time){
        Calendar day = Calendar.getInstance();
        String toDay = getStringDate(day) +" " +time;
        try {
            Date date = dateAndTimeFormat.parse(toDay);
            return date;
        }catch (ParseException e){
            //TODO: log
            return null;
        }
    }

    public static String convertDateToString(Date date) {
        DateFormat df = new SimpleDateFormat(DateConstants.DATE_FORMAT);
        return df.format(date);
    }

}
