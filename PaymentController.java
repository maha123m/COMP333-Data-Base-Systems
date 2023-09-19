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



public class PaymentController implements Initializable  {

    //  interface contents ------->

    @FXML
    TableView<Payment> PaymentTableview = new TableView<>();

    @FXML
    TableColumn<Payment, Integer> Pid;
    @FXML
    TableColumn<Payment, Integer> StudentId ;
    @FXML
    TableColumn<Payment, Integer> amount ;
    @FXML
    TableColumn<Payment, String> Pmethood;
    @FXML
    TableColumn<Payment, String> Pstatus ;
    @FXML
    TableColumn<Payment, Date> Pdate;
    /////////////////////////////////////////////

    // Buttons:
    @FXML
    private Button addPayment;
    @FXML
    private Button deletePayment;
    @FXML
    private Button updatePayment;
    /////////////////////////////////////////////////////////////////////////////////////
    // textBoxes
    @FXML
    private TextField PaymentIDD;

    @FXML
    private TextField StudentIDD;

    @FXML
    private TextField amounnnt;

    /////combo box
    @FXML
    private  ComboBox payMethood;

    @FXML
    private  ComboBox payStatus;

    //date box
    @FXML
    private DatePicker payDate;

    ///////////////////////////////////////////////////////////////////////////
    ObservableList <Payment> listM;

    int index = -1;

    Connection conn = null;

    ResultSet rs = null;

    PreparedStatement pst = null ;

    @FXML
    private TextField searchTextField;

    ObservableList<Payment> list = FXCollections.observableArrayList();
    ////////////////////////////////////////////////

    public void initialize (URL url , ResourceBundle rb) {

        Connecter conn = new Connecter() ;
        try {
            Connection connectDB = conn.getConnection();
            String viewquery = "SELECT Pid,StudentId,amount,Pstatus,Pmethood,Pdate FROM payment";
            Statement statment = connectDB.createStatement();
            ResultSet queryoutput = statment.executeQuery(viewquery);

            while (queryoutput.next()) {

                Integer queryID = queryoutput.getInt("Pid");
                Integer Sid = queryoutput.getInt("StudentId");
                Integer Amount1 = queryoutput.getInt("amount");
                String payStatus = queryoutput.getString("Pstatus");
                String PayMethood = queryoutput.getString("Pmethood");
                Date payDate = queryoutput.getDate("Pdate");

                Payment payment = new Payment(queryID, Sid, Amount1, payStatus, PayMethood, payDate);
                list.add(payment);
            }

            Pid.setCellValueFactory(new PropertyValueFactory<>("Pid"));
            StudentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            Pstatus.setCellValueFactory(new PropertyValueFactory<>("Pstatus"));
            Pmethood.setCellValueFactory(new PropertyValueFactory<>("Pmethood"));
            Pdate.setCellValueFactory(new PropertyValueFactory<>("Pdate"));

            PaymentTableview.setItems(list);

            // Create a filtered list to hold the filtered data
            FilteredList<Payment> filteredList = new FilteredList<>(list);

            // Set the filter predicate for the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(payment -> {
                    // If the search text is empty, show all payment
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Convert search text to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if the payment's first name contains the search text
                    if (payment.getPstatus().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false; // Does not match the search text
                });
            });

            // Wrap the filtered list in a sorted list to enable sorting
            SortedList<Payment> sortedList = new SortedList<>(filteredList);

            // Bind the sorted list to the table view
            sortedList.comparatorProperty().bind(PaymentTableview.comparatorProperty());
            PaymentTableview.setItems(sortedList);


        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE,null,e1);
        }
    }


}














