package pl.edu.pjwstk.jazapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jazapi.security.User;
import pl.edu.pjwstk.jazapi.security.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){
        if(user==null)return ResponseEntity.noContent().build();
        if(user.getAuthorities()==null) user.setAuthorities(List.of());
        return service.register(user) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/remove")
    public ResponseEntity remove(String username){
        return service.remove(username) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/modify")
    public ResponseEntity modify(@RequestBody User user){
        if(user==null)return ResponseEntity.noContent().build();
        return service.update(user) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
