package io.codelex.adsapp;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VideoCopier {

    public static double currentProgress;

    public void copyVideos(File input, File output, List<Ad> ads, ProgressBar progressBar) throws IOException, InterruptedException {

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

                        assert vidPath != null;
                        outputPath = collect.get(0).toString() + "/" + file.getName();
                    }
                }
            }

            assert vidPath != null;
            Files.copy(vidPath, Paths.get(outputPath));
            Thread.sleep(2000);
            i++;
            currentProgress = i / ads.size();
            Platform.runLater(() -> progressBar.setProgress(currentProgress));
        }
    }
}