package com.example.maha;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Trainercontroller implements Initializable {







    //  interface contents ------->

    // table :


    @FXML
    TableView<Trainer> trainertableView = new TableView<>();

    @FXML
    TableColumn<Trainer, Integer> trainerID;
    @FXML
    TableColumn<Trainer, String> trainerFirstName ;
    @FXML
    TableColumn<Trainer, String> trainerLastName ;
    @FXML
    TableColumn<Trainer, String> streetAddress;
    @FXML
    TableColumn<Trainer, String> cityAddress ;
    @FXML
    TableColumn<Trainer, Integer> numberOfVehiclesOwns;
    @FXML
    TableColumn<Trainer, Integer> phone1;
    @FXML
    TableColumn<Trainer, Integer> phone2;







    // Buttons:
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;

    // textBoxes

    @FXML
    private TextField cityaddress;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField nofvhicles;

    @FXML
    private TextField phone11;

    @FXML
    private TextField phone22;

    @FXML
    private TextField streetaddress;

    @FXML
    private TextField trainerIDd;


    ObservableList <Trainer> listM;

    int index = -1;

    Connection conn = null;

    ResultSet rs = null;

    PreparedStatement pst = null ;

    @FXML
    private TextField searchTextField;



    ObservableList<Trainer> list = FXCollections.observableArrayList();

    public void initialize (URL url , ResourceBundle rb) {



        // Event handler for selecting a row
        trainertableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Trainer selectedTrainer = trainertableView.getSelectionModel().getSelectedItem();

                // Fill the text fields with the selected trainer's information
                trainerIDd.setText(String.valueOf(selectedTrainer.getTrainerID()));
                firstname.setText(selectedTrainer.getTrainerFirstName());
                lastname.setText(selectedTrainer.getTrainerLastName());
                nofvhicles.setText(String.valueOf(selectedTrainer.getNumberOfVehiclesOwns()));
                cityaddress.setText(selectedTrainer.getCityAddress());
                streetaddress.setText(selectedTrainer.getStreetAddress());
                phone11.setText(String.valueOf(selectedTrainer.getPhone1()));
                phone22.setText(String.valueOf(selectedTrainer.getPhone2()));
            }
        });

        Connecter conn = new Connecter() ;
        try {
            Connection connectDB = conn.getConnection();
            String viewquery = "SELECT trainerID,trainerFirstName,trainerLastName,numberOfVehiclesOwns,cityAddress,streetAddress,phone1,phone2 FROM trainer";
            Statement statment = connectDB.createStatement();
            ResultSet queryoutput = statment.executeQuery(viewquery);

            while (queryoutput.next()) {


                Integer queryID = queryoutput.getInt("trainerID");
                String firstName = queryoutput.getString("trainerFirstName");
                String lastName = queryoutput.getString("trainerLastName");
                Integer numVehicles = queryoutput.getInt("numberOfVehiclesOwns");
                String city = queryoutput.getString("cityAddress");
                String street = queryoutput.getString("streetAddress");
                Integer phone1Value = queryoutput.getInt("phone1");
                Integer phone2Value = queryoutput.getInt("phone2");

                Trainer trainer = new Trainer(queryID, firstName, lastName, numVehicles, city, street, phone1Value, phone2Value);
                list.add(trainer);




            }

            trainerID.setCellValueFactory(new PropertyValueFactory<>("trainerID"));
            trainerFirstName.setCellValueFactory(new PropertyValueFactory<>("trainerFirstName"));
            trainerLastName.setCellValueFactory(new PropertyValueFactory<>("trainerLastName"));
            numberOfVehiclesOwns.setCellValueFactory(new PropertyValueFactory<>("numberOfVehiclesOwns"));
            cityAddress.setCellValueFactory(new PropertyValueFactory<>("cityAddress"));
            streetAddress.setCellValueFactory(new PropertyValueFactory<>("streetAddress"));
            phone1.setCellValueFactory(new PropertyValueFactory<>("phone1"));
            phone2.setCellValueFactory(new PropertyValueFactory<>("phone2"));

            trainertableView.setItems(list);

            // Create a filtered list to hold the filtered data
            FilteredList<Trainer> filteredList = new FilteredList<>(list);

            // Set the filter predicate for the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(trainer -> {
                    // If the search text is empty, show all trainers
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Convert search text to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if the trainer's first name contains the search text
                    if (trainer.getTrainerFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false; // Does not match the search text
                });
            });

            // Wrap the filtered list in a sorted list to enable sorting
            SortedList<Trainer> sortedList = new SortedList<>(filteredList);

            // Bind the sorted list to the table view
            sortedList.comparatorProperty().bind(trainertableView.comparatorProperty());
            trainertableView.setItems(sortedList);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Logger.getLogger(Trainercontroller.class.getName()).log(Level.SEVERE,null,e1);
        }
    }


    // add trainner button
    @FXML
    private void addTrainer(ActionEvent event) {
        // Get the values from the text fields
        int trainerID = Integer.parseInt(trainerIDd.getText());
        String firstName = firstname.getText();
        String lastName = lastname.getText();
        int numVehicles = Integer.parseInt(nofvhicles.getText());
        String city = cityaddress.getText();
        String street = streetaddress.getText();
        int phone1Value = Integer.parseInt(phone11.getText());
        int phone2Value = Integer.parseInt(phone22.getText());

        // Create a Trainer object with the entered values
        Trainer trainer = new Trainer(trainerID, firstName, lastName, numVehicles, city, street, phone1Value, phone2Value);

        // Insert the trainer into the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Check if the trainer ID already exists in the table
            String checkQuery = "SELECT COUNT(*) FROM trainer WHERE trainerID = ?";
            PreparedStatement checkStatement = connectDB.prepareStatement(checkQuery);
            checkStatement.setInt(1, trainer.getTrainerID());
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                // Display a warning message if the ID already exists
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Duplicate Trainer ID");
                alert.setHeaderText("Warning: Duplicate Trainer ID");
                alert.setContentText("The entered Trainer ID already exists in the database. Please enter a unique ID.");
                alert.showAndWait();
            } else {
                // Insert the trainer into the table
                String insertQuery = "INSERT INTO trainer (trainerID, trainerFirstName, trainerLastName, numberOfVehiclesOwns, cityAddress, streetAddress, phone1, phone2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connectDB.prepareStatement(insertQuery);
                statement.setInt(1, trainer.getTrainerID());
                statement.setString(2, trainer.getTrainerFirstName());
                statement.setString(3, trainer.getTrainerLastName());
                statement.setInt(4, trainer.getNumberOfVehiclesOwns());
                statement.setString(5, trainer.getCityAddress());
                statement.setString(6, trainer.getStreetAddress());
                statement.setInt(7, trainer.getPhone1());
                statement.setInt(8, trainer.getPhone2());
                statement.executeUpdate();

                // Add the trainer to the table view
                list.add(trainer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(Trainercontroller.class.getName()).log(Level.SEVERE, null, e);
        }
    }




    // update trainner button
    @FXML
    private void updateTrainer(ActionEvent event) {
        // Check if a trainer is selected in the table
        Trainer selectedTrainer = trainertableView.getSelectionModel().getSelectedItem();
        if (selectedTrainer == null) {
            //   warningLabel.setText("Warning: No trainer selected!");
            return;
        }

        // Get the updated values from the text fields
        String firstName = firstname.getText();
        String lastName = lastname.getText();
        int numVehicles = Integer.parseInt(nofvhicles.getText());
        String city = cityaddress.getText();
        String street = streetaddress.getText();
        int phone1Value = Integer.parseInt(phone11.getText());
        int phone2Value = Integer.parseInt(phone22.getText());

        // Update the trainer object with the new values
        selectedTrainer.setTrainerFirstName(firstName);
        selectedTrainer.setTrainerLastName(lastName);
        selectedTrainer.setNumberOfVehiclesOwns(numVehicles);
        selectedTrainer.setCityAddress(city);
        selectedTrainer.setStreetAddress(street);
        selectedTrainer.setPhone1(phone1Value);
        selectedTrainer.setPhone2(phone2Value);

        // Update the trainer in the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Update the trainer's information
            String updateQuery = "UPDATE trainer SET trainerFirstName = ?, trainerLastName = ?, numberOfVehiclesOwns = ?, cityAddress = ?, streetAddress = ?, phone1 = ?, phone2 = ? WHERE trainerID = ?";
            PreparedStatement statement = connectDB.prepareStatement(updateQuery);
            statement.setString(1, selectedTrainer.getTrainerFirstName());
            statement.setString(2, selectedTrainer.getTrainerLastName());
            statement.setInt(3, selectedTrainer.getNumberOfVehiclesOwns());
            statement.setString(4, selectedTrainer.getCityAddress());
            statement.setString(5, selectedTrainer.getStreetAddress());
            statement.setInt(6, selectedTrainer.getPhone1());
            statement.setInt(7, selectedTrainer.getPhone2());
            statement.setInt(8, selectedTrainer.getTrainerID());
            statement.executeUpdate();

            // Refresh the table view
            trainertableView.refresh();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(Trainercontroller.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // delete trainner button
    @FXML
    private void deleteTrainer(ActionEvent event) {
        // Get the trainer ID from the text field
        int trainerID = Integer.parseInt(trainerIDd.getText());

        // Find the trainer in the list
        Trainer trainerToRemove = null;
        for (Trainer trainer : list) {
            if (trainer.getTrainerID() == trainerID) {
                trainerToRemove = trainer;
                break;
            }
        }

        if (trainerToRemove != null) {
            // Remove the trainer from the list
            list.remove(trainerToRemove);

            // Delete the trainer from the database
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();

                // Delete the trainer from the table using the trainer ID
                String deleteQuery = "DELETE FROM trainer WHERE trainerID = ?";
                PreparedStatement statement = connectDB.prepareStatement(deleteQuery);
                statement.setInt(1, trainerID);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                Logger.getLogger(Trainercontroller.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            // Display a warning message if the trainer was not found
            // warningLabel.setText("Warning: Trainer not found!");
        }
    }








}



















// delete trainner button