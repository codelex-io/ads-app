package io.codelex.adsapp;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VideoValidatorTest {
    private VideoValidator videoValidator = new VideoValidator();

    @Test
    public void should_check_if_csv_id_has_matching_video_id() {
        //given
        List<Ad> ads = Arrays.asList(new Ad("01.04.19.", "06:00", "180", "M11016"));
        List<String> vidId = Arrays.asList("M11016");

        List<ValidationStatus> results = videoValidator.validate(ads, vidId);

        Assertions.assertEquals(0, results.size());

    }

    @Test
    public void should_return_error_if_not_found() {
        //given
        List<Ad> ads = Arrays.asList(new Ad("01.04.19.", "06:00", "180", "M11016"));
        List<String> vidId = Arrays.asList("M11816");

        List<ValidationStatus> results = videoValidator.validate(ads, vidId);

        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("06:30 Not found M11016", results.get(0).getMessage());
    }

    @Test
    public void should_return_error_for_each_video_not_found() {
        //given
        List<Ad> ads = Arrays.asList(new Ad("01.04.19.", "06:00", "180", "M11016"),
                new Ad("01.04.19.", "06:00", "180", "M11516"));

        List<String> vidId = new ArrayList<>();

        List<ValidationStatus> results = videoValidator.validate(ads, vidId);

        Assertions.assertEquals(2, results.size());
    }
}