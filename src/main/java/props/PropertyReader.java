package props;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static String getConnectionUrlForPostgres() {
        try (InputStream input = PropertyReader.class.getClassLoader()
                .getResourceAsStream("app.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);

            return new StringBuilder("jdbc:postgresql://")
                    .append(prop.getProperty("postgres.db.host"))
                    .append(":")
                    .append(prop.getProperty("postgres.db.port"))
                    .append("/")
                    .append(prop.getProperty("postgres.db.database"))
                    .toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getUserForPostgres() {
        try (InputStream input = PropertyReader.class.getClassLoader()
                .getResourceAsStream("app.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);

            return prop.getProperty("postgres.db.username");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getPasswordForPostgres() {
        try (InputStream input = PropertyReader.class.getClassLoader()
                .getResourceAsStream("app.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);

            return prop.getProperty("postgres.db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

