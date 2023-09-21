package com.example.maha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AvailableTimeInfoQ implements Initializable {
    @FXML
    TableView<AvailableTimeInfo> AvailableTimeView = new TableView<>();

    @FXML
    private TableColumn<AvailableTimeInfo, String> Time;

    @FXML
    private TableColumn<AvailableTimeInfo, String> Day;

    @FXML
    private TableColumn<AvailableTimeInfo, String> Date;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the column mappings
        Time.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        Day.setCellValueFactory(cellData -> cellData.getValue().dayProperty());
        Date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        // Retrieve data from the database
        try {
            Connection connectDB = Connecter.getConnection();
            String query = "SELECT t.avalableDay, t.avalableTime, s.sessionDate " +
                    "FROM trainerAvalibility AS t " +
                    "JOIN session AS s ON t.trainerID = s.trainerID " +
                    "WHERE t.trainerID = 1";
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ObservableList<AvailableTimeInfo> availableTimeList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                String time = resultSet.getString("avalableTime");
                String day = resultSet.getString("avalableDay");
                String date = resultSet.getString("sessionDate");

                // Create an AvailableTimeInfo object and add it to the list
                AvailableTimeInfo availableTimeInfo = new AvailableTimeInfo(time, day, date);
                availableTimeList.add(availableTimeInfo);
            }

            // Set the table data
            AvailableTimeView.setItems(availableTimeList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions
        }
    }
}
