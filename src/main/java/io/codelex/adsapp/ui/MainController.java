package io.codelex.adsapp.ui;

import javafx.scene.control.TextField;

import java.io.File;

class MainController {
    
    private TextField txtVidPath;
    private TextField txtDirectoryPath;
    
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