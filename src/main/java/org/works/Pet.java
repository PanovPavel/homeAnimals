package org.works;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Pet {
    private int id;
    private TypePet typePet;
    private double weight;
    private String color;
    private String nickname;



}
