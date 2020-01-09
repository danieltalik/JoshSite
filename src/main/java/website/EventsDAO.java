package website;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventsDAO {
    private final String url = "jdbc:postgresql://localhost:5432/JoshSite";
    private final String user = "postgres";
    private final String password = "password";
    private final String eventQuery = "SELECT * FROM events ORDER BY event_datetime ASC;";


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
                event.setName(rs.getString("Event_Name"));
                event.setDate(rs.getTimestamp("Event_DateTime"));
                event.setLocation(rs.getString("Event_Location"));
                events.add(event);
            }
        }catch (SQLException e){
            e.getMessage();
        }
        return events;
    }
}
