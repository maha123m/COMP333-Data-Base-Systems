package com.example.maha;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class Administration{


    @FXML
    private Button TrainersButton;

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
    private Button TestBtton;
    @FXML
    private Button Button;

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
//////test interface



    @FXML
    private void testButtonOnAction(ActionEvent event) {
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/test_interface.fxml"));
            Parent root = loader.load();

            Stage trainerStage = new Stage();
            trainerStage.setTitle("Test Interface");
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

    /////////////////////////////////////////////////////////

    // button -----> Vehicale interface

    @FXML
    private void veicleButtonOnAction(ActionEvent event) {
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vechile_interface.fxml"));


            Parent root = loader.load();

            Stage vehicleStage = new Stage();
            vehicleStage.setTitle("Vehicle Interface");
            System.out.println("lana");
            vehicleStage.setScene(new Scene(root));
            vehicleStage.show();


            // Close the admin interface
            Button TrainersButton = (Button) event.getSource();
            Stage adminStage = (Stage) TrainersButton.getScene().getWindow();
            adminStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    ///////////////////////////////////////////////////////////////////
    // button -----> License interface

    @FXML
    private void LicenseButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/license_interface.fxml"));
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

    ///////////////////////////////////////////////////////////////////
    // button -----> Session interface

    @FXML
    private void SessionsButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/session_interface.fxml"));


            Parent root = loader.load();

            Stage vehicleStage = new Stage();
            vehicleStage.setTitle("Vehicle Interface");
            System.out.println("lana");
            vehicleStage.setScene(new Scene(root));
            vehicleStage.show();

            // Close the admin interface
            Button TrainersButton = (Button) event.getSource();
            Stage adminStage = (Stage) TrainersButton.getScene().getWindow();
            adminStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    ////////////////////////////////////////////////////////

    // button -----> Vehicale interface

    @FXML
    private void studentButtonOnAction(ActionEvent event) {
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Student_interface.fxml"));


            Parent root = loader.load();

            Stage vehicleStage = new Stage();
            vehicleStage.setTitle("Student Interface");
            System.out.println("lana");
            vehicleStage.setScene(new Scene(root));
            vehicleStage.show();


            // Close the admin interface
            Button TrainersButton = (Button) event.getSource();
            Stage adminStage = (Stage) TrainersButton.getScene().getWindow();
            adminStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }










    public void CancleButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) Button.getScene().getWindow();

        stage.close();
    }





















}