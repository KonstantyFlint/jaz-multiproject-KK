package pl.edu.pjwstk.jazapi;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pjwstk.jazapi.model.Cart;
import pl.edu.pjwstk.jazapi.model.Person;
import pl.edu.pjwstk.jazapi.model.Train;

import java.util.List;

@SpringBootTest
public class CartTests {
    @Mock
    Train train;
    @Mock
    Person person;
    @Mock
    Person person2;

    @Test
    public void noSpaceTest(){
        Cart cart = new Cart(0L,train,0);
       assert (cart.accept(person) == false);
    }
    @Test
    public void noSpaceTest2(){
        Cart cart = new Cart(0L,train,1);
        cart.accept(person);
        assert (cart.accept(person2) == false);
    }
    @Test
    public void hasSpaceTest(){
        Cart cart = new Cart(0L,train,1);
        assert (cart.accept(person));
    }
}