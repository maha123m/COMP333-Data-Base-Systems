package com.example.maha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
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

    String x = Data.UName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the column mappings
        FirstNameE.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        TotalSessionE.setCellValueFactory(cellData -> cellData.getValue().totalSessionProperty());
        TotalCostE.setCellValueFactory(cellData -> cellData.getValue().totalCostProperty());

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
            String query = "SELECT t.trainerFirstName, COUNT(s.sessionID) AS sessionCount, SUM(s.sessionCost) AS totalEarnings " +
                    "FROM trainer t " +
                    "JOIN session s ON t.trainerID = s.trainerID " +
                    "WHERE t.trainerID = " + ID + " " +
                    "GROUP BY t.trainerFirstName";
            Statement statement = connectDB.createStatement();
            ResultSet resultSet1 = statement.executeQuery(query);

            ObservableList<EarningInfo> earningsList = FXCollections.observableArrayList();

            while (resultSet1.next()) {
                String firstName = resultSet1.getString("trainerFirstName");
                String totalSession = resultSet1.getString("sessionCount");
                String totalCost = resultSet1.getString("totalEarnings");

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
    
    @FXML
    private Button Home;
    
    @FXML
    private void goToAdminInterface(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TrainerLogin_interface.fxml"));
            Parent root = loader.load();

            Stage adminStage = new Stage();
            adminStage.setTitle("TrainerLogin ");
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
