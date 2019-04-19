package io.codelex.adsapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VideoCopier {

    public void videoCopier(File input, File output, List<Ad> ads) throws IOException {
        File[] files = input.listFiles();
        Path vidPath = null;

        for (Ad ad : ads) {
            String adId = ad.getAdvertisingName();
            String startTime = ad.getProgramStart().toString();
            assert files != null;
            for (File file : files) {
                if (file.getName().contains(adId)) {
                    vidPath = Paths.get(file.getAbsolutePath());
                }


                try (Stream<Path> pathStream = Files.find(output.toPath(),
                        2,
                        (path, basicFileAttributes) -> path.getFileName().toString().contains(startTime))) {
                    List<Path> collect = pathStream.collect(Collectors.toList());

                    assert vidPath != null;
                    Files.copy(vidPath, collect.get(0));
                }

            }
        }
    }
}


/*
String fileName = stream
                            .sorted()
                            .map(String::valueOf)
                            .filter((path) -> {
                                //System.out.println("In Filter : "+path);
                                return String.valueOf(path).endsWith("system_health_12_55_TestServer.json");
                            })
                            .collect(Collectors.joining());
        System.out.println("fileName : "+fileName);



stream.forEach(System.out::println);
try (Stream<Path> stream = Files.find(Paths.get("Folder 1"), 5,
            (path, attr) -> path.getFileName().toString().equals("Myfile.txt") )) {
        System.out.println(stream.findAny().isPresent());
} catch (IOException e) {
        e.printStackTrace();
}



listOfFiles[i].getName();
public String setOutput(String name, File file) {
    File[] list = file.listFiles();
    if (list != null) {
        for (File fil : list) {
            String path = null;
            if (fil.isDirectory()) {
                path = setOutput(name, fil);
                if (path != null) {
                    return path;
                }
            } else if (fil.getName().contains(name)) {
                path =fil.getAbsolutePath();
                if (path != null) {
                    return path;
                }
            }
        }
    }
    return null; // nothing found
}


@Test
public void givenNIO2_whenCopied_thenCopyExistsWithSameContents() 
  throws IOException {
  
    Path copied = Paths.get("src/test/resources/copiedWithNio.txt");
    Path originalPath = original.toPath();
    Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
  
    assertThat(copied).exists();
    assertThat(Files.readAllLines(originalPath)
      .equals(Files.readAllLines(copied)));
}*/
