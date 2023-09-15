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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StudentController implements Initializable {
    //  interface contents ------->

    @FXML
    TableView<Student> StudenttableView = new TableView<>();

    @FXML
    TableColumn<Student, Integer> studentId;
    @FXML
    TableColumn<Student, String> studentFirstName;
    @FXML
    TableColumn<Student, String> studentmiddleName;
    @FXML
    TableColumn<Student, String> studentLastName;
    @FXML
    TableColumn<Student, String> cityAddress;
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
    private ComboBox gender1;

    //date box
    @FXML
    private DatePicker bDate;

    ///////////////////////////////////////////////////////////////////////////
    ObservableList<Student> listM;

    int index = -1;

    Connection conn = null;

    ResultSet rs = null;

    PreparedStatement pst = null;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button Home;

    ObservableList<Student> list = FXCollections.observableArrayList();


////////////////////////////////////////////////

    public void initialize(URL url, ResourceBundle rb) {


        // Event handler for selecting a row
        StudenttableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Student selectedStudent = StudenttableView.getSelectionModel().getSelectedItem();

                // Fill the text fields with the selected student's information
                sId.setText(String.valueOf(selectedStudent.getStudentId()));
                Fname.setText(selectedStudent.getStudentFirstName());
                Mname.setText(selectedStudent.getStudentmiddleName());
                lastname.setText(selectedStudent.getStudentLastName());
                cityaddress.setText(selectedStudent.getCityAddress());
                streetaddress.setText(selectedStudent.getStreetAddress());
                phone11.setText(String.valueOf(selectedStudent.getPhone1()));
                phone22.setText(String.valueOf(selectedStudent.getPhone2()));
                wallet1.setText(String.valueOf(selectedStudent.getWallet()));
                gender1.setValue(selectedStudent.getGender());
                bDate.setValue(((java.sql.Date) selectedStudent.getBirthdate()).toLocalDate());
            }
        });

        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();
            String viewquery = "SELECT studentId, studentFirstName, studentmiddleName, studentLastName, cityAddress, streetAddress, phone1, phone2, wallet, gender, birthdate FROM students";
            Statement statement = connectDB.createStatement();
            ResultSet queryoutput = statement.executeQuery(viewquery);

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
                Date birthDate = queryoutput.getDate("birthdate");

                Student students = new Student(queryID, sFName, sMName, sLName, cAddress, sAddress, pPhone1, pPhone2, Wallet, Gender, birthDate);
                list.add(students);
            }

            studentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
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

            gender1.getItems().addAll("Male", "Female");

            StudenttableView.setItems(list);


            // Create a filtered list to hold the filtered data
            FilteredList<Student> filteredList = new FilteredList<>(list);

            // Set the filter predicate for the search text field
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(student -> {
                    // If the search text is empty, show all students
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Convert search text to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if the student's first name contains the search text
                    if (student.getStudentFirstName().toLowerCase().contains(lowerCaseFilter)) {
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
            e1.printStackTrace();
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, e1);
        }
    }

    //add a student

    @FXML
    private void addStudent(ActionEvent event) {
        // Get the values from the text fields
        int studentID = Integer.parseInt(sId.getText());
        String firstName = Fname.getText();
        String middleName = Mname.getText();
        String lastName = lastname.getText();
        String city = cityaddress.getText();
        String street = streetaddress.getText();
        int phone1Value = Integer.parseInt(phone11.getText());
        int phone2Value = Integer.parseInt(phone22.getText());
        int walletValue = Integer.parseInt(wallet1.getText());
        String genderValue = gender1.getValue().toString();
        LocalDate birthdateValue = bDate.getValue();

        // Create a Student object with the entered values
        Date birthdate = java.sql.Date.valueOf(birthdateValue);
        Student student = new Student(studentID, firstName, middleName, lastName, city, street, phone1Value, phone2Value, walletValue, genderValue, birthdate);

        // Insert the student into the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Check if the student ID already exists in the table
            String checkQuery = "SELECT COUNT(*) FROM students WHERE studentId = ?";
            PreparedStatement checkStatement = connectDB.prepareStatement(checkQuery);
            checkStatement.setInt(1, student.getStudentId());
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count > 0) {
                // Display a warning message if the ID already exists
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Duplicate Student ID");
                alert.setHeaderText("Warning: Duplicate Student ID");
                alert.setContentText("The entered Student ID already exists in the database. Please enter a unique ID.");
                alert.showAndWait();
            } else {
                // Insert the student into the table
                String insertQuery = "INSERT INTO students (studentId, studentFirstName, studentmiddleName, studentLastName, cityAddress, streetAddress, phone1, phone2, wallet, gender, birthdate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connectDB.prepareStatement(insertQuery);
                statement.setInt(1, student.getStudentId());
                statement.setString(2, student.getStudentFirstName());
                statement.setString(3, student.getStudentmiddleName());
                statement.setString(4, student.getStudentLastName());
                statement.setString(5, student.getCityAddress());
                statement.setString(6, student.getStreetAddress());
                statement.setInt(7, student.getPhone1());
                statement.setInt(8, student.getPhone2());
                statement.setInt(9, student.getWallet());
                statement.setString(10, student.getGender());
                statement.setDate(11, (java.sql.Date) student.getBirthdate());
                statement.executeUpdate();

                // Add the student to the table view
                list.add(student);

                // Display a success message
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Student Added");
                alert.setHeaderText("Success: Student Added");
                alert.setContentText("The student has been added successfully.");
                alert.showAndWait();

                // Assign the student to the tutor with the fewest students
                String assignQuery = "SELECT trainerID, COUNT(*) AS studentCount FROM student_trainer GROUP BY trainerID ORDER BY studentCount ASC LIMIT 1";
                Statement assignStatement = connectDB.createStatement();
                ResultSet assignResult = assignStatement.executeQuery(assignQuery);

                if (assignResult.next()) {
                    int tutorID = assignResult.getInt("trainerID");

                    // Insert the assignment into the trainers_students table
                    String assignmentQuery = "INSERT INTO student_trainer (trainerID, studentID) VALUES (?, ?)";
                    // display a notification for the student to know his new trainer
                    PreparedStatement assignmentStatement = connectDB.prepareStatement(assignmentQuery);
                    assignmentStatement.setInt(1, tutorID);
                    assignmentStatement.setInt(2, student.getStudentId());

                    assignmentStatement.executeUpdate();
                }


            }


        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    // Method to retrieve the name of the assigned trainer
    private String getAssignedTrainerName(int studentId) {
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();
            String query = "SELECT t.trainerName FROM trainers t JOIN student_trainer st ON t.trainerID = st.trainerID WHERE st.studentID = ?";
            PreparedStatement statement = connectDB.prepareStatement(query);
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("trainerName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }
        return "N/A";
    }
    // update a student

    @FXML
    private void updateStudent(ActionEvent event) {
        // Check if a student is selected in the table
        Student selectedStudent = StudenttableView.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            // Display a warning message if no student is selected
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Student Selected");
            alert.setHeaderText("Warning: No student selected!");
            alert.setContentText("Please select a student from the table.");
            alert.showAndWait();
            return;
        }

        // Get the updated values from the text fields
        int studentId = Integer.parseInt(sId.getText());
        String firstName = Fname.getText();
        String middleName = Mname.getText();
        String lastName = lastname.getText();
        String city = cityaddress.getText();
        String street = streetaddress.getText();
        int phone1Value = Integer.parseInt(phone11.getText());
        int phone2Value = Integer.parseInt(phone22.getText());
        int walletValue = Integer.parseInt(wallet1.getText());
        String genderValue = gender1.getValue().toString();
        LocalDate birthdateValue = bDate.getValue();

        // Update the selected student object with the new values
        selectedStudent.setStudentId(studentId);
        selectedStudent.setStudentFirstName(firstName);
        selectedStudent.setStudentmiddleName(middleName);
        selectedStudent.setStudentLastName(lastName);
        selectedStudent.setCityAddress(city);
        selectedStudent.setStreetAddress(street);
        selectedStudent.setPhone1(phone1Value);
        selectedStudent.setPhone2(phone2Value);
        selectedStudent.setWallet(walletValue);
        selectedStudent.setGender(genderValue);
        selectedStudent.setBirthdate(java.sql.Date.valueOf(birthdateValue));

        // Update the student in the MySQL table
        Connecter conn = new Connecter();
        try {
            Connection connectDB = conn.getConnection();

            // Update the student's information
            String updateQuery = "UPDATE students SET studentId = ?, studentFirstName = ?, studentmiddleName = ?, studentLastName = ?, cityAddress = ?, streetAddress = ?, phone1 = ?, phone2 = ?, wallet = ?, gender = ?, birthdate = ? WHERE studentId = ?";
            PreparedStatement statement = connectDB.prepareStatement(updateQuery);
            statement.setInt(1, selectedStudent.getStudentId());
            statement.setString(2, selectedStudent.getStudentFirstName());
            statement.setString(3, selectedStudent.getStudentmiddleName());
            statement.setString(4, selectedStudent.getStudentLastName());
            statement.setString(5, selectedStudent.getCityAddress());
            statement.setString(6, selectedStudent.getStreetAddress());
            statement.setInt(7, selectedStudent.getPhone1());
            statement.setInt(8, selectedStudent.getPhone2());
            statement.setInt(9, selectedStudent.getWallet());
            statement.setString(10, selectedStudent.getGender());
            statement.setDate(11, (java.sql.Date) selectedStudent.getBirthdate());
            statement.setInt(12, selectedStudent.getStudentId());
            statement.executeUpdate();

            // Refresh the table view
            StudenttableView.refresh();

            // Display a success message
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Student Updated");
            alert.setHeaderText("Success: Student Updated");
            alert.setContentText("The student has been updated successfully.");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    @FXML
    private void deleteStudent(ActionEvent event) {
        // Check if a student is selected in the table
        Student selectedStudent = StudenttableView.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            // Display a warning message if no student is selected
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Student Selected");
            alert.setHeaderText("Warning: No student selected!");
            alert.setContentText("Please select a student from the table.");
            alert.showAndWait();
            return;
        }

        // Show a confirmation dialog to confirm the deletion
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Student");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete the selected student?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Delete the student from the MySQL table
            Connecter conn = new Connecter();
            try {
                Connection connectDB = conn.getConnection();

                // Delete the student using the student ID
                String deleteQuery = "DELETE FROM students WHERE studentId = ?";
                PreparedStatement statement = connectDB.prepareStatement(deleteQuery);
                statement.setInt(1, selectedStudent.getStudentId());
                statement.executeUpdate();

                // Remove the student from the table view
                list.remove(selectedStudent);

                // Display a success message
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Student Deleted");
                alert1.setHeaderText("Success: Student Deleted");
                alert1.setContentText("The student has been deleted successfully.");

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













