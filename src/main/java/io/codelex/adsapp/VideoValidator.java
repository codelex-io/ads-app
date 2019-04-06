package io.codelex.adsapp;

import java.util.ArrayList;
import java.util.List;

public class VideoValidator {
    
    
    public List<ValidationError> validate(List<Ad> ads, List<String> vidIds) {
        List<ValidationError> results = new ArrayList<>();

        for (Ad ad : ads) {
            
            if(!vidIds.contains(ad.getAdvertisingName())){
                results.add(new ValidationError(ad.getProgramStart() + " Not found " + ad.getAdvertisingName()));
            }
        }
        return results;
    }
}
   