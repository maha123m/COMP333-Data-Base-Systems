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
    @FXML
    private Button Home;
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

        // Event handler for selecting a row
        PaymentTableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Payment selectedPayment = PaymentTableview.getSelectionModel().getSelectedItem();

                // Fill the text fields with the selected student's information
                PaymentIDD.setText(String.valueOf(selectedPayment.getPid()));
                StudentIDD.setText(String.valueOf(selectedPayment.getStudentId()));
                amounnnt.setText(String.valueOf(selectedPayment.getAmount()));

                payStatus.setValue(selectedPayment.getPstatus());
                payMethood.setValue(selectedPayment.getPmethood());

                payDate.setValue(((java.sql.Date) selectedPayment.getPdate()).toLocalDate());
            }
        });
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

            payStatus.getItems().addAll("Paid", "Pending");
            payMethood.getItems().addAll("Cash", "checks","Cridit Card");


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


    ///////////////////////////////////////////
    //add a payment

    @FXML
    private void addPaymentOnAction(ActionEvent event) {

        // Check if a payment is selected
        if (PaymentIDD.getText().isEmpty() || StudentIDD.getText().isEmpty() || amounnnt.getText().isEmpty()
                || payStatus.getValue() == null || payMethood.getValue() == null || payDate.getValue() == null) {
            // No payment selected, display an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Payment Selected");
            alert.setHeaderText("No Payment Selected");
            alert.setContentText("Please fill in all the fields to add a payment.");
            alert.showAndWait();
            return; // Exit the method
        }



        // Get the values from the text fields

        int pID = Integer.parseInt(PaymentIDD.getText());

        int sID = Integer.parseInt(StudentIDD.getText());


        int Amount = Integer.parseInt(amounnnt.getText());

        String pStatus = payStatus.getValue().toString();

        String pMethood = payMethood.getValue().toString();

        LocalDate pDayValue = payDate.getValue();

        // Create a payment object with the entered values
        Date payDate = java.sql.Date.valueOf(pDayValue);

        Payment payment = new Payment(pID, sID, Amount,pStatus, pMethood,payDate);

        // Insert the payment into the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Check if the payment ID already exists in the table
            String checkQuery = "SELECT COUNT(*) FROM payment WHERE Pid = ?";
            PreparedStatement checkStatement = connectDB.prepareStatement(checkQuery);
            checkStatement.setInt(1, payment.getPid());
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                // Display a warning message if the ID already exists
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Duplicate Payment ID");
                alert.setHeaderText("Warning: Duplicate Payment ID");
                alert.setContentText("The entered Payment ID already exists in the database. Please enter a unique ID.");
                alert.showAndWait();
            } else {
                // Insert the student into the table
                String insertQuery = "INSERT INTO payment (Pid, StudentId, amount, Pstatus, Pmethood, Pdate) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connectDB.prepareStatement(insertQuery);
                statement.setInt(1, payment.getPid());
                statement.setInt(2, payment.getStudentId());
                statement.setInt(3, payment.getAmount());
                statement.setString(4, payment.getPstatus());
                statement.setString(5, payment.getPmethood());
                statement.setDate(6, (java.sql.Date) payment.getPdate());
                statement.executeUpdate();

                // Add the student to the table view
                list.add(payment);

                // Display a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Payment  Added");
                alert.setHeaderText("Success: Payment Added");
                alert.setContentText("The Payment has been added successfully.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //////////////////////
    //update payment button
    @FXML
    private void updatePaymentOnAction(ActionEvent event) {
        // Check if a payment is selected in the table
        Payment selectedPayment = PaymentTableview.getSelectionModel().getSelectedItem();
        if (selectedPayment == null) {
            // Display a warning message if no payment is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Payment Selected");
            alert.setHeaderText("Warning: No Payment selected!");
            alert.setContentText("Please select a Payment from the table.");
            alert.showAndWait();
            return;
        }

        // Get the updated values from the text fields
        int pId = Integer.parseInt(PaymentIDD.getText());
        int sId = Integer.parseInt(StudentIDD.getText());
        int Amount = Integer.parseInt(amounnnt.getText());
        String payStatusValue = payStatus.getValue().toString();
        String payMethoodValue = payMethood.getValue().toString();
        LocalDate paydateValue = payDate.getValue();

        // Update the selected student object with the new values
        selectedPayment.setPid(pId);
        selectedPayment.setStudentId(sId);
        selectedPayment.setAmount(Amount);
        selectedPayment.setPstatus(payStatusValue);
        selectedPayment.setPmethood(payMethoodValue);
        selectedPayment.setPdate(java.sql.Date.valueOf(paydateValue));

        // Update the student in the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Update the payment information
            String updateQuery = "UPDATE payment SET Pid = ?, StudentId = ?, amount = ?, Pstatus = ?, Pmethood = ?, Pdate = ? WHERE Pid = ?";
            PreparedStatement statement = connectDB.prepareStatement(updateQuery);
            statement.setInt(1, selectedPayment.getPid());
            statement.setInt(2, selectedPayment.getStudentId());
            statement.setInt(3, selectedPayment.getAmount());
            statement.setString(4, selectedPayment.getPstatus());
            statement.setString(5, selectedPayment.getPmethood());
            statement.setDate(6, (java.sql.Date) selectedPayment.getPdate());
            statement.setInt(7, selectedPayment.getPid());
            statement.executeUpdate();

            // Refresh the table view
            PaymentTableview.refresh();

            // Display a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Payment Updated");
            alert.setHeaderText("Success: Payment Updated");
            alert.setContentText("The Payment has been updated successfully.");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    @FXML
    private void deletePaymentOnAction(ActionEvent event) {
        // Check if a student is selected in the table
        Payment selectedPayment = PaymentTableview.getSelectionModel().getSelectedItem();
        if (selectedPayment == null) {
            // Display a warning message if no payment is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Payment Selected");
            alert.setHeaderText("Warning: No payment selected!");
            alert.setContentText("Please select a payment from the table.");
            alert.showAndWait();
            return;
        }

        // Show a confirmation dialog to confirm the deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Payment");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete the selected Payment?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Delete the student from the MySQL table
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();

                // Delete the student using the student ID
                String deleteQuery = "DELETE FROM payment WHERE Pid  = ?";
                PreparedStatement statement = connectDB.prepareStatement(deleteQuery);
                statement.setInt(1, selectedPayment.getPid());
                statement.executeUpdate();

                // Remove the payment from the table view
                list.remove(selectedPayment);

                // Display a success message
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Payment Deleted");
                alert1.setHeaderText("Success: Payment Deleted");
                alert1.setContentText("The payment has been deleted successfully.");

                alert1.showAndWait();


            } catch (SQLException e) {
                e.printStackTrace();
                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


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








}



























