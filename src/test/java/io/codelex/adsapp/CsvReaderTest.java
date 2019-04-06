package io.codelex.adsapp;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.*;

public class CsvReaderTest {

    private final File file = new File("./src/test/resources/test_file.csv");

    private CsvReader csvReader = new CsvReader();

    @Test
    public void should_return_true_if_csv_file_found() {
        //then
        assertTrue(file.exists());

    }

    @Test
    public void should_return_false_if_csv_file_not_found() {
        //when
        File file = new File("./src/test/resources/doesnt_exist.csv");
        //then
        assertFalse(file.exists());

    }

    @Test
    public void should_parse_test_file() throws IOException {
        //given
        Reader reader = Files.newBufferedReader(Paths.get("./src/test/resources/test_file.csv"));
        //when
        List<Ad> ads = csvReader.parseCsv(reader);
        //then
        assertEquals(LocalDate.of(2019, 4, 1), ads.get(0).getDate());
        assertEquals(LocalTime.of(6, 0), ads.get(0).getProgramStart());
        assertEquals(180, ads.get(0).getBreakMinutes());
        assertTrue(ads.get(0).getAdvertisingName().matches("[M][0-9]{5}"));

    }

    @Test
    public void should_parse_local_file() throws IOException {
        //given
        Reader reader = Files.newBufferedReader(Paths.get("/home/nils/Downloads/xxx.csv"));
        //when
        List<Ad> ads = csvReader.parseCsv(reader);
        //then
        assertEquals(LocalDate.of(2019, 4, 1), ads.get(0).getDate());
        assertEquals(LocalTime.of(6, 0), ads.get(0).getProgramStart());
        assertEquals(180, ads.get(0).getBreakMinutes());
        assertTrue(ads.get(0).getAdvertisingName().matches("[M][0-9]{5}"));
    }
    
}