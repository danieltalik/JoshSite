package website.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Event {

    public int id;
    public String name;
    public String location;
    public String date;
    public String link;
    public boolean multiDay;
    public String inputStartDate;
    public String inputEndDate;

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


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
      this.date = date;
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

    public void setInputStartDate(String inputStartDate) {
        this.inputStartDate = inputStartDate;
    }

    public String getInputStartDate() {
        return inputStartDate;
    }

    public void setInputEndDate(String inputEndDate) {
        this.inputEndDate = inputEndDate;
    }
    public String getInputEndDate(){
        return inputEndDate;
    }


}
