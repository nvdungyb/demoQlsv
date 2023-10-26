package com.example.demo;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class MainController implements Initializable {
    @FXML
    private PasswordField Password;
    @FXML
    public TextField userName;
    @FXML
    private Button LoginButton;
    @FXML
    private Label status;

    public static String userNameLogin;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public void handleLoginButton(ActionEvent event) {
        if (event.getSource() == LoginButton) {
            checkInfo();
        }
    }

    public void statusPause() {
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(even -> {
            status.setText("");
        });
        delay.play();
    }

    public void checkInfo() {
        String name = userName.getText().trim();
        String pass = Password.getText().trim();

        if (name.equals("") || pass.equals("")) {
            status.setText("Vui lòng nhập đầy đủ thông tin!");
            statusPause();
        } else {
            String sql = "SELECT nameSV, maSV FROM sys.students WHERE nameSV = '" + name + "' AND maSV = '" + pass + "'";
            connection = ConnectDatabase.connect();
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
                    status.setText("Đăng nhập thành công!");
                    statusPause();

                    userNameLogin = name;
                    LoginButton.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("UserWindow.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                } else {
                    status.setText("Tên đăng nhập hoặc mật khẩu sai!");
                    statusPause();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getUserName() {
        return userNameLogin;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (LoginButton != null)
            LoginButton.setOnAction(this::handleLoginButton);
    }
}