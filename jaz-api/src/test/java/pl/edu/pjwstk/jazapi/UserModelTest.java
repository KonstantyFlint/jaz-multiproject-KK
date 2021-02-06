package pl.edu.pjwstk.jazapi;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pjwstk.jazapi.model.UserModel;
import pl.edu.pjwstk.jazapi.security.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
public class UserModelTest {
    @Test
    public void convertBackAndForth(){
        User user = new User();
        user.setAuthorities(Set.of(()->"fff",()->"fffg"));
        user.setUsername("usrnm");
        user.setPassword("pwwfd");

        UserModel model = new UserModel(user);
        User user2 = model.toRealUser();


        assert (user2.getUsername().equals("usrnm"));
        assert (user2.getPassword().equals("pwwfd"));
        assert (user2.getAuthorities().stream().map(Object::toString).collect(Collectors.toList()).containsAll(List.of("fff","fffg")));
    }
}
