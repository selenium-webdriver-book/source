package swip.ch16table.mapper;

import swip.framework.Element;
import swip.ch16table.domain.Person;

import java.util.List;
import java.util.function.Function;

public enum PersonMapper implements Function<List<Element>, Person> {

    MAPPER_NON_JAVA_8 {
        @Override
        public Person apply(List<Element> cells) {
            return new Person(
                Integer.parseInt(cells.get(0).getText()),
                cells.get(1).getText(),
                cells.get(2).getText(),
                Integer.parseInt(cells.get(3).getText())
            );
        }
    }
}