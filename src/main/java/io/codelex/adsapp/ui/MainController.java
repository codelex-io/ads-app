package io.codelex.adsapp.ui;

import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainController {

    private TextField txtCsvPath;
    private TextField txtVidPath;
    private TextField txtDirectoryPath;

    public Reader getTxtCsvPath() throws IOException {
        Path csvPath = Paths.get(txtCsvPath.getText());
        return Files.newBufferedReader(csvPath);
    }

    void setTxtCsvPath(TextField txtCsvPath) {
        this.txtCsvPath = txtCsvPath;
    }

    File getTxtVidPath() {
        return new File(txtVidPath.getText());
    }

    void setTxtVidPath(TextField txtVidPath) {
        this.txtVidPath = txtVidPath;
    }

    File getTxtDirectoryPath() {
        return new File(txtDirectoryPath.getText());
    }

    void setTxtDirectoryPath(TextField txtDirectoryPath) {
        this.txtDirectoryPath = txtDirectoryPath;
    }
}
