package za.ac.cput.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventsDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/eventappdb";
    private static final String USER = "root";
    private static final String PASSWORD = "uwaiz1234";

    public static void testConnection() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to DB: " + conn.getMetaData().getURL());
        } catch (SQLException e) {
            System.err.println("Connection failed:");
            e.printStackTrace();
        }
    }

    public static List<String> getAllEvents() {
        List<String> events = new ArrayList<>();
        String sql = "SELECT event_id, event_name, event_descriptions, event_location, event_date, event_time, category, status FROM events";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String event = rs.getLong("event_id") + " - " +
                        rs.getString("event_name") + " | " +
                        rs.getString("event_descriptions") + " | " +
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

    // Save a new event
    public static void saveEvent(String name, String description, String location, String date, String time, String category, String status) {
        String sql = "INSERT INTO events (event_name, event_descriptions, event_location, event_date, event_time, category, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, location);
            stmt.setString(4, date);
            stmt.setString(5, time);
            stmt.setString(6, category);
            stmt.setString(7, status);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing event by event_id
    public static void updateEvent(long eventId, String name, String description, String location, String date, String time, String category, String status) {
        String sql = "UPDATE events SET event_name = ?, event_descriptions = ?, event_location = ?, event_date = ?, event_time = ?, category = ?, status = ? WHERE event_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, location);
            stmt.setString(4, date);
            stmt.setString(5, time);
            stmt.setString(6, category);
            stmt.setString(7, status);
            stmt.setLong(8, eventId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
