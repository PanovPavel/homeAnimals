package org.works;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Pet {
    int id;
    private List<TypePet> typePet;
    private double weight;
    private String color;
    private String nickname;
    public void addTypePet(TypePet tp){
        if(typePet == null){
            typePet = new ArrayList<>();
        }typePet.add(tp);
    }

}
