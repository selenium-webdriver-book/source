package swip.ch07managingwebdriver;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.logging.Logger;

public class ConfigFactory {
    private static final Logger LOGGER = Logger.getLogger(ConfigFactory.class.getName());
    public static final String BASE_URL = baseUrl(LOGGER);

    private static String baseUrl(Logger logger) {
        try {
            String baseUrl = System.getProperty("webdriver.baseUrl", "http://" + getHostName() + ":8080");
            logger.info("baseUrl " + baseUrl);
            return baseUrl;
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getHostName() throws UnknownHostException, SocketException {
        Optional<NetworkInterface> first = Collections
                .list(NetworkInterface.getNetworkInterfaces())
                .stream()
                .peek(p -> LOGGER.info(p.getName() + " " + p.getInetAddresses().nextElement().getHostAddress()))
                .filter(p -> Arrays.asList("docker0", "vboxnet1", "vboxnet0").contains(p.getName()))
                .findFirst();
        return first.isPresent()
                ? first
                .get()
                .getInetAddresses()
                .nextElement()
                .getHostAddress()
                : InetAddress.getLocalHost().getHostAddress();
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        System.out.println(getHostName());
    }
}
