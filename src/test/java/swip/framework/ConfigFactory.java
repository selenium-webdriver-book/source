package swip.framework;

public class ConfigFactory {
    public static final String BASE_URL =
            System.getProperty("webdriver.baseUrl", "http://localhost:8080");
}
