package io.codelex.adsapp;

import java.util.ArrayList;
import java.util.List;

public class VideoValidator {

    public List<ValidationStatus> validate(List<String> ads, List<String> vidIds) {
        List<ValidationStatus> results = new ArrayList<>();

        for (String ad : ads) {
            if (!vidIds.contains(ads.get(4))) {
                results.add(new ValidationStatus(ads.get(3) + " Not found " + ads.get(4)));
            }
        }
        return results;
    }
}
