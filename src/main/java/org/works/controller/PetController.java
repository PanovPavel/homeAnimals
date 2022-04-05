package org.works.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.works.Pet;
import org.works.TypePet;

@RestController
public class PetController {

    @GetMapping("api/pet")
    public Pet getPet(){
        Pet pet = new Pet();
        pet.setId(1);
        pet.setColor("Синий");
        pet.setNickname("Собака");
        pet.setWeight(25.1);

        TypePet typePet = new TypePet();
        typePet.setType("Собака");
        TypePet typePet2 = new TypePet();
        typePet2.setType("Собака");
        typePet2.setId(1);

        pet.addTypePet(typePet);
        pet.addTypePet(typePet2);

        return pet;
    }
}
