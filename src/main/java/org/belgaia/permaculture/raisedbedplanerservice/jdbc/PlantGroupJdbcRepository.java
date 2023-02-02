package org.belgaia.permaculture.raisedbedplanerservice.jdbc;

import org.belgaia.permaculture.raisedbedplanerservice.AlreadyExistingElementException;
import org.belgaia.permaculture.raisedbedplanerservice.beans.Plant;
import org.belgaia.permaculture.raisedbedplanerservice.beans.PlantGroup;
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
public class PlantGroupJdbcRepository implements PlantGroupRepository {

    private final Logger logger = LoggerFactory.getLogger(PlantGroupJdbcRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<PlantGroup> getAllPlantGroups() {
        String sql = "SELECT * FROM plant_groups";

        try {
            return jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(PlantGroup.class)
            );
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public PlantGroup getPlantGroupByName(String name) {
        String sql = "SELECT * FROM plant_groups WHERE name=?";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(PlantGroup.class),
                    name
            );
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public PlantGroup createPlantGroup(PlantGroup plantGroup) throws AlreadyExistingElementException {

        PlantGroup existingPlantGroup = getPlantGroupByName(plantGroup.getName());
        if(existingPlantGroup!=null) {
            throw new AlreadyExistingElementException("Plant group with name " + plantGroup.getName() + " already exists.");
        }

        String sql = "INSERT INTO plant_groups (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int savedElement = jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, plantGroup.getName());
                    return ps;
                }, keyHolder);

        Integer plantGroupId = null;
        if(savedElement > 0 && keyHolder.getKey() != null) {
            plantGroupId = keyHolder.getKey().intValue();
            logger.info("Successfully saved and initialized plant group with ID {}", plantGroupId);
        }
        return getPlantGroupById(plantGroupId);
    }

    private PlantGroup getPlantGroupById(Integer plantGroupId) {
        String sql = "SELECT * FROM plant_groups WHERE id=?";

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(PlantGroup.class),
                    plantGroupId
            );
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
}
