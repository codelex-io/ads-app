package io.codelex.adsapp;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class VideoReader {

    public List<String> inputVideos(File directory) {
        File[] files = directory.listFiles();
        List<String> results = new ArrayList<>();
        for (File file : Objects.requireNonNull(files)) {
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
