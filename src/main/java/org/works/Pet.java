package org.works;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Pet {
    private int id;
    private String data;
    private TypePet typePet;
    private double weight;
    private String color;
    private String nickname;
    public Pet() {

    }

    public Pet(int id, double weight, String color, String nickname) {
        this.id = id;
        this.weight = weight;
        this.color = color;
        this.nickname = nickname;
    }

    public Pet(int id, String data, double weight, String color, String nickname) {
        this.id = id;
        this.data = data;
        this.weight = weight;
        this.color = color;
        this.nickname = nickname;
    }

    private List<Person> personList;
    public void addPersonInPet(Person person){
        if(personList == null){
            personList = new ArrayList<>();
        }
        personList.add(person);
    }


}
