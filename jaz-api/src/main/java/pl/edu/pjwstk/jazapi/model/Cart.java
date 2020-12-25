package pl.edu.pjwstk.jazapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.edu.pjwstk.jazapi.service.DbEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "carts")
public class Cart implements DbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id")
    private Train train;

    private int capacity;

    @Transient
    private Set<Person> people = new HashSet<>();

    @Transient
    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = new HashSet<>(people);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Cart() {
    }

    public Cart(Long id, Train train, int capacity) {
        this.id = id;
        this.train = train;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Train getTrain() {
        return train;
    }

    public Long getTrainId(){return train.getId();}

    public void setTrain(Train train) {
        this.train = train;
    }

    public boolean accept(Person person){
        if(isFull())return false;
        people.forEach(p-> System.out.println(p.getName()+" "+p.getSurname()));
        return people.add(person);
    }

    boolean isFull(){
        return people.size()>=capacity;
    }

    void letOut(){
        var wantOut = people.stream()
                .filter(p->p.wantsOut(getTrain().getStation()))
                .collect(Collectors.toList());
        people.removeAll(wantOut);
    }
}
