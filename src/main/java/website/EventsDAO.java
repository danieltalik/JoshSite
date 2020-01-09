package website;

import website.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventsDAO {
    private final String url = "jdbc:postgresql://localhost:5432/JoshSite";
    private final String user = "postgres";
    private final String password = "password";
    private final String eventQuery = "SELECT * FROM events ORDER BY event_startDate ASC;";


    public Connection connection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            e.getMessage();
        } return conn;
    }
    public List<Event> setEvent(){
        List<Event> events = new ArrayList<>();

        try {
            connection();
            Statement st = connection().createStatement();
            ResultSet rs = st.executeQuery(eventQuery);
            while (rs.next()){
                Event event = new Event();

                event.setId(rs.getInt("event_id"));
                event.setName(rs.getString("event_name"));
                event.setMultiDay(rs.getBoolean("event_mulitDay"));
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
