package pl.edu.pjwstk.jazapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jazapi.model.UserModel;
import pl.edu.pjwstk.jazapi.repository.UserRepository;

import java.net.URL;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository) throws Exception {
        this.users = repository;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        userSetup();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = users.findByUsername(username);
        if(user.size()==0)return null;
        return user.get(0).toRealUser();
    }

    public boolean register(User user){
        if(users.findByUsername(user.getUsername()).size()>0)return false;
        UserModel dbModel = new UserModel(user);
        encodeAndInsert(dbModel);
        System.out.println("registered:    "+user.getUsername());
        return true;
    }

    public boolean update(User user){
        var replacedUser = users.findByUsername(user.getUsername());
        if(replacedUser.size()==0)return false;
        UserModel dbModel = new UserModel(user);
        dbModel.setId(replacedUser.get(0).getId());
        encodeAndInsert(dbModel);
        System.out.println("updated:    "+user.getUsername());
        return true;
    }

    public boolean remove(String username){
        var user = users.findByUsername(username);
        if(user.size()==0)return false;
        users.delete(user.get(0));
        System.out.println("removed:    "+username);
        return true;
    }



    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    private void encodeAndInsert(UserModel user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.save(user);
    }

    private void userSetup() throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            URL usersPath = getClass().getClassLoader().getResource("users.json");
            UserModel[] users = objectMapper.readValue(usersPath, UserModel[].class);
            for(UserModel user : users) encodeAndInsert(user);

        }catch (Exception e){
            throw new Exception("FAILED TO INITIALISE USERS, you've fucked up users.json, good luck inserting by hand");
        }
    }
}
