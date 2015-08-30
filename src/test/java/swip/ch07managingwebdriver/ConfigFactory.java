package swip.ch07managingwebdriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ConfigFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFactory.class);
    public static final String BASE_URL = baseUrl(LOGGER);

    private static String baseUrl(Logger logger) {
        String baseUrl = System.getProperty("webdriver.baseUrl", "http://localhost:8080");
        logger.info("baseUrl " + baseUrl);
        return baseUrl;
    }
}
