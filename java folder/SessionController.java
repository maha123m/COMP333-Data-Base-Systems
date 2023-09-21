package com.example.maha;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class SessionController implements Initializable {
    //  interface contents ------->

    @FXML
    TableView<Session> SessiontableView = new TableView<>();

    @FXML
    TableColumn<Session, Integer>sessionId;
    @FXML
    TableColumn<Session, Integer> studentID ;
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
    private TextField StudentID;

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

    ObservableList<Session> list = FXCollections.observableArrayList();
    ////////////////////////////////////////////////

    public void initialize (URL url , ResourceBundle rb) {

        Connecter conn = new Connecter() ;
        try {
            Connection connectDB = Connecter.getConnection();
            String viewquery = "SELECT sessionId,studentID ,plateNumber,trainerID,sessionCost,sessioncompleted,sessionDay,sessionTime,sessionDate,sessionStatus FROM session";
            Statement statment = connectDB.createStatement();
            ResultSet queryoutput = statment.executeQuery(viewquery);

            while (queryoutput.next()) {

                Integer queryID = queryoutput.getInt("sessionId");
                Integer Sid = queryoutput.getInt("studentID");
                Integer pNumber = queryoutput.getInt("plateNumber");
                Integer Tid = queryoutput.getInt("trainerID");
                Integer sCost = queryoutput.getInt("sessionCost");
                Integer  sComplete = queryoutput.getInt("sessioncompleted");
                String sDay = queryoutput.getString("sessionDay");
                String sTime = queryoutput.getString("sessionTime");
                Date sDate = queryoutput.getDate("sessionDate");
                String sStatus = queryoutput.getString("sessionStatus");


               Session session = new Session(queryID, Sid,pNumber,Tid,sCost,sComplete,sDay,sTime,sDate,sStatus );
                list.add(session);
            }

            sessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
            studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
            plateNumber.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));
            trainerID.setCellValueFactory(new PropertyValueFactory<>("trainerID"));
            sessionCost.setCellValueFactory(new PropertyValueFactory<>("sessionCost"));
            sessioncompleted.setCellValueFactory(new PropertyValueFactory<>("sessioncompleted"));
            sessionCost.setCellValueFactory(new PropertyValueFactory<>("sessionDay"));
            sessionCost.setCellValueFactory(new PropertyValueFactory<>("sessionTime"));
            sessionCost.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
            sessionCost.setCellValueFactory(new PropertyValueFactory<>("sessionStatus"));
            SessiontableView.setItems(list);

            // Create a filtered list to hold the filtered data
            FilteredList<Session> filteredList = new FilteredList<>(list);

            // Set the filter predicate for the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(session -> {
                    // If the search text is empty, show all payment
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Convert search text to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if the payment's first name contains the search text
                    if (session.getSessionStatus().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false; // Does not match the search text
                });
            });

            // Wrap the filtered list in a sorted list to enable sorting
            SortedList<Session> sortedList = new SortedList<>(filteredList);

            // Bind the sorted list to the table view
            sortedList.comparatorProperty().bind(SessiontableView.comparatorProperty());
            SessiontableView.setItems(sortedList);


        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE,null,e1);
        }
    }
















}
