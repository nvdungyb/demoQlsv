package com.example.demo;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class ImageChooser {
    public String getImageUrl() throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        File selecttedFile = fileChooser.showOpenDialog(new Stage());
        if (selecttedFile != null) {
            return selecttedFile.toURL().toString();
        }

        return null;
    }
}
