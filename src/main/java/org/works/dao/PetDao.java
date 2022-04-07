package org.works.dao;

import org.works.Pet;

import java.util.List;

public interface PetDao extends Dao<Pet>{
    List<Pet> getTimeIntervalAddPet(int idPerson, String dateStart, String dataEnd);
}
