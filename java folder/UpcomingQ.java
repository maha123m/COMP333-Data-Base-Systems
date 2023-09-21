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

public class UpcomingQ implements Initializable {
    @FXML
    TableView<SessionInformation> SessionView = new TableView<>();

    @FXML
    private TableColumn<SessionInformation, String> SessionDateU;

    @FXML
    private TableColumn<SessionInformation, String> SessionDayU;

    @FXML
    private TableColumn<SessionInformation, String> SessionTimeU;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the column mappings
        SessionDateU.setCellValueFactory(cellData -> cellData.getValue().sessionDateProperty());
        SessionDayU.setCellValueFactory(cellData -> cellData.getValue().sessionDayProperty());
        SessionTimeU.setCellValueFactory(cellData -> cellData.getValue().sessionTimeProperty());

        // Retrieve data from the database
        try {
            Connection connectDB = Connecter.getConnection();
            String query = "SELECT sessionDate, sessionDay, sessionTime FROM session " +
                    "WHERE trainerID = 1 AND sessionDate >= CURDATE() AND sessionStatus = 'Scheduled'";
            Statement statement = connectDB.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ObservableList<SessionInformation> sessionList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                String sessionDate = resultSet.getString("sessionDate");
                String sessionDay = resultSet.getString("sessionDay");
                String sessionTime = resultSet.getString("sessionTime");

                // Create a SessionInformation object and add it to the list
                SessionInformation sessionInfo = new SessionInformation(sessionDate, sessionDay, sessionTime);
                sessionList.add(sessionInfo);
            }

            // Set the table data
            SessionView.setItems(sessionList);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions
        }
    }
}

