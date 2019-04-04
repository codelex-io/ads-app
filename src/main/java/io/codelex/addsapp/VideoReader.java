package io.codelex.addsapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoReader {

    public List<String> inputVideos(File[] files) {
        List<String> results = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                String vid = file.getName();
                Pattern pattern = Pattern.compile("(?<=_)(M.*?)(?=_)");
                Matcher matcher = pattern.matcher(vid);
                if (matcher.find()) {
                    results.add(matcher.group());
                }
            }
        }
        return results;
    }
}
