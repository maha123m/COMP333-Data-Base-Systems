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

public class VehicaleController implements Initializable {

    // Interface contents ------
    @FXML
    TableView<Vehicale> vehicaletableView = new TableView<>();
    @FXML
    TableColumn<Vehicale, Integer> plateNumber;
    @FXML
    TableColumn<Vehicale, Integer> trainerID;
    @FXML
    TableColumn<Vehicale, String> vehicaleModel ;
    @FXML
    TableColumn<Vehicale, Integer> productionYear ;
    @FXML
    TableColumn<Vehicale, String> transmissionType;
    @FXML
    TableColumn<Vehicale, Date> insuranteDate ;
    @FXML
    TableColumn<Vehicale, Date> licenseDate;

    // Buttons:
    @FXML
    private Button addVehicale;
    @FXML
    private Button deleteVehicale;
    @FXML
    private Button updateVehicale;
    @FXML
    private Button home;

    // TextBoxes
    @FXML
    private TextField plateNumber1;
    @FXML
    private TextField trainnerID;
    @FXML
    private TextField vechileModel;
    @FXML
    private TextField productionYear1;

    // Combo Box
    @FXML
    private ComboBox<String> trainsType;

    // Date Box
    @FXML
    private DatePicker insuranteDate1;
    @FXML
    private DatePicker licenseDate1;

    // Search TextField
    @FXML
    private TextField searchTextField;

    ObservableList<Vehicale> list = FXCollections.observableArrayList();




