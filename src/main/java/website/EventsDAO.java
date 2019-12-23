package website;

import java.sql.*;
import java.time.format.DateTimeFormatter;

public class EventsDAO {
    private final String url = "jdbc:postgresql://localhost:5432/JoshSite";
    private final String user = "postgres";
    private final String password = "password";
    private final String testQuery = "SELECT * FROM events WHERE event_name = 'Music Royale Recovery Benefit Concert'";


    public Connection connection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            e.getMessage();
        } return conn;
    }
    public Events setEvent(){
        Events events = new Events();
        try {
            connection();
            Statement st = connection().createStatement();
            ResultSet rs = st.executeQuery(testQuery);
            while (rs.next()){
                events.setName(rs.getString("Event_Name"));
                events.setDtf(rs.getTimestamp("Event_DateTime"));
                events.setLocation(rs.getString("Event_Location"));
            }
        }catch (SQLException e){
            e.getMessage();
        }
        return events;
    }
}
