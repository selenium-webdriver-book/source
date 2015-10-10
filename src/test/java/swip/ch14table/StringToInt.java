package swip.ch14table;


import static java.lang.Integer.parseInt;

public enum StringToInt implements Locator<String, Integer> {

    PARSE_INT {
        @Override
        public Integer locate(String element) {
            return parseInt(element);
        }

    }
}
