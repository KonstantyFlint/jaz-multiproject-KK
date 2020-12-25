package pl.edu.pjwstk.jazapi.util;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Utils {
    private static Random random = new Random();
    public static <T> T fallbackIfNull(T mainChoice, T alternativeChoice) {
        return mainChoice != null
                ? mainChoice
                : alternativeChoice;
    }

    public static <T> T randomMember(List<T> list){
        return list.get(random.nextInt(list.size()));
    }
    public static Long randomLong(Long min, Long max){
        Long range = max - min + 1;
        return Math.abs(random.nextLong()) % range + min;
    }
}
