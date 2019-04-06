package io.codelex.adsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class AdsApplication implements CommandLineRunner {

    @Autowired
    CsvReader csvReader;

    @Autowired
    VideoReader videoReader;
    
    @Autowired
    VideoValidator videoValidator;

    public static void main(String[] args) {
        SpringApplication.run(AdsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Enter link for csv");  //   /home/nils/Downloads/test_file.csv
        Scanner s = new Scanner(System.in);
        String pathCsv = s.nextLine();
        System.out.println("Enter video map path");  //   /home/nils/Downloads/input/input
        String pathVid = s.nextLine();
        Reader reader = Files.newBufferedReader(Paths.get(pathCsv));
        List<Ad> ads = csvReader.parseCsv(reader);
        List<String> vidIds = videoReader.inputVideos(new File(pathVid));

        for (Ad ad : ads) {
            System.out.print(Arrays.asList(ad.getAdvertisingName()));
        }
        System.out.println("\n" + vidIds);
    }
}