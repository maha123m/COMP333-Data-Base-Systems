package com.example.maha;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TrainerContactQ implements Initializable {
    @FXML
    TableView<ContactInformation> ContactView = new TableView<>();

    @FXML
    private TableColumn<ContactInformation, String> FirstNameC;

    @FXML
    private TableColumn<ContactInformation, String> LastNameC;

    @FXML
    private TableColumn<ContactInformation, String> phone1C;

    @FXML
    private TableColumn<ContactInformation, String> phone2C;
    String x = Data.UName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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
            String viewquery = "SELECT trainerFirstName, trainerLastName, phone1, phone2 FROM trainer WHERE trainerID =" + ID ;
            Statement statment = connectDB.createStatement();
            ResultSet queryoutput = statment.executeQuery(viewquery);

            System.out.println(queryoutput);





            ObservableList<ContactInformation> List = FXCollections.observableArrayList();

            while (queryoutput.next()) {
                String firstName = queryoutput.getString("trainerFirstName");
                String lastName = queryoutput.getString("trainerLastName");
                String phone1 = queryoutput.getString("phone1");
                String phone2 = queryoutput.getString("phone2");

                ContactInformation contactInformation = new ContactInformation(firstName, lastName, phone1, phone2);
                List.add(contactInformation);
            }

            // Set the column mappings
            FirstNameC.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            LastNameC.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            phone1C.setCellValueFactory(new PropertyValueFactory<>("phone1"));
            phone2C.setCellValueFactory(new PropertyValueFactory<>("phone2"));

            ContactView.setItems(List);

        } catch (SQLException e) {
            e.printStackTrace();
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