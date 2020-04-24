package website.DAO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import website.Transformation.DateTransform;
import website.Model.Event;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class EventsDAO{

    private ConnectionConfiguration config;

    @Value("${dao.currentEventQuery}")
    private String currentEventQuery;
    @Value("${dao.pastEventQuery}")
    private String pastEventQuery;
    @Value("${dao.removeEventQuery}")
    private String removeEventQuery;
    @Value("${dao.addEvent}")
    private String addEvent;
    @Value("${dao.deleteEvent}")
    private String deleteQuery;

    public EventsDAO(ConnectionConfiguration config){
        this.config = config;
    }

    public List<Event> setPastEvent(){
        List<Event> events = new ArrayList<>();

        try {
            config.connection();
            Statement st = config.connection().createStatement();
            ResultSet rs = st.executeQuery(pastEventQuery);
            while (rs.next()){
                Event event = new Event();
                DateTransform dateTransform = new DateTransform();

                event.setId(rs.getInt("event_id"));
                event.setName(rs.getString("event_name"));
                event.setMultiDay(rs.getBoolean("event_multiDay"));
                dateTransform.setMultiDay(event.isMultiDay());
                dateTransform.setStartDate(rs.getTimestamp("event_startDate"));
                dateTransform.setDateRange(rs.getTimestamp("event_endDate"));
                event.setLocation(rs.getString("event_location"));
                event.setLink(rs.getString("event_link"));
                dateTransform.setDate(dateTransform.getMultiDay());
                event.setDate(dateTransform.getDate());
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
            config.connection();
            Statement st = config.connection().createStatement();
            ResultSet rs = st.executeQuery(currentEventQuery);
            while (rs.next()){
                Event event = new Event();
                DateTransform dateTransform = new DateTransform();

                event.setId(rs.getInt("event_id"));
                event.setName(rs.getString("event_name"));
                event.setMultiDay(rs.getBoolean("event_multiDay"));
                dateTransform.setMultiDay(event.isMultiDay());
                dateTransform.setStartDate(rs.getTimestamp("event_startDate"));
                dateTransform.setDateRange(rs.getTimestamp("event_endDate"));
                event.setLocation(rs.getString("event_location"));
                event.setLink(rs.getString("event_link"));
                dateTransform.setDate(dateTransform.getMultiDay());
                event.setDate(dateTransform.getDate());
                events.add(event);
            }
        }catch (SQLException e){
            e.getMessage();
        }
        return events;
    }

    public String eventEntry(Event event){

        LocalDateTime start = LocalDateTime.parse(event.getInputStartDate());
        LocalDateTime end;

        if(event.getInputEndDate() == null || event.getInputEndDate() == ""){
            end = LocalDateTime.parse(event.getInputStartDate());
        } else {
            end = LocalDateTime.parse(event.getInputEndDate());
        }
        Timestamp isd = Timestamp.valueOf(start);
        Timestamp ied = Timestamp.valueOf(end);


        try {
            config.connection();
            PreparedStatement ps = config.connection().prepareStatement(addEvent);
            ps.setString(1,event.getName());
            ps.setBoolean(2,event.isMultiDay());
            ps.setTimestamp(3,isd);
            ps.setTimestamp(4,ied);
            ps.setString(5,event.getLocation());
            ps.setString(6, event.getLink());
            ps.executeUpdate();

        }catch (SQLException e){
            e.getMessage();
        }
        return "blog";
    }
    public String deleteEvent(int id){

        try {
            config.connection();
            PreparedStatement ps = config.connection().prepareStatement(deleteQuery);
            ps.setInt(1,id);
            ps.executeUpdate();

        }catch (SQLException e){
            e.getMessage();
        }
        return "events";
    }
}
