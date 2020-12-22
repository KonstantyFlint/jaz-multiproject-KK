package pl.edu.pjwstk.jazapi.model;

public class Person {
    private String name;
    private String surname;
    private Long targetStation;

    public boolean wantsOut(Long currentStation){
        return targetStation.equals(currentStation);
    }

    public Person(String name, String surname, Long targetStation) {
        this.name = name;
        this.surname = surname;
        this.targetStation = targetStation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getTargetStation() {
        return targetStation;
    }

    public void setTargetStation(Long targetStation) {
        this.targetStation = targetStation;
    }

}
