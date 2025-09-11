package za.ac.cput.DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//for my database logic
public class venuesDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/eventbooking";
    private static final String USER = "root";       
    private static final String PASSWORD = "uwaiz1234"; 
    public static List<String> getAllVenues() {
        List<String> venues = new ArrayList<>();
        String sql = "SELECT name, location, capacity FROM venues";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String venue = rs.getString("name") + " - " +
                               rs.getString("location") + " (Capacity: " +
                               rs.getInt("capacity") + ")";
                venues.add(venue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venues;
    }
}

