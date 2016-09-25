package com.labs;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    private static final Reloader reloader = new Reloader();

    //-Dclasses.package=com.labs.content -Dclasses.name.pattern=Content
    public static void main( String[] args ) throws ClassNotFoundException, InterruptedException, IOException, IllegalAccessException, InstantiationException {
        int iter = 0;
        while (true) {
            String classSignature = FileUtils.getFullClassName();
            Class clazz = reloader.reloadClass(classSignature);
            System.out.println( ++iter + " " + clazz.newInstance());
            Thread.sleep(1000);
        }
    }

}
