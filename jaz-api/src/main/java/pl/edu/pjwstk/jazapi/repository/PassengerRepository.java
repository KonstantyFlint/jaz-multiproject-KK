package pl.edu.pjwstk.jazapi.repository;

import pl.edu.pjwstk.jazapi.model.Cart;
import pl.edu.pjwstk.jazapi.model.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PassengerRepository {
    private static Map<Long, Set<Person>> passengersMap = new HashMap<>();

    public Cart load(Cart cart){
        Long id = cart.getId();
        Set<Person> people = passengersMap
                .containsKey(id) ? passengersMap.get(id) : Set.of();
        cart.setPeople(people);
        return cart;
    }

    public Cart save(Cart cart){
        passengersMap.put(cart.getId(),cart.getPeople());
        return cart;
    }

    public List<Cart> saveAll(List<Cart> carts){
        carts.forEach(this::save);
        return carts;
    }

    public void delete(Long id){
        passengersMap.remove(id);
    }
}
