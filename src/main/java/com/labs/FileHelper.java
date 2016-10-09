package com.labs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by ameks on 09.10.2016.
 */
public class FileHelper {

    private static final String filesFolder = "files";

    private static final String testFileName = "testFile.txt";

    public static final Path getFilePath() {
        return Paths.get(filesFolder + File.separator + testFileName);
    }
    
    public static List<String> readLines(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
