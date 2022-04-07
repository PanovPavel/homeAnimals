package org.works.service;

import org.works.Person;
import org.works.exceptions.ConstraintUniquenessQtyTypePetException;

public interface PersonService extends Service<Person>{
    void bindPetInPerson(int idPerson, int idPet) throws ConstraintUniquenessQtyTypePetException;
}
