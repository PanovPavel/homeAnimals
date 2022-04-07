package org.works.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.works.Pet;
import org.works.exceptions.ObjectNotExitDatabaseForUpdateException;
import org.works.mapper.PetExtractor;

import java.util.List;
@Repository
public class PetDaoImpl implements PetDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PetExtractor petMapper;
    @Override
    public List<Pet> getAll() {
        String query =
                "Select pet.*, person.*, tp.*, person_pet.data\n" +
                "From pet\n" +
                "    LEFT  JOIN person_pet\n" +
                "        ON pet.id = person_pet.pet_id\n" +
                "    LEFT JOIN person\n" +
                "        ON person_pet.person_id = person.id\n" +
                "    LEFT JOIN type_pet tp\n" +
                "        on pet.type_pet_id = tp.id";
        return jdbcTemplate.query(query, petMapper);
    }

    @Override
    public Pet get(int id) {
        String query =
                "Select pet.*, person.*, tp.*, person_pet.data\n" +
                        "From pet\n" +
                        "    LEFT  JOIN person_pet\n" +
                        "        ON pet.id = person_pet.pet_id\n" +
                        "    LEFT JOIN person\n" +
                        "        ON person_pet.person_id = person.id\n" +
                        "    LEFT JOIN type_pet tp\n" +
                        "        on pet.type_pet_id = tp.id WHERE pet.id = ?;";
        return jdbcTemplate.query(query, petMapper, new Object[]{id}).stream().findAny().orElse(null);
    }

    @Override
    public void saveOrUpdate(Pet pet) {


        if (pet.getId() == 0){
            jdbcTemplate.update("insert pet(type_pet_id, weight, color, nickname) values (?, ?, ?, ?)",
                    pet.getTypePet().getId(), pet.getWeight(), pet.getColor(), pet.getNickname());
        }
        else{
            Pet pets = new Pet();
            int availability = jdbcTemplate.queryForObject("select count(*) from pet where id = ?", Integer.class, new Object[]{pets.getId()});
            if(availability == 0){
                throw new ObjectNotExitDatabaseForUpdateException("The object with the specified id is not in the database");
            }
            jdbcTemplate.update("update pet set type_pet_id = ?, weight = ?, color = ?, nickname = ? WHERE id = ?",
                    pet.getTypePet().getId(), pet.getWeight(), pet.getColor(), pet.getNickname(), pet.getId());
        }
    }

    @Override
    public void delete(int id) {
        Pet pets = new Pet();
        int availability = jdbcTemplate.queryForObject("select count(*) from pet where id = ?", Integer.class, new Object[]{pets.getId()});
        if(availability == 0){
            throw new ObjectNotExitDatabaseForUpdateException("The object with the specified id is not in the database");
        }
        jdbcTemplate.update("delete from pet where id = ?", id);
    }

    @Override
    public List<Pet> getTimeIntervalAddPet(int idPerson, String dateStart, String dataEnd) {
        String start = dateStart;
        String end = dataEnd;
        String query =
                "SELECT *\n" +
                        "From person_pet\n" +
                        "    Inner join pet\n" +
                        "        ON person_pet.pet_id = pet.id\n" +
                        "    INNER join type_pet tp\n" +
                        "        on pet.type_pet_id = tp.id\n" +
                        "    INNER join person\n" +
                        "        on person_pet.person_id = person.id\n" +
                        "WHERE data >= "+start+" AND data <= "+end+" AND person_pet.person_id = ?;";
        return jdbcTemplate.query(query, petMapper, new Object[]{idPerson});
    }
}
