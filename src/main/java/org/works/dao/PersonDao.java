package org.works.dao;

import org.works.Person;
import org.works.exceptions.ConstraintUniquenessQtyTypePetException;

import java.util.Date;

public interface PersonDao extends Dao<Person>{
    void bindPetInPerson(int idPerson, int idPet) throws ConstraintUniquenessQtyTypePetException;
    void getTimeIntervalAddPet(Date dateStart, Date dataEnd);
}
