package org.works.dao;

import org.works.Person;
import org.works.Pet;
import org.works.exceptions.ConstraintUniquenessQtyTypePetException;

import java.util.Date;
import java.util.List;

public interface PersonDao extends Dao<Person>{
    void bindPetInPerson(int idPerson, int idPet) throws ConstraintUniquenessQtyTypePetException;
}
