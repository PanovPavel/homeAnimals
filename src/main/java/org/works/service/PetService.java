package org.works.service;

import org.works.Pet;

import java.util.List;

public interface PetService extends Service<Pet> {
    List<Pet> getTimeIntervalAddPet(int idPerson, String dateStart, String dataEnd);
}