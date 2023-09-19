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


    // textBoxes
    @FXML
    private TextField trainerID;

    @FXML
    private TextField studentid;

    /////combo box
    @FXML
    private  ComboBox testResult;

    //date box
    @FXML
    private DatePicker teatDate;

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
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();
            String viewquery = "SELECT Tid, StudentId, Tresult, Pdate FROM Test";
            Statement statment = connectDB.createStatement();
            ResultSet queryoutput = statment.executeQuery(viewquery);

            while (queryoutput.next()) {
                Integer queryID = queryoutput.getInt("Tid");
                Integer Sid = queryoutput.getInt("StudentId");
                String testResult = queryoutput.getString("Tresult");
                Date testDate = queryoutput.getDate("Pdate");

                Test Test = new Test(queryID, Sid, testResult, testDate);
                list.add(Test);
            }

            Tid.setCellValueFactory(new PropertyValueFactory<>("Tid"));
            StudentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
            Tresult.setCellValueFactory(new PropertyValueFactory<>("Tresult"));
            Pdate.setCellValueFactory(new PropertyValueFactory<>("Pdate"));

            testTableview.setItems(list);

            // Create a filtered list to hold the filtered data
            FilteredList<Test> filteredList = new FilteredList<>(list);

            // Set the filter predicate for the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(Test -> {
                    // If the search text is empty, show all Test
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Convert search text to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if the Test's test result contains the search text
                    if (Test.getTresult().toLowerCase().contains(lowerCaseFilter)) {
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
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, e1);
        }
    }



}
