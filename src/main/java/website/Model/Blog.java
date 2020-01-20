package website.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Blog {

    private int id;
    private String date;
    private String title;
    private String content;
    private Timestamp postDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Timestamp date) {

        LocalDateTime ldt = date.toLocalDateTime();
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

        this.date = dayOfWeek + space + month + space + day + "th, " + year + space + "@" + space + hour + ":" + formatMinute + (isAm ? " A.M." : " P.M.");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }
}
