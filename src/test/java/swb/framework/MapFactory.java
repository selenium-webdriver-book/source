package swb.framework;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class MapFactory {

    private static void populate(Map<String, String> properties, Map<String, Object> map) {
        properties.entrySet().stream()
                .map(e -> new AbstractMap.SimpleImmutableEntry<>(e.getKey(), e.getValue()))
                .forEach(e -> getParent(e.getKey(), map).put(removePrefix(e.getKey()), stringToObject(e.getValue())));
    }

    private static String removePrefix(String s) {
        return s.replaceFirst(".*\\.", "");
    }

    private static Object stringToObject(String s) {

        switch (s) {
            case "true":
                return true;
            case "false":
                return false;
        }

        if (s.matches("[0-9]*")) {
            return Integer.parseInt(s);
        }

        if (s.matches("[0-9]*\\.[0-9]*")) {
            return Double.parseDouble(s);
        }

        return s;
    }

    private static Map<String, Object> getParent(String key, Map<String, Object> map) {

        String[] bits = key.split("\\.");

        for (int i = 0; i < bits.length - 1; i++) {
            String bit = bits[i];
            if (!map.containsKey(bit)) {
                map.put(bit, new HashMap<String, Object>());
            }
            Object value = map.get(bit);
            if (!(value instanceof Map)) {
                throw new IllegalArgumentException(String.format("at %s found %s but expected map", key, value.getClass()));
            }
            //noinspection unchecked
            map = (Map<String, Object>) value;
        }

        return map;
    }

    public Map<String, Object> create(Map<String, String> properties) {
        HashMap<String, Object> map = new HashMap<>();

        populate(properties, map);

        return map;
    }
}
