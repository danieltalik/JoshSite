package website.Transformation;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateTransform {

    private LocalDateTime ldt;
    private String startDate;
    private String dateRange;
    private String date;
    private boolean multiDay;

    public void setStartDate(Timestamp dtf) {
        ldt = dtf.toLocalDateTime();
        boolean isAm = false;
        int hour = ldt.getHour();
        int minute = ldt.getMinute();
        String formatMinute;
        String month = ldt.getMonth().toString().substring(0, 1).toUpperCase() + ldt.getMonth().toString().substring(1).toLowerCase();
        String day = String.valueOf(ldt.getDayOfMonth());
        String dayOfWeek = ldt.getDayOfWeek().toString().substring(0, 1) + ldt.getDayOfWeek().toString().substring(1).toLowerCase();
        String year = String.valueOf(ldt.getYear());
        //TODO: Find a library or other methodology to "AMERICANIZE" the hour
        if (hour == 0) {
            hour = 12;
            isAm = true;
        } else if (hour >= 13) {
            hour -= 12;
            isAm = false;
        }

        if (minute < 10) {
            formatMinute = String.format("%02d", minute);
        } else formatMinute = Integer.toString(minute);

        if (multiDay) {
            startDate = String.join(" ", dayOfWeek, month, String.valueOf(year));
        } else {
            startDate = String.join(" ", dayOfWeek, month, day, year, "@",
                    hour + ":" + formatMinute, (isAm ? "A.M." : "P.M."));
        }

    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(Timestamp dtf) {

        ldt = dtf.toLocalDateTime();
        boolean isAm = false;
        int hour = ldt.getHour();
        int minute = ldt.getMinute();
        String formatMinute;
        String space = " ";
        String month = ldt.getMonth().toString().substring(0, 1).toUpperCase() + ldt.getMonth().toString().substring(1).toLowerCase();
        int day = ldt.getDayOfMonth();
        String dayOfWeek = ldt.getDayOfWeek().toString().substring(0, 1) + ldt.getDayOfWeek().toString().substring(1).toLowerCase();
        int year = ldt.getYear();

        //TODO: Find a library or other methodology to "AMERICANIZE" the hour
        if (hour == 0) {
            hour = 12;
            isAm = true;
        } else if (hour >= 13) {
            hour -= 12;
            isAm = false;
        }
        if (minute < 10) {
            formatMinute = String.format("%02d", minute);
        } else formatMinute = Integer.toString(minute);

        if (multiDay) {
            dateRange = String.join(" ", startDate, "-", dayOfWeek
                    , month, String.valueOf(day));
            //dateRange = startDate + " - " + dayOfWeek + space + month + space + day;
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(boolean multiDay) {
        if (multiDay) {
            date = dateRange;
        } else date = startDate;
    }

    public void setMultiDay(boolean multiDay) {
        this.multiDay = multiDay;
    }

    public boolean getMultiDay() {
        return multiDay;
    }

    public String blogDate(Timestamp date) {

        LocalDateTime ldt = date.toLocalDateTime();
        boolean isAm = false;
        int hour = ldt.getHour();
        int minute = ldt.getMinute();
        String formatMinute;
        String month = ldt.getMonth().toString().substring(0, 1).toUpperCase() + ldt.getMonth().toString().substring(1).toLowerCase();
        String day = String.valueOf(ldt.getDayOfMonth());
        String dayOfWeek = ldt.getDayOfWeek().toString().substring(0, 1) + ldt.getDayOfWeek().toString().substring(1).toLowerCase();
        String year = String.valueOf(ldt.getYear());

        if (hour == 0) {
            hour = 12;
            isAm = true;
        } else if (hour >= 13) {
            hour -= 12;
            isAm = false;
        }
        if (minute < 10) {
            formatMinute = String.format("%02d", minute);
        } else formatMinute = Integer.toString(minute);

        // this.date = dayOfWeek + space + month + space + day + "th, " + year + space + "@" + space + hour + ":" + formatMinute + (isAm ? " A.M." : " P.M.");
        return String.join(" ", dayOfWeek, month, day, year, "@", hour + ":" + formatMinute, (isAm ? "A.M." : "P.M."));
    }
}
