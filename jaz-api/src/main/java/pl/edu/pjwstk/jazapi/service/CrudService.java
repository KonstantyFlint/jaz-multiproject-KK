package pl.edu.pjwstk.jazapi.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class CrudService<T extends DbEntity> {
    JpaRepository<T, Long> repository;

    public CrudService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public Stream<T> getAll(){
        return repository.findAll().stream();
    }

    public Stream<T> getAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest).get();
    }

    public T getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        Optional<T> item = repository.findById(id);

        if (item.isPresent()) {
            repository.delete(item.orElseThrow());
        }
    }

    public Long count(){return repository.count();}

    public List<T> createOrUpdate(Collection<T> updateEntities){
        updateEntities.forEach(this::createOrUpdate);
        return new ArrayList<>(updateEntities);
    }

    public abstract T createOrUpdate(T updateEntity);
}
