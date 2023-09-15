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

public class StudentInfoQ implements Initializable {
    @FXML
    TableView<StudentInfoQuery> NamesView = new TableView<>();

    @FXML
    private TableColumn<StudentInfoQuery, String> FirstNameS;

    @FXML
    private TableColumn<StudentInfoQuery, String> LastNameS;

    @FXML
    private TableColumn<StudentInfoQuery, String> CityS;

    @FXML
    private TableColumn<StudentInfoQuery, String> StreetS;

    @FXML
    private TableColumn<StudentInfoQuery, String> PhoneS;

    @FXML
    private TableColumn<StudentInfoQuery, String> WalletS;

    String x = Data.UName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the column mappings
        FirstNameS.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        LastNameS.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        CityS.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        StreetS.setCellValueFactory(cellData -> cellData.getValue().streetProperty());
        PhoneS.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        WalletS.setCellValueFactory(cellData -> cellData.getValue().walletProperty());

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
            String query = "SELECT s.studentFirstName, s.studentLastName, s.cityAddress, s.streetAddress, s.phone1, s.wallet " +
                    "FROM students s JOIN student_trainer st ON s.studentId = st.studentId " +
                    "WHERE st.trainerId = " + ID;
            Statement statement = connectDB.createStatement();
            ResultSet resultSet1 = statement.executeQuery(query);

            ObservableList<StudentInfoQuery> NamesList = FXCollections.observableArrayList();

            while (resultSet1.next()) {
                String firstName = resultSet1.getString("studentFirstName");
                String lastName = resultSet1.getString("studentLastName");
                String city = resultSet1.getString("cityAddress");
                String street = resultSet1.getString("streetAddress");
                String phone = resultSet1.getString("phone1");
                String wallet = resultSet1.getString("wallet");

                // Create a StudentInformation object and add it to the list
                StudentInfoQuery studentInfo = new StudentInfoQuery(firstName, lastName, city, street, phone, wallet);
                NamesList.add(studentInfo);
            }

            // Set the table data
            NamesView.setItems(NamesList);
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

