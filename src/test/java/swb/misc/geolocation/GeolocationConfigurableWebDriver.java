package swb.misc.geolocation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

public class GeolocationConfigurableWebDriver {

    private static final String GEO_LOCATION_JSON
            = "/tmp/geo-location.json"; // <1> Must be an absolute path.

    @SuppressWarnings("unchecked")
    public static WebDriver create(
            Function<FirefoxProfile, FirefoxDriver> driverFactory,
            GeolocationStatus status,
            double lat,
            double lng
    ) {

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("geo.wifi.uri", "file://" + GEO_LOCATION_JSON); // <2> Set the location of a geo-location JSON file.
        profile.setPreference("geo.prompt.testing", true); // <3> Enable test mode.
        profile.setPreference("geo.prompt.testing.allow", status.equals(GeolocationStatus.OK));

        try {
            Files.write(Paths.get(GEO_LOCATION_JSON), String.format("{" +
                            "    \"status\": \"OK\"," + // <4> Firefox has changed the format, this repetition makes sure it works every where.
                            "    \"accuracy\": 10.0," +
                            "    \"location\": {" +
                            "        \"lat\": %s," +
                            "        \"lng\": %s," +
                            "        \"latitude\": %s," +
                            "        \"longitude\": %s," +
                            "        \"accuracy\": 10.0" +
                            "    }" +
                            "}",
                    lat, lng, lat, lng).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return driverFactory.apply(profile);
    }
}
