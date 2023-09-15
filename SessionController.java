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


public class SessionController implements Initializable {
    //  interface contents ------->

    @FXML
    private Button Home;

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


    ObservableList <Session> listM;

    int index = -1;

    Connection conn = null;

    ResultSet rs = null;

    PreparedStatement pst = null ;

    @FXML
    private TextField searchTextField;

    ObservableList<Session> list = FXCollections.observableArrayList();
    ////////////////////////////////////////////////

    public void initialize (URL url , ResourceBundle rb) {

        try {
            Connection connectDB = new Connecter().getConnection();
            String viewquery = "SELECT sessionId, studentID, plateNumber, trainerID, sessionCost, sessioncompleted, sessionDay, sessionTime, sessionDate, sessionStatus FROM session";
            Statement statement = connectDB.createStatement();
            ResultSet queryoutput = statement.executeQuery(viewquery);

            // Clear the list before loading the data
            list.clear();

            while (queryoutput.next()) {
                Integer sessionId = queryoutput.getInt("sessionId");
                Integer studentID = queryoutput.getInt("studentID");
                Integer plateNumber = queryoutput.getInt("plateNumber");
                Integer trainerID = queryoutput.getInt("trainerID");
                Integer sessionCost = queryoutput.getInt("sessionCost");
                Integer sessionCompleted = queryoutput.getInt("sessioncompleted");
                String sessionDay = queryoutput.getString("sessionDay");
                String sessionTime = queryoutput.getString("sessionTime");
                Date sessionDate = queryoutput.getDate("sessionDate");
                String sessionStatus = queryoutput.getString("sessionStatus");

                Session session = new Session(sessionId, studentID, plateNumber, trainerID, sessionCost, sessionCompleted, sessionDay, sessionTime, sessionDate, sessionStatus);
                list.add(session);
            }

            sessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
            studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
            plateNumber.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));
            trainerID.setCellValueFactory(new PropertyValueFactory<>("trainerID"));
            sessionCost.setCellValueFactory(new PropertyValueFactory<>("sessionCost"));
            sessioncompleted.setCellValueFactory(new PropertyValueFactory<>("sessionCompleted"));
            sessionDay.setCellValueFactory(new PropertyValueFactory<>("sessionDay"));
            sessionTime.setCellValueFactory(new PropertyValueFactory<>("sessionTime"));
            sessionDate.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
            sessionStatus.setCellValueFactory(new PropertyValueFactory<>("sessionStatus"));

            SessiontableView.setItems(list);


            // Create a filtered list based on the original list
            FilteredList<Session> filteredList = new FilteredList<>(list, p -> true);

            // Add a change listener to the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(session -> {
                    if (newValue == null || newValue.isEmpty()) {
                        // Show all sessions when search text is empty
                        return true;
                    }

                    // Convert the search text to lowercase for case-insensitive search
                    String searchText = newValue.toLowerCase();

                    // Check if any session attributes contain the search text
                    return String.valueOf(session.getSessionId()).toLowerCase().contains(searchText)
                            || String.valueOf(session.getStudentId()).toLowerCase().contains(searchText)
                            || String.valueOf(session.getPlateNumber()).toLowerCase().contains(searchText)
                            || String.valueOf(session.getTrainerID()).toLowerCase().contains(searchText)
                            || String.valueOf(session.getSessionCost()).toLowerCase().contains(searchText)
                            || String.valueOf(session.getSessionCompleted()).toLowerCase().contains(searchText)
                            || session.getSessionDay().toLowerCase().contains(searchText)
                            || session.getSessionTime().toLowerCase().contains(searchText)
                            || session.getSessionDate().toString().toLowerCase().contains(searchText)
                            || session.getSessionStatus().toLowerCase().contains(searchText);
                });
            });

            // Create a sorted list from the filtered list
            SortedList<Session> sortedList = new SortedList<>(filteredList);

            // Bind the sorted list to the table view
            sortedList.comparatorProperty().bind(SessiontableView.comparatorProperty());
            SessiontableView.setItems(sortedList);






        } catch (SQLException e1) {
            e1.printStackTrace();
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, e1);
        }
    }

    ///////////////////////////////////////////////////
    //Back Button


    @FXML
    private Button home;

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
