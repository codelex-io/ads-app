package io.codelex.adsapp.ui;

import io.codelex.adsapp.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
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
    private DirectoryCreator directoryCreator = new DirectoryCreator();
    private VideoCopier videoCopier = new VideoCopier();
    private ProgressBar progressBar = new ProgressBar();

    public void start(Stage primaryStage) {
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

        Label directoryLabel = new Label("Path for new directory:");
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

        //Progress Bar
        GridPane.setConstraints(progressBar, 0, 7);
        grid.getChildren().add(progressBar);
        progressBar.setMinWidth(300);

        Text actionIndicator = new Text("Waiting for input");
        GridPane.setConstraints(actionIndicator, 0, 6);
        grid.getChildren().add(actionIndicator);

        Button exit = new Button("Exit");
        GridPane.setConstraints(exit, 2, 7);
        grid.getChildren().add(exit);
        exit.setMinWidth(71);
        exit.setDisable(true);
        exit.setOnAction(e -> closeProgram(primaryStage));

        Button start = new Button("Start");
        start.setMinWidth(71);
        start.setOnAction(event -> {
            start.setDisable(true);
            if (videoFile.getText().isEmpty() || csvFile.getText().isEmpty() || directoryPath.getText().isEmpty()) {
                Alert fail = new Alert(Alert.AlertType.WARNING);
                fail.setHeaderText(null);
                fail.setContentText("Enter all paths");
                fail.showAndWait();
            } else {
                List<Ad> ads = csvReader.parseCsv(csvFile.getText());
                List<String> vid = videoReader.inputVideos(mainController.getTxtVidPath());
                String errorList = videoValidator.validate(ads, vid).stream().map(ValidationStatus::getMessage).collect(Collectors.joining("\n"));
                if (!errorList.isEmpty()) {

                    Alert error = new Alert(Alert.AlertType.WARNING);
                    error.setHeaderText("Videos missing");
                    error.setContentText(null);
                    error.getDialogPane().setContent(new TextArea(errorList));
                    error.showAndWait();
                } else {
                    Thread thread = new Thread(() -> {
                        browseCsv.setDisable(true);
                        browseVideo.setDisable(true);
                        browseFile.setDisable(true);
                        directoryCreator.directoryCreator(mainController.getTxtDirectoryPath(), ads);
                        try {
                            actionIndicator.setText("Copying videos...");
                            videoCopier.copyVideos(mainController.getTxtVidPath(), mainController.getTxtDirectoryPath(), ads, progressBar);
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                        actionIndicator.setText("Done!");
                        exit.setDisable(false);
                    });
                    thread.setDaemon(true);
                    thread.start();
                }
            }
        });

        GridPane.setConstraints(start, 2, 6);
        grid.getChildren().add(start);

        Scene scene = new Scene(grid, 410, 270);
        primaryStage.setTitle("Ads app");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void closeProgram(Stage primaryStage) {
        primaryStage.close();
    }
}