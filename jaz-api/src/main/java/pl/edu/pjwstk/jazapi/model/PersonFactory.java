package pl.edu.pjwstk.jazapi.model;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static pl.edu.pjwstk.jazapi.util.Utils.randomMember;

public class PersonFactory {
    private static Random random = new Random();
    private static List<String> nameList = List.of("Adam", "Bernard", "C");
    private static List<String> surnameList = List.of("AAA", "BBB", "ĄĄĄ");
    private Train train;

    PersonFactory(Train train) {
        this.train = train;
    }

    public Person spawn() {
        return new Person(
                randomMember(nameList),
                randomMember(surnameList),
                train.getForward() ?
                        random.nextLong() % (train.getLastStation() - train.getStation()) + train.getStation() :
                        random.nextLong() % train.getStation()
        );
    }

    public Stream<Person> stream() {
        return Stream.generate(this::spawn);
    }

    public Stream<Person> limitedStream(int min, int max) {
        int count = random.nextInt(max - min + 1) + min;
        return stream().limit(count);
    }
}
