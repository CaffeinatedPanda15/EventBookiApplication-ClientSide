package za.ac.cput.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EventClient {

    private static final String BASE_URL = "http://localhost:8080/api/events";

    public static String createEvent(String category, String time, String date, String description, String name) throws Exception {
        String json = String.format("{ \"category\":\"%s\", \"eventTime\"" +
                        ":\"%s\", " + "\"eventDate\":\"%s\", " +
                        "\"eventDescription\":\"%s\", \"eventName\":\"%s\" }",
                category, time, date, description, name
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201 || response.statusCode() == 200) {
            return " Event created successfully!\nResponse: " + response.body();
        } else {
            return " Failed to create event.\nStatus: " + response.statusCode() + "\nResponse: " + response.body();
        }
    }

}
