package io.codelex.adsapp.ui;

import io.codelex.adsapp.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Ui {
    private MainController mainController = new MainController();
    private CsvReader csvReader = new CsvReader();
    private VideoReader videoReader = new VideoReader();
    private VideoValidator videoValidator = new VideoValidator();

    public void startApplication(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label csvLabel = new Label("CSV file Path:");
        final TextField csvFile = new TextField();
        csvFile.setPrefWidth(300);
        Button browseCsv = new Button("Select...");
        browseCsv.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Csv Files", "*.csv"));
            File selectedCsvDirectory = fileChooser.showOpenDialog(primaryStage);
            csvFile.setText(selectedCsvDirectory.getAbsolutePath());
            mainController.setTxtCsvPath(csvFile);
        });

        GridPane.setConstraints(csvLabel, 0, 0);
        grid.getChildren().add(csvLabel);
        GridPane.setConstraints(browseCsv, 2, 1);
        GridPane.setConstraints(csvFile, 0, 1);
        grid.getChildren().add(browseCsv);
        grid.getChildren().add(csvFile);

        Label videoLabel = new Label("Video file Path:");
        BrowseDirectory browseVideoFile = new BrowseDirectory(primaryStage).invoke();
        TextField videoFile = browseVideoFile.getTextField();
        Button browseVideo = browseVideoFile.getButton();
        mainController.setTxtVidPath(videoFile);

        GridPane.setConstraints(videoLabel, 0, 2);
        GridPane.setConstraints(browseVideo, 2, 3);
        GridPane.setConstraints(videoFile, 0, 3);
        grid.getChildren().add(videoLabel);
        grid.getChildren().add(browseVideo);
        grid.getChildren().add(videoFile);

        Label directoryLabel = new Label("Path for new directory");
        BrowseDirectory browseDirectoryFile = new BrowseDirectory(primaryStage).invoke();
        TextField directoryPath = browseDirectoryFile.getTextField();
        Button browseFile = browseDirectoryFile.getButton();
        mainController.setTxtDirectoryPath(directoryPath);

        GridPane.setConstraints(directoryLabel, 0, 4);
        GridPane.setConstraints(browseFile, 2, 5);
        GridPane.setConstraints(directoryPath, 0, 5);
        grid.getChildren().add(directoryLabel);
        grid.getChildren().add(browseFile);
        grid.getChildren().add(directoryPath);


        Button start = new Button("Start");
        start.setOnAction(event -> {
            if (videoFile.getText().isEmpty() || csvFile.getText().isEmpty() || directoryPath.getText().isEmpty()) {
                Alert fail = new Alert(Alert.AlertType.WARNING);
                fail.setHeaderText(null);
                fail.setContentText("Enter all paths");
                fail.showAndWait();
            } else {
                List<String> ads = CsvReader.parseCsv(mainController.getTxtCsvPath());
                List<String> vid = videoReader.inputVideos(mainController.getTxtVidPath());
                String errorList = videoValidator.validate(ads, vid).stream().map(ValidationStatus::getMessage).collect(Collectors.joining("\n"));
                if (!errorList.isEmpty()) {

                    Alert error = new Alert(Alert.AlertType.WARNING);
                    error.setHeaderText("Videos missing");
                    error.setContentText(null);
                    error.getDialogPane().setContent(new TextArea(errorList));
                    error.showAndWait();
                } else {
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setHeaderText("Successful \nCopying");
                    success.showAndWait();

                }

            }
        });

        GridPane.setConstraints(start, 0, 6);
        grid.getChildren().add(start);

        Scene scene = new Scene(grid, 410, 200);
        primaryStage.setTitle("Ads app");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
