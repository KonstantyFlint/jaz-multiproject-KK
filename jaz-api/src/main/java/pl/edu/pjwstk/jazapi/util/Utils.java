package pl.edu.pjwstk.jazapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.edu.pjwstk.jazapi.model.UserModel;

import java.util.List;
import java.util.Random;

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
    public static void adminjson(){
        UserModel model = new UserModel();
        model.setUsername("admin");
        model.setPassword("admin");
        model.setAuthorities("ROLE_ADMIN");
        ObjectMapper mapper = new ObjectMapper();

        try {
            System.out.println(mapper.writeValueAsString(mapper));
        } catch (JsonProcessingException e) {
            System.out.println("asdasfchuj kurwa dipa");
        }
    }
}
