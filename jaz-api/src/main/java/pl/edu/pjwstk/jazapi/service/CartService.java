package pl.edu.pjwstk.jazapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jazapi.model.Cart;
import pl.edu.pjwstk.jazapi.repository.CartRepository;
import pl.edu.pjwstk.jazapi.repository.PassengerRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CartService extends CrudService<Cart> {
    PassengerRepository passengerRepository;
    public CartService(CartRepository repository) {
        super(repository);
        passengerRepository = new PassengerRepository();
    }

    @Override
    public Cart createOrUpdate(Cart updateEntity) {
        passengerRepository.save(updateEntity);
        return repository.save(updateEntity);
    }

    @Override
    public List<Cart> createOrUpdate(Collection<Cart> updateEntities){
        updateEntities.forEach(this::createOrUpdate);
        return new ArrayList<>(updateEntities);
    }

    @Override
    public Stream<Cart> getAll() {
        return super.getAll().map(passengerRepository::load);
    }

    @Override
    public Stream<Cart> getAll(PageRequest pageRequest) {
        return super.getAll(pageRequest).map(passengerRepository::load);
    }

    @Override
    public Cart getById(Long id) {
        return passengerRepository.load(super.getById(id));
    }
}