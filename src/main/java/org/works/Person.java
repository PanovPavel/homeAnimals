package org.works;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person {
    private int id;
    private String name;
    private String surname;
    private String lastname;
    private List<Pet> listPet;
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
