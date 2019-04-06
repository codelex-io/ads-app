package io.codelex.adsapp;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoValidator {
    
    public List<ValidationStatus> validate(List<Ad> ads, List<String> vidIds) {
        List<ValidationStatus> results = new ArrayList<>();

        for (Ad ad : ads) {
            if(!vidIds.contains(ad.getAdvertisingName())){
                results.add(new ValidationStatus(ad.getProgramStart() + " Not found " + ad.getAdvertisingName()));
            }
            
        }
        if(results.isEmpty()){
            results.add(new ValidationStatus("Successful"));
        }
        return results;
    }
}
   