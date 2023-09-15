package com.example.maha;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
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

    String x = Data.UName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the column mappings
        FirstNameP.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        LastNameP.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        AmountP.setCellValueFactory(cellData -> cellData.getValue().amountProperty());

     
        // Retrieve data from the database
        try {
        	 Connection connectDB1 = Connecter.getConnection();
             String viewquery1 = "SELECT trainerID FROM userAccounts WHERE Username ='" + x + "'";
             System.out.println(viewquery1);
             Statement statement1 = connectDB1.createStatement();
             ResultSet resultSet = statement1.executeQuery(viewquery1);
             int ID = 0;
             while (resultSet.next()) {
                 Object value = resultSet.getObject("trainerID");
                 if (value instanceof Integer) {
                     int trainerId = (int) value;
                     System.out.println("Trainer ID: " + trainerId);
                     ID = trainerId;
                 } else {
                     System.out.println("Invalid Trainer ID");
                 }
             }

            Connection connectDB = Connecter.getConnection();
          
            
            String query = "SELECT s.studentFirstName, s.studentLastName, p.amount " +
                    "FROM students s " +
                    "JOIN payment p ON s.studentId = p.StudentId " +
                    "JOIN student_trainer st ON s.studentId = st.studentId " +
                    "WHERE st.trainerId = " + ID + " AND p.Pstatus = 'Pending'";

            

            Statement statement = connectDB.createStatement();
            ResultSet resultSet1 = statement.executeQuery(query);

            ObservableList<PendingInfo> paymentList = FXCollections.observableArrayList();

            while (resultSet1.next()) {
                String firstName = resultSet1.getString("studentFirstName");
                String lastName = resultSet1.getString("studentLastName");
                String amount = resultSet1.getString("amount");

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
    
    @FXML
    private Button Home;
    
    @FXML
    private void goToAdminInterface(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TrainerLogin_interface.fxml"));
            Parent root = loader.load();

            Stage adminStage = new Stage();
            adminStage.setTitle("Admin Interface");
            adminStage.setScene(new Scene(root));
            adminStage.show();

         // Close the vehicle_interface
            Stage vehicleStage = (Stage) Home.getScene().getWindow();
            vehicleStage.close();

           
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
