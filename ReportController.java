package com.example.maha;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ReportController {
	
	
	 @FXML
	  private Button Home;
	   
    @FXML
    private Button trainerReports;
    


    @FXML
    private Button trainerFirstquery; // display all trainers ordering by their first name
    
    @FXML
    private Button trainerSecondquery; // display the number of trainers
    @FXML
    private Button trainerThrdquery;
    
    @FXML
    private Button trainerfourthquery;
    
    
    @FXML
    private Button trainerFifthquery;
    
    
    @FXML
    private Button studentReports;
    @FXML
    private Button studentFirstquery;
    @FXML
    private Button studentSecondquery; 
    
    
    @FXML
    private Button studentThirdquery; 
    
    
    @FXML
    private Button studentFourthquery; 
    
    @FXML
    private Button studentFifthquery; 
    
    @FXML
    private Button studentSixthquery; 
    
    
    @FXML
    private Button studentSeventhquery; 
    
    
    
    @FXML
    
    private Button vehicleReports;
    
    
    @FXML
    private Button vehicleFirstquery; 
    
    

    @FXML
    private Button vehicleSecondquery; 
    
   
    @FXML
    private Button vehicleThirdquery; 
    
    
    
    @FXML
    private Button sessionReports;

    @FXML
    private Button sessionFirstquery;

    

    @FXML
    private Button sessionSecondquery;
    
    
    @FXML
    private Button testReports;
    
    @FXML
    private Button testFirstquery;


    @FXML
    private Button testSecondquery;
    
    
    
    @FXML
    private Button licenceReports;

    @FXML
    private Button licenseFirstquery;

    @FXML
    private Button licenceSecondquery;

    @FXML
    private Button licenceThirdquery;
    
    
    @FXML
    private Button paymentReports;
    
    @FXML
    private Button paymentFirstquery; 
    
    @FXML
    private Button paymentSecondquery; 


    @FXML
    private void initialize() {
        trainerFirstquery.setVisible(false); // Set trainerFirstquery button to be initially hidden
        trainerSecondquery.setVisible(false);
        trainerThrdquery.setVisible(false);
        trainerfourthquery.setVisible(false);
        trainerFifthquery.setVisible(false);
        studentFirstquery.setVisible(false);
        studentSecondquery.setVisible(false);
        studentThirdquery.setVisible(false);
        studentFourthquery.setVisible(false);
        studentFifthquery.setVisible(false);
        studentSixthquery.setVisible(false);
        studentSeventhquery.setVisible(false);
        vehicleFirstquery.setVisible(false);
        vehicleSecondquery.setVisible(false);
        vehicleThirdquery.setVisible(false);
        sessionFirstquery.setVisible(false);
        sessionSecondquery.setVisible(false);
        testFirstquery.setVisible(false);
        testSecondquery.setVisible(false);
        licenseFirstquery.setVisible(false);
        licenceSecondquery.setVisible(false);
        licenceThirdquery.setVisible(false);
        paymentFirstquery.setVisible(false);
        paymentSecondquery.setVisible(false);
    }
    
    @FXML
    private void handlePaymentReportsButton() {
    	paymentFirstquery.setVisible(true);
    	paymentSecondquery.setVisible(true);
       
    }
    
    @FXML
    private void paymentbuttonsOff() {
    	paymentFirstquery.setVisible(false);
    	paymentSecondquery.setVisible(false);
     
        
    }
    
    
    @FXML
    private void handleLicenceReportsButton() {
        licenseFirstquery.setVisible(true);
        licenceSecondquery.setVisible(true);
        licenceThirdquery.setVisible(true);
    }
    
    
    @FXML
    private void licensebuttonsOff() {
        licenseFirstquery.setVisible(false);
        licenceSecondquery.setVisible(false);
        licenceThirdquery.setVisible(false);
        
    }

    @FXML
    private void handleTrainerReportsButton() {
        trainerFirstquery.setVisible(true); // Set trainerFirstquery button to be visible when trainerReports button is chosen
        trainerSecondquery.setVisible(true);
        trainerThrdquery.setVisible(true);
        trainerfourthquery.setVisible(true);
        trainerFifthquery.setVisible(true);
    }
    
    
    @FXML
    private void trainerbuttonsOff() {
        trainerFirstquery.setVisible(false); // Set trainerFirstquery button to be visible when trainerReports button is chosen
        trainerSecondquery.setVisible(false);
        trainerThrdquery.setVisible(false);
        trainerfourthquery.setVisible(false);
        trainerFifthquery.setVisible(false);
        
    }
    
    
    @FXML
    private void handlestudentReportsButton() {
    	studentFirstquery.setVisible(true); // Set trainerFirstquery button to be visible when trainerReports button is chosen
    	studentSecondquery.setVisible(true);
    	studentThirdquery.setVisible(true);
    	studentFourthquery.setVisible(true);
    	studentFifthquery.setVisible(true);
    	studentSixthquery.setVisible(true);
    	  studentSeventhquery.setVisible(true);
     
    }
    
    
    @FXML
    private void studentbuttonsOff() {
    	studentFirstquery.setVisible(false); // Set trainerFirstquery button to be visible when trainerReports button is chosen
    	studentSecondquery.setVisible(false);
    	studentThirdquery.setVisible(false);
    	studentFourthquery.setVisible(false);
    	studentFifthquery.setVisible(false);
    	studentSixthquery.setVisible(false);
    	studentSeventhquery.setVisible(false);
     
    }
    
    
    
    @FXML
    private void handlevehicleReportsButton() {
    	vehicleFirstquery.setVisible(true); // Set trainerFirstquery button to be visible when trainerReports button is chosen
    	  vehicleSecondquery.setVisible(true);
    	  vehicleThirdquery.setVisible(true);
    	
     
    }
    
    @FXML
    private void vehiclebuttonsOff() {
    	vehicleFirstquery.setVisible(false); // Set trainerFirstquery button to be visible when trainerReports button is chosen
    	 vehicleSecondquery.setVisible(false);
    	  vehicleThirdquery.setVisible(false);
    }
    
    
    @FXML
    private void handleSessionReportsButton() {
    	sessionFirstquery.setVisible(true); // Set trainerFirstquery button to be visible when trainerReports button is chosen
    	sessionSecondquery.setVisible(true);
    }
    
    @FXML
    private void sessionbuttonsOff() {
    	sessionFirstquery.setVisible(false); // Set trainerFirstquery button to be visible when trainerReports button is chosen
    	sessionSecondquery.setVisible(false);
    }
    
    
    @FXML
    private void handletestReportsButton() {
    	  testFirstquery.setVisible(true);
          testSecondquery.setVisible(true);       
    }
    
    
    @FXML
    private void testbuttonsOff() {
    	  testFirstquery.setVisible(false);
          testSecondquery.setVisible(false);
        
    }
    
    
    

    @FXML
    private void trainerReportsButtonOnAction(ActionEvent event) {

        handleTrainerReportsButton();
        studentbuttonsOff();
        vehiclebuttonsOff();
        sessionbuttonsOff() ;
        testbuttonsOff();
        licensebuttonsOff();
        paymentbuttonsOff();

        trainerFirstquery.setOnAction(e -> {
            // Perform the desired action for trainerFirstquery here
            System.out.println("Trainer First Query button clicked!");

            // Add your custom code or method calls here
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();
                Statement statement = connectDB.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM trainer ORDER BY trainerFirstName");

                // Create an alert dialog
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Trainer Reports");
                alert.setHeaderText(null);

                // Set the monospaced font for the alert dialog
                Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
                alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
                alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

                StringBuilder data = new StringBuilder();

                // Append the column headers
                data.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                        "Trainer ID", "First Name", "Last Name", "Vehicles Owns", "City Address", "Street Address", "Phone 1", "Phone 2"));

                // Append the separator line
                data.append("-".repeat(15 * 8)).append("\n");

                // Iterate over the result set and append the data
                while (resultSet.next()) {
                    int trainerID = resultSet.getInt("trainerID");
                    String firstName = resultSet.getString("trainerFirstName");
                    String lastName = resultSet.getString("trainerLastName");
                    int numberOfVehiclesOwns = resultSet.getInt("numberOfVehiclesOwns");
                    String cityAddress = resultSet.getString("cityAddress");
                    String streetAddress = resultSet.getString("streetAddress");
                    String phone1 = resultSet.getString("phone1");
                    String phone2 = resultSet.getString("phone2");

                    // Append the formatted data
                    data.append(String.format("%-15d %-15s %-15s %-15d %-15s %-15s %-15s %-15s\n",
                            trainerID, firstName, lastName, numberOfVehiclesOwns, cityAddress, streetAddress, phone1, phone2));
                }

                // Set the content text of the alert dialog
                alert.setContentText(data.toString());

                // Set the width of the alert dialog
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setMinWidth(1000); // Adjust the desired width

                // Show the alert dialog
                alert.showAndWait();

                // Close the database connections
                resultSet.close();
                statement.close();
                connectDB.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle any exceptions that may occur during the database operations
            }
        });
        
        
        trainerSecondquery.setOnAction(e -> {
            // Perform the desired action for trainerSecondquery here
            System.out.println("Trainer Second Query button clicked!");

            // Add your custom code or method calls here
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();
                Statement statement = connectDB.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS totalTrainers FROM trainer");

                if (resultSet.next()) {
                    int totalTrainers = resultSet.getInt("totalTrainers");

                    // Create an alert dialog to display the number of trainers
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Number of Trainers");
                    alert.setHeaderText(null);
                    alert.setContentText("Total Trainers: " + totalTrainers);
                    alert.showAndWait();
                }

                // Close the database connections
                resultSet.close();
                statement.close();
                connectDB.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle any exceptions that may occur during the database operations
            }
        });
        
        
        trainerThrdquery.setOnAction(e -> {
            // Perform the desired action for trainerThrdquery here
            System.out.println("Trainer Third Query button clicked!");

            // Create an alert dialog
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Trainer Third Query");
            alert.setHeaderText("Enter the number of vehicles");

            // Create a label and a text box for input
            Label label = new Label("Number of Vehicles:");
            TextField textField = new TextField();

            // Create a layout for the label and text box
            VBox vbox = new VBox(10);
            vbox.getChildren().addAll(label, textField);

            // Set the layout as the content of the alert dialog
            alert.getDialogPane().setContent(vbox);

            // Show the alert dialog and wait for user input
            Optional<ButtonType> result = alert.showAndWait();

            // Check if the user clicked the OK button
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Get the input value from the text box
                String numberOfVehicles = textField.getText();

                // Add your custom code or method calls here
                Connecter conn = new Connecter();
                try {
                    Connection connectDB = conn.getConnection();
                    Statement statement = connectDB.createStatement();

                    ResultSet resultSet = statement.executeQuery("SELECT trainerID, trainerFirstName, trainerLastName, numberOfVehiclesOwns FROM trainer WHERE numberOfVehiclesOwns = " + numberOfVehicles);

                    // Create an alert dialog
                    Alert resultAlert = new Alert(AlertType.INFORMATION);
                    resultAlert.setTitle("Trainer Results");
                    resultAlert.setHeaderText(null);

                    // Set the monospaced font for the alert dialog
                    Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
                    resultAlert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
                    resultAlert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

                    StringBuilder data = new StringBuilder();

                    // Append the column headers
                    data.append(String.format("%-15s %-15s %-15s %-15s\n",
                            "Trainer ID", "First Name", "Last Name", "Vehicles Owns"));

                    // Append the separator line
                    data.append("-".repeat(15 * 4)).append("\n");

                    // Iterate over the result set and append the data
                    while (resultSet.next()) {
                        int trainerID = resultSet.getInt("trainerID");
                        String firstName = resultSet.getString("trainerFirstName");
                        String lastName = resultSet.getString("trainerLastName");
                        int numberOfVehiclesOwns = resultSet.getInt("numberOfVehiclesOwns");

                        // Append the formatted data
                        data.append(String.format("%-15d %-15s %-15s %-15d\n",
                                trainerID, firstName, lastName, numberOfVehiclesOwns));
                    }

                    // Set the content text of the alert dialog
                    resultAlert.setContentText(data.toString());

                    // Set the width of the alert dialog
                    DialogPane dialogPane = resultAlert.getDialogPane();
                    dialogPane.setMinWidth(1000); // Adjust the desired width

                    // Show the alert dialog
                    resultAlert.showAndWait();

                    // Close the database connections
                    resultSet.close();
                    statement.close();
                    connectDB.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Handle any exceptions that may occur during the database operations
                }
            }
        });
        
        
        

        trainerfourthquery.setOnAction(e -> {
            // Create an alert dialog
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Trainer Fourth Query");
            alert.setHeaderText("Enter Trainer ID");

            // Create a label and a text box for input
            Label label = new Label("Trainer ID:");
            TextField textField = new TextField();

            // Create a layout for the label and text box
            VBox vbox = new VBox(10);
            vbox.getChildren().addAll(label, textField);

            // Set the layout as the content of the alert dialog
            alert.getDialogPane().setContent(vbox);

            // Show the alert dialog and wait for user input
            Optional<ButtonType> result = alert.showAndWait();

            // Check if the user clicked the OK button
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Get the input value from the text box
                String trainerID = textField.getText();

                // Add your custom code or method calls here
                Connecter conn = new Connecter();
                try {
                    Connection connectDB = conn.getConnection();
                    Statement statement = connectDB.createStatement();

                    // Check if the trainer ID exists in the database
                    ResultSet trainerExistsResult = statement.executeQuery("SELECT COUNT(*) AS trainerCount FROM trainer WHERE trainerID = " + trainerID);
                    if (trainerExistsResult.next()) {
                        int trainerCount = trainerExistsResult.getInt("trainerCount");

                        // If the trainer exists, retrieve the count of associated students
                        if (trainerCount > 0) {
                            ResultSet countResult = statement.executeQuery("SELECT COUNT(*) AS studentCount FROM students s JOIN student_trainer st ON s.studentId = st.studentId WHERE st.trainerId = " + trainerID);

                            // Create an alert dialog to display the count of students and print them
                            Alert resultAlert = new Alert(AlertType.INFORMATION);
                            resultAlert.setTitle("Trainer Students");
                            resultAlert.setHeaderText(null);

                            // Set the monospaced font for the alert dialog
                            Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
                            resultAlert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
                            resultAlert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

                            StringBuilder data = new StringBuilder();

                            // Append the column headers
                            data.append(String.format("%-15s %-15s %-15s %-15s %-15s\n",
                                    "Student ID", "First Name", "Middle Name", "Last Name", "Phone 1"));

                            // Append the separator line
                            data.append("-".repeat(15 * 5)).append("\n");

                            // Iterate over the result set and append the data
                            while (countResult.next()) {
                                int studentCount = countResult.getInt("studentCount");
                                data.append("Number of students associated with Trainer ID ").append(trainerID).append(": ").append(studentCount).append("\n");
                            }

                            // Append an empty line
                            data.append("\n");

                            // Retrieve the associated students
                            ResultSet studentsResult = statement.executeQuery("SELECT s.* FROM students s JOIN student_trainer st ON s.studentId = st.studentId WHERE st.trainerId = " + trainerID);

                            // Iterate over the result set and append the data
                            while (studentsResult.next()) {
                                int studentID = studentsResult.getInt("studentId");
                                String firstName = studentsResult.getString("studentFirstName");
                                String middleName = studentsResult.getString("studentmiddleName");
                                String lastName = studentsResult.getString("studentLastName");
                                int phone1 = studentsResult.getInt("phone1");

                                // Append the formatted data
                                data.append(String.format("%-15d %-15s %-15s %-15s %-15d\n",
                                        studentID, firstName, middleName, lastName, phone1));
                            }

                            // Set the content text of the alert dialog
                            resultAlert.setContentText(data.toString());

                            // Set the width of the alert dialog
                            DialogPane dialogPane = resultAlert.getDialogPane();
                            dialogPane.setMinWidth(1000); // Adjust the desired width

                            // Show the alert dialog
                            resultAlert.showAndWait();

                            // Close the students result set
                            studentsResult.close();
                        } else {
                            // If the trainer does not exist, display a message
                            Alert errorAlert = new Alert(AlertType.ERROR);
                            errorAlert.setTitle("Trainer Not Found");
                            errorAlert.setHeaderText(null);
                            errorAlert.setContentText("Trainer with ID " + trainerID + " does not exist.");
                            errorAlert.showAndWait();
                        }
                    }

                    // Close the database connections
                    trainerExistsResult.close();
                    statement.close();
                    connectDB.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Handle any exceptions that may occur during the database operations
                }
            }
        });
        
        trainerFifthquery.setOnAction(e -> {
            // Perform the desired action for trainerFirstquery here
            System.out.println("Trainer First Query button clicked!");

            // Add your custom code or method calls here
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();
                Statement statement = connectDB.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT t.trainerID, t.trainerFirstName, t.trainerLastName, t.numberOfVehiclesOwns, v.plateNumber, v.transmissionType, v.vehicleModel FROM trainer t LEFT JOIN vehicle v ON t.trainerID = v.trainerID ORDER BY t.trainerFirstName");

                // Create an alert dialog
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Trainer Reports");
                alert.setHeaderText(null);

                // Set the monospaced font for the alert dialog
                Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
                alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
                alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

                StringBuilder data = new StringBuilder();

                // Append the column headers
                data.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                        "Trainer ID", "First Name", "Last Name", "Vehicles Owns", "Vehicle ID", "Vehicle Type", "Vehicle Model"));

                // Append the separator line
                data.append("-".repeat(15 * 7)).append("\n");

                // Iterate over the result set and append the data
                while (resultSet.next()) {
                    int trainerID = resultSet.getInt("t.trainerID");
                    String firstName = resultSet.getString("t.trainerFirstName");
                    String lastName = resultSet.getString("t.trainerLastName");
                    int numberOfVehiclesOwns = resultSet.getInt("t.numberOfVehiclesOwns");
                    int vehicleID = resultSet.getInt("v.plateNumber");
                    String vehicleMake = resultSet.getString("v.transmissionType");
                    String vehicleModel = resultSet.getString("v.vehicleModel");

                    // Append the formatted data
                    data.append(String.format("%-15d %-15s %-15s %-15d %-15d %-15s %-15s\n",
                            trainerID, firstName, lastName, numberOfVehiclesOwns, vehicleID, vehicleMake, vehicleModel));
                }

                // Set the content text of the alert dialog
                alert.setContentText(data.toString());

                // Set the width of the alert dialog
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setMinWidth(1200); // Adjust the desired width

                // Show the alert dialog
                alert.showAndWait();

                // Close the database connections
                resultSet.close();
                statement.close();
                connectDB.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle any exceptions that may occur during the database operations
            }
        });


    }
    
    
    
    @FXML
    private void studentReportsButtonOnAction(ActionEvent event) {
    	
    	trainerbuttonsOff();
    	handlestudentReportsButton();
    	vehiclebuttonsOff();
    	 sessionbuttonsOff() ;
    	 testbuttonsOff();
    	 licensebuttonsOff();
    	 paymentbuttonsOff();

    	
    	studentFirstquery.setOnAction(e -> {
    	    // Perform the desired action for studentFirstquery here
    	    System.out.println("Student First Query button clicked!");

    	    // Add your custom code or method calls here
    	    Connecter conn = new Connecter();
    	    try {
    	        Connection connectDB = conn.getConnection();
    	        Statement statement = connectDB.createStatement();
    	        ResultSet resultSet = statement.executeQuery("SELECT * FROM students ORDER BY studentFirstName");

    	        // Create an alert dialog
    	        Alert alert = new Alert(AlertType.INFORMATION);
    	        alert.setTitle("Student Reports");
    	        alert.setHeaderText(null);

    	        // Set the monospaced font for the alert dialog
    	        Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
    	        alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
    	        alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

    	        StringBuilder data = new StringBuilder();

    	        // Append the column headers
    	        data.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
    	                "Student ID", "First Name", "Middle Name", "Last Name", "City Address",
    	                "Street Address", "Phone 1", "Phone 2", "Wallet", "Gender", "Birthdate"));

    	        // Append the separator line
    	        data.append("-".repeat(15 * 11)).append("\n");

    	        // Iterate over the result set and append the data
    	        while (resultSet.next()) {
    	            int studentID = resultSet.getInt("studentId");
    	            String firstName = resultSet.getString("studentFirstName");
    	            String middleName = resultSet.getString("studentmiddleName");
    	            String lastName = resultSet.getString("studentLastName");
    	            String cityAddress = resultSet.getString("cityAddress");
    	            String streetAddress = resultSet.getString("streetAddress");
    	            int phone1 = resultSet.getInt("phone1");
    	            int phone2 = resultSet.getInt("phone2");
    	            int wallet = resultSet.getInt("wallet");
    	            String gender = resultSet.getString("gender");
    	            Date birthdate = resultSet.getDate("birthdate");

    	            // Append the formatted data
    	            data.append(String.format("%-15d %-15s %-15s %-15s %-15s %-15s %-15d %-15d %-15d %-15s %-15s\n",
    	                    studentID, firstName, middleName, lastName, cityAddress, streetAddress,
    	                    phone1, phone2, wallet, gender, birthdate));
    	        }

    	        // Set the content text of the alert dialog
    	        alert.setContentText(data.toString());

    	        // Set the width of the alert dialog
    	        DialogPane dialogPane = alert.getDialogPane();
    	        dialogPane.setMinWidth(1400); // Adjust the desired width

    	        // Show the alert dialog
    	        alert.showAndWait();

    	        // Close the database connections
    	        resultSet.close();
    	        statement.close();
    	        connectDB.close();
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	        // Handle any exceptions that may occur during the database operations
    	    }
    	});
    	
    	
    	
    	studentSecondquery.setOnAction(e -> {
    	    // Perform the desired action for studentSecondquery here
    	    System.out.println("Student Second Query button clicked!");

    	    // Add your custom code or method calls here
    	    Connecter conn = new Connecter();
    	    try {
    	        Connection connectDB = conn.getConnection();
    	        Statement statement = connectDB.createStatement();
    	        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS totalStudents FROM students");

    	        if (resultSet.next()) {
    	            int totalStudents = resultSet.getInt("totalStudents");

    	            // Create an alert dialog to display the number of students
    	            Alert alert = new Alert(AlertType.INFORMATION);
    	            alert.setTitle("Number of Students");
    	            alert.setHeaderText(null);
    	            alert.setContentText("Total Students: " + totalStudents);
    	            alert.showAndWait();
    	        }

    	        // Close the database connections
    	        resultSet.close();
    	        statement.close();
    	        connectDB.close();
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	        // Handle any exceptions that may occur during the database operations
    	    }
    	});

    	
    	studentThirdquery.setOnAction(e -> {
    	    // Perform the desired action for studentThirdquery here
    	    System.out.println("Student Third Query button clicked!");

    	    // Add your custom code or method calls here
    	    Connecter conn = new Connecter();
    	    try {
    	        Connection connectDB = conn.getConnection();
    	        Statement statement = connectDB.createStatement();

    	        // Retrieve the count of passed students
    	        ResultSet resultSetCount = statement.executeQuery("SELECT COUNT(*) AS totalPassedStudents FROM students AS s " +
    	                "JOIN Test AS t ON s.studentId = t.StudentId " +
    	                "WHERE t.Tresult = 'Pass'");

    	        if (resultSetCount.next()) {
    	            int totalPassedStudents = resultSetCount.getInt("totalPassedStudents");

    	            // Create a StringBuilder to store the content of the alert dialog
    	            StringBuilder alertContent = new StringBuilder();

    	            // Append the count of passed students at the beginning
    	            alertContent.append("Total Passed Students: ").append(totalPassedStudents).append("\n\n");

    	            // Retrieve the details of the passed students along with the test information
    	            ResultSet resultSet = statement.executeQuery("SELECT s.studentId, s.studentFirstName, s.studentLastName, s.phone1, " +
    	                    "t.Tid, t.Tresult, t.Pdate " +
    	                    "FROM students AS s " +
    	                    "JOIN Test AS t ON s.studentId = t.StudentId " +
    	                    "WHERE t.Tresult = 'Pass'");

    	            // Append the column headers for student details and test information
    	            alertContent.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
    	                    "Student ID", "First Name", "Last Name", "Phone", "Test ID", "Test Result", "Test Date"));

    	            // Append the separator line
    	            alertContent.append("-".repeat(15 * 7)).append("\n");

    	            // Iterate over the result set and append the data
    	            while (resultSet.next()) {
    	                int studentID = resultSet.getInt("studentId");
    	                String firstName = resultSet.getString("studentFirstName");
    	                String lastName = resultSet.getString("studentLastName");
    	                int phone = resultSet.getInt("phone1");
    	                int testID = resultSet.getInt("Tid");
    	                String testResult = resultSet.getString("Tresult");
    	                Date testDate = resultSet.getDate("Pdate");

    	                // Append the formatted data
    	                alertContent.append(String.format("%-15d %-15s %-15s %-15d %-15d %-15s %-15s\n",
    	                        studentID, firstName, lastName, phone, testID, testResult, testDate));
    	            }

    	            // Create an alert dialog
    	            Alert alert = new Alert(AlertType.INFORMATION);
    	            alert.setTitle("Passed Students Details");
    	            alert.setHeaderText(null);

    	            // Set the monospaced font for the alert dialog
    	            Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
    	            alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
    	            alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

    	            // Set the content text of the alert dialog
    	            alert.setContentText(alertContent.toString());

    	            // Set the width of the alert dialog
    	            DialogPane dialogPane = alert.getDialogPane();
    	            dialogPane.setMinWidth(900); // Adjust the desired width

    	            // Show the alert dialog
    	            alert.showAndWait();

    	            // Close the database connections
    	            resultSetCount.close();
    	            resultSet.close();
    	            statement.close();
    	            connectDB.close();
    	        }
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	        // Handle any exceptions that may occur during the database operations
    	    }
    	});
    	
    	
    	studentFourthquery.setOnAction(e -> {
    	    // Perform the desired action for studentFourthquery here
    	    System.out.println("Student Fourth Query button clicked!");

    	    // Add your custom code or method calls here
    	    Connecter conn = new Connecter();
    	    try {
    	        Connection connectDB = conn.getConnection();
    	        Statement statement = connectDB.createStatement();

    	        // Retrieve the count of failed students who have no passed tests
    	        ResultSet resultSetCount = statement.executeQuery("SELECT COUNT(*) AS totalFailedStudents " +
    	                "FROM students AS s " +
    	                "JOIN Test AS t ON s.studentId = t.StudentId " +
    	                "LEFT JOIN Test AS t_pass ON s.studentId = t_pass.StudentId AND t_pass.Tresult = 'Pass' " +
    	                "WHERE t.Tresult = 'Fail' AND t_pass.Tid IS NULL");

    	        if (resultSetCount.next()) {
    	            int totalFailedStudents = resultSetCount.getInt("totalFailedStudents");

    	            // Create a StringBuilder to store the content of the alert dialog
    	            StringBuilder alertContent = new StringBuilder();

    	            // Append the count of failed students at the beginning
    	            alertContent.append("Total Failed Students (No Passed Tests): ").append(totalFailedStudents).append("\n\n");

    	            // Retrieve the details of the failed students who have no passed tests along with the test information
    	            ResultSet resultSet = statement.executeQuery("SELECT s.studentId, s.studentFirstName, s.studentLastName, s.phone1, " +
    	                    "t.Tid, t.Tresult, t.Pdate " +
    	                    "FROM students AS s " +
    	                    "JOIN Test AS t ON s.studentId = t.StudentId " +
    	                    "LEFT JOIN Test AS t_pass ON s.studentId = t_pass.StudentId AND t_pass.Tresult = 'Pass' " +
    	                    "WHERE t.Tresult = 'Fail' AND t_pass.Tid IS NULL");

    	            // Append the column headers for student details and test information
    	            alertContent.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
    	                    "Student ID", "First Name", "Last Name", "Phone", "Test ID", "Test Result", "Test Date"));

    	            // Append the separator line
    	            alertContent.append("-".repeat(15 * 7)).append("\n");

    	            // Iterate over the result set and append the data
    	            while (resultSet.next()) {
    	                int studentID = resultSet.getInt("studentId");
    	                String firstName = resultSet.getString("studentFirstName");
    	                String lastName = resultSet.getString("studentLastName");
    	                int phone = resultSet.getInt("phone1");
    	                int testID = resultSet.getInt("Tid");
    	                String testResult = resultSet.getString("Tresult");
    	                Date testDate = resultSet.getDate("Pdate");

    	                // Append the formatted data
    	                alertContent.append(String.format("%-15d %-15s %-15s %-15d %-15d %-15s %-15s\n",
    	                        studentID, firstName, lastName, phone, testID, testResult, testDate));
    	            }

    	            // Create an alert dialog
    	            Alert alert = new Alert(AlertType.INFORMATION);
    	            alert.setTitle("Failed Students Details (No Passed Tests)");
    	            alert.setHeaderText(null);

    	            // Set the monospaced font for the alert dialog
    	            Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
    	            alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
    	            alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

    	            // Set the content text of the alert dialog
    	            alert.setContentText(alertContent.toString());

    	            // Set the width of the alert dialog
    	            DialogPane dialogPane = alert.getDialogPane();
    	            dialogPane.setMinWidth(900); // Adjust the desired width

    	            // Show the alert dialog
    	            alert.showAndWait();

    	            // Close the database connections
    	            resultSetCount.close();
    	            resultSet.close();
    	            statement.close();
    	            connectDB.close();
    	        }
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	        // Handle any exceptions that may occur during the database operations
    	    }
    	});


    	studentFifthquery.setOnAction(e -> {
    	    // Perform the desired action for studentFifthquery here
    	    System.out.println("Student Fifth Query button clicked!");

    	    // Add your custom code or method calls here
    	    Connecter conn = new Connecter();
    	    try {
    	        Connection connectDB = conn.getConnection();
    	        Statement statement = connectDB.createStatement();

    	        // Retrieve the count of students who have not taken the test (neither passed nor failed)
    	        ResultSet resultSetCount = statement.executeQuery("SELECT COUNT(*) AS totalNotTakenStudents FROM students AS s " +
    	                "LEFT JOIN Test AS t ON s.studentId = t.StudentId " +
    	                "WHERE t.StudentId IS NULL");

    	        if (resultSetCount.next()) {
    	            int totalNotTakenStudents = resultSetCount.getInt("totalNotTakenStudents");

    	            // Create a StringBuilder to store the content of the alert dialog
    	            StringBuilder alertContent = new StringBuilder();

    	            // Append the count of students who have not taken the test at the beginning
    	            alertContent.append("Total Students Not Taken the Test: ").append(totalNotTakenStudents).append("\n\n");

    	            // Retrieve the details of the students who have not taken the test
    	            ResultSet resultSet = statement.executeQuery("SELECT s.studentId, s.studentFirstName, s.studentLastName, s.phone1 " +
    	                    "FROM students AS s " +
    	                    "LEFT JOIN Test AS t ON s.studentId = t.StudentId " +
    	                    "WHERE t.StudentId IS NULL");

    	            // Append the column headers for student details
    	            alertContent.append(String.format("%-15s %-15s %-15s %-15s\n",
    	                    "Student ID", "First Name", "Last Name", "Phone"));

    	            // Append the separator line
    	            alertContent.append("-".repeat(15 * 4)).append("\n");

    	            // Iterate over the result set and append the data
    	            while (resultSet.next()) {
    	                int studentID = resultSet.getInt("s.studentId"); // Specify the table name for studentId column
    	                String firstName = resultSet.getString("studentFirstName");
    	                String lastName = resultSet.getString("studentLastName");
    	                int phone = resultSet.getInt("phone1");

    	                // Append the formatted data
    	                alertContent.append(String.format("%-15d %-15s %-15s %-15d\n",
    	                        studentID, firstName, lastName, phone));
    	            }

    	            // Create an alert dialog
    	            Alert alert = new Alert(AlertType.INFORMATION);
    	            alert.setTitle("Students Not Taken the Test");
    	            alert.setHeaderText(null);

    	            // Set the monospaced font for the alert dialog
    	            Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
    	            alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
    	            alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

    	            // Set the content text of the alert dialog
    	            alert.setContentText(alertContent.toString());

    	            // Set the width of the alert dialog
    	            DialogPane dialogPane = alert.getDialogPane();
    	            dialogPane.setMinWidth(600); // Adjust the desired width

    	            // Show the alert dialog
    	            alert.showAndWait();

    	            // Close the database connections
    	            resultSetCount.close();
    	            resultSet.close();
    	            statement.close();
    	            connectDB.close();
    	        }
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	        // Handle any exceptions that may occur during the database operations
    	    }
    	});
    	
    	
    	studentSixthquery.setOnAction(e -> {
    	    // Perform the desired action for studentSixthquery here
    	    System.out.println("Student Sixth Query button clicked!");

    	    // Create an input dialog to prompt for the student ID
    	    TextInputDialog dialog = new TextInputDialog();
    	    dialog.setTitle("Student ID");
    	    dialog.setHeaderText(null);
    	    dialog.setContentText("Enter the student ID:");

    	    // Show the input dialog and wait for the user's input
    	    Optional<String> result = dialog.showAndWait();

    	    // Process the user's input if available
    	    result.ifPresent(studentIdInput -> {
    	        int studentId = Integer.parseInt(studentIdInput.trim());

    	        // Add your custom code or method calls here
    	        Connecter conn = new Connecter();
    	        try {
    	            Connection connectDB = conn.getConnection();
    	            Statement statement = connectDB.createStatement();

    	            // Retrieve the student's full name and the associated trainer ID and name, if available
    	            String query = "SELECT s.studentFirstName, s.studentLastName, st.trainerId, t.trainerFirstName, t.trainerLastName " +
    	                    "FROM students AS s " +
    	                    "LEFT JOIN student_trainer AS st ON s.studentId = st.studentId " +
    	                    "LEFT JOIN trainer AS t ON st.trainerId = t.trainerId " +
    	                    "WHERE s.studentId = " + studentId;

    	            ResultSet resultSet = statement.executeQuery(query);

    	            // Check if a record was found for the provided student ID
    	            if (resultSet.next()) {
    	                String studentFirstName = resultSet.getString("studentFirstName");
    	                String studentLastName = resultSet.getString("studentLastName");
    	                int trainerId = resultSet.getInt("trainerId");
    	                String trainerFirstName = resultSet.getString("trainerFirstName");
    	                String trainerLastName = resultSet.getString("trainerLastName");

    	                // Create an alert dialog
    	                Alert alert = new Alert(AlertType.INFORMATION);
    	                alert.setTitle("Student Details");
    	                alert.setHeaderText(null);
    	                alert.setContentText("Student ID: " + studentId +
    	                        "\nFull Name: " + studentFirstName + " " + studentLastName +
    	                        "\n\nTrainer ID: " + trainerId +
    	                        "\nTrainer Name: " + trainerFirstName + " " + trainerLastName);
    	                alert.showAndWait();
    	            } else {
    	                // If no record was found, display an error message
    	                Alert alert = new Alert(AlertType.ERROR);
    	                alert.setTitle("Student Not Found");
    	                alert.setHeaderText(null);
    	                alert.setContentText("No student found with ID: " + studentId);
    	                alert.showAndWait();
    	            }

    	            // Close the database connections
    	            resultSet.close();
    	            statement.close();
    	            connectDB.close();
    	        } catch (SQLException ex) {
    	            ex.printStackTrace();
    	            // Handle any exceptions that may occur during the database operations
    	        }
    	    });
    	});
    	
    	
    	
    	studentSeventhquery.setOnAction(e -> {
    	    // Perform the desired action for studentSeventhquery here
    	    System.out.println("Student Seventh Query button clicked!");

    	    // Add your custom code or method calls here
    	    Connecter conn = new Connecter();
    	    try {
    	        Connection connectDB = conn.getConnection();
    	        Statement statement = connectDB.createStatement();

    	        // Retrieve the student ID, full name, and license information for students with licenses, and order the display by student ID
    	        String query = "SELECT s.studentId, CONCAT(s.studentFirstName, ' ', s.studentmiddleName, ' ', s.studentLastName) AS fullName, l.licenseId, l.licensetype " +
    	                "FROM students AS s " +
    	                "INNER JOIN license AS l ON s.studentId = l.studentId " +
    	                "ORDER BY s.studentId";

    	        ResultSet resultSet = statement.executeQuery(query);

    	        // Create a StringBuilder to store the information
    	        StringBuilder sb = new StringBuilder();
    	        sb.append(String.format("%-15s %-30s %-15s %-15s\n", "Student ID", "Full Name", "License ID", "License Type"));
    	        sb.append("-".repeat(15)).append(" ").append("-".repeat(30)).append(" ").append("-".repeat(15)).append(" ").append("-".repeat(15)).append("\n");

    	        // Iterate through the result set and append the information to the StringBuilder
    	        while (resultSet.next()) {
    	            int studentId = resultSet.getInt("studentId");
    	            String fullName = resultSet.getString("fullName");
    	            int licenseId = resultSet.getInt("licenseId");
    	            String licenseType = resultSet.getString("licensetype");

    	            sb.append(String.format("%-15d %-30s %-15d %-15s\n", studentId, fullName, licenseId, licenseType));
    	        }

    	        // Create an alert dialog to display the information
    	        Alert alert = new Alert(AlertType.INFORMATION);
    	        alert.setTitle("Students with Licenses");
    	        alert.setHeaderText(null);

    	        // Set the monospaced font for the alert dialog
    	        Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
    	        alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
    	        alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

    	        // Set the content text of the alert dialog
    	        alert.setContentText(sb.toString());
    	        
    	     // Set the width of the alert dialog
    	        DialogPane dialogPane = alert.getDialogPane();
    	        dialogPane.setMinWidth(800); // Adjust the desired width
    	        
    	        alert.showAndWait();

    	        // Close the database connections
    	        resultSet.close();
    	        statement.close();
    	        connectDB.close();
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	        // Handle any exceptions that may occur during the database operations
    	    }
    	});


    	
    }
    
    @FXML
    private void vehicleReportsButtonOnAction(ActionEvent event) {
    	
    	handlevehicleReportsButton();
    	trainerbuttonsOff();
    	studentbuttonsOff();
    	 sessionbuttonsOff() ;
    	 testbuttonsOff();
    	 licensebuttonsOff();
    	 paymentbuttonsOff();

    	
    	
    	vehicleFirstquery.setOnAction(e -> {
    	    // Perform the desired action for vehicleFirstquery here
    	    System.out.println("Vehicle First Query button clicked!");

    	    // Add your custom code or method calls here
    	    Connecter conn = new Connecter();
    	    try {
    	        Connection connectDB = conn.getConnection();
    	        Statement statement = connectDB.createStatement();
    	        ResultSet resultSet = statement.executeQuery("SELECT plateNumber, trainerID, vehicleModel, transmissionType, insuranceDate, licenseDate FROM vehicle ORDER BY plateNumber");

    	        // Create an alert dialog
    	        Alert alert = new Alert(AlertType.INFORMATION);
    	        alert.setTitle("Vehicle Reports");
    	        alert.setHeaderText(null);

    	        // Set the monospaced font for the alert dialog
    	        Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
    	        alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
    	        alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

    	        StringBuilder data = new StringBuilder();

    	        // Append the column headers
    	        data.append(String.format("%-15s %-15s %-15s %-20s %-15s %-15s\n",
    	                "Plate Number", "Trainer ID", "Vehicle Model", "Transmission Type", "Insurance Date", "License Date"));

    	        // Append the separator line
    	        data.append("-".repeat(15 * 6 + 5 + 20)).append("\n");

    	        // Iterate over the result set and append the data
    	        while (resultSet.next()) {
    	            int plateNumber = resultSet.getInt("plateNumber");
    	            int trainerID = resultSet.getInt("trainerID");
    	            String vehicleModel = resultSet.getString("vehicleModel");
    	            String transmissionType = resultSet.getString("transmissionType");
    	            Date insuranceDate = resultSet.getDate("insuranceDate");
    	            Date licenseDate = resultSet.getDate("licenseDate");

    	            // Append the formatted data
    	            data.append(String.format("%-15d %-15d %-15s %-20s %-15s %-15s\n",
    	                    plateNumber, trainerID, vehicleModel, transmissionType, insuranceDate, licenseDate));
    	        }

    	        // Set the content text of the alert dialog
    	        alert.setContentText(data.toString());

    	        // Set the width of the alert dialog
    	        DialogPane dialogPane = alert.getDialogPane();
    	        dialogPane.setMinWidth(900); // Adjust the desired width

    	        // Show the alert dialog
    	        alert.showAndWait();

    	        // Close the database connections
    	        resultSet.close();
    	        statement.close();
    	        connectDB.close();
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	        // Handle any exceptions that may occur during the database operations
    	    }
    	});
    	
    	vehicleSecondquery.setOnAction(e -> {
    	    // Perform the desired action for vehicleSecondquery here
    	    System.out.println("Vehicle Second Query button clicked!");

    	    // Add your custom code or method calls here
    	    Connecter conn = new Connecter();
    	    try {
    	        Connection connectDB = conn.getConnection();
    	        Statement statement = connectDB.createStatement();
    	        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS totalVehicles FROM vehicle");

    	        if (resultSet.next()) {
    	            int totalVehicles = resultSet.getInt("totalVehicles");

    	            // Create an alert dialog to display the number of vehicles
    	            Alert alert = new Alert(AlertType.INFORMATION);
    	            alert.setTitle("Number of Vehicles");
    	            alert.setHeaderText(null);
    	            alert.setContentText("Total Vehicles: " + totalVehicles);
    	            alert.showAndWait();
    	        }

    	        // Close the database connections
    	        resultSet.close();
    	        statement.close();
    	        connectDB.close();
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	        // Handle any exceptions that may occur during the database operations
    	    }
    	});
    	
    	
    
    	
    	vehicleThirdquery.setOnAction(e -> {
    	    // Perform the desired action for vehicleThirdquery here
    	    System.out.println("Vehicle Third Query button clicked!");

    	    // Add your custom code or method calls here
    	    Connecter conn = new Connecter();
    	    try {
    	        Connection connectDB = conn.getConnection();
    	        Statement statement = connectDB.createStatement();
    	        ResultSet resultSet = statement.executeQuery("SELECT plateNumber, trainerID, vehicleModel, productionYear, insuranceDate, licenseDate FROM vehicle WHERE trainerID IS NULL");

    	        // Create an alert dialog
    	        Alert alert = new Alert(AlertType.INFORMATION);
    	        alert.setTitle("Vehicles without Trainer");
    	        alert.setContentText(null);

    	        // Set the monospaced font for the alert dialog
    	        Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
    	        alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
    	        alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

    	        StringBuilder data = new StringBuilder();

    	        // Append the column headers
    	        data.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s\n",
    	                "Plate Number", "Trainer ID", "Vehicle Model", "Production Year", "Insurance Date", "License Date"));

    	        // Append the separator line
    	        data.append("-".repeat(15 * 6)).append("\n");

    	        // Iterate over the result set and append the data
    	        while (resultSet.next()) {
    	            int plateNumber = resultSet.getInt("plateNumber");
    	            int trainerID = resultSet.getInt("trainerID");
    	            String vehicleModel = resultSet.getString("vehicleModel");
    	            int productionYear = resultSet.getInt("productionYear");
    	            Date insuranceDate = resultSet.getDate("insuranceDate");
    	            Date licenseDate = resultSet.getDate("licenseDate");

    	            // Append the formatted data
    	            data.append(String.format("%-15d %-15d %-15s %-15d %-15s %-15s\n",
    	                    plateNumber, trainerID, vehicleModel, productionYear, insuranceDate, licenseDate));
    	        }

    	        // Close the result set and statement
    	        resultSet.close();
    	        statement.close();

    	        // Create a new statement for the count query
    	        Statement countStatement = connectDB.createStatement();

    	        // Get the count of vehicles without a trainer
    	        ResultSet countResultSet = countStatement.executeQuery("SELECT COUNT(*) AS count FROM vehicle WHERE trainerID IS NULL");
    	        countResultSet.next();
    	        int count = countResultSet.getInt("count");

    	        // Close the count result set and statement
    	        countResultSet.close();
    	        countStatement.close();

    	        // Set the header text with the count
    	        alert.setHeaderText("Count: " + count);

    	        // Set the content text of the alert dialog
    	        alert.setContentText(data.toString());

    	        // Set the width of the alert dialog
    	        DialogPane dialogPane = alert.getDialogPane();
    	        dialogPane.setMinWidth(800); // Adjust the desired width

    	        // Show the alert dialog
    	        alert.showAndWait();

    	        // Close the database connection
    	        connectDB.close();
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	        // Handle any exceptions that may occur during the database operations
    	    }
    	});




	   }
    
    
    @FXML
    private void sessionReportsButtonOnAction(ActionEvent event) {
    	
    	handleSessionReportsButton();
    	  studentbuttonsOff();
          vehiclebuttonsOff();
        trainerbuttonsOff();
        testbuttonsOff();
        licensebuttonsOff();
        paymentbuttonsOff();

        
        sessionFirstquery.setOnAction(e -> {
            // Perform the desired action for sessionFirstquery here
            System.out.println("Session First Query button clicked!");

            // Add your custom code or method calls here
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();
                Statement statement = connectDB.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM session ORDER BY sessionId");

                // Create an alert dialog
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Session Reports");
                alert.setHeaderText(null);

                // Set the monospaced font for the alert dialog
                Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
                alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
                alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

                StringBuilder data = new StringBuilder();

                // Append the column headers
                data.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-20s %-15s %-15s\n",
                        "Session ID", "Student ID", "Plate Number", "Trainer ID", "Session Cost", "Session Completed",
                        "Session Day", "Session Time", "Session Date", "Session Status"));

                // Append the separator line
                data.append("-".repeat(15 * 11)).append("\n");

                // Iterate over the result set and append the data
                while (resultSet.next()) {
                    int sessionId = resultSet.getInt("sessionId");
                    int studentID = resultSet.getInt("studentID");
                    int plateNumber = resultSet.getInt("plateNumber");
                    int trainerID = resultSet.getInt("trainerID");
                    int sessionCost = resultSet.getInt("sessionCost");
                    int sessionCompleted = resultSet.getInt("sessioncompleted");
                    String sessionDay = resultSet.getString("sessionDay");
                    String sessionTime = resultSet.getString("sessionTime");
                    Date sessionDate = resultSet.getDate("sessionDate");
                    String sessionStatus = resultSet.getString("sessionStatus");

                    // Append the formatted data
                    data.append(String.format("%-15d %-15d %-15d %-15d %-15d %-15d %-15s %-20s %-15s %-15s\n",
                            sessionId, studentID, plateNumber, trainerID, sessionCost, sessionCompleted,
                            sessionDay, sessionTime, sessionDate, sessionStatus));
                }

                // Set the content text of the alert dialog
                alert.setContentText(data.toString());

                // Set the width of the alert dialog
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setMinWidth(1400); // Adjust the desired width

                // Show the alert dialog
                alert.showAndWait();

                // Close the database connections
                resultSet.close();
                statement.close();
                connectDB.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle any exceptions that may occur during the database operations
            }
        });
        
        sessionSecondquery.setOnAction(e -> {
            // Perform the desired action for sessionSecondquery here
            System.out.println("Session Second Query button clicked!");

            // Add your custom code or method calls here
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();
                Statement statement = connectDB.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS totalSessions FROM session");

                if (resultSet.next()) {
                    int totalSessions = resultSet.getInt("totalSessions");

                    // Create an alert dialog to display the number of sessions
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Number of Sessions");
                    alert.setHeaderText(null);
                    alert.setContentText("Total Sessions: " + totalSessions);
                    alert.showAndWait();
                }

                // Close the database connections
                resultSet.close();
                statement.close();
                connectDB.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle any exceptions that may occur during the database operations
            }
        });


        
        
    }
    
    
    @FXML
    private void testReportsButtonOnAction(ActionEvent event) {
    	
    	handletestReportsButton();
    	 studentbuttonsOff();
         vehiclebuttonsOff();
         sessionbuttonsOff() ;
        trainerbuttonsOff();
        licensebuttonsOff();
        paymentbuttonsOff();

        
        testFirstquery.setOnAction(e -> {
            // Perform the desired action for testFirstquery here
            System.out.println("Test First Query button clicked!");

            // Add your custom code or method calls here
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();
                Statement statement = connectDB.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Test");

                // Create an alert dialog
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Test Reports");
                alert.setHeaderText(null);

                // Set the monospaced font for the alert dialog
                Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
                alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
                alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

                StringBuilder data = new StringBuilder();

                // Append the column headers
                data.append(String.format("%-10s %-15s %-15s %-15s\n",
                        "Test ID", "Result", "Date", "Student ID"));

                // Append the separator line
                data.append("-".repeat(10 + 15 + 15 + 15)).append("\n");

                // Iterate over the result set and append the data
                while (resultSet.next()) {
                    int testID = resultSet.getInt("Tid");
                    String result = resultSet.getString("Tresult");
                    Date date = resultSet.getDate("Pdate");
                    int studentID = resultSet.getInt("StudentId");

                    // Append the formatted data
                    data.append(String.format("%-10d %-15s %-15s %-15d\n",
                            testID, result, date, studentID));
                }

                // Set the content text of the alert dialog
                alert.setContentText(data.toString());

                // Set the width of the alert dialog
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setMinWidth(600); // Adjust the desired width

                // Show the alert dialog
                alert.showAndWait();

                // Close the database connections
                resultSet.close();
                statement.close();
                connectDB.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle any exceptions that may occur during the database operations
            }
        });
        
        
        testSecondquery.setOnAction(e -> {
            // Perform the desired action for testSecondquery here
            System.out.println("Test Second Query button clicked!");

            // Add your custom code or method calls here
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();
                Statement statement = connectDB.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS totalTests FROM Test");

                if (resultSet.next()) {
                    int totalTests = resultSet.getInt("totalTests");

                    // Create an alert dialog to display the number of tests
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Number of Tests");
                    alert.setHeaderText(null);
                    alert.setContentText("Total Tests: " + totalTests);
                    alert.showAndWait();
                }

                // Close the database connections
                resultSet.close();
                statement.close();
                connectDB.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle any exceptions that may occur during the database operations
            }
        });



    
    }
    
    
    
    @FXML
    private void licenseReportsButtonOnAction(ActionEvent event) {

        handleLicenceReportsButton();
        studentbuttonsOff();
        vehiclebuttonsOff();
        sessionbuttonsOff();
        trainerbuttonsOff();
        testbuttonsOff();
        paymentbuttonsOff();

        licenseFirstquery.setOnAction(e -> {
            // Perform the desired action for licenseFirstQuery here
            System.out.println("License First Query button clicked!");

            // Add your custom code or method calls here
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();
                Statement statement = connectDB.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM license");

                // Create an alert dialog
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("License Reports");
                alert.setHeaderText(null);

                // Set the monospaced font for the alert dialog
                Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
                alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
                alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

                StringBuilder data = new StringBuilder();

                // Append the column headers
                data.append(String.format("%-10s %-15s %-15s\n",
                        "Student ID", "License ID", "License Type"));

                // Append the separator line
                data.append("-".repeat(10 + 15 + 15)).append("\n");

                // Iterate over the result set and append the data
                while (resultSet.next()) {
                    int studentID = resultSet.getInt("studentId");
                    int licenseID = resultSet.getInt("licenseId");
                    String licenseType = resultSet.getString("licensetype");

                    // Append the formatted data
                    data.append(String.format("%-10d %-15d %-15s\n",
                            studentID, licenseID, licenseType));
                }

                // Set the content text of the alert dialog
                alert.setContentText(data.toString());

                // Set the width of the alert dialog
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setMinWidth(600); // Adjust the desired width

                // Show the alert dialog
                alert.showAndWait();

                // Close the database connections
                resultSet.close();
                statement.close();
                connectDB.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle any exceptions that may occur during the database operations
            }
        });
        
        
        licenceSecondquery.setOnAction(e -> {
            // Perform the desired action for licenseSecondQuery here
            System.out.println("License Second Query button clicked!");

            // Add your custom code or method calls here
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();
                Statement statement = connectDB.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS totalLicenses FROM license");

                if (resultSet.next()) {
                    int totalLicenses = resultSet.getInt("totalLicenses");

                    // Create an alert dialog to display the number of licenses
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Number of Licenses");
                    alert.setHeaderText(null);
                    alert.setContentText("Total Licenses: " + totalLicenses);
                    alert.showAndWait();
                }

                // Close the database connections
                resultSet.close();
                statement.close();
                connectDB.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle any exceptions that may occur during the database operations
            }
        });
        
       
        
        licenceThirdquery.setOnAction(e -> {
            // Perform the desired action for licenseThirdQuery here
            System.out.println("License Third Query button clicked!");

            // Add your custom code or method calls here
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();

                // Create an input dialog to get the student ID
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Student ID");
                dialog.setHeaderText("Enter Student ID");
                dialog.setContentText("Student ID:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    String studentIDInput = result.get();

                    // Prepare the query to retrieve the student's licenses and the count
                    PreparedStatement licenseStatement = connectDB.prepareStatement("SELECT * FROM license WHERE studentId = ?");
                    licenseStatement.setString(1, studentIDInput);
                    ResultSet licenseResultSet = licenseStatement.executeQuery();

                    PreparedStatement countStatement = connectDB.prepareStatement("SELECT COUNT(*) AS studentLicenses FROM license WHERE studentId = ?");
                    countStatement.setString(1, studentIDInput);
                    ResultSet countResultSet = countStatement.executeQuery();

                    // Create an alert dialog to display the student's licenses and the count
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Student Licenses");
                    alert.setHeaderText(null);

                    // Set the monospaced font for the alert dialog
                    Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
                    alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
                    alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

                    StringBuilder data = new StringBuilder();

                    // Append the count of student licenses
                    if (countResultSet.next()) {
                        int studentLicenses = countResultSet.getInt("studentLicenses");
                        data.append("Student Licenses: ").append(studentLicenses).append("\n\n");
                    }

                    // Append the column headers
                    data.append(String.format("%-10s %-15s %-15s\n",
                            "Student ID", "License ID", "License Type"));

                    // Append the separator line
                    data.append("-".repeat(10 + 15 + 15)).append("\n");

                    // Iterate over the result set and append the data
                    while (licenseResultSet.next()) {
                        int studentID = licenseResultSet.getInt("studentId");
                        int licenseID = licenseResultSet.getInt("licenseId");
                        String licenseType = licenseResultSet.getString("licensetype");

                        // Append the formatted data
                        data.append(String.format("%-10d %-15d %-15s\n",
                                studentID, licenseID, licenseType));
                    }

                    // Set the content text of the alert dialog
                    alert.setContentText(data.toString());

                    // Set the width of the alert dialog
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setMinWidth(600); // Adjust the desired width

                    // Show the alert dialog
                    alert.showAndWait();

                    // Close the result sets and statements
                    licenseResultSet.close();
                    licenseStatement.close();
                    countResultSet.close();
                    countStatement.close();
                }

                // Close the database connection
                connectDB.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle any exceptions that may occur during the database operations
            }
        });
    }
    
    
    @FXML
    private void paymentReportsButtonOnAction(ActionEvent event) {
    	
    	handlePaymentReportsButton();
    	 studentbuttonsOff();
         vehiclebuttonsOff();
         sessionbuttonsOff();
         trainerbuttonsOff();
         testbuttonsOff();
         licensebuttonsOff();
         
         paymentFirstquery.setOnAction(e -> {
        	    System.out.println("First Query button clicked!");

        	    // Add your custom code or method calls here
        	    Connecter conn = new Connecter();
        	    try {
        	        Connection connectDB = conn.getConnection();
        	        Statement statement = connectDB.createStatement();
        	        ResultSet resultSet = statement.executeQuery("SELECT * FROM payment");

        	        // Create an alert dialog
        	        Alert alert = new Alert(AlertType.INFORMATION);
        	        alert.setTitle("Payment Reports");
        	        alert.setHeaderText(null);

        	        // Set the monospaced font for the alert dialog
        	        Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
        	        alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        	        alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

        	        StringBuilder data = new StringBuilder();

        	        // Append the column headers
        	        data.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s\n",
        	                "Payment ID", "Student ID", "Amount", "Status", "Payment Method", "Payment Date"));

        	        // Append the separator line
        	        data.append("--".repeat(11 * 4)).append("\n");

        	        // Iterate over the result set and append the data
        	        while (resultSet.next()) {
        	            int paymentID = resultSet.getInt("Pid");
        	            int studentID = resultSet.getInt("StudentId");
        	            int amount = resultSet.getInt("amount");
        	            String status = resultSet.getString("Pstatus");
        	            String method = resultSet.getString("Pmethood");
        	            Date date = resultSet.getDate("Pdate");

        	            // Append the formatted data
        	            data.append(String.format("%-15d %-15d %-15d %-15s %-15s %-15s\n",
        	                    paymentID, studentID, amount, status, method, date));
        	        }

        	        // Set the content text of the alert dialog
        	        alert.setContentText(data.toString());

        	        // Set the width of the alert dialog
        	        DialogPane dialogPane = alert.getDialogPane();
        	        dialogPane.setMinWidth(1000); // Adjust the desired width

        	        // Show the alert dialog
        	        alert.showAndWait();

        	        // Close the database connections
        	        resultSet.close();
        	        statement.close();
        	        connectDB.close();
        	    } catch (SQLException ex) {
        	        ex.printStackTrace();
        	        // Handle any exceptions that may occur during the database operations
        	    }
        	});
         
         
         paymentSecondquery.setOnAction(e -> {
        	    System.out.println("Second Query button clicked!");

        	    // Add your custom code or method calls here
        	    Connecter conn = new Connecter();
        	    try {
        	        Connection connectDB = conn.getConnection();
        	        Statement statement = connectDB.createStatement();

        	        // Create an TextInputDialog to get the student ID
        	        TextInputDialog dialog = new TextInputDialog();
        	        dialog.setTitle("Student ID");
        	        dialog.setHeaderText("Enter the Student ID:");
        	        dialog.setContentText("Student ID:");

        	        // Retrieve the entered student ID
        	        Optional<String> result = dialog.showAndWait();
        	        if (result.isPresent()) {
        	            int studentID = Integer.parseInt(result.get());

        	            // Execute the query to check if the student ID exists in the payment table
        	            String query = "SELECT * FROM payment WHERE StudentId = " + studentID;
        	            ResultSet resultSet = statement.executeQuery(query);

        	            // Check if the result set is empty
        	            if (!resultSet.isBeforeFirst()) {
        	                // Student ID not found in the payment table, display an error alert
        	                Alert alert = new Alert(AlertType.ERROR);
        	                alert.setTitle("Student Payments");
        	                alert.setHeaderText(null);
        	                alert.setContentText("Student ID: " + studentID + " not found in the payment table");
        	                alert.showAndWait();
        	            } else {
        	                // Student ID found, retrieve the student's payments
        	                resultSet = statement.executeQuery("SELECT * FROM payment WHERE StudentId = " + studentID);

        	                // Create an alert dialog
        	                Alert alert = new Alert(AlertType.INFORMATION);
        	                alert.setTitle("Student Payments");
        	                alert.setHeaderText(null);

        	                // Set the monospaced font for the alert dialog
        	                Font font = Font.font("Courier New", FontWeight.NORMAL, 12);
        	                alert.getDialogPane().getScene().getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        	                alert.getDialogPane().setStyle("-fx-font-family: 'Courier New';");

        	                StringBuilder data = new StringBuilder();

        	                // Append the column headers
        	                data.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s\n",
        	                        "Payment ID", "Student ID", "Amount", "Status", "Payment Method", "Payment Date"));

        	                // Append the separator line
        	                data.append("--".repeat(11 * 4)).append("\n");

        	                // Iterate over the result set and append the data
        	                while (resultSet.next()) {
        	                    int paymentID = resultSet.getInt("Pid");
        	                    int studentID1 = resultSet.getInt("StudentId");
        	                    int amount = resultSet.getInt("amount");
        	                    String status = resultSet.getString("Pstatus");
        	                    String method = resultSet.getString("Pmethood");
        	                    Date date = resultSet.getDate("Pdate");

        	                    // Append the formatted data
        	                    data.append(String.format("%-15d %-15d %-15d %-15s %-15s %-15s\n",
        	                            paymentID, studentID1, amount, status, method, date));
        	                }

        	                // Set the content text of the alert dialog
        	                alert.setContentText(data.toString());

        	                // Set the width of the alert dialog
        	                DialogPane dialogPane = alert.getDialogPane();
        	                dialogPane.setMinWidth(1000); // Adjust the desired width

        	                // Show the alert dialog
        	                alert.showAndWait();
        	            }

        	            // Close the result set and statement
        	            resultSet.close();
        	            statement.close();
        	        }
        	        // Close the database connection
        	        connectDB.close();
        	    } catch (SQLException ex) {
        	        ex.printStackTrace();
        	        // Handle any exceptions that may occur during the database operations
        	    }
        	});


    	
    }

    
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
