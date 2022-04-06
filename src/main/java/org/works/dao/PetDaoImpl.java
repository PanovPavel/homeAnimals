package org.works.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.works.Pet;
import org.works.mapper.PetMapper;
import org.works.mapper.TypePetMapper;

import java.util.List;
@Repository
public class PetDaoImpl implements Dao<Pet>{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PetMapper petMapper;
    @Override
    public List<Pet> getAll() {
        return jdbcTemplate.query("Select * From pet", petMapper);
    }

    @Override
    public Pet get(int id) {
        return jdbcTemplate.queryForObject("Select * From pet Where id = ?", petMapper, new Object[]{id});
    }

    @Override
    public Pet saveOrUpdate(Pet pet) {
        if (pet.getId() == 0){
            jdbcTemplate.update("insert pet(type_pet_id, weight, color, nickname) values (?, ?, ?, ?)",
                    pet.getTypePet().getId(), pet.getWeight(), pet.getColor(), pet.getNickname());
        }
        else{
            jdbcTemplate.update("update pet set type_pet_id = ?, weight = ?, color = ?, nickname = ? WHERE id = ?",
                    pet.getTypePet().getId(), pet.getWeight(), pet.getColor(), pet.getNickname(), pet.getId());
        }
        return pet;
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from pet where id = ?", id);
    }
}
