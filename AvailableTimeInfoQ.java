package com.example.maha;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AvailableTimeInfoQ implements Initializable {
    @FXML
    TableView<AvailableTimeInfo> AvailableTimeView = new TableView<>();

    @FXML
    private TableColumn<AvailableTimeInfo, String> Time;

    @FXML
    private TableColumn<AvailableTimeInfo, String> Day;

    @FXML
    private Button AddTime;
    @FXML
    private TextField InsertTime;
    @FXML
    private TextField InsterDay;
    @FXML
    private TextField TrainerID;


    String x = Data.UName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the column mappings
        Time.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        Day.setCellValueFactory(cellData -> cellData.getValue().dayProperty());

        // Retrieve data from the database
        try {

            Connection connectDB1 = Connecter.getConnection();
            String viewquery1 = "select trainerID FROM userAccounts WHERE Username ='" + x +"'";
            Statement statment1 = connectDB1.createStatement();
            ResultSet RES = statment1.executeQuery(viewquery1);
            int ID =0;
            while (RES.next()) {
                Object value = RES.getObject("trainerID");
                if (value instanceof Integer) {
                    int trainerId = (int) value;
                    System.out.println("Trainer ID: " + trainerId);
                    ID = trainerId;
                } else {
                    System.out.println("Invalid Trainer ID");
                }
            }

            Connection connectDB = Connecter.getConnection();
            String query = "SELECT t.avalableDay, t.avalableTime " +
                    "FROM trainerAvalibility AS t " +
                    "WHERE t.trainerID = " + ID;
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ObservableList<AvailableTimeInfo> availableTimeList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                String time = resultSet.getString("avalableTime");
                String day = resultSet.getString("avalableDay");

                // Create an AvailableTimeInfo object and add it to the list
                AvailableTimeInfo availableTimeInfo = new AvailableTimeInfo(time , day);
                availableTimeList.add(availableTimeInfo);
            }

            // Set the table data
            AvailableTimeView.setItems(availableTimeList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions
        }
    }
    @FXML
    private void AddTimeOnAction(ActionEvent event) {
        int ID = Integer.parseInt(TrainerID.getText());
        String time = InsertTime.getText();
        String day = InsterDay.getText();

        try {
            // Check if the time and day combination already exists in the database
            Connection connectDB = Connecter.getConnection();
            String checkQuery = "SELECT COUNT(*) FROM trainerAvalibility WHERE trainerID = ? AND avalableTime = ? AND avalableDay = ?";
            PreparedStatement checkStatement = connectDB.prepareStatement(checkQuery);
            checkStatement.setInt(1, ID);
            checkStatement.setString(2, time);
            checkStatement.setString(3, day);
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                // Display a warning message if the combination already exists
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Duplicate Time and Day");
                alert.setHeaderText("Warning: Duplicate Time and Day");
                alert.setContentText("The entered time and day combination already exists in the database. Please enter a unique combination.");
                alert.showAndWait();
            } else {
                // Insert the new available time into the database
                String insertQuery = "INSERT INTO trainerAvalibility (trainerID, avalableTime, avalableDay) VALUES (?, ?, ?)";
                PreparedStatement statement = connectDB.prepareStatement(insertQuery);
                statement.setInt(1, ID);
                statement.setString(2, time);
                statement.setString(3, day);
                statement.executeUpdate();

                // Create an AvailableTimeInfo object with the entered values
                AvailableTimeInfo availableTimeInfo = new AvailableTimeInfo(time, day);

                // Add the new available time to the table view
                AvailableTimeView.getItems().add(availableTimeInfo);

                // Display a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Available Time Added");
                alert.setHeaderText("Success: Available Time Added");
                alert.setContentText("The available time has been added successfully.");
                alert.showAndWait();
            }
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
