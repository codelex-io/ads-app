package io.codelex.adsapp;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DirectoryCreatorTest {
    
    private DirectoryCreator directoryCreator = new DirectoryCreator();
    private File testDir;
    
    @Test
    public void should_create_parent_directory(){
        //given
        List<Ad> ads = Arrays.asList(new Ad("01.04.19.", "06:00", "180", "M11016"));
        //when
        directoryCreator.directoryCreator(testDir, ads);
        //then
        
        
    }

}