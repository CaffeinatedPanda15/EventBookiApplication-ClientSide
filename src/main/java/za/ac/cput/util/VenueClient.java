package za.ac.cput.util;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class VenueClient {

        private static final String BASE_URL = "http://localhost:8080/api/venue";

        public String createVenue(String VenueName, String VenueAddress, String VenueDescription,
                                  String VenueCapacity, Double VenuePrice, byte[] VenueImage) throws Exception {
            String encodedImage = Base64.getEncoder().encodeToString(VenueImage);
            String json = String.format(
                    "{ \"VenueName\":\"%s\", \"VenueAddress\":\"%s\", \"VenueDescription\":\"%s\", " +
                            "\"VenueCapacity\":\"%s\", \"VenuePrice\":\"%.2f\", \"VenueImage\":\"%s\"  }",
                    VenueName, VenueAddress, VenueDescription, VenueCapacity,
                    VenuePrice,encodedImage
            );

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201 || response.statusCode() == 200) {
                JSONObject obj = new JSONObject(response.body());
                int VenueId = obj.getInt("venueId");
                String venueName = obj.getString("VenueName");

                String eventStatus;
                if (obj.isNull("status")) {
                    eventStatus = "UNKNOWN";
                } else {
                    eventStatus = obj.getString("status");
                }

                return "Event Created!\nID: " + VenueId + "\nName: " + venueName +
                        "\nStatus: " + eventStatus;
            } else {
                return "Failed to create event.\nStatus: " + response.statusCode() +
                        "\nResponse: " + response.body();
            }
        }

}
