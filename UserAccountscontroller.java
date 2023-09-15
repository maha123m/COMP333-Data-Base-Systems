package com.example.maha;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class UserAccountscontroller implements Initializable {

    @FXML
    private TableView<UserAccount> accview;

    @FXML
    private TableColumn<UserAccount, Integer> idUserAccountss;

    @FXML
    private TableColumn<UserAccount, String> Firstname;

    @FXML
    private TableColumn<UserAccount, String> Lastname;

    @FXML
    private TableColumn<UserAccount, String> Username;

    @FXML
    private TableColumn<UserAccount, String> passowrd;

    @FXML
    private TableColumn<UserAccount, Integer> trainerID;

    @FXML
    private TableColumn<UserAccount, Integer> studentID;

    @FXML
    private TableColumn<UserAccount, String> Class;
    
    

    @FXML
    private TextField Classt;

    @FXML
    private Label FirstNamet;


    @FXML
    private TextField Fnamet;

    @FXML
    private Button Home;

    @FXML
    private Label LastNamet;


    @FXML
    private Label Phone1t;

    @FXML
    private Label Phone2t;

    @FXML
    private Label Phone2t1;


    @FXML
    private Label Usernamet;

 

    @FXML
    private TextField idUserAccountstt;

    @FXML
    private TextField lastnamet;


    @FXML
    private TextField passowrdtt;

    @FXML
    private TextField searchTextField;

    

    @FXML
    private TextField studentIDt;



    @FXML
    private TextField trainerIDt;

    @FXML
    private TextField usernamet;
    
    @FXML
    private Button addButton;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Initialize table columns
        idUserAccountss.setCellValueFactory(new PropertyValueFactory<>("idUserAccounts"));
        Firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        Lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        Username.setCellValueFactory(new PropertyValueFactory<>("username"));
        passowrd.setCellValueFactory(new PropertyValueFactory<>("password"));
        trainerID.setCellValueFactory(new PropertyValueFactory<>("trainerID"));
        studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        Class.setCellValueFactory(new PropertyValueFactory<>("className"));

        // Fetch data from the database and populate the TableView
        ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList();

        try {
            Connection connection = Connecter.getConnection(); // Replace with your database connection code

            String query = "SELECT * FROM userAccounts";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int idUserAccounts = resultSet.getInt("idUserAccounts");
                String firstname = resultSet.getString("Firstname");
                String lastname = resultSet.getString("Lastname");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("passowrd");
                int trainerID = resultSet.getInt("trainerID");
                int studentID = resultSet.getInt("studentId");
                String className = resultSet.getString("class");

                UserAccount userAccount = new UserAccount(idUserAccounts, firstname, lastname, username, password, trainerID, studentID, className);
                userAccounts.add(userAccount);
            }

            accview.setItems(userAccounts);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add listener for search text field
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Perform search based on the new value
            filterTableData(newValue);
        });

        // Add listener for table row selection
        accview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Populate text fields with selected row's data
                UserAccount selectedAccount = accview.getSelectionModel().getSelectedItem();
                idUserAccountstt.setText(Integer.toString(selectedAccount.getIdUserAccounts()));
                Fnamet.setText(selectedAccount.getFirstname());
                lastnamet.setText(selectedAccount.getLastname());
                usernamet.setText(selectedAccount.getUsername());
                passowrdtt.setText(selectedAccount.getPassword());
                trainerIDt.setText(Integer.toString(selectedAccount.getTrainerID()));
                studentIDt.setText(Integer.toString(selectedAccount.getStudentID()));
                Classt.setText(selectedAccount.getClassName());
            }
        });
    }

    // Method to filter table data based on search text
    private void filterTableData(String searchText) {
        accview.getItems().clear();

        // Fetch filtered data from the database and populate the TableView
        ObservableList<UserAccount> filteredAccounts = FXCollections.observableArrayList();

        try {
            Connection connection = Connecter.getConnection(); // Replace with your database connection code

            String query = "SELECT * FROM userAccounts WHERE firstname LIKE '%" + searchText + "%' OR lastname LIKE '%" + searchText + "%'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
            	 int idUserAccounts = resultSet.getInt("idUserAccounts");
            	    String firstname = resultSet.getString("Firstname");
            	    String lastname = resultSet.getString("Lastname");
            	    String username = resultSet.getString("Username");
            	    String password = resultSet.getString("passowrd");
            	    int trainerID = resultSet.getInt("trainerID");
            	    int studentID = resultSet.getInt("studentId");
            	    String className = resultSet.getString("class");

            	    UserAccount userAccount = new UserAccount(idUserAccounts, firstname, lastname, username, password, trainerID, studentID, className);
            	    filteredAccounts.add(userAccount);
            }

            accview.setItems(filteredAccounts);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    @FXML
    void addButtonOnAction(ActionEvent event) {
        // Get the input values from the text fields
        int idUserAccounts = Integer.parseInt(idUserAccountstt.getText());
        String firstname = Fnamet.getText();
        String lastname = lastnamet.getText();
        String username = usernamet.getText();
        String password = passowrdtt.getText();
        int trainerID = Integer.parseInt(trainerIDt.getText());
        int studentID = Integer.parseInt(studentIDt.getText());
        String className = Classt.getText();

        // Create a new UserAccount object
        UserAccount newAccount = new UserAccount(idUserAccounts, firstname, lastname, username, password, trainerID, studentID, className);

        // Add the new account to the TableView
        accview.getItems().add(newAccount);

        // Clear the input fields after adding
        idUserAccountstt.clear();
        Fnamet.clear();
        lastnamet.clear();
        usernamet.clear();
        passowrdtt.clear();
        trainerIDt.clear();
        studentIDt.clear();
        Classt.clear();
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
            Stage vehicleStage = (Stage) Home.getScene().getWindow();
            vehicleStage.close();

           
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
