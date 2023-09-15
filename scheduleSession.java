package com.example.maha;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class scheduleSession implements Initializable {


    @FXML
    TableView<TrainerAvailability> availabilityTable = new TableView<>();

    @FXML
    TableColumn<TrainerAvailability, Integer> trainerID;
    @FXML
    TableColumn<TrainerAvailability, String> avalableDay;
    @FXML
    TableColumn<TrainerAvailability, String> avalableTime;
    ObservableList<TrainerAvailability> list = FXCollections.observableArrayList();
    @FXML
    private TextField searchTextField;
    @FXML
    private Button reserveButton;

    // janesmith has 1001 and trainer 1 whose johndoe
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //int studentId = 1001;
        int studentId = SampleController.studentid;
        // int studentId=SampleController.getstudentID();

        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Modify the query to include a WHERE clause to filter by the student ID
            String query = "SELECT * FROM trainerAvalibility WHERE trainerID IN (SELECT trainerID FROM student_trainer WHERE studentId = ?);";
            PreparedStatement statement = connectDB.prepareStatement(query);
            statement.setInt(1, studentId);
            ResultSet res = statement.executeQuery();

            /*
            String query = "SELECT * from trainerAvalibility;";
            Statement sta = connectDB.createStatement();
            System.out.println("jana");
            ResultSet res = sta.executeQuery(query);*/


            while (res.next()) {


                Integer queryID = res.getInt("trainerID");
                String availableDay = res.getString("avalableDay");
                String availableTime = res.getString("avalableTime");

                TrainerAvailability trainerAvai = new TrainerAvailability(queryID, availableDay, availableTime);
                list.add(trainerAvai);
            }

            trainerID.setCellValueFactory(new PropertyValueFactory<>("trainerID"));
            avalableDay.setCellValueFactory(new PropertyValueFactory<>("avalableDay"));
            avalableTime.setCellValueFactory(new PropertyValueFactory<>("avalableTime"));


            availabilityTable.setItems(list);

            // Create a filtered list to hold the filtered data
            FilteredList<TrainerAvailability> filteredList = new FilteredList<>(list);

            // Set the filter predicate for the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(trainerAvai -> {
                    // If the search text is empty, show all trainers
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Convert search text to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if the trainer's first name contains the search text
                    if (trainerAvai.getAvalableDay().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false; // Does not match the search text
                });
            });

            // Wrap the filtered list in a sorted list to enable sorting
            SortedList<TrainerAvailability> sortedList = new SortedList<>(filteredList);

            // Bind the sorted list to the table view
            sortedList.comparatorProperty().bind(availabilityTable.comparatorProperty());
            availabilityTable.setItems(sortedList);

            // Disable the reserve button initially
            reserveButton.setDisable(true);

            // Add a listener to enable the button when a row is selected
            availabilityTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    reserveButton.setDisable(false);
                } else {
                    reserveButton.setDisable(true);
                }
            });

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Logger.getLogger(scheduleSession.class.getName()).log(Level.SEVERE, null, e1);
        }


    }


    public void handleReserveButton(ActionEvent actionEvent) {
        TrainerAvailability selectedAvailability = availabilityTable.getSelectionModel().getSelectedItem();
        if (selectedAvailability != null) {
            int studentId = SampleController.studentid; // Set the student ID accordingly

            try {
                Connecter conn = new Connecter();
                Connection connectDB = conn.getConnection();

                // Prepare the SELECT statement to check for duplicates
                String duplicateQuery = "SELECT * FROM studentsession WHERE studentID = ? AND trainerID = ? AND avalableDay = ? AND avalableTime = ?";
                PreparedStatement duplicateStatement = connectDB.prepareStatement(duplicateQuery);
                duplicateStatement.setInt(1, studentId);
                duplicateStatement.setInt(2, selectedAvailability.getTrainerID());
                duplicateStatement.setString(3, selectedAvailability.getAvalableDay());
                duplicateStatement.setString(4, selectedAvailability.getAvalableTime());
                ResultSet duplicateResult = duplicateStatement.executeQuery();

                if (duplicateResult.next()) {
                    // A duplicate row exists
                    // Display an alert or perform any other action if needed
                } else {
                    // No duplicate row found, proceed with the reservation
                    // Prepare the INSERT statement
                    String insertQuery = "INSERT INTO studentsession (studentID, trainerID, avalableDay, avalableTime) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertStatement = connectDB.prepareStatement(insertQuery);

                    // Set the parameter values for the INSERT statement
                    insertStatement.setInt(1, studentId);
                    insertStatement.setInt(2, selectedAvailability.getTrainerID());
                    insertStatement.setString(3, selectedAvailability.getAvalableDay());
                    insertStatement.setString(4, selectedAvailability.getAvalableTime());

                    // Execute the INSERT statement
                    int rowsAffected = insertStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Reservation successful.");

                        // Delete the reserved session from trainerAvalibility
                        String deleteQuery = "DELETE FROM trainerAvalibility WHERE trainerID = ? AND avalableDay = ? AND avalableTime = ?";
                        PreparedStatement deleteStatement = connectDB.prepareStatement(deleteQuery);
                        deleteStatement.setInt(1, selectedAvailability.getTrainerID());
                        deleteStatement.setString(2, selectedAvailability.getAvalableDay());
                        deleteStatement.setString(3, selectedAvailability.getAvalableTime());
                        int deleteRowsAffected = deleteStatement.executeUpdate();

                        if (deleteRowsAffected > 0) {
                            System.out.println("Reserved session removed from trainerAvalibility.");

                            // Remove the selected row from the table view
                            //availabilityTable.getItems().remove(selectedAvailability);
                        }
                    } else {
                        System.out.println("Reservation failed.");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                Logger.getLogger(scheduleSession.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


    @FXML
      private Button Home;
      
      @FXML
      private void goTomainInterface(ActionEvent event) {
          try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainStudent.fxml"));
              Parent root = loader.load();

              Stage adminStage = new Stage();
              adminStage.setTitle("Student Interface");
              adminStage.setScene(new Scene(root));
              adminStage.show();

              // Close the vehicle_interface
              Stage h = (Stage) Home.getScene().getWindow();
              h.close();


              //loginStage.close();
          } catch (IOException ex) {
              ex.printStackTrace();
          }
      }
}


