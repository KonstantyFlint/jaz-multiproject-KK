package pl.edu.pjwstk.jazapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jazapi.model.Train;
import pl.edu.pjwstk.jazapi.repository.CartRepository;
import pl.edu.pjwstk.jazapi.repository.PassengerRepository;
import pl.edu.pjwstk.jazapi.repository.TrainRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TrainService extends CrudService<Train> {
    private final CartRepository cartRepository;
    private final PassengerRepository passengerRepository;

    public TrainService(TrainRepository trainRepository, CartRepository cartRepository) {
        super(trainRepository);
        this.cartRepository = cartRepository;
        this.passengerRepository = new PassengerRepository();
    }

    @Override
    public Train createOrUpdate(Train updateEntity) {
        repository.save(updateEntity);
        cartRepository.saveAll(updateEntity.getCarts());
        passengerRepository.saveAll(updateEntity.getCarts());
        return repository.findById(updateEntity.getId()).get();
    }

    @Override
    public Stream<Train> getAll() {
        var trains = super.getAll().collect(Collectors.toList());
        trains.forEach(
                t->t.getCarts().forEach(passengerRepository::load)
        );
        return trains.stream();
    }

    @Override
    public Stream<Train> getAll(PageRequest pageRequest) {
        var trains = super.getAll(pageRequest).collect(Collectors.toList());
        trains.forEach(
                t->t.getCarts().forEach(passengerRepository::load)
        );
        return trains.stream();
    }

    @Override
    public Train getById(Long id) {
        var train = super.getById(id);
        train.getCarts().forEach(passengerRepository::load);
        return train;
    }

    @Override
    public List<Train> createOrUpdate(Collection<Train> updateEntities) {
        updateEntities.forEach(this::createOrUpdate);
        return new ArrayList<>(updateEntities);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
        passengerRepository.delete(id);
    }
}