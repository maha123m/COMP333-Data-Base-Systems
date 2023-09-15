package com.example.maha;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

import java.util.Objects;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login.fxml")));
			Scene scene = new Scene(root, 520, 400);
			primaryStage.setResizable(false);
			scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/application.css")).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}