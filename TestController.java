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

public class TestController implements Initializable{


    //  interface contents ------->

    @FXML
    TableView<Test> testTableview = new TableView<>();

    @FXML
    TableColumn<Test, Integer> Tid;
    @FXML
    TableColumn<Test, Integer> StudentId;
    @FXML
    TableColumn<Test, String> Tresult;
    @FXML
    TableColumn<Test, Date> Pdate;

/////////////////////////////////////////////

    // Buttons:
    @FXML
    private Button addTest;
    @FXML
    private Button deleteTest;
    @FXML
    private Button updateTest;
    @FXML
    private Button Home;


    // textBoxes
    @FXML
    private TextField testID;

    @FXML
    private TextField studentid;

    /////combo box
    @FXML
    private  ComboBox testResult;

    //date box
    @FXML
    private DatePicker testDate;

    ///////////////////////////////////////////////////////////////////////////
    ObservableList <Test> listM;

    int index = -1;

    Connection conn = null;

    ResultSet rs = null;

    PreparedStatement pst = null ;

    @FXML
    private TextField searchTextField;

    ObservableList<Test> list = FXCollections.observableArrayList();
    ////////////////////////////////////////////////
    public void initialize(URL url, ResourceBundle rb) {
        // Event handler for selecting a row
        testTableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Test selectedTest = testTableview.getSelectionModel().getSelectedItem();

                // Fill the text fields with the selected Test information
                testID.setText(String.valueOf(selectedTest.getTid()));
                studentid.setText(String.valueOf(selectedTest.getStudentId()));
                testResult.setValue(selectedTest.getTresult());

                testDate.setValue(((java.sql.Date) selectedTest.getPdate()).toLocalDate());
            }
        });

        try {
            Connection connectDB = new Connecter().getConnection();
            String viewquery = "SELECT Tid, StudentId, Tresult, Pdate FROM test";
            Statement statement = connectDB.createStatement();
            ResultSet queryoutput = statement.executeQuery(viewquery);

            // Clear the list before loading the data
            list.clear();



            while (queryoutput.next()) {
                Integer queryID = queryoutput.getInt("Tid");
                Integer Sid = queryoutput.getInt("StudentId");
                String tResult = queryoutput.getString("Tresult");
                Date payDate = queryoutput.getDate("Pdate");

                Test test1 = new Test(queryID, Sid, tResult, payDate);
                list.add(test1);
            }

            Tid.setCellValueFactory(new PropertyValueFactory<>("Tid"));
            StudentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
            Tresult.setCellValueFactory(new PropertyValueFactory<>("Tresult"));
            Pdate.setCellValueFactory(new PropertyValueFactory<>("Pdate"));

            testResult.getItems().addAll("Pass", "Fail");
            testTableview.setItems(list);

            // Create a filtered list to hold the filtered data
            FilteredList<Test> filteredList = new FilteredList<>(list);

            // Set the filter predicate for the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(test1 -> {
                    // If the search text is empty, show all payments
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Convert search text to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if the payment's test result contains the search text
                    if (test1.getTresult().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false; // Does not match the search text
                });
            });

            // Wrap the filtered list in a sorted list to enable sorting
            SortedList<Test> sortedList = new SortedList<>(filteredList);

            // Bind the sorted list to the table view
            sortedList.comparatorProperty().bind(testTableview.comparatorProperty());
            testTableview.setItems(sortedList);
        } catch (SQLException e1) {
            e1.printStackTrace();
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, e1);
        }

    }

    /////////////////////////////////////////////////////////////

    ////add a test
    @FXML

    private void addTestOnAction(ActionEvent event) {
        // Get the values from the text fields
        int testIDD = Integer.parseInt(testID.getText());
        int sIdd = Integer.parseInt(studentid.getText());

        String testResultValue = testResult.getValue().toString();
        LocalDate testdateValue = testDate.getValue();

        // Create a test object with the entered values
        Date testDate = java.sql.Date.valueOf(testdateValue);
        Test test1 = new Test(testIDD, sIdd, testResultValue, testDate);

        // Insert the test into the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Check if the test ID already exists in the table
            String checkQuery = "SELECT COUNT(*) FROM test WHERE Tid = ?";
            PreparedStatement checkStatement = connectDB.prepareStatement(checkQuery);
            checkStatement.setInt(1, test1.getTid());
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                // Display a warning message if the ID already exists
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Duplicate Test ID");
                alert.setHeaderText("Warning: Duplicate Test ID");
                alert.setContentText("The entered Test ID already exists in the database. Please enter a unique ID.");
                alert.showAndWait();
            } else {
                // Insert the test into the table
                String insertQuery = "INSERT INTO test (Tid, StudentId,Tresult,Pdate) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connectDB.prepareStatement(insertQuery);
                statement.setInt(1, test1.getTid());
                statement.setInt(2, test1.getStudentId());
                statement.setString(3, test1.getTresult());
                statement.setDate(4, (java.sql.Date) test1.getPdate());
                statement.executeUpdate();

                // Add the test to the table view
                list.add(test1);

                // Display a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Added");
                alert.setHeaderText("Success: Test Added");
                alert.setContentText("The Test has been added successfully.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    ///////////////////////////////////////////////////////
    //update payment button
    @FXML
    private void updateTestOnAction(ActionEvent event) {
        // Check if a payment is selected in the table
        Test selectedTest = testTableview.getSelectionModel().getSelectedItem();
        if (selectedTest == null) {
            // Display a warning message if no Test is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Test Selected");
            alert.setHeaderText("Warning: No Test selected!");
            alert.setContentText("Please select a Test from the table.");
            alert.showAndWait();
            return;
        }

        // Get the updated values from the text fields
        int tId = Integer.parseInt(testID.getText());
        int sId = Integer.parseInt(studentid.getText());
        String testResultValue = testResult.getValue().toString();
        LocalDate tDate = testDate.getValue();

        // Update the selected student object with the new values
        selectedTest.setTid(tId);
        selectedTest.setStudentId(sId);
        selectedTest.setTresult(testResultValue);
        selectedTest.setPdate(java.sql.Date.valueOf(tDate));

        // Update the student in the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Update the test information
            String updateQuery = "UPDATE test SET Tid = ?, StudentId = ?, Tresult = ?, Pdate = ?  WHERE Tid = ?";
            PreparedStatement statement = connectDB.prepareStatement(updateQuery);
            statement.setInt(1, selectedTest.getTid());
            statement.setInt(2, selectedTest.getStudentId());
            statement.setString(3, selectedTest.getTresult());
            statement.setDate(4, (java.sql.Date) selectedTest.getPdate());
            statement.setInt(5, selectedTest.getTid());
            statement.executeUpdate();

            // Refresh the table view
            testTableview.refresh();

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Updated");
            alert.setHeaderText("Success: Test Updated");
            alert.setContentText("The Test has been updated successfully.");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /////////////////////////////
    // delete test button
    @FXML
    private void deleteTestOnAction(ActionEvent event) {
        // Check if a Test is selected in the table
        Test selectedTest = testTableview.getSelectionModel().getSelectedItem();
        if (selectedTest == null) {
            // Display a warning message if no Test is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Test Selected");
            alert.setHeaderText("Warning: No Test selected!");
            alert.setContentText("Please select a Test from the table.");
            alert.showAndWait();
            return;
        }

        // Show a confirmation dialog to confirm the deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Test");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete the selected Test?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Delete the Test from the MySQL table
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();

                // Delete the test using the test ID
                String deleteQuery = "DELETE FROM test WHERE Tid  = ?";
                PreparedStatement statement = connectDB.prepareStatement(deleteQuery);
                statement.setInt(1, selectedTest.getTid());
                statement.executeUpdate();

                // Remove the test from the table view
                list.remove(selectedTest);

                // Display a success message
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Test Deleted");
                alert1.setHeaderText("Success: Test Deleted");
                alert1.setContentText("The Test has been deleted successfully.");

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
            Stage h = (Stage) Home.getScene().getWindow();
            h.close();


            //loginStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }









}


