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


public class UpcomingQ implements Initializable {
    //  interface contents ------->

    @FXML
    TableView<Session> SessiontableView = new TableView<>();

    @FXML
    TableColumn<Session, Integer>sessionId;
    @FXML
    TableColumn<Session, Integer> studentId;
    @FXML
    TableColumn<Session, Integer> plateNumber ;
    @FXML
    TableColumn<Session, Integer> trainerID;
    @FXML
    TableColumn<Session, Integer> sessionCost;
    @FXML
    TableColumn<Session, Integer> sessioncompleted;
    @FXML
    TableColumn<Session, String> sessionDay ;
    @FXML
    TableColumn<Session, String> sessionTime ;
    @FXML
    TableColumn<Session, Date> sessionDate ;
    @FXML
    TableColumn<Session, String> sessionStatus ;

    /////////////////////////////////////////////

    // Buttons:
    @FXML
    private Button addSession;
    @FXML
    private Button deleteSession;
    @FXML
    private Button updateSession;
    /////////////////////////////////////////////////////////////////////////////////////
    // textBoxes
    @FXML
    private TextField SessionID;

    @FXML
    private TextField studentID1;

    @FXML
    private TextField plateNumber1;

    @FXML
    private TextField TrainerID;
    @FXML
    private TextField SessionCost;
    @FXML
    private TextField SessionCompleted1;
    @FXML
    private TextField SessionTime1;

    /////combo box
    @FXML
    private  ComboBox SessionDay1;

    @FXML
    private  ComboBox SessionStatus;

    //date box
    @FXML
    private DatePicker SessionDate1;

    ///////////////////////////////////////////////////////////////////////////
    ObservableList <Session> listM;

    int index = -1;

    Connection conn = null;

    ResultSet rs = null;

    PreparedStatement pst = null ;

    @FXML
    private TextField searchTextField;

    String x = Data.UName;

    ObservableList<Session> list = FXCollections.observableArrayList();
    ////////////////////////////////////////////////

    public void initialize(URL url, ResourceBundle rb) {
        // Event handler for selecting a row
        SessiontableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Session selectedSession = SessiontableView.getSelectionModel().getSelectedItem();

                // Fill the text fields with the selected session information
                SessionID.setText(String.valueOf(selectedSession.getSessionId()));
                studentID1.setText(String.valueOf(selectedSession.getStudentId()));
                plateNumber1.setText(String.valueOf(selectedSession.getPlateNumber()));
                TrainerID.setText(String.valueOf(selectedSession.getTrainerId()));
                SessionCost.setText(String.valueOf(selectedSession.getSessionCost()));
                SessionCompleted1.setText(String.valueOf(selectedSession.getSessionCompleted()));
                SessionDay1.setValue(selectedSession.getSessionDay());
                SessionTime1.setText(selectedSession.getSessionTime());
                SessionDate1.setValue(((java.sql.Date) selectedSession.getSessionDate()).toLocalDate());
                SessionStatus.setValue(selectedSession.getSessionStatus());
            }
        });

        Connecter conn = new Connecter();
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

            Connection connectDB = conn.getConnection();
            String viewquery = "SELECT sessionId, studentID, plateNumber, trainerID, sessionCost, sessioncompleted, sessionDay, sessionTime, sessionDate, sessionStatus " +
                    "FROM session " +
                    "WHERE trainerID = " + ID + " AND sessionStatus = 'Scheduled'";
            Statement statement = connectDB.createStatement();
            ResultSet queryoutput = statement.executeQuery(viewquery);

            while (queryoutput.next()) {
                Integer queryID = queryoutput.getInt("sessionId");
                Integer studentID = queryoutput.getInt("studentID");
                Integer plateNumber = queryoutput.getInt("plateNumber");
                Integer trainerID = queryoutput.getInt("trainerID");
                Integer sessionCost = queryoutput.getInt("sessionCost");
                Integer sessionCompleted = queryoutput.getInt("sessioncompleted");
                String sessionDay = queryoutput.getString("sessionDay");
                String sessionTime = queryoutput.getString("sessionTime");
                Date sessionDate = queryoutput.getDate("sessionDate");
                String sessionStatus = queryoutput.getString("sessionStatus");

                Session session = new Session(queryID, studentID, plateNumber, trainerID, sessionCost, sessionCompleted, sessionDay, sessionTime, sessionDate, sessionStatus);
                list.add(session);
            }

            sessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
            studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
            plateNumber.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));
            trainerID.setCellValueFactory(new PropertyValueFactory<>("trainerId"));
            sessionCost.setCellValueFactory(new PropertyValueFactory<>("sessionCost"));
            sessioncompleted.setCellValueFactory(new PropertyValueFactory<>("sessionCompleted"));
            sessionDay.setCellValueFactory(new PropertyValueFactory<>("sessionDay"));
            sessionTime.setCellValueFactory(new PropertyValueFactory<>("sessionTime"));
            sessionDate.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
            sessionStatus.setCellValueFactory(new PropertyValueFactory<>("sessionStatus"));

            SessionDay1.getItems().addAll("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday");
            SessionStatus.getItems().addAll("Complete", "Scheduled");

            SessiontableView.setItems(list);

            // Create a filtered list to hold the filtered data
            FilteredList<Session> filteredList = new FilteredList<>(list);

            // Set the filter predicate for the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(session -> {
                    // If the search text is empty, show all sessions
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Convert search text to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if the session's status contains the search text
                    return session.getSessionStatus().toLowerCase().contains(lowerCaseFilter);
                });
            });

            // Wrap the filtered list in a sorted list to enable sorting
            SortedList<Session> sortedList = new SortedList<>(filteredList);

            // Bind the sorted list to the table view
            sortedList.comparatorProperty().bind(SessiontableView.comparatorProperty());
            SessiontableView.setItems(sortedList);
        } catch (SQLException e1) {
            e1.printStackTrace();
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, e1);
        }
    }


    ///////////////////////////////////////////
    //add a session

    @FXML
    private void addSessionOnAction(ActionEvent event) {
        // Get the values from the text fields
        int sID = Integer.parseInt(SessionID.getText());
        int studID = Integer.parseInt(studentID1.getText());
        int pNum = Integer.parseInt(plateNumber1.getText());
        int tID = Integer.parseInt(TrainerID.getText());
        int sCost = Integer.parseInt(SessionCost.getText());
        int sCompleted = Integer.parseInt(SessionCompleted1.getText());
        String sDay = SessionDay1.getValue().toString();
        String sTime = SessionTime1.getText();
        LocalDate sDate = SessionDate1.getValue();
        String sStatus = SessionStatus.getValue().toString();

        Date sessDate = java.sql.Date.valueOf(sDate);
        // Create a Session object with the entered values

        Session session = new Session(sID, studID,pNum,tID,sCost,sCompleted,sDay,sTime, sessDate,sStatus);

        // Insert the license into the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Check if the payment ID already exists in the table
            String checkQuery = "SELECT COUNT(*) FROM session WHERE sessionId = ?";
            PreparedStatement checkStatement = connectDB.prepareStatement(checkQuery);
            checkStatement.setInt(1, session.getSessionId());
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                // Display a warning message if the ID already exists
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("DuplicateSession ID");
                alert.setHeaderText("Warning: Duplicate Session ID");
                alert.setContentText("The entered Session ID already exists in the database. Please enter a unique ID.");
                alert.showAndWait();
            } else {
                // Insert the License into the table
                String insertQuery = "INSERT INTO session (sessionId, studentID, plateNumber, trainerID, sessionCost,sessioncompleted,sessionDay,sessionTime,sessionDate,sessionStatus) VALUES (?, ?, ?,?,?,?,?,?,?,?)";
                PreparedStatement statement = connectDB.prepareStatement(insertQuery);
                statement.setInt(1, session.getSessionId());
                statement.setInt(2, session.getStudentId());
                statement.setInt(3, session.getPlateNumber());
                statement.setInt(4, session.getTrainerId());
                statement.setInt(5, session.getSessionCost());
                statement.setInt(6, session.getSessionCompleted());
                statement.setString(7, session.getSessionDay());
                statement.setString(8, session.getSessionTime());
                statement.setDate(9, (java.sql.Date) session.getSessionDate());
                statement.setString(10, session.getSessionStatus());
                statement.executeUpdate();

                // Add the session to the table view
                list.add(session);

                // Display a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Session  Added");
                alert.setHeaderText("Success: Session Added");
                alert.setContentText("The Session has been added successfully.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    //////////////////////
    //update session button
    @FXML
    private void updatePaymentOnAction(ActionEvent event) {
        // Check if a Session is selected in the table
        Session selectedSession = SessiontableView.getSelectionModel().getSelectedItem();
        if (selectedSession == null) {
            // Display a warning message if no session is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Session Selected");
            alert.setHeaderText("Warning: No Session selected!");
            alert.setContentText("Please select a Session from the table.");
            alert.showAndWait();
            return;
        }

        // Get the updated values from the text fields
        int sId = Integer.parseInt(SessionID.getText());
        int studentId = Integer.parseInt(studentID1.getText());
        int plateId = Integer.parseInt(plateNumber1.getText());
        int tId = Integer.parseInt(TrainerID.getText());
        int sCost = Integer.parseInt(SessionCost.getText());
        int sComplete = Integer.parseInt(SessionCompleted1.getText());
        String sessionDay = SessionDay1.getValue().toString();
        String sTime = SessionTime1.getText();
        LocalDate sDate = SessionDate1.getValue();
        String sStatus = SessionStatus.getValue().toString();

        // Update the selected session object with the new values
        selectedSession.setSessionId(sId);
        selectedSession.setStudentId(studentId);
        selectedSession.setPlateNumber(plateId);
        selectedSession.setTrainerId(tId);
        selectedSession.setSessionCost(sCost);
        selectedSession.setSessionCompleted(sComplete);
        selectedSession.setSessionDay(sessionDay);
        selectedSession.setSessionTime(sTime);
        selectedSession.setSessionDate(java.sql.Date.valueOf(sDate));
        selectedSession.setSessionStatus(sStatus);

        // Update the session in the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Update the payment information
            String updateQuery = "UPDATE session SET sessionId = ?, studentID = ?, plateNumber = ?, trainerID = ?, sessionCost = ?, sessioncompleted = ?,sessionDay = ?,sessionTime = ?,sessionDate = ?,sessionStatus = ? WHERE sessionId = ?";
            PreparedStatement statement = connectDB.prepareStatement(updateQuery);
            statement.setInt(1, selectedSession.getSessionId());
            statement.setInt(2, selectedSession.getStudentId());
            statement.setInt(3, selectedSession.getPlateNumber());
            statement.setInt(4, selectedSession.getTrainerId());
            statement.setInt(5, selectedSession.getSessionCost());
            statement.setInt(6, selectedSession.getSessionCompleted());
            statement.setString(7, selectedSession.getSessionDay());
            statement.setString(8, selectedSession.getSessionTime());
            statement.setDate(9, (java.sql.Date) selectedSession.getSessionDate());
            statement.setString(10, selectedSession.getSessionStatus());
            statement.setInt(11, selectedSession.getSessionId());
            statement.executeUpdate();

            // Refresh the table view
            SessiontableView.refresh();

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Session Updated");
            alert.setHeaderText("Success: Session Updated");
            alert.setContentText("The Session has been updated successfully.");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    ////////////////////////
    //Delete Session Button
    @FXML
    private void deleteSessionOnAction(ActionEvent event) {
        // Check if a session is selected in the table
        Session selectedSession = SessiontableView.getSelectionModel().getSelectedItem();
        if (selectedSession == null) {
            // Display a warning message if no payment is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Session Selected");
            alert.setHeaderText("Warning: No Session selected!");
            alert.setContentText("Please select a Session from the table.");
            alert.showAndWait();
            return;
        }

        // Show a confirmation dialog to confirm the deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Session");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete the selected Session?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Delete the student from the MySQL table
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();

                // Delete the session using the session ID
                String deleteQuery = "DELETE FROM session WHERE sessionId  = ?";
                PreparedStatement statement = connectDB.prepareStatement(deleteQuery);
                statement.setInt(1, selectedSession.getSessionId());
                statement.executeUpdate();

                // Remove the payment from the table view
                list.remove(selectedSession);

                // Display a success message
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Session Deleted");
                alert1.setHeaderText("Success: Session Deleted");
                alert1.setContentText("The Session has been deleted successfully.");

                alert1.showAndWait();


            } catch (SQLException e) {
                e.printStackTrace();
                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    ///////////////////////////////////////////////////
    //Back Button
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
