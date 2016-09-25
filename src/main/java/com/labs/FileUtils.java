package com.labs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by ameks on 10.09.2016.
 */
public class FileUtils {

    public static String CLASS_SEPARATOR = ".";
    public static String PATH_SEPARATOR = "\\";
    public static String CLASS_PACKAGE = System.getProperty("classes.package", "");
    public static String PACKAGE_FOLDER = "target\\classes";
    public static String CLASS_PATTERN = System.getProperty("classes.name.pattern", "");

    public static boolean compile( String javaFile ) throws IOException {
        Process p = Runtime.getRuntime().exec( "javac "+javaFile );
        try {
            p.waitFor();
        } catch( InterruptedException ie ) { System.out.println( ie ); }
        int ret = p.exitValue();
        return ret == 0;
    }

    public static byte[] getBytes(String filename) throws IOException {
        File file = new File(filename);
        long len;
        byte bytes[] = null;
        boolean fileLoaded = false;
        int repeatCount = 3;
        while (repeatCount != 0) {
            try (FileInputStream fin = new FileInputStream(file)) {
                len = file.length();
                bytes = new byte[(int) len];
                int r = fin.read(bytes);
                if (r != len )
                    throw new IOException("Can't read all, " + r + " != " + len);
                repeatCount = 0;
            } catch (IOException ex) {
                System.out.println("Waiting for file's compilation.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repeatCount--;
            }
        }
        return bytes;
    }

    public static String getFullClassName() {
        return FileUtils.CLASS_PACKAGE + FileUtils.CLASS_SEPARATOR + FileUtils.CLASS_PATTERN;
    }
}
