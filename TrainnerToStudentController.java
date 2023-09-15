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


public class TrainnerToStudentController implements Initializable {
    //  interface contents ------->

    @FXML
    TableView<TrainnerToStudent> trainnetToStudentTableView = new TableView<>();

    @FXML
    TableColumn<TrainnerToStudent, Integer> studentId;
    @FXML
    TableColumn<TrainnerToStudent, Integer> trainerId;

/////////////////////////////////////////////

    // Buttons:
    @FXML
    private Button addStudentTrainnerButton;



    // textBoxes
    @FXML
    private TextField sID;

    @FXML
    private TextField tID;
    ///////////////////////////////////////////////////////////////////////////
    ObservableList <TrainnerToStudent> listM;

    int index = -1;

    Connection conn = null;

    ResultSet rs = null;

    PreparedStatement pst = null ;

    @FXML
    private TextField searchTextField;

    ObservableList<TrainnerToStudent> list = FXCollections.observableArrayList();
    public void initialize(URL url, ResourceBundle rb) {
        // Event handler for selecting a row
        trainnetToStudentTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                TrainnerToStudent selectedStoT = trainnetToStudentTableView.getSelectionModel().getSelectedItem();
                // Fill the text fields with the selected Test information
                sID.setText(String.valueOf(selectedStoT.getStudentId()));
                tID.setText(String.valueOf(selectedStoT.getTrainerId()));
            }
        });

        try {
            Connection connectDB = new Connecter().getConnection();
            String viewquery = "SELECT studentId, trainerId FROM student_trainer";
            Statement statement = connectDB.createStatement();
            ResultSet queryoutput = statement.executeQuery(viewquery);

            // Clear the list before loading the data
            list.clear();

            while (queryoutput.next()) {
                Integer queryID = queryoutput.getInt("studentId");
                Integer Tid = queryoutput.getInt("trainerId");

                TrainnerToStudent StoT = new TrainnerToStudent(queryID, Tid);
                list.add(StoT);
            }

            studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
            trainerId.setCellValueFactory(new PropertyValueFactory<>("trainerId"));
            // Create a filtered list to hold the filtered data
            FilteredList<TrainnerToStudent> filteredList = new FilteredList<>(list);

            // Set the filter predicate for the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(StoT -> {
                    // If the search text is empty, show all payments
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Convert search text to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if the trainerId contains the search text
                    if (String.valueOf(StoT.getTrainerId()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false; // Does not match the search text
                });
            });

            // Wrap the filtered list in a sorted list to enable sorting
            SortedList<TrainnerToStudent> sortedList = new SortedList<>(filteredList);

            // Bind the sorted list to the table view
            sortedList.comparatorProperty().bind(trainnetToStudentTableView.comparatorProperty());
            trainnetToStudentTableView.setItems(sortedList);
        } catch (SQLException e1) {
            e1.printStackTrace();
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, e1);
        }
    }
    
    
    @FXML
    private void onAddStudentTrainerButtonAction(ActionEvent event) {
        // Retrieve values from text fields
        int studentIdValue = Integer.parseInt(sID.getText());
        int trainerIdValue = Integer.parseInt(tID.getText());

        try {
            Connection connectDB = new Connecter().getConnection();

            // Check if the student is already assigned to a trainer
            String selectQuery = "SELECT * FROM student_trainer WHERE studentId = ?";
            PreparedStatement selectStatement = connectDB.prepareStatement(selectQuery);
            selectStatement.setInt(1, studentIdValue);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // Student is already assigned to a trainer, show an alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Student Already Assigned");
                alert.setContentText("The selected student is already assigned to a trainer.");
                alert.showAndWait();
            } else {
                // Student is not assigned to a trainer, proceed with adding the assignment
                String insertQuery = "INSERT INTO student_trainer (studentId, trainerId) VALUES (?, ?)";
                PreparedStatement insertStatement = connectDB.prepareStatement(insertQuery);
                insertStatement.setInt(1, studentIdValue);
                insertStatement.setInt(2, trainerIdValue);

                int rowsAffected = insertStatement.executeUpdate();
                if (rowsAffected > 0) {
                    // Show success message or perform any other necessary actions
                    System.out.println("Student Trainer added successfully!");
                } else {
                    // Show error message or handle the case when no rows were affected
                    System.out.println("Failed to add Student Trainer.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(TrainnerToStudentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private Button Home;
    
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
            Stage h = (Stage) Home.getScene().getWindow();
            h.close();

           
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /////////////////////////////////////////////////////////////
    
}
