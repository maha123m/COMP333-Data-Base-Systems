package com.example.maha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SampleController {

    @FXML
    private Button CancleButton;
    @FXML
    private Label LoginMessageLabel;
    @FXML
    private TextField UsernameTextFiledd;
    @FXML
    private PasswordField PasswordFieldd;

    @FXML
    private RadioButton AdministratorRadioButton;

    @FXML
    private RadioButton TrainerRadioButton;

    @FXML
    private RadioButton StudentRadioButton;

    private Administration administration;

    private TrainerLogin trainer ;


    public SampleController() {
        administration = new Administration();
        trainer = new TrainerLogin();
    }


    public void CancleButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) CancleButton.getScene().getWindow();
        stage.close();
    }

    public void LoginButtonLabelOnAction(ActionEvent e) throws SQLException {
        LoginMessageLabel.setTextFill(Color.RED);

        if (!UsernameTextFiledd.getText().isBlank() && !PasswordFieldd.getText().isBlank()) {
            validateLogin();
        } else {
            LoginMessageLabel.setText("Please enter your Username and Password!");
        }
    }


    public void validateLogin() throws SQLException {
        Connection connectDB = null;

        try {
            connectDB = Connecter.getConnection();

            String verifyLogin = "SELECT class FROM userAccounts WHERE Username = '"
                    + UsernameTextFiledd.getText() + "' AND passowrd = '" + PasswordFieldd.getText() + "'";

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            if (queryResult.next()) {
                String userClass = queryResult.getString("class");
                if (userClass.equals("administrator") && AdministratorRadioButton.isSelected()) {

                    administration.adminRadioButtonOnAction(CancleButton);

                }


                else if (userClass.equals("student") && StudentRadioButton.isSelected()) {

                    System.out.println("Student");


                }


                else if (userClass.equals("trainer") && TrainerRadioButton.isSelected()) {

                    trainer.TrainerRadioButtonOnAction(CancleButton);


                }

                else {
                    LoginMessageLabel.setText("Invalid login. Please try again!");
                }
            } else {
                LoginMessageLabel.setText("Invalid login. Please try again!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connectDB != null) {
                connectDB.close();
            }
        }
    }




}