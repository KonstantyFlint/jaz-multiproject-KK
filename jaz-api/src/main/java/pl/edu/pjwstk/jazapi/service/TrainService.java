package pl.edu.pjwstk.jazapi.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jazapi.model.Train;
import pl.edu.pjwstk.jazapi.repository.CartRepository;
import pl.edu.pjwstk.jazapi.repository.TrainRepository;

@Service
public class TrainService extends CrudService<Train> {
    private final CartRepository cartRepository;

    public TrainService(TrainRepository trainRepository, CartRepository cartRepository) {
        super(trainRepository);
        this.cartRepository = cartRepository;
    }

    @Override
    public Train createOrUpdate(Train updateEntity) {
        repository.save(updateEntity);
        cartRepository.saveAll(updateEntity.getCarts());
        return repository.findById(updateEntity.getId()).get();
    }
}