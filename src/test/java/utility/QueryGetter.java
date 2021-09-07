package utility;

import java.io.IOException;
import java.util.Properties;

public class QueryGetter {
    static final Properties properties = new Properties();
    static {
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("test.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String target) {
        return properties.getProperty(target);
    }
}
