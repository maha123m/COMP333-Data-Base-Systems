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

public class EarningInfoQ implements Initializable {
    @FXML
    TableView<EarningInfo> EarningsView = new TableView<>();

    @FXML
    private TableColumn<EarningInfo, String> FirstNameE;

    @FXML
    private TableColumn<EarningInfo, String> TotalSessionE;

    @FXML
    private TableColumn<EarningInfo, String> TotalCostE;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the column mappings
        FirstNameE.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        TotalSessionE.setCellValueFactory(cellData -> cellData.getValue().totalSessionProperty());
        TotalCostE.setCellValueFactory(cellData -> cellData.getValue().totalCostProperty());

        // Retrieve data from the database
        try {
            Connection connectDB = Connecter.getConnection();
            String query = "SELECT t.trainerFirstName, COUNT(s.sessionID) AS sessionCount, SUM(s.sessionCost) AS totalEarnings " +
                    "FROM trainer t " +
                    "JOIN session s ON t.trainerID = s.trainerID " +
                    "WHERE t.trainerID = 1 " +
                    "GROUP BY t.trainerFirstName";
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ObservableList<EarningInfo> earningsList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                String firstName = resultSet.getString("trainerFirstName");
                String totalSession = resultSet.getString("sessionCount");
                String totalCost = resultSet.getString("totalEarnings");

                // Create an EarningsInfo object and add it to the list
                EarningInfo earningsInfo = new EarningInfo(firstName, totalSession, totalCost);
                earningsList.add(earningsInfo);
            }

            // Set the table data
            EarningsView.setItems(earningsList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions
        }
    }
}