    public void initialize(URL url, ResourceBundle rb) {

        trainsType.getItems().addAll("Automatic", "Manual", "Semi-Automatic", "Dual-Clutch", "Gear");
        // Event handler for selecting a row
        vehicaletableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Vehicale selectedVehicale = vehicaletableView.getSelectionModel().getSelectedItem();

                // Fill the text fields with the selected vehicle's information
                plateNumber1.setText(String.valueOf(selectedVehicale.getPlateNumber()));
                trainnerID.setText(String.valueOf(selectedVehicale.getTrainerID()));
                vechileModel.setText(selectedVehicale.getVehicleModel());
                productionYear1.setText(String.valueOf(selectedVehicale.getProductionYear()));
                trainsType.setValue(selectedVehicale.getTransmissionType());
                insuranteDate1.setValue(((java.sql.Date) selectedVehicale.getInsuranceDate()).toLocalDate());
                licenseDate1.setValue(((java.sql.Date) selectedVehicale.getLicenseDate()).toLocalDate());
            }
        });





        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();
            String viewquery = "SELECT plateNumber, trainerID, vehicleModel, productionYear, transmissionType, insuranceDate, licenseDate FROM vehicle";
            Statement statement = connectDB.createStatement();
            ResultSet queryoutput = statement.executeQuery(viewquery);

            while (queryoutput.next()) {
                Integer queryID = queryoutput.getInt("plateNumber");
                Integer Trid = queryoutput.getInt("trainerID");
                String vModel = queryoutput.getString("vehicleModel");
                Integer prYear = queryoutput.getInt("productionYear");
                String trType = queryoutput.getString("transmissionType");
                Date insDate = queryoutput.getDate("insuranceDate");
                Date liDate = queryoutput.getDate("licenseDate");

                Vehicale vehicale = new Vehicale(queryID, Trid, vModel, prYear, trType, insDate, liDate);
                list.add(vehicale);
            }

            plateNumber.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));
            trainerID.setCellValueFactory(new PropertyValueFactory<>("trainerID"));
            vehicaleModel.setCellValueFactory(new PropertyValueFactory<>("vehicleModel")); // Corrected property name
            productionYear.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
            transmissionType.setCellValueFactory(new PropertyValueFactory<>("transmissionType"));
            insuranteDate.setCellValueFactory(new PropertyValueFactory<>("insuranceDate")); // Corrected property name
            licenseDate.setCellValueFactory(new PropertyValueFactory<>("licenseDate"));

            System.out.println("*");


            vehicaletableView.setItems(list);

            // Create a filtered list to hold the filtered data
            FilteredList<Vehicale> filteredList = new FilteredList<>(list);

            // Set the filter predicate for the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(vehicale -> {
                    // If the search text is empty, show all payments
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Convert search text to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if the payment's first name contains the search text
                    if (vehicale.getTransmissionType().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false; // Does not match the search text
                });
            });

            // Wrap the filtered list in a sorted list to enable sorting
            SortedList<Vehicale> sortedList = new SortedList<>(filteredList);

            // Bind the sorted list to the table view
            sortedList.comparatorProperty().bind(vehicaletableView.comparatorProperty());
            vehicaletableView.setItems(sortedList);
        } catch (SQLException e1) {
            e1.printStackTrace();
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE,null,e1);
        }
    }


    @FXML
    private void addVehicle(ActionEvent event) {



        // Get the values from the text fields
        int plateNumber = Integer.parseInt(plateNumber1.getText());
        int trainerID = trainnerID.getText().isEmpty() ? 0 : Integer.parseInt(trainnerID.getText());
        String vehicleModel = vechileModel.getText();
        int productionYear = Integer.parseInt(productionYear1.getText());
        String transmissionType = trainsType.getValue();
        LocalDate insuranceDateValue = insuranteDate1.getValue();
        LocalDate licenseDateValue = licenseDate1.getValue();

        // Create a Vehicle object with the entered values
        Date insuranceDate = java.sql.Date.valueOf(insuranceDateValue);
        Date licenseDate = java.sql.Date.valueOf(licenseDateValue);
        Vehicale vehicle = new Vehicale(plateNumber, trainerID, vehicleModel, productionYear, transmissionType, insuranceDate, licenseDate);

        // Insert the vehicle into the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Check if the plate number already exists in the SQL table
            String checkQuery = "SELECT COUNT(*) FROM vehicle WHERE plateNumber = ?";
            PreparedStatement checkStatement = connectDB.prepareStatement(checkQuery);
            checkStatement.setInt(1, plateNumber);
            ResultSet checkResult = checkStatement.executeQuery();
            checkResult.next();
            int count = checkResult.getInt(1);
            if (count > 0) {
                // Display an alert if the plate number already exists
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Plate Number Error");
                alert.setHeaderText("Duplicate Plate Number");
                alert.setContentText("The entered plate number already exists in the database.");
                alert.showAndWait();
                return; // Exit the method
            }

            // Check if the trainer ID exists in the SQL table
            if (trainerID != 0) {


                // Increase the trainer's vehicle count by one
                String updateQuery = "UPDATE trainer SET numberOfVehiclesOwns = numberOfVehiclesOwns + 1 WHERE trainerID = ?";
                PreparedStatement updateStatement = connectDB.prepareStatement(updateQuery);
                updateStatement.setInt(1, trainerID);
                updateStatement.executeUpdate();


                String trainerCheckQuery = "SELECT COUNT(*) FROM trainer WHERE trainerID = ?";
                PreparedStatement trainerCheckStatement = connectDB.prepareStatement(trainerCheckQuery);
                trainerCheckStatement.setInt(1, trainerID);
                ResultSet trainerCheckResult = trainerCheckStatement.executeQuery();
                trainerCheckResult.next();
                int trainerCount = trainerCheckResult.getInt(1);

                if (trainerCount == 0) {
                    // Display an alert if the trainer ID does not exist
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Trainer ID Error");
                    alert.setHeaderText("Invalid Trainer ID");
                    alert.setContentText("The entered Trainer ID does not exist in the database.");
                    alert.showAndWait();
                    return; // Exit the method
                }
            }

            // Insert the vehicle into the table
            String insertQuery = "INSERT INTO vehicle (plateNumber, trainerID, vehicleModel, productionYear, transmissionType, insuranceDate, licenseDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connectDB.prepareStatement(insertQuery);
            statement.setInt(1, vehicle.getPlateNumber());
            if (trainerID == 0) {
                statement.setNull(2, java.sql.Types.INTEGER);
            } else {
                statement.setInt(2, vehicle.getTrainerID());
            }
            statement.setString(3, vehicle.getVehicleModel());
            statement.setInt(4, vehicle.getProductionYear());
            statement.setString(5, vehicle.getTransmissionType());
            statement.setDate(6, (java.sql.Date) vehicle.getInsuranceDate());
            statement.setDate(7, (java.sql.Date) vehicle.getLicenseDate());
            statement.executeUpdate();

            // Add the vehicle to the table view
            list.add(vehicle);

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vehicle Added");
            alert.setHeaderText("Success: Vehicle Added");
            alert.setContentText("The vehicle has been added successfully.");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(VehicaleController.class.getName()).log(Level.SEVERE, null, e);
        }
    }




    @FXML
    private void updateVehicle(ActionEvent event) {
        // Check if a vehicle is selected from the table
        Vehicale selectedVehicle = vehicaletableView.getSelectionModel().getSelectedItem();
        if (selectedVehicle == null) {
            // No vehicle selected, display an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Vehicle Selected");
            alert.setHeaderText("No Vehicle Selected");
            alert.setContentText("Please select a vehicle from the table to update.");
            alert.showAndWait();
            return; // Exit the method
        }

        // Get the updated values from the text fields
        int plateNumber = Integer.parseInt(plateNumber1.getText());
        int trainerID = trainnerID.getText().isEmpty() ? 0 : Integer.parseInt(trainnerID.getText());
        String vehicleModel = vechileModel.getText();
        int productionYear = Integer.parseInt(productionYear1.getText());
        String transmissionType = trainsType.getValue();
        LocalDate insuranceDateValue = insuranteDate1.getValue();
        LocalDate licenseDateValue = licenseDate1.getValue();

        // Create a Vehicle object with the updated values
        Date insuranceDate = java.sql.Date.valueOf(insuranceDateValue);
        Date licenseDate = java.sql.Date.valueOf(licenseDateValue);
        Vehicale updatedVehicle = new Vehicale(plateNumber, trainerID, vehicleModel, productionYear, transmissionType, insuranceDate, licenseDate);

        // Update the vehicle in the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Check if the trainer ID exists in the SQL table
            if (trainerID != 0) {
                String checkQuery = "SELECT COUNT(*) FROM trainer WHERE trainerID = ?";
                PreparedStatement checkStatement = connectDB.prepareStatement(checkQuery);
                checkStatement.setInt(1, trainerID);
                ResultSet checkResult = checkStatement.executeQuery();
                checkResult.next();
                int count = checkResult.getInt(1);
                if (count == 0) {
                    // Display an alert if the trainer ID does not exist
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Trainer ID Error");
                    alert.setHeaderText("Invalid Trainer ID");
                    alert.setContentText("The entered Trainer ID does not exist in the database.");
                    alert.showAndWait();
                    return; // Exit the method
                }
            }

            // Update the vehicle in the table
            String updateQuery = "UPDATE vehicle SET plateNumber = ?, trainerID = ?, vehicleModel = ?, productionYear = ?, transmissionType = ?, insuranceDate = ?, licenseDate = ? WHERE plateNumber = ?";
            PreparedStatement statement = connectDB.prepareStatement(updateQuery);
            statement.setInt(1, updatedVehicle.getPlateNumber());
            if (trainerID == 0) {
                decreaseVehicleCount(selectedVehicle.getTrainerID(), connectDB);
                statement.setNull(2, java.sql.Types.INTEGER);
            } else if (selectedVehicle.getTrainerID() == 0) {
                increaseVehicleCount(trainerID, connectDB);
                statement.setInt(2, updatedVehicle.getTrainerID());
            } else {
                decreaseVehicleCount(selectedVehicle.getTrainerID(), connectDB);
                increaseVehicleCount(trainerID, connectDB);
                statement.setInt(2, updatedVehicle.getTrainerID());
            }
            statement.setString(3, updatedVehicle.getVehicleModel());
            statement.setInt(4, updatedVehicle.getProductionYear());
            statement.setString(5, updatedVehicle.getTransmissionType());
            statement.setDate(6, (java.sql.Date) updatedVehicle.getInsuranceDate());
            statement.setDate(7, (java.sql.Date) updatedVehicle.getLicenseDate());
            statement.setInt(8, selectedVehicle.getPlateNumber());
            statement.executeUpdate();

            // Update the vehicle in the list and refresh the table view
            int index = list.indexOf(selectedVehicle);
            list.set(index, updatedVehicle);
            vehicaletableView.refresh();

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vehicle Updated");
            alert.setHeaderText("Success: Vehicle Updated");
            alert.setContentText("The vehicle has been updated successfully.");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(VehicaleController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void decreaseVehicleCount(int trainerID, Connection connection) throws SQLException {
        // Decrease the vehicle count for the given trainer ID
        String updateQuery = "UPDATE trainer SET numberOfVehiclesOwns = numberOfVehiclesOwns - 1 WHERE trainerID = ?";
        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
        updateStatement.setInt(1, trainerID);
        updateStatement.executeUpdate();
    }

    private void increaseVehicleCount(int trainerID, Connection connection) throws SQLException {
        // Increase the vehicle count for the given trainer ID
        String updateQuery = "UPDATE trainer SET numberOfVehiclesOwns = numberOfVehiclesOwns + 1 WHERE trainerID = ?";
        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
        updateStatement.setInt(1, trainerID);
        updateStatement.executeUpdate();
    }


    @FXML
    private void deleteVehicle(ActionEvent event) {
        // Check if a vehicle is selected from the table
        Vehicale selectedVehicle = vehicaletableView.getSelectionModel().getSelectedItem();
        if (selectedVehicle == null) {
            // No vehicle selected, display an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Vehicle Selected");
            alert.setHeaderText("No Vehicle Selected");
            alert.setContentText("Please select a vehicle from the table to delete.");
            alert.showAndWait();
            return; // Exit the method
        }

        // Get the selected vehicle's plate number and trainer ID
        int plateNumber = selectedVehicle.getPlateNumber();
        int trainerID = selectedVehicle.getTrainerID();

        // Delete the vehicle from the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            if (trainerID == 0) {
                // Vehicle has no trainer, simply delete it
                String deleteQuery = "DELETE FROM vehicle WHERE plateNumber = ?";
                PreparedStatement deleteStatement = connectDB.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, plateNumber);
                deleteStatement.executeUpdate();
            } else {
                // Find a vehicle with a null trainer ID to reassign
                String reassignQuery = "UPDATE vehicle SET trainerID = ? WHERE trainerID IS NULL AND transmissionType = ? LIMIT 1";
                PreparedStatement reassignStatement = connectDB.prepareStatement(reassignQuery);
                reassignStatement.setInt(1, trainerID);
                reassignStatement.setString(2, selectedVehicle.getTransmissionType());
                int rowsUpdated = reassignStatement.executeUpdate();

                // Update the trainer ID of the deleted vehicle's trainer to the new assigned vehicle
                if (rowsUpdated > 0) {
                    String updateTrainerQuery = "UPDATE vehicle SET trainerID = ? WHERE trainerID IS NULL AND transmissionType = ? AND plateNumber <> ?";
                    PreparedStatement updateTrainerStatement = connectDB.prepareStatement(updateTrainerQuery);
                    updateTrainerStatement.setInt(1, trainerID);
                    updateTrainerStatement.setString(2, selectedVehicle.getTransmissionType());
                    updateTrainerStatement.setInt(3, plateNumber);
                    updateTrainerStatement.executeUpdate();
                }

                // Decrease the number of vehicles for the trainer
                String decreaseQuery = "UPDATE trainer SET numberOfVehiclesOwns = numberOfVehiclesOwns - 1 WHERE trainerID = ?";
                PreparedStatement decreaseStatement = connectDB.prepareStatement(decreaseQuery);
                decreaseStatement.setInt(1, trainerID);
                decreaseStatement.executeUpdate();

                // Delete the vehicle
                String deleteQuery = "DELETE FROM vehicle WHERE plateNumber = ?";
                PreparedStatement deleteStatement = connectDB.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, plateNumber);
                deleteStatement.executeUpdate();
            }

            // Remove the vehicle from the list
            list.remove(selectedVehicle);

            // Refresh the table view to reflect the changes
            vehicaletableView.refresh();

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vehicle Deleted");
            alert.setHeaderText("Success: Vehicle Deleted");
            alert.setContentText("The vehicle has been deleted successfully.");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(VehicaleController.class.getName()).log(Level.SEVERE, null, e);
        }
    }



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
            Stage vehicleStage = (Stage) home.getScene().getWindow();
            vehicleStage.close();


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }





}
