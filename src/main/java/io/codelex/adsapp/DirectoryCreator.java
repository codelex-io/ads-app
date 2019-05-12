package io.codelex.adsapp;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DirectoryCreator {

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void directoryCreator(File directory, List<Ad> ads, ObservableList consoleList) {

        Text create = new Text(dtf.format(LocalTime.now()) + " Creating directories");
        Platform.runLater(() -> consoleList.add(create));

        String created = "";
        int id = 1;
        String parentDirName = ads.get(0).getDate();
        File parentFile = new File(directory + "/" + parentDirName);
        parentFile.mkdir();

        for (Ad ad : ads) {
            String dirNameStartTime = ad.getProgramStart();
            String dirNameAdsBreakTime = String.valueOf(ad.getBreakMinutes());
            if (!created.equals(dirNameStartTime)) {
                new File(parentFile.getAbsolutePath()
                        + "/" + id
                        + "_" + dirNameStartTime
                        + "(" + dirNameAdsBreakTime
                        + ")")
                        .mkdirs();
                created = dirNameStartTime;
                id++;
            }
        }
    }
}
