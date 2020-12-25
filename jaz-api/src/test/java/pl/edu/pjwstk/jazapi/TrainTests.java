package pl.edu.pjwstk.jazapi;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pjwstk.jazapi.model.Cart;
import pl.edu.pjwstk.jazapi.model.Train;

import java.util.List;

@SpringBootTest
public class TrainTests {

    @Mock
    List<Cart> carts;

    @Test
    public void goForwardsTest() {
        Train train = new Train(6L);
        train.setForward(true);
        train.setCarts(carts);
        train.move();
        assert (train.getStation() == 7L);
    }

    @Test
    public void goBackwardsTest() {
        Train train = new Train(6L);
        train.setForward(false);
        train.setCarts(carts);
        train.move();
        assert (train.getStation() == 5L);
    }

    @Test
    public void turnAroundTest() {
        Train train = new Train(14L);
        train.setCarts(carts);
        train.setForward(true);
        train.move();
        assert (train.getForward() == false);
    }

    @Test
    public void turnAroundTest2() {
        Train train = new Train(1L);
        train.setCarts(carts);
        train.setForward(false);
        train.move();
        assert (train.getForward() == true);
    }
}
