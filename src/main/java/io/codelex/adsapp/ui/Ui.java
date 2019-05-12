package io.codelex.adsapp.ui;

import io.codelex.adsapp.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ui {

    private MainController mainController = new MainController();
    private CsvReader csvReader = new CsvReader();
    private VideoReader videoReader = new VideoReader();
    private VideoValidator videoValidator = new VideoValidator();
    private DirectoryCreator directoryCreator = new DirectoryCreator();
    private VideoCopier videoCopier = new VideoCopier();
    private ProgressBar progressBar = new ProgressBar();
    private TextFlow console = new TextFlow();
    private ScrollPane scrollPane = new ScrollPane(console);

    private boolean inputOnError;

    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label csvLabel = new Label("CSV file Path:");
        final TextField csvFile = new TextField();
        csvFile.setPrefWidth(300);
        Button browseCsv = new Button("Select...");
        browseCsv.setMinWidth(71);
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
        browseVideo.setMinWidth(71);
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
        browseFile.setMinWidth(71);
        mainController.setTxtDirectoryPath(directoryPath);

        GridPane.setConstraints(directoryLabel, 0, 4);
        GridPane.setConstraints(browseFile, 2, 5);
        GridPane.setConstraints(directoryPath, 0, 5);
        grid.getChildren().add(directoryLabel);
        grid.getChildren().add(browseFile);
        grid.getChildren().add(directoryPath);

        //Progress Bar
        Label progressLabel = new Label("Progress:");
        GridPane.setConstraints(progressLabel, 0, 6);
        GridPane.setConstraints(progressBar, 0, 7);
        grid.getChildren().add(progressBar);
        grid.getChildren().add(progressLabel);
        progressBar.setMinWidth(300);

        //Console
        Label consoleLabel = new Label("Log:");
        GridPane.setConstraints(consoleLabel, 0, 8);
        GridPane.setConstraints(scrollPane, 0, 9, 3, 1);
        scrollPane.setMaxHeight(105);
        scrollPane.setMinHeight(105);
        scrollPane.vvalueProperty().bind(console.heightProperty());
        grid.getChildren().add(scrollPane);
        grid.getChildren().add(consoleLabel);
        ObservableList<Node> consoleList = console.getChildren();

        Button exit = new Button("Exit");
        GridPane.setConstraints(exit, 2, 8);
        grid.getChildren().add(exit);
        exit.setMinWidth(71);
        exit.setDisable(true);
        exit.setOnAction(e -> closeProgram(primaryStage));

        Button start = new Button("Start");
        start.setMinWidth(71);
        start.setOnAction(event -> {
            start.setDisable(true);
            inputOnError = true;

            if (videoFile.getText().isEmpty() || csvFile.getText().isEmpty() || directoryPath.getText().isEmpty()) {
                Alert fail = new Alert(Alert.AlertType.WARNING);
                fail.setHeaderText(null);
                fail.setContentText("Enter all paths");
                fail.showAndWait();
            } else {
                List<Ad> ads = csvReader.parseCsv(csvFile.getText());
                List<String> vid = videoReader.inputVideos(mainController.getTxtVidPath());
                String errorList = videoValidator
                        .validate(ads, vid)
                        .stream()
                        .map(ValidationStatus::getMessage)
                        .collect(Collectors.joining("\n"));

                if (!errorList.isEmpty()) {
                    Alert error = new Alert(Alert.AlertType.WARNING);
                    error.setHeaderText("Videos missing. Copy anyway?");
                    error.setContentText(null);
                    error.getDialogPane().setContent(new TextArea(errorList));

                    ButtonType yesButton = new ButtonType("Yes");
                    ButtonType noButton = new ButtonType("No");

                    error.getButtonTypes().clear();
                    error.getButtonTypes().addAll(yesButton, noButton);
                    Optional<ButtonType> option = error.showAndWait();

                    if (option.get() == noButton) {
                        Text cancel = new Text("Copying canceled by user\n");
                        Platform.runLater(() -> consoleList.add(cancel));
                        inputOnError = false;
                        start.setDisable(false);
                    }
                }
                if (inputOnError) {
                    Thread thread = new Thread(() -> {
                        browseCsv.setDisable(true);
                        browseVideo.setDisable(true);
                        browseFile.setDisable(true);
                        directoryCreator.directoryCreator(mainController.getTxtDirectoryPath(), ads, consoleList);
                        try {
                            videoCopier.copyVideos(mainController.getTxtVidPath(),
                                    mainController.getTxtDirectoryPath(),
                                    ads, progressBar, consoleList);
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                        exit.setDisable(false);
                    });
                    thread.setDaemon(true);
                    thread.start();
                }
            }
        });


        GridPane.setConstraints(start, 2, 7);
        grid.getChildren().add(start);

        Scene scene = new Scene(grid, 410, 410);
        primaryStage.setTitle("Ads app");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void closeProgram(Stage primaryStage) {
        primaryStage.close();
    }
}