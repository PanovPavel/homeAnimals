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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class PersonExtractor implements ResultSetExtractor<List<Person>>{
    @Override
    public List<Person> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Person> list = new ArrayList<>();
        int currentId = 0;
        int i = -1;
        while (rs.next()){
            Person person =
                    new Person(rs.getInt("id"), rs.getString("name"),
                    rs.getString("surname"), rs.getString("lastname"));
            Pet pet =
                    new Pet(rs.getInt("pet.id"), rs.getString("data"),
                    rs.getDouble("weight"), rs.getString("color"),
                    rs.getString("nickname"));
            TypePet typePet = new TypePet();

//            person.setId(rs.getInt("id"));
//            person.setName(rs.getString("name"));
//            person.setSurname(rs.getString("surname"));
//            person.setLastname(rs.getString("lastname"));

//            pet.setData(rs.getString("data"));
//            pet.setId(rs.getInt("pet.id"));
//            pet.setWeight(rs.getDouble("weight"));
//            pet.setColor(rs.getString("color"));
//            pet.setNickname(rs.getString("nickname"));

            typePet.setId(rs.getInt("type_pet.id"));
            typePet.setType(rs.getString("type_pet.type_pet"));
            pet.setTypePet(typePet);

            if(currentId == rs.getInt("id")){
                list.get(i).addPetInPerson(pet);
                continue;
            }
            if(pet.getId() != 0) {
                person.addPetInPerson(pet);
            }
            list.add(person);
            currentId = rs.getInt("id");
            i++;
        }
        return list;
    }
}
