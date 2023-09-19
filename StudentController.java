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
public class StudentController implements Initializable {

    //  interface contents ------->

    @FXML
    TableView<Student> StudenttableView = new TableView<>();

    @FXML
    TableColumn<Student, Integer> studentId;
    @FXML
    TableColumn<Student, String> studentFirstName ;
    @FXML
    TableColumn<Student, String> studentmiddleName ;
    @FXML
    TableColumn<Student, String> studentLastName;
    @FXML
    TableColumn<Student, String> cityAddress ;
    @FXML
    TableColumn<Student, String> streetAddress;
    @FXML
    TableColumn<Student, Integer> phone1;
    @FXML
    TableColumn<Student, Integer> phone2;
    @FXML
    TableColumn<Student, Integer> wallet;
    @FXML
    TableColumn<Student, String> gender;
    @FXML
    TableColumn<Student, Date> birthdate;
    // textBoxes
    @FXML
    private TextField sId;

    @FXML
    private TextField Fname;

    @FXML
    private TextField Mname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField cityaddress;
    @FXML
    private TextField streetaddress;
    @FXML
    private TextField phone11;
    @FXML
    private TextField phone22;
    @FXML
    private TextField wallet1;
    /////combo box
    @FXML
    private  ComboBox gender1;

    //date box
    @FXML
    private DatePicker birthdate1;

    ///////////////////////////////////////////////////////////////////////////
    ObservableList <Student> listM;

    int index = -1;

    Connection conn = null;

    ResultSet rs = null;

    PreparedStatement pst = null ;

    @FXML
    private TextField searchTextField;

    ObservableList<Student> list = FXCollections.observableArrayList();


////////////////////////////////////////////////

    public void initialize (URL url , ResourceBundle rb) {

        Connecter conn = new Connecter() ;
        try {
            Connection connectDB = conn.getConnection();
            String viewquery = "SELECT studentId,studentFirstName,studentmiddleName,studentLastName,cityAddress,streetAddress,phone1,phone2,wallet,gender,birthdate FROM students";
            Statement statment = connectDB.createStatement();
            ResultSet queryoutput = statment.executeQuery(viewquery);

            while (queryoutput.next()) {

                Integer queryID = queryoutput.getInt("studentId");
                String sFName = queryoutput.getString("studentFirstName");
                String sMName = queryoutput.getString("studentmiddleName");
                String sLName = queryoutput.getString("studentLastName");
                String cAddress = queryoutput.getString("cityAddress");
               String sAddress = queryoutput.getString("streetAddress");
                Integer pPhone1 = queryoutput.getInt("phone1");
                Integer pPhone2 = queryoutput.getInt("phone2");
                Integer Wallet = queryoutput.getInt("wallet");
                String Gender = queryoutput.getString("gender");
                Date birthDate = queryoutput.getDate("birthdate ");



                Student students = new Student(queryID, sFName, sMName, sLName, cAddress, sAddress,pPhone1,pPhone2,Wallet,Gender,birthDate );
                list.add(students);
            }

            studentId.setCellValueFactory(new PropertyValueFactory<>(" studentId"));
            studentFirstName.setCellValueFactory(new PropertyValueFactory<>("studentFirstName"));
            studentmiddleName.setCellValueFactory(new PropertyValueFactory<>("studentmiddleName"));
            studentLastName.setCellValueFactory(new PropertyValueFactory<>("studentLastName"));
            cityAddress.setCellValueFactory(new PropertyValueFactory<>("cityAddress"));
            streetAddress.setCellValueFactory(new PropertyValueFactory<>("streetAddress"));
            phone1.setCellValueFactory(new PropertyValueFactory<>("phone1"));
            phone2.setCellValueFactory(new PropertyValueFactory<>("phone2"));
            wallet.setCellValueFactory(new PropertyValueFactory<>("wallet"));
            gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));

            StudenttableView.setItems(list);

            // Create a filtered list to hold the filtered data
            FilteredList<Student> filteredList = new FilteredList<>(list);

            // Set the filter predicate for the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(students -> {
                    // If the search text is empty, show all payment
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Convert search text to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if the payment's first name contains the search text
                    if (students.getStudentFirstName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    return false; // Does not match the search text
                });
            });

            // Wrap the filtered list in a sorted list to enable sorting
            SortedList<Student> sortedList = new SortedList<>(filteredList);

            // Bind the sorted list to the table view
            sortedList.comparatorProperty().bind(StudenttableView.comparatorProperty());
            StudenttableView.setItems(sortedList);


        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE,null,e1);
        }
    }






}
