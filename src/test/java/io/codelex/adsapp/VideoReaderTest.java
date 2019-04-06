package io.codelex.adsapp;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VideoReaderTest {

    private VideoReader videoReader = new VideoReader();

    private Path testDir;

    private CsvReader csvReader = new CsvReader();

    @Before
    public void setUp() throws Exception {
        testDir = Files.createTempDirectory("ads_test");

        List<String> fileNames = Arrays.asList("Lcom_M09864_Fairy_Shake_Up_Lat_25", "Lcom_M10859_Bayer_Canesten_Janmai_Lat_26", "Lcom_M11016_Regulax_2019_Lat_5");

        for (String fileName : fileNames) {
            Files.createTempFile(testDir, fileName, ".mfx");
        }
    }

    @Test
    public void should_return_list_of_video_names() {
        //given
        File file = new File(String.valueOf(testDir));
        //when
        List<String> result = videoReader.inputVideos(file);
        //then
        assertFalse(result.isEmpty());
    }

    @Test
    public void should_add_only_id_to_list() {
        //given
        File file = new File(String.valueOf(testDir));
        //when
        List<String> result = videoReader.inputVideos(file);
        //then
        assertTrue(result.get(0).matches("[M][0-9]{5}"));
    }
}