package org.works;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Person {
    private int id;
    private String data;
    private String name;
    private String surname;
    private String lastname;
    private List<Pet> listPet;
    @JsonIgnore
    private final int maxCountTypePet = 3;

    public Person(int id, String name, String surname, String lastname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
    }

    public Person(int id, String data, String name, String surname, String lastname) {
        this.id = id;
        this.data = data;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
    }

    public void addPetInPerson(Pet pet){
        if(listPet == null){
            listPet = new ArrayList<>();
        }
        listPet.add(pet);
    }
    public List<Pet> getListPet() {
        return listPet;
    }
    public Person() {
    }
}
