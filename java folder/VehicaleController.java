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


public class VehicaleController implements Initializable {

    //  interface contents ------->

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
    /////////////////////////////////////////////

    // Buttons:
    @FXML
    private Button addVehicale;
    @FXML
    private Button deleteVehicale;
    @FXML
    private Button updateVehicale;
    /////////////////////////////////////////////////////////////////////////////////////
    // textBoxes
    @FXML
    private TextField plateNumber1;

    @FXML
    private TextField trainnerID;

    @FXML
    private TextField vechileModel;

    @FXML
    private TextField productionYear1;

    /////combo box
    @FXML
    private  ComboBox trainsType;

    //date box
    @FXML
    private DatePicker insuranteDate1;

    @FXML
    private DatePicker licenseDate1;


    ///////////////////////////////////////////////////////////////////////////
    ObservableList <Vehicale> listM;

    int index = -1;

    Connection conn = null;

    ResultSet rs = null;

    PreparedStatement pst = null ;

    @FXML
    private TextField searchTextField;

    ObservableList<Vehicale> list = FXCollections.observableArrayList();
    ////////////////////////////////////////////////

    public void initialize (URL url , ResourceBundle rb) {

        Connecter conn = new Connecter() ;
        try {
            Connection connectDB = conn.getConnection();
            String viewquery = "SELECT plateNumber,trainerID,vehicaleModel,productionYear,transmissionType,insuranteDate,licenseDate FROM vehicale";
            Statement statment = connectDB.createStatement();
            ResultSet queryoutput = statment.executeQuery(viewquery);

            while (queryoutput.next()) {

                Integer queryID = queryoutput.getInt("plateNumber");
                Integer Trid = queryoutput.getInt("trainerID");
                String vModel = queryoutput.getString("vehicaleModel");
                Integer prYear = queryoutput.getInt("productionYear");
                String trType = queryoutput.getString("transmissionType");
                Date insDate = queryoutput.getDate("insuranteDate");
                Date liDate = queryoutput.getDate("licenseDate");

                Vehicale vehicale = new Vehicale(queryID, Trid, vModel, prYear, trType, insDate,liDate);
                list.add(vehicale);
            }

            plateNumber.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));
            trainerID.setCellValueFactory(new PropertyValueFactory<>("trainerID"));
            vehicaleModel.setCellValueFactory(new PropertyValueFactory<>("vehicaleModel"));
            productionYear.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
            transmissionType.setCellValueFactory(new PropertyValueFactory<>("transmissionType"));
            insuranteDate.setCellValueFactory(new PropertyValueFactory<>("insuranteDate"));
            licenseDate.setCellValueFactory(new PropertyValueFactory<>("ilicenseDate"));


            vehicaletableView.setItems(list);

            // Create a filtered list to hold the filtered data
            FilteredList<Vehicale> filteredList = new FilteredList<>(list);

            // Set the filter predicate for the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(vehicale -> {
                    // If the search text is empty, show all payment
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
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE,null,e1);
        }
    }



}
