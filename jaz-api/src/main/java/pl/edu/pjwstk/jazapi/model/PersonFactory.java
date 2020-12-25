package pl.edu.pjwstk.jazapi.model;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static pl.edu.pjwstk.jazapi.util.Utils.randomLong;
import static pl.edu.pjwstk.jazapi.util.Utils.randomMember;

public class PersonFactory {
    private static Random random = new Random();
    private static List<String> nameList = List.of("Arnold", "Bernard", "Czesław", "Damian", "Edward", "Franek", "Grzegoż");
    private static List<String> surnameList = List.of("Kowalski", "Marley", "Kimono", "Stachura", "Schwarzenegger");
    private Train train;

    PersonFactory(Train train) {
        this.train = train;
    }

    public Person spawn() {
        return new Person(
                randomMember(nameList),
                randomMember(surnameList),
                train.getForward() ?
                        randomLong(train.getStation() + 1, train.getLastStation()) :
                        randomLong(0L, train.getStation() - 1)
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
