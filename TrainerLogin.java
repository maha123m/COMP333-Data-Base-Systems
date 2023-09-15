package com.example.maha;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TrainerLogin {
    @FXML
    private Button UpcomingSessionsButton; //done

    @FXML
    private Button ContactButton;

    @FXML
    private Button StudentButton;

    @FXML
    private Button PendingButton;

    @FXML
    private Button TotalEarningsButton;

    @FXML
    private Button TimeButton;

    public void TrainerRadioButtonOnAction(Button cancelButton) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TrainerLogin_interface.fxml"));
            Parent root = loader.load();

            Stage trainerStage = new Stage();
            trainerStage.setTitle("Trainer Interface");
            trainerStage.setScene(new Scene(root));
            trainerStage.show();

            // Close the login interface
            Stage loginStage = (Stage) cancelButton.getScene().getWindow();

            loginStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void UpcomingSessionsButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/upcomingSessionQ.fxml"));


            Parent root = loader.load();

            Stage upStage = new Stage();
            upStage.setTitle("UPCOMENG SESSION Interface");
            System.out.println("Ok");
            upStage.setScene(new Scene(root));
            upStage.show();

            // Close the trainer interface
            Button UpButton = (Button) event.getSource();
            Stage trainerStage = (Stage) UpButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void ContactButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TrainerContactQ.fxml"));


            Parent root = loader.load();

            Stage contactStage = new Stage();
            contactStage.setTitle("Contact information Interface");
            System.out.println("OK");
            contactStage.setScene(new Scene(root));
            contactStage.show();

            // Close the trainer interface
            Button ContactButton = (Button) event.getSource();
            Stage trainerStage = (Stage) ContactButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void StudentButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StudentNamesQ.fxml"));


            Parent root = loader.load();

            Stage vehicleStage = new Stage();
            vehicleStage.setTitle("Student Interface");
            System.out.println("OK");
            vehicleStage.setScene(new Scene(root));
            vehicleStage.show();

            // Close the Trainer interface
            Button StudentButton = (Button) event.getSource();
            Stage trainerStage = (Stage) StudentButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void PendingButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PendingQ.fxml"));


            Parent root = loader.load();

            Stage vehicleStage = new Stage();
            vehicleStage.setTitle("Pending Payments Interface");
            System.out.println("OK");
            vehicleStage.setScene(new Scene(root));
            vehicleStage.show();

            // Close the trainer interface
            Button PendingButton = (Button) event.getSource();
            Stage trainerStage = (Stage) PendingButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void TotalEarningsButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ToltalEarningsQ.fxml"));


            Parent root = loader.load();

            Stage vehicleStage = new Stage();
            vehicleStage.setTitle("Earnings Interface");
            System.out.println("ok");
            vehicleStage.setScene(new Scene(root));
            vehicleStage.show();

            // Close the trainer interface
            Button EarningButton = (Button) event.getSource();
            Stage trainerStage = (Stage) EarningButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void TimeButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TrainerAvailableQ.fxml"));


            Parent root = loader.load();

            Stage vehicleStage = new Stage();
            vehicleStage.setTitle("Availability Interface");
            System.out.println("OK");
            vehicleStage.setScene(new Scene(root));
            vehicleStage.show();

            // Close the TRAINER interface
            Button TimeButton = (Button) event.getSource();
            Stage trainerStage = (Stage) TimeButton.getScene().getWindow();
            trainerStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
