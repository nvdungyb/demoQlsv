package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    private Label userName;
    private ImageChooser imageChooser;

    @FXML
    private ImageView userImage;

    public void handlerImageView() throws MalformedURLException {
        userImage.setOnMouseClicked(event -> {
            if (event.getSource() == userImage) {
                System.out.println("not null");
                imageChooser = new ImageChooser();
                String imageUrl = null;
                try {
                    imageUrl = imageChooser.getImageUrl();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                if (imageUrl != null) {
                    userImage.setImage(new Image(imageUrl));
                } else {
                    System.out.println("null");
                }
            }
        });
    }

    public void start() throws MalformedURLException {

        handlerImageView();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            userName.setText(MainController.getUserName());
            start();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
