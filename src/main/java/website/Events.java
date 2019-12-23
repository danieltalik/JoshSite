package website;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class Events {
    public String name;
    public String location;
    public Timestamp dtf;

    public Timestamp getDtf() {
        return dtf;
    }

    public void setDtf(Timestamp dtf) {
        this.dtf = dtf;
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
