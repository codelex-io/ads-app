package io.codelex.adsapp.ui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

class BrowseDirectory {
    private Stage primaryStage;
    private TextField textField;
    private Button button;

    BrowseDirectory(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    TextField getTextField() {
        return textField;
    }

    Button getButton() {
        return button;
    }

    BrowseDirectory invoke() {
        textField = new TextField();
        button = new Button("Select...");
        button.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedVideoDirectory = directoryChooser.showDialog(primaryStage);
            textField.setText(selectedVideoDirectory.getAbsolutePath());
        });
        return this;
    }
}

