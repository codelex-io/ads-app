package io.codelex.addsapp;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
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
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test_file.csv").getFile());
        //when
        Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));

        CsvToBean<Ad> csvToBean = new CsvToBeanBuilder<Ad>(reader)
                .withType(Ad.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSkipLines(1)
                .build();

        List<Ad> ads = csvToBean.parse();
        
        //then
        assertEquals(LocalDate.of(2019, 4, 1), ads.get(0).getDate());
        assertEquals(LocalTime.of(6, 0), ads.get(0).getProgramStart());
        assertEquals(180, ads.get(0).getBreakMinutes());
        assertTrue(ads.get(0).getAdvertisingName().matches("[M]{1}[0-9]{5}"));
        
    }
      
}