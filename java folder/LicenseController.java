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

    /////////////////////////////////////////////////////////////////////////////////////
    // textBoxes
    @FXML
    private TextField  licenseID;

    @FXML
    private TextField studentID;


    ///////////////////////////////////////////////////////////////////////////

    //combo box
    @FXML
    private  ComboBox licenseType;
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

        Connecter conn = new Connecter() ;
        try {
            Connection connectDB = conn.getConnection();
            String viewquery = "SELECT licenseId,studentId,licensetype FROM license";
            Statement statment = connectDB.createStatement();
            ResultSet queryoutput = statment.executeQuery(viewquery);

            while (queryoutput.next()) {

                Integer queryID = queryoutput.getInt("licenseId");
                Integer Sid_L = queryoutput.getInt("studentId");
                String type_L = queryoutput.getString("licensetype");


                License license = new License(queryID, Sid_L, type_L);
                list.add(license);
            }

            licenseId.setCellValueFactory(new PropertyValueFactory<>("licenseId"));
            studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
            licensetype.setCellValueFactory(new PropertyValueFactory<>("licensetype"));


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










}
