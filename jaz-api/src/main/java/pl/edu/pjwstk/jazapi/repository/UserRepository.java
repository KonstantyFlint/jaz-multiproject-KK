package pl.edu.pjwstk.jazapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.jazapi.model.UserModel;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel,Long> {
    List<UserModel> findByUsername(String username);
}
