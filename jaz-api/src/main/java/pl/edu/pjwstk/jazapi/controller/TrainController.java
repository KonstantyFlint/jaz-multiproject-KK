package pl.edu.pjwstk.jazapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jazapi.model.Train;
import pl.edu.pjwstk.jazapi.service.TrainService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trains")
public class TrainController extends CrudController<Train> {

    public TrainController(TrainService service) {
        super(service);
    }

    @GetMapping("/move")
    public void move(){
        var trains = service.getAll().collect(Collectors.toList());
        trains.forEach(Train::move);
        service.createOrUpdate(trains);
    }

    public Function<Train, Map<String, Object>> transformToDTO() {
        return train -> {
            var payload = new LinkedHashMap<String, Object>();
            payload.put("id", train.getId());
            payload.put("station", train.getStation());
            payload.put("carts",train.getCarts());
            return payload;
        };
    }
}
