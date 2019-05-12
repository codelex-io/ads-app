package io.codelex.adsapp;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VideoCopier {

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static double currentProgress;

    public void copyVideos(File input,
                           File output,
                           List<Ad> ads,
                           ProgressBar progressBar,
                           ObservableList<Node> consoleList) throws IOException, InterruptedException {

        long start = System.nanoTime();
        int loopCount = 0;
        Set notCopied = new HashSet<>();

        File[] files = input.listFiles();
        Path vidPath = null;
        String outputPath = null;
        double i = 0.0;

        for (Ad ad : ads) {
            String adId = ad.getAdvertisingName();
            String startTime = ad.getProgramStart();
            assert files != null;
            List<Path> collect;
            for (File file : files) {
                if (file.getName().contains(adId)) {
                    vidPath = Paths.get(file.getAbsolutePath());
                    String parentDirName = ads.get(0).getDate();
                    File finalFile = new File(output + "/" + parentDirName);

                    try (Stream<Path> pathStream = Files.find(finalFile.toPath(),
                            2,
                            (path, basicFileAttributes) -> path.getFileName().toString().contains(startTime))) {
                        collect = pathStream.collect(Collectors.toList());
                        outputPath = collect.get(0).toString() + "/" + file.getName();
                    }
                }
            }
            boolean noError = true;
            assert vidPath != null;
            try {
                Files.copy(vidPath, Paths.get(outputPath));
            } catch (Exception e) {
                Text fileNotFound = new Text("\nFile " + adId + " not copied to folder " + startTime);
                fileNotFound.setFill(Color.RED);
                Platform.runLater(() -> consoleList.add(fileNotFound));
                noError = false;
                notCopied.add(adId);
            }

            if (noError) {
                Thread.sleep(2000);
                i++;
                Text currentVideoCopying = new Text("\n"
                        + dtf.format(LocalTime.now()) + " Copying: "
                        + adId + " to "
                        + startTime);
                Platform.runLater(() -> consoleList.add(currentVideoCopying));
                currentProgress = i / ads.size();
                Platform.runLater(() -> progressBar.setProgress(currentProgress));
                loopCount++;
            }
        }
        Platform.runLater(() -> progressBar.setProgress(1));
        long elapsedTime = System.nanoTime() - start;
        Text done = new Text("\n"
                + loopCount + " out of "
                + ads.size() + " videos copied in "
                + elapsedTime / 1000000000 / 60 + " minutes and "
                + elapsedTime / 1000000000 % 60 + " seconds!");
        if (loopCount < ads.size()) {
            done.setFill(Color.RED);
            Text notCopiedVideos = new Text("\nFollowing videos not copied: " + Arrays.toString(notCopied.toArray()));
            notCopiedVideos.setFill(Color.RED);
            Platform.runLater(() -> consoleList.add(notCopiedVideos));
        } else {
            done.setFill(Color.GREEN);
        }
        Platform.runLater(() -> consoleList.add(done));
    }
}