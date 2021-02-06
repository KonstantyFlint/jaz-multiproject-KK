package pl.edu.pjwstk.jazapi.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.edu.pjwstk.jazapi.security.User;
import pl.edu.pjwstk.jazapi.service.DbEntity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class UserModel implements DbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String authorities;

    public UserModel() {
    }

    public UserModel(String username, String password, String authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public UserModel(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        if (user.getAuthorities().size() == 0) this.authorities = "";
        else {
            this.authorities = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User toRealUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (this.authorities.equals("")) user.setAuthorities(List.of());
        else {
            user.setAuthorities(
                    List.of(authorities.split(","))
                            .stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
            );
        }
        return user;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString(){
        return String.format("username: %s\npassword: %s\nauth: %s",getUsername(),getPassword(),getAuthorities());
    }
}