package za.ac.cput.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventsDAO {



    private static final String URL = "jdbc:mysql://localhost:3306/eventappdb";
    private static final String USER = "root";
    private static final String PASSWORD = "uwaiz1234";


    public static List<String> getAllEvents() {
        List<String> events = new ArrayList<>();
        String sql = "SELECT event_id, event_name, event_description, event_location, event_date, event_time, category, status FROM events";


        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                String event = rs.getLong("event_id") + " - " +
                        rs.getString("event_name") + " | " +
                        rs.getString("event_description") + " | " +
                        rs.getString("event_location") + " | " +
                        rs.getString("event_date") + " " +
                        rs.getString("event_time") + " | " +
                        rs.getString("category") + " | " +
                        rs.getString("status");
                events.add(event);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }
}