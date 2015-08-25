package swip.ch07managingwebdriver;

import java.util.logging.Logger;

public class ConfigFactory {
    private static final Logger LOGGER = Logger.getLogger(ConfigFactory.class.getName());
    public static final String BASE_URL = baseUrl(LOGGER);

    private static String baseUrl(Logger logger) {
        String baseUrl = System.getProperty("webdriver.baseUrl", "http://localhost:8080");
        logger.info("baseUrl " + baseUrl);
        return baseUrl;
    }
}
