package website;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Event {

    public int id;
    public String name;
    public String location;
    //public Timestamp dtf;
    public LocalDateTime ldt;
    public String startDate;
    public String dateRange;
    public String date;
    public String link;
    public boolean multiDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMultiDay() {
        return multiDay;
    }

    public void setMultiDay(boolean multiDay) {
        this.multiDay = multiDay;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp dtf) {
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
            startDate = dayOfWeek + space + month + space + day;
        } else {
            startDate = dayOfWeek + space + month + space + day + "th, " + year + space + "@" + space + hour + ":" + formatMinute + (isAm ? " A.M." : " P.M.");
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
            dateRange = startDate + " - " + dayOfWeek + space + month + space + day;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
