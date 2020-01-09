package website;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    public String name;
    public String location;
    //public Timestamp dtf;
    public LocalDateTime ldt;
    public String date;

    public String getDate() {
        return date;
    }

    public void setDate(Timestamp dtf)
    {
        ldt = dtf.toLocalDateTime();
        boolean isAm = false;
        int hour = ldt.getHour();
        int minute = ldt.getMinute();
        String formatMinute;
        String space = " ";
        String month = ldt.getMonth().toString().substring(0,1).toUpperCase() + ldt.getMonth().toString().substring(1).toLowerCase();
        int day = ldt.getDayOfMonth();
        String dayOfWeek = ldt.getDayOfWeek().toString().substring(0,1) + ldt.getDayOfWeek().toString().substring(1).toLowerCase();
        int year = ldt.getYear();

        if(hour == 0){
           hour = 12;
           isAm = true;
        } else if (hour >= 13){
            hour -= 12;
            isAm = false;
        }
        if(minute < 10){
            formatMinute = String.format("%02d",minute);
        } else formatMinute = Integer.toString(minute);

        date = dayOfWeek + space + month + space + day + "th, " + year + space + "@"+ space + hour +":"+formatMinute+(isAm?" A.M." : " P.M.");

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
}
