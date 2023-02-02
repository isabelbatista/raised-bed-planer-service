package org.belgaia.permaculture.raisedbedplanerservice.jdbc;

import org.belgaia.permaculture.raisedbedplanerservice.AlreadyExistingElementException;
import org.belgaia.permaculture.raisedbedplanerservice.beans.Plant;
import org.belgaia.permaculture.raisedbedplanerservice.beans.Plant2Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PlantToGroupRelationJdbcRepository implements PlantToGroupRelRepository {

    private final Logger logger = LoggerFactory.getLogger(PlantToGroupRelationJdbcRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Plant2Group> getAllPlantToGroupRelations() {
        String sql = "SELECT * FROM plant_to_group_rel";

        try {
            return jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(Plant2Group.class)
            );
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Plant2Group> getPlantsByGroup(Integer plantGroupId) {

        String sql = "SELECT * FROM plant_to_group_rel WHERE plant_group_id=?";

        try {
            return jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(Plant2Group.class),
                    plantGroupId
            );
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Plant2Group getPlantToGroupRelationByCombination(Integer plantGroupId, Integer plantId) {
        String sql = "SELECT * FROM plant_to_group_rel WHERE plant_group_id=? AND plant_id=?";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(Plant2Group.class),
                    plantGroupId,
                    plantId
            );
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Boolean createPlantToGroupRelation(Plant2Group plantGroupRelation) throws AlreadyExistingElementException {

        Plant2Group existingRelation = getPlantToGroupRelationByCombination(plantGroupRelation.getPlantGroupId(), plantGroupRelation.getPlantId());
        if(existingRelation!=null) {
            throw new AlreadyExistingElementException("Plant relation of group " + plantGroupRelation.getPlantGroupName() + " already exists.");
        }

        String sql = "INSERT INTO plant_to_group_rel (plant_group_id, plant_group_name, plant_id, plant_name) VALUES (?,?,?,?)";

        jdbcTemplate.update(sql,
                plantGroupRelation.getPlantGroupId(),
                plantGroupRelation.getPlantGroupName(),
                plantGroupRelation.getPlantId(),
                plantGroupRelation.getPlantName()
        );
        return true;
    }
}
