package pl.edu.pjwstk.jazapi.model;

import pl.edu.pjwstk.jazapi.service.DbEntity;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.edu.pjwstk.jazapi.util.Utils.randomMember;


@Entity
@Table(name = "trains")
public class Train implements DbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long station;
    private static Long lastStation = 15L;
    @Transient
    private PersonFactory personFactory = new PersonFactory(this);

    private int cooldown = 0;

    public void noCooldown(){
        cooldown=0;
    }

    private boolean forward = true;

    public boolean getForward() {
        return forward;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }

    public boolean endOfTheLine() {
        return station.equals(0L) || station.equals(lastStation);
    }

    public Long getLastStation() {
        return lastStation;
    }

    public void move() {
        letOut();
        if (cooldown > 0) {
            cooldown--;
            return;
        }
        letIn(personFactory.limitedStream(2, 8));
        if (forward) station++;
        else station--;
        if (endOfTheLine()) {
            forward = !forward;
            cooldown = 3;
        }
    }

    public void letOut() {
        carts.forEach(Cart::letOut);
    }

    public void letIn(Stream<Person> people) {
        people.forEach(this::letIn);
    }

    public boolean letIn(Person person){
        var free = carts.stream().filter(c->!c.isFull()).collect(Collectors.toList());
        if(free.size()==0)return false;
        return randomMember(free).accept(person);
    }

    @OneToMany()
    @JoinColumn(name = "train_id")
    @OrderBy("ordinal")
    private List<Cart> carts;

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public Long getStation() {
        return station;
    }

    public void setStation(Long station) {
        this.station = station;
    }

    public Train() {
    }

    public Train(Long station) {
        this.station = station;
    }

    public Long getId() {
        return id;
    }
}
