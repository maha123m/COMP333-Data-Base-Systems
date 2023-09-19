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

    // Create an observable list to hold the retrieved data


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the column mappings
        FirstNameC.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        LastNameC.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        phone1C.setCellValueFactory(cellData -> cellData.getValue().phone1Property());
        phone2C.setCellValueFactory(cellData -> cellData.getValue().phone2Property());

        // Retrieve data from the database
        try {

            Connection connectDB = Connecter.getConnection();
            String viewquery = "SELECT trainerFirstName, trainerLastName, phone1, phone2 FROM trainer WHERE trainerID = 2";
            Statement statment = connectDB.createStatement();
            ResultSet queryoutput = statment.executeQuery(viewquery);


            ObservableList<ContactInformation> List = FXCollections.observableArrayList();


            while (queryoutput.next()) {
                String firstName = queryoutput.getString("trainerFirstName");
                String lastName = queryoutput.getString("trainerLastName");
                String phone1 = queryoutput.getString("phone1");
                String phone2 = queryoutput.getString("phone2");

                // Create a ContactInformation object and add it to the list
                ContactInformation contactInformation = new ContactInformation(firstName, lastName, phone1, phone2);
                List.add(contactInformation);
            }

            // Set the table data
            ContactView.setItems(List);

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions
        }
    }
}


