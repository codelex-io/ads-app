package io.codelex.adsapp;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class DirectoryCreatorTest {
    
    private DirectoryCreator directoryCreator = new DirectoryCreator();
    private File testDir;
    
    @Test
    public void should_create_parent_directory(){
        //given
        List<Ad> ads = Arrays.asList(new Ad(LocalDate.of(2019, 4, 1), LocalTime.of(6, 30), 30, "M11016"));
        //when
        directoryCreator.directoryCreator(testDir, ads);
        //then
        
        
    }

}