package com.example.maha;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;

public class StudentInfoQ implements Initializable {
    @FXML
    TableView<StudentInfoQuery> NamesView = new TableView<>();

    @FXML
    private TableColumn<StudentInfoQuery, String> FirstNameS;

    @FXML
    private TableColumn<StudentInfoQuery, String> LastNameS;

    @FXML
    private TableColumn<StudentInfoQuery, String> CityS;

    @FXML
    private TableColumn<StudentInfoQuery, String> StreetS;

    @FXML
    private TableColumn<StudentInfoQuery, String> PhoneS;

    @FXML
    private TableColumn<StudentInfoQuery, String> WalletS;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the column mappings
        FirstNameS.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        LastNameS.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        CityS.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        StreetS.setCellValueFactory(cellData -> cellData.getValue().streetProperty());
        PhoneS.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        WalletS.setCellValueFactory(cellData -> cellData.getValue().walletProperty());

        // Retrieve data from the database
        try {
            Connection connectDB = Connecter.getConnection();
            String query = "SELECT s.studentFirstName, s.studentLastName, s.cityAddress, s.streetAddress, s.phone1, s.wallet " +
                    "FROM students s JOIN student_trainer st ON s.studentId = st.studentId " +
                    "WHERE st.trainerId = 1";
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ObservableList<StudentInfoQuery> NamesList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                String firstName = resultSet.getString("studentFirstName");
                String lastName = resultSet.getString("studentLastName");
                String city = resultSet.getString("cityAddress");
                String street = resultSet.getString("streetAddress");
                String phone = resultSet.getString("phone1");
                String wallet = resultSet.getString("wallet");

                // Create a StudentInformation object and add it to the list
                StudentInfoQuery studentInfo = new StudentInfoQuery(firstName, lastName, city, street, phone, wallet);
                NamesList.add(studentInfo);
            }

            // Set the table data
            NamesView.setItems(NamesList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions
        }
    }
}

