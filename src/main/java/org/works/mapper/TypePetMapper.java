package org.works.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.works.TypePet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypePetMapper implements RowMapper<TypePet> {
    @Override
    public TypePet mapRow(ResultSet rs, int rowNum) throws SQLException {
        TypePet typePet = new TypePet();
        typePet.setId(rs.getInt("id"));
        typePet.setType(rs.getString("type_pet"));
        return typePet;
    }
}
