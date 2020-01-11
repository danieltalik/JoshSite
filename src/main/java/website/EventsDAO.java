package website;

import website.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventsDAO {
    private final String url = "jdbc:postgresql://localhost:5432/JoshSite";
    private final String user = "postgres";
    private final String password = "password";
    private final String currentEventQuery = "SELECT * FROM events WHERE event_enddate > CURRENT_TIMESTAMP ORDER BY event_startDate ASC;";
    private final String pastEventQuery = "SELECT * FROM events WHERE event_enddate < CURRENT_TIMESTAMP ORDER BY event_startDate ASC ;";


    public Connection connection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            e.getMessage();
        } return conn;
    }
    public List<Event> setPastEvent(){
        List<Event> events = new ArrayList<>();

        try {
            connection();
            Statement st = connection().createStatement();
            ResultSet rs = st.executeQuery(pastEventQuery);
            while (rs.next()){
                Event event = new Event();

                event.setId(rs.getInt("event_id"));
                event.setName(rs.getString("event_name"));
                event.setMultiDay(rs.getBoolean("event_multiDay"));
                event.setStartDate(rs.getTimestamp("event_startDate"));
                event.setDateRange(rs.getTimestamp("event_endDate"));
                event.setLocation(rs.getString("event_location"));
                event.setLink(rs.getString("event_link"));
                event.setDate(event.multiDay);

                events.add(event);
            }
        }catch (SQLException e){
            e.getMessage();
        }
        return events;
    }

    public List<Event> setUpcomingEvent(){
        List<Event> events = new ArrayList<>();

        try {
            connection();
            Statement st = connection().createStatement();
            ResultSet rs = st.executeQuery(currentEventQuery);
            while (rs.next()){
                Event event = new Event();

                event.setId(rs.getInt("event_id"));
                event.setName(rs.getString("event_name"));
                event.setMultiDay(rs.getBoolean("event_multiDay"));
                event.setStartDate(rs.getTimestamp("event_startDate"));
                event.setDateRange(rs.getTimestamp("event_endDate"));
                event.setLocation(rs.getString("event_location"));
                event.setLink(rs.getString("event_link"));
                event.setDate(event.multiDay);

                events.add(event);
            }
        }catch (SQLException e){
            e.getMessage();
        }
        return events;
    }
}
