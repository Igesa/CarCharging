package SpringBoot.RestApi.controller;

import SpringBoot.RestApi.dto.Invoice;
import SpringBoot.RestApi.queue.Producer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceController {

    private final static String BROKER_URL = "tcp://localhost:6616";
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/dist?user=distuser&password=distpw";

    @PostMapping("/invoices/{type}/{name}")
    public Invoice create(@PathVariable String type, @PathVariable String name) {

        Producer.send(name,type.toUpperCase(),BROKER_URL);

        return new Invoice(type, name);
    }
    @GetMapping("/invoices")
    public List<Invoice> readAll() {
        List<Invoice> invoices = new ArrayList<>();

        try (Connection conn = connect()) {
            String sql = "SELECT * FROM invoices";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Invoice invoice = new Invoice();

                invoice.invoiceID = resultSet.getString("invoiceID");
                invoice.StationID = Integer.parseInt(resultSet.getString("StationID"));
                invoice.kWH = Integer.parseInt(resultSet.getString("status"));

                invoices.add(invoice);
            }
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return invoices;
    }
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION);
    }
}
