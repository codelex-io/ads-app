package io.codelex.adsapp;

import io.codelex.adsapp.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

public class AdsApplication extends Application {
    
    private Ui ui = new Ui();

    @Override
    public void start(Stage stage) {
        ui.startApplication(stage);
    }
       
    public static void main(String[] args) {
        launch(args);
    }
}