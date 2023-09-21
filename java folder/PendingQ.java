package com.example.maha;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;

public class PendingQ implements Initializable  {
    @FXML
    TableView<PendingInfo> PaymentView = new TableView<>();

    @FXML
    private TableColumn<PendingInfo, String> FirstNameP;

    @FXML
    private TableColumn<PendingInfo, String> LastNameP;

    @FXML
    private TableColumn<PendingInfo, String> AmountP;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the column mappings
        FirstNameP.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        LastNameP.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        AmountP.setCellValueFactory(cellData -> cellData.getValue().amountProperty());

        // Retrieve data from the database
        try {
            Connection connectDB = Connecter.getConnection();
            String query = "SELECT s.studentFirstName, s.studentLastName, p.amount " +
                    "FROM students s " +
                    "JOIN payment p ON s.studentId = p.StudentId " +
                    "JOIN session ses ON s.studentId = ses.studentID " +
                    "WHERE ses.trainerID = 3 AND p.Pstatus = 'Pending'";
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ObservableList<PendingInfo> paymentList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                String firstName = resultSet.getString("studentFirstName");
                String lastName = resultSet.getString("studentLastName");
                String amount = resultSet.getString("amount");

                // Create a PaymentInfo object and add it to the list
                PendingInfo paymentInfo = new PendingInfo(firstName, lastName, amount);
                paymentList.add(paymentInfo);
            }

            // Set the table data
            PaymentView.setItems(paymentList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions
        }
    }
}
