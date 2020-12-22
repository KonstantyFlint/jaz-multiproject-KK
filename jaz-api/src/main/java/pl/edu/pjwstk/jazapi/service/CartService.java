package pl.edu.pjwstk.jazapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jazapi.model.Cart;
import pl.edu.pjwstk.jazapi.model.Person;
import pl.edu.pjwstk.jazapi.repository.CartRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CartService extends CrudService<Cart> {
    public CartService(CartRepository repository) {
        super(repository);
    }

    private static Map<Long, Set<Person>> passengersMap = new HashMap<>();

    @Override
    public Cart createOrUpdate(Cart updateEntity) {
        passengersMap.put(updateEntity.getId(), updateEntity.getPeople());
        return repository.save(updateEntity);
    }

    @Override
    public Stream<Cart> getAll() {
        var carts = super.getAll().collect(Collectors.toList());
        carts.forEach(c->c.setPeople(passengersMap.get(c.getId())));
        carts.forEach(
                c->c.getPeople().forEach(System.out::println)
        );
        return carts.stream();
    }

    @Override
    public Stream<Cart> getAll(PageRequest request){
        var carts = super.getAll(request).collect(Collectors.toList());
        carts.forEach(c->c.setPeople(passengersMap.get(c.getId())));
        carts.forEach(
                c->c.getPeople().forEach(System.out::println)
        );
        return carts.stream();
    }

    @Override
    public Cart getById(Long id){
        var cart = super.getById(id);
        cart.setPeople(passengersMap.get(cart.getId()));
        cart.getPeople().forEach(System.out::println);
        return cart;
    }
}