package org.works.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.works.Person;
import org.works.Pet;
import org.works.TypePet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PetExtractor implements ResultSetExtractor<List<Pet>>{
    @Override
    public List<Pet> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Pet> petList = new ArrayList<>();
        int currentIdRs = 0;
        int i = -1;
        while (rs.next()){
            Pet pet =
                    new Pet(rs.getInt("pet.id"), rs.getDouble("weight"),
                            rs.getString("color"), rs.getString("nickname"));
            Person person =
                    new Person(rs.getInt("person.id"), rs.getString("name"),
                            rs.getString("surname"), rs.getString("lastname"), rs.getString("data"));
            TypePet typePet = new TypePet();


//            person.setId(rs.getInt("person.id"));
//            person.setName(rs.getString("name"));
//            person.setSurname(rs.getString("surname"));
//            person.setLastname(rs.getString("lastname"));
//            person.setData(rs.getString("data"));

//            pet.setId(rs.getInt("pet.id"));
//            pet.setWeight(rs.getDouble("weight"));
//            pet.setColor(rs.getString("color"));
//            pet.setNickname(rs.getString("nickname"));

            typePet.setId(rs.getInt("tp.id"));
            typePet.setType(rs.getString("type_pet"));

            pet.setTypePet(typePet);

            if(currentIdRs == rs.getInt("id")){
                petList.get(i).addPersonInPet(person);
                continue;
            }
            if(person.getId() != 0){
                pet.addPersonInPet(person);
            }
            petList.add(pet);
            currentIdRs = rs.getInt("id");
            i++;
        }

        return petList;
    }
}
