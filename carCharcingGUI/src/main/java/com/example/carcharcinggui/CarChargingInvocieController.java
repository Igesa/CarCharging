package com.example.carcharcinggui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CarChargingInvocieController {
    private static final String API = "http://localhost:8080/invoices";
    @FXML
    public TextField userIDInput;
    @FXML
    private ListView<String> InvoiceDetails;


    public void sendID(ActionEvent actionEvent) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API + userIDInput.getText()))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        userIDInput.setText("");
    }

    public void printInvoice(ActionEvent actionEvent) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        // System.out.println(response.body());
        JSONArray jsonArray = new JSONArray(response.body());

        ObservableList<String> orders = FXCollections.observableArrayList();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            orders.add(jsonObject.toString());
        }

        InvoiceDetails.setItems(orders);
    }
}