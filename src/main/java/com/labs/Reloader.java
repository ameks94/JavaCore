package com.labs;

import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Reloader {

    private static Map<String, Pair<String, CustomClassLoader>> loadedClassesHashesLoaders = new HashMap<>();

    public Class reloadClass(String name) throws ClassNotFoundException {
        // read the bytes
        byte b[] = null;
        try {
            b = FileUtils.getBytes( FileUtils.PACKAGE_FOLDER + FileUtils.PATH_SEPARATOR + name.replace(FileUtils.CLASS_SEPARATOR, FileUtils.PATH_SEPARATOR) + ".class" );
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ( b != null ) {
            String classHash = new String(b);
            Pair loadedHashLoader = loadedClassesHashesLoaders.get( name );

            if ( loadedHashLoader != null && classHash.equals(loadedHashLoader.getKey())) {
                return ( (CustomClassLoader) loadedHashLoader.getValue() ).getLoadedClass(name);  /// need classloader for specified class
            }

            ClassLoader customClassLoader = new CustomClassLoader(b);

            System.out.println("Reloading in JVM by " + customClassLoader);

            Class definedClazz = customClassLoader.loadClass(name);

            loadedClassesHashesLoaders.put(name, new Pair(classHash, customClassLoader));

            return definedClazz;
        } else {
            return Class.forName(name);
        }
    }
}
