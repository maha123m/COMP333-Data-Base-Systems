package com.example.maha;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import javax.sql.rowset.CachedRowSet;

import javafx.fxml.FXML;

public class StudentRadio{
	
	
    @FXML
    private Button paymentquery;
    
    @FXML
    private Button contactquery;
    
    @FXML
    private Button testquery;


    private int studentID; // Global variable for studentID

    public void StudentRadioButton(Button cancelButton, int st) {
        try {
            studentID = st; // Update the value of studentID with st

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainStudent.fxml"));
            Parent root = loader.load();

            Stage adminStage = new Stage();
            adminStage.setTitle("Student Interface");
            adminStage.setScene(new Scene(root));
            adminStage.show();

            // Close the login interface
            Stage loginStage = (Stage) cancelButton.getScene().getWindow();
            loginStage.close();

            // Call the displayNotification method with the student ID
            displayNotification(studentID);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void displayNotification(int studentID) {
        Connecter conn = new Connecter();

        try {
            Connection connectDB = conn.getConnection();

            // Check if the student ID exists in the studentAssignment table
            String checkQuery = "SELECT * FROM studentAssignment WHERE studentID = ?";

            PreparedStatement checkStatement = connectDB.prepareStatement(checkQuery);
            checkStatement.setInt(1, studentID);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                // Display an alert with the new trainer information
                String newTrainerFirstName = resultSet.getString("newTrainerFirstName");
                String newTrainerLastName = resultSet.getString("newTrainerLastName");

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("New Trainer Assigned");
                alert.setContentText("Your new trainer is: " + newTrainerFirstName + " " + newTrainerLastName);
                alert.showAndWait();

                // Delete the student record from the studentAssignment table
                String deleteQuery = "DELETE FROM studentAssignment WHERE studentID = ?";
                PreparedStatement deleteStatement = connectDB.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, studentID);
                deleteStatement.executeUpdate();
            }

            resultSet.close();
            checkStatement.close();
            connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void scheduleSession(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the new interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/schedulesession_interface.fxml"));
            Parent root = loader.load();

            // Create a new stage for the new interface
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();

            // Close the current stage
            Button scheduleButton = (Button) actionEvent.getSource();
            Stage adminStage = (Stage) scheduleButton.getScene().getWindow();
            adminStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void PaymentReportOnAction(ActionEvent event) {
        // Add your custom code or method calls here
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();
            PreparedStatement statement = connectDB.prepareStatement("SELECT * FROM payment WHERE StudentId = ?");

            statement.setInt(1, SampleController.studentid); // Use the studentID parameter

            ResultSet resultSet = statement.executeQuery();

            // Create a CachedRowSet and populate it with the data from the ResultSet
            CachedRowSet cachedRowSet = javax.sql.rowset.RowSetProvider.newFactory().createCachedRowSet();
            cachedRowSet.populate(resultSet);

            // Create an alert dialog
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Payment Report");
            alert.setHeaderText(null);

            StringBuilder data = new StringBuilder();

            // Append the sum of payments
            int sum = 0;
            while (cachedRowSet.next()) {
                int amount = cachedRowSet.getInt("amount");
                sum += amount;
            }
            data.append("Total Payments: ").append(sum).append("\n\n");

            // Append the column headers
            data.append(String.format("%-20s   %-20s   %-12s   %-15s   %-15s   %-20s\n",
                    "Payment ID", "Student ID", "Amount", "Status", "Method", "Date"));

            // Append the separator line
            data.append("-------------------------------------------------------------------------------------------------------------\n");

            // Reset the CachedRowSet cursor to the beginning
            cachedRowSet.beforeFirst();

            // Iterate over the CachedRowSet and append the data
            while (cachedRowSet.next()) {
                int paymentID = cachedRowSet.getInt("Pid");
                int studentId = cachedRowSet.getInt("StudentId");
                int amount = cachedRowSet.getInt("amount");
                String status = cachedRowSet.getString("Pstatus");
                String method = cachedRowSet.getString("Pmethood");
                Date date = cachedRowSet.getDate("Pdate");

                // Append the formatted data
                data.append(String.format("%-20d          %-20d       %-12d       %-15s       %-15s      %-20s\n",
                        paymentID, studentId, amount, status, method, date));
            }

            // Set the content text of the alert dialog
            alert.setContentText(data.toString());

            // Get the dialog pane
            DialogPane dialogPane = alert.getDialogPane();

            // Increase the width of the dialog pane
            dialogPane.setMinWidth(800);
            dialogPane.setMaxWidth(800);

            // Show the alert dialog
            alert.showAndWait();

            // Close the database connections
            resultSet.close();
            statement.close();
            connectDB.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any exceptions that may occur during the database operations
        }
    }
    
    
    @FXML
    private void contactQueryOnAction(ActionEvent event) {
        // Add your custom code or method calls here
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();
            PreparedStatement statement = connectDB.prepareStatement("SELECT t.phone1, t.phone2 FROM trainer t JOIN student_trainer st ON t.trainerID = st.trainerID WHERE st.studentID = ?");

            int studentID = SampleController.studentid;

            statement.setInt(1, studentID);

            ResultSet resultSet = statement.executeQuery();

            // Create an alert dialog
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Student Trainer Contacts");
            alert.setHeaderText(null);

            StringBuilder data = new StringBuilder();

            // Iterate over the result set and append the phone numbers
            while (resultSet.next()) {
                String phone1 = resultSet.getString("t.phone1");
                String phone2 = resultSet.getString("t.phone2");

                // Append the phone numbers
                data.append("Phone 1: ").append(phone1).append("\n");
                if (phone2 != null) {
                    data.append("Phone 2: ").append(phone2).append("\n");
                }
            }

            // Set the content text of the alert dialog
            alert.setContentText(data.toString());

            // Show the alert dialog
            alert.showAndWait();

            // Close the result set and statement
            resultSet.close();
            statement.close();

            // Close the database connection
            connectDB.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any exceptions that may occur during the database operations
        }
    }
    
    
    @FXML
    private void TestReportOnAction(ActionEvent event) {
        // Add your custom code or method calls here
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Test WHERE StudentId = " + SampleController.studentid);

            // Create an alert dialog
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Test Report");
            alert.setHeaderText(null);

            StringBuilder data = new StringBuilder();

            // Append the column headers
            data.append(String.format("%-12s | %-12s | %-12s | %-15s\n",
                    "Test ID", "Student ID", "Result", "Date"));

            // Append the line between columns
            data.append("--------------------------------------------------------------\n");

            // Iterate over the result set and append the data
            while (resultSet.next()) {
                int testID = resultSet.getInt("Tid");
                int studentId = resultSet.getInt("StudentId");
                String resultValue = resultSet.getString("Tresult");
                Date date = resultSet.getDate("Pdate");

                // Append the formatted data
                data.append(String.format("%-12d | %-12d | %-12s | %-15s\n",
                        testID, studentId, resultValue, date));
            }

            // Set the content text of the alert dialog
            alert.setContentText(data.toString());

            // Show the alert dialog
            alert.showAndWait();

            // Close the database connections
            resultSet.close();
            statement.close();
            connectDB.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any exceptions that may occur during the database operations
        }
    }
}
