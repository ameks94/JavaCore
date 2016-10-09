package com.labs;

import java.io.IOException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class Launcher
{
    
    public static void main( String[] args ) throws IOException {
        Path path = FileHelper.getFilePath();
        
        List<String> lines = FileHelper.readLines(path);
        String lineWithMaxWords = lines
                .stream()
                .sorted(
                        (line1, line2) -> 
                                line1.split("' '|,|\\.").length > line2.split("' '|,|\\.").length ? -1 : 1)
                .findFirst()
                .get();
        System.out.println("Longest line is: " + lineWithMaxWords);
    }
}
