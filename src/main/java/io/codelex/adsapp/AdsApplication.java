package io.codelex.adsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class AdsApplication implements CommandLineRunner {
    
    @Autowired
    CsvReader csvReader;
    
    public static void main(String[] args) {
        SpringApplication.run(AdsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Enter link for csv");
        Scanner a = new Scanner(System.in);
        String path = a.nextLine();
        Reader reader = Files.newBufferedReader(Paths.get(path));
        List<Ad> ads = csvReader.parseCsv(reader);
        System.out.println(ads);
        
    }
}
///home/nils/Downloads/Playlist_ReTV010419_generated280320190336.csv