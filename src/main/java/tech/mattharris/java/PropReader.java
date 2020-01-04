package tech.mattharris.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropReader {
    String location = "";
    String speed = "";
    String grid = "";
    boolean fullscreen = false;

    PropReader() throws IOException {
        InputStream inputStream = null;
        try {
            final Properties prop = new Properties();
            final String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            // get the property value and print it out
            location = prop.getProperty("location");
            speed = prop.getProperty("speed");
            grid = prop.getProperty("grid");
            fullscreen = prop.getProperty("fullscreen").equals("TRUE");

        } catch (final Exception e) {
            System.out.println("Exception: " + e);
            System.exit(-1);
        }
        inputStream.close();
    }
}
