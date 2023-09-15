package com.example.maha;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Administration {


    @FXML
    private Button TrainersButton;


    @FXML
    private Button student_trainer;



    @FXML
    private Button StudentsButton;

    @FXML
    private Button veiclesButton;

    @FXML
    private Button sessionsButton;

    @FXML
    private Button LicenseButton;

    @FXML
    private Button PaymentButton;

    @FXML
    private Button testButton;

    @FXML
    private Button userAccounts;

    @FXML
    private Button cancelButton;

    public void adminRadioButtonOnAction(Button cancelButton) {
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin_interface.fxml"));



            Parent root = loader.load();

            Stage adminStage = new Stage();
            adminStage.setTitle("Admin Interface");
            adminStage.setScene(new Scene(root));
            adminStage.show();

            // Close the login interface
            Stage loginStage = (Stage) cancelButton.getScene().getWindow();

            loginStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }




    // button -----> trainer interface



    @FXML
    private void trainersButtonOnAction(ActionEvent event) {
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/trainer_interface.fxml"));


            Parent root = loader.load();

            Stage trainerStage = new Stage();
            trainerStage.setTitle("Trainer Interface");
            System.out.println("lana");
            trainerStage.setScene(new Scene(root));
            trainerStage.show();


            // Close the admin interface
            Button TrainersButton = (Button) event.getSource();
            Stage adminStage = (Stage) TrainersButton.getScene().getWindow();
            adminStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // button -----> Student interface

    @FXML
    private void studentButtonOnAction(ActionEvent event) {
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Student_interface.fxml"));


            Parent root = loader.load();

            Stage studentStage = new Stage();
            studentStage.setTitle("Student Interface");
            studentStage.setScene(new Scene(root));
            studentStage.show();


            // Close the admin interface
            Button TrainersButton = (Button) event.getSource();
            Stage adminStage = (Stage) TrainersButton.getScene().getWindow();
            adminStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }




    ////////////payment interface
    @FXML
    private void paymentButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/payment_interface.fxml"));
            Parent root = loader.load();

            Stage paymentStage = new Stage();
            paymentStage.setTitle("Payment Interface");
            System.out.println("lana");
            paymentStage.setScene(new Scene(root));
            paymentStage.show();

            // Close the trainer interface
            Button paymentButton = (Button) event.getSource();
            Stage trainerStage = (Stage) paymentButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    ////////////payment interface
    @FXML
    private void vehicleButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vechile_interface.fxml"));
            Parent root = loader.load();

            Stage paymentStage = new Stage();
            paymentStage.setTitle("Vehicle Interface");
            System.out.println("lana");
            paymentStage.setScene(new Scene(root));
            paymentStage.show();

            // Close the trainer interface
            Button veiclesButton = (Button) event.getSource();
            Stage trainerStage = (Stage) veiclesButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    private void testButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/test_interface.fxml"));
            Parent root = loader.load();

            Stage paymentStage = new Stage();
            paymentStage.setTitle("Vehicle Interface");
            System.out.println("lana");
            paymentStage.setScene(new Scene(root));
            paymentStage.show();

            // Close the trainer interface
            Button veiclesButton = (Button) event.getSource();
            Stage trainerStage = (Stage) veiclesButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    private void sessionButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/session_interface.fxml"));
            Parent root = loader.load();

            Stage paymentStage = new Stage();
            paymentStage.setTitle("Session Interface");
            System.out.println("lana");
            paymentStage.setScene(new Scene(root));
            paymentStage.show();

            // Close the trainer interface
            Button veiclesButton = (Button) event.getSource();
            Stage trainerStage = (Stage) veiclesButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    private void licenseButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/license_interface.fxml"));
            Parent root = loader.load();

            Stage paymentStage = new Stage();
            paymentStage.setTitle("Session Interface");
            System.out.println("lana");
            paymentStage.setScene(new Scene(root));
            paymentStage.show();

            // Close the trainer interface
            Button veiclesButton = (Button) event.getSource();
            Stage trainerStage = (Stage) veiclesButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    private void ReportsButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reports.fxml"));
            Parent root = loader.load();

            Stage paymentStage = new Stage();
            paymentStage.setTitle("Session Interface");
            System.out.println("lana");
            paymentStage.setScene(new Scene(root));
            paymentStage.show();

            // Close the trainer interface
            Button veiclesButton = (Button) event.getSource();
            Stage trainerStage = (Stage) veiclesButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    private void userAccountsButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/u.fxml"));
            Parent root = loader.load();

            Stage paymentStage = new Stage();
            paymentStage.setTitle("Session Interface");
            System.out.println("lana");
            paymentStage.setScene(new Scene(root));
            paymentStage.show();

            // Close the trainer interface
            Button veiclesButton = (Button) event.getSource();
            Stage trainerStage = (Stage) veiclesButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void student_trainerButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentToTrainer.fxml"));
            Parent root = loader.load();

            Stage paymentStage = new Stage();
            paymentStage.setTitle("Session Interface");
            System.out.println("lana");
            paymentStage.setScene(new Scene(root));
            paymentStage.show();

            // Close the trainer interface
            Button veiclesButton = (Button) event.getSource();
            Stage trainerStage = (Stage) veiclesButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}