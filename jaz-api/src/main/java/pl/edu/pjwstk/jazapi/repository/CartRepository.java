package pl.edu.pjwstk.jazapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jazapi.model.Cart;

import java.util.Collection;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
