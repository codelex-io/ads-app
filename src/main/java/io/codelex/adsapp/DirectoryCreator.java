package io.codelex.adsapp;

import java.io.File;
import java.util.List;

public class DirectoryCreator {

    public void directoryCreator(File directory, List<Ad> ads) {


        String created = "";
        int id = 1;
        String parentDirName = ads.get(0).getDate();
        File parentFile = new File(directory + "/" + parentDirName);
        parentFile.mkdir();

        for (Ad ad : ads) {
            String dirNameStartTime = ad.getProgramStart();
            String dirNameAdsBreakTime = String.valueOf(ad.getBreakMinutes());
            if (!created.equals(dirNameStartTime)) {
                new File(parentFile.getAbsolutePath() + "/" + id + "_" + dirNameStartTime + "(" + dirNameAdsBreakTime + ")").mkdirs();
                created = dirNameStartTime;
                id++;
            }
        }
    }
}
