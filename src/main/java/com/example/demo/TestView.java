package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestView extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(TestView.class.getResource("UserWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Phần mềm quản lý sinh viên");
        stage.setScene(scene);
        stage.show();
    }
}
