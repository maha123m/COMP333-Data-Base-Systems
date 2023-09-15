package com.example.maha;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LicenseController implements Initializable {

    //  interface contents ------->

    @FXML
    TableView<License> LicenseTableview = new TableView<>();

    @FXML
    TableColumn<License, Integer> licenseId;
    @FXML
    TableColumn<License, Integer> studentId ;
    @FXML
    TableColumn<License, String> licensetype ;
    /////////////////////////////////////////////

    // Buttons:
    @FXML
    private Button addLicense;
    @FXML
    private Button deleteLicense;
    @FXML
    private Button updateLicense;

    @FXML
    private Button Back;

    /////////////////////////////////////////////////////////////////////////////////////
    // textBoxes
    @FXML
    private TextField  licenseID;

    @FXML
    private TextField studentID;


    ///////////////////////////////////////////////////////////////////////////

    //combo box
    @FXML
    private  ComboBox LicenseType;
    ////////////////////////////////////////////////////////////////////////////
    ObservableList <License> listM;

    int index = -1;

    Connection conn = null;

    ResultSet rs = null;

    PreparedStatement pst = null ;

    @FXML
    private TextField searchTextField;

    ObservableList<License> list = FXCollections.observableArrayList();


    public void initialize (URL url , ResourceBundle rb) {

        // Event handler for selecting a row
        LicenseTableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                License selectedLicense = LicenseTableview.getSelectionModel().getSelectedItem();

                // Fill the text fields with the selected license information
                licenseID.setText(String.valueOf(selectedLicense.getLicenseId()));
                studentID.setText(String.valueOf(selectedLicense.getStudentId()));
                LicenseType.setValue(selectedLicense.getLicensetype());

            }
        });
        Connecter conn = new Connecter() ;
        try {
            Connection connectDB = conn.getConnection();
            String viewquery = "SELECT licenseId,studentId,licensetype FROM license";
            Statement statment = connectDB.createStatement();
            ResultSet queryoutput = statment.executeQuery(viewquery);

            while (queryoutput.next()) {

                Integer queryID = queryoutput.getInt("licenseId");
                Integer Sid = queryoutput.getInt("studentId");
                String Ltype = queryoutput.getString("licensetype");

                License license = new License(queryID, Sid, Ltype);
                list.add(license);
            }

            licenseId.setCellValueFactory(new PropertyValueFactory<>("licenseId"));
            studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
            licensetype.setCellValueFactory(new PropertyValueFactory<>("licensetype"));

            // value of combo box
            LicenseType.getItems().addAll("Car", "Motorcycle","Truck");

            LicenseTableview.setItems(list);

            // Create a filtered list to hold the filtered data
            FilteredList<License> filteredList = new FilteredList<>(list);

            // Set the filter predicate for the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(license -> {
                    // If the search text is empty, show all payment
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Convert search text to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if the payment's first name contains the search text
                    if (license.getLicensetype().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false; // Does not match the search text
                });
            });

            // Wrap the filtered list in a sorted list to enable sorting
            SortedList<License> sortedList = new SortedList<>(filteredList);

            // Bind the sorted list to the table view
            sortedList.comparatorProperty().bind(LicenseTableview.comparatorProperty());
            LicenseTableview.setItems(sortedList);


        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE,null,e1);
        }
    }

    ///////////////////////////////////////////
    //add a License

    @FXML
    private void addLicenseOnAction(ActionEvent event) {
        // Get the values from the text fields

        int lID = Integer.parseInt(licenseID.getText());
        int sID = Integer.parseInt(studentID.getText());
        String Ltype = LicenseType.getValue().toString();


        // Create a License object with the entered values

        License license = new License(lID, sID, Ltype);

        // Insert the license into the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Check if the payment ID already exists in the table
            String checkQuery = "SELECT COUNT(*) FROM license WHERE licenseID = ?";
            PreparedStatement checkStatement = connectDB.prepareStatement(checkQuery);
            checkStatement.setInt(1, license.getLicenseId());
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                // Display a warning message if the ID already exists
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Duplicate License ID");
                alert.setHeaderText("Warning: Duplicate License ID");
                alert.setContentText("The entered License ID already exists in the database. Please enter a unique ID.");
                alert.showAndWait();
            } else {
                // Insert the License into the table
                String insertQuery = "INSERT INTO license (licenseID, studentID, LicenseType) VALUES (?, ?, ?)";
                PreparedStatement statement = connectDB.prepareStatement(insertQuery);
                statement.setInt(1, license.getLicenseId());
                statement.setInt(2, license.getStudentId());
                statement.setString(3, license.getLicensetype());
                statement.executeUpdate();

                // Add the license to the table view
                list.add(license);

                // Display a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("License  Added");
                alert.setHeaderText("Success: License Added");
                alert.setContentText("The License has been added successfully.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //////////////////////
    //update license button
    @FXML
    private void updateLicenseOnAction(ActionEvent event) {
        // Check if a payment is selected in the table
        License selectedLicense = LicenseTableview.getSelectionModel().getSelectedItem();
        if (selectedLicense == null) {
            // Display a warning message if no License is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No License Selected");
            alert.setHeaderText("Warning: No License selected!");
            alert.setContentText("Please select a License from the table.");
            alert.showAndWait();
            return;
        }

        // Get the updated values from the text fields
        int lId = Integer.parseInt(licenseID.getText());
        int sId = Integer.parseInt(studentID.getText());
        String lType = LicenseType.getValue().toString();

        // Update the selected license object with the new values
        selectedLicense.setLicenseId(lId);
        selectedLicense.setStudentId(sId);
        selectedLicense.setLicensetype(lType);

        // Update the license in the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Update the payment information
            String updateQuery = "UPDATE license SET licenseId = ?, studentId = ?, licensetype = ? WHERE licenseId = ?";
            PreparedStatement statement = connectDB.prepareStatement(updateQuery);
            statement.setInt(1, selectedLicense.getLicenseId());
            statement.setInt(2, selectedLicense.getStudentId());
            statement.setString(3, selectedLicense.getLicensetype());
            statement.setInt(4, selectedLicense.getLicenseId());
            statement.executeUpdate();


            // Refresh the table view
            LicenseTableview.refresh();

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("License Updated");
            alert.setHeaderText("Success: License Updated");
            alert.setContentText("The License has been updated successfully.");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    @FXML
    private void deleteLicenseOnAction(ActionEvent event) {
        // Check if a student is selected in the table
        License selectedLicense = LicenseTableview.getSelectionModel().getSelectedItem();
        if (selectedLicense == null) {
            // Display a warning message if no License is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No License Selected");
            alert.setHeaderText("Warning: No License selected!");
            alert.setContentText("Please select a license from the table.");
            alert.showAndWait();
            return;
        }

        // Show a confirmation dialog to confirm the deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete License");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete the selected License?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Delete the License from the MySQL table
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();

                // Delete the License using the License ID
                String deleteQuery = "DELETE FROM license WHERE licenseId  = ?";
                PreparedStatement statement = connectDB.prepareStatement(deleteQuery);
                statement.setInt(1, selectedLicense.getLicenseId());
                statement.executeUpdate();

                // Remove the license from the table view
                list.remove(selectedLicense);

                // Display a success message
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("License Deleted");
                alert1.setHeaderText("Success: License Deleted");
                alert1.setContentText("The license has been deleted successfully.");

                alert1.showAndWait();


            } catch (SQLException e) {
                e.printStackTrace();
                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


    ////////////////////////////////////////////////////////////////
    //Back Button
    @FXML
    private void goToAdminInterface(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin_interface.fxml"));
            Parent root = loader.load();

            Stage adminStage = new Stage();
            adminStage.setTitle("Admin Interface");
            adminStage.setScene(new Scene(root));
            adminStage.show();

            // Close the vehicle_interface
            Stage vehicleStage = (Stage) Back.getScene().getWindow();
            vehicleStage.close();


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }











}
