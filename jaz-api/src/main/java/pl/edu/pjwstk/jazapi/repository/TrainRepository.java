package pl.edu.pjwstk.jazapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jazapi.model.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {

}
