package pl.edu.pjwstk.jazapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jazapi.model.Cart;
import pl.edu.pjwstk.jazapi.service.CartService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/carts")
public class CartController extends CrudController<Cart> {

    public CartController(CartService service) {
        super(service);
    }

    public Function<Cart, Map<String, Object>> transformToDTO() {
        return cart -> {
            var payload = new LinkedHashMap<String, Object>();
            payload.put("id", cart.getId());
            payload.put("capacity", cart.getCapacity());
            payload.put("train", cart.getTrain().getId());
            payload.put("people", cart.getPeople());
            return payload;
        };
    }
}