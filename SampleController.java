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


    // to save the id to use in the queries later on

    public static int studentid;
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
    private   StudentRadio studentRadio ;

    private TrainerLogin trainer ;



    public SampleController() {
        administration = new Administration();
        studentRadio = new StudentRadio();
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

            String un= UsernameTextFiledd.getText();
            Data data = new Data();
            data.UName=un;


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

                    String verifyLogin1 = "SELECT studentId FROM userAccounts WHERE Username = '"
                            + UsernameTextFiledd.getText() + "' AND passowrd = '" + PasswordFieldd.getText() + "'";

                    Statement statement1 = connectDB.createStatement();
                    ResultSet queryResult1 = statement1.executeQuery(verifyLogin1);

                    if (queryResult1.next()) {
                        int studentID = queryResult1.getInt("studentId");
                        studentid = studentID;


                        System.out.println(studentID + "lanaaaa");

                        studentRadio.StudentRadioButton(CancleButton, studentID);
                    }

                    System.out.println("Student");


                }


                else if (userClass.equals("trainer") && TrainerRadioButton.isSelected()) {

                    trainer.TrainerRadioButtonOnAction(CancleButton);

                    System.out.println("trainer");


                }

                else {
                    LoginMessageLabel.setText("Invalid login. Please try againnnnnn!");
                }
            } else {
                LoginMessageLabel.setText("Invalid login. Please try againn!");
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