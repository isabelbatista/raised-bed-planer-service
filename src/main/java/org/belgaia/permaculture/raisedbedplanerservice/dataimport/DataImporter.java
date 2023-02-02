package org.belgaia.permaculture.raisedbedplanerservice.dataimport;

import org.belgaia.permaculture.raisedbedplanerservice.AlreadyExistingElementException;
import org.belgaia.permaculture.raisedbedplanerservice.beans.Plant;
import org.belgaia.permaculture.raisedbedplanerservice.beans.Plant2Group;
import org.belgaia.permaculture.raisedbedplanerservice.beans.PlantGroup;
import org.belgaia.permaculture.raisedbedplanerservice.beans.PlantNeighbour;
import org.belgaia.permaculture.raisedbedplanerservice.jdbc.PlantGroupRepository;
import org.belgaia.permaculture.raisedbedplanerservice.jdbc.PlantNeighbourRepository;
import org.belgaia.permaculture.raisedbedplanerservice.jdbc.PlantRepository;
import org.belgaia.permaculture.raisedbedplanerservice.jdbc.PlantToGroupRelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataImporter {

    private final Logger logger = LoggerFactory.getLogger(DataImporter.class);

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    PlantGroupRepository plantGroupRepository;

    @Autowired
    PlantToGroupRelRepository plantToGroupRelationRepository;

    @Autowired
    PlantNeighbourRepository plantNeighbourRepository;

    @Autowired
    PlantGroupDataCsvReader plantGroupDataCsvReader;

    @Autowired
    PlantToGroupRelationsDataCsvReader plantToGroupRelationsDataCsvReader;

    @Autowired
    PlantNeighbourDataCSVReader plantNeighbourDataCSVReader;

    @Autowired
    PlantDataCSVReader plantDataCsvReader;

    public void importPlants() {
        List<CSVPlantDataSchema> plantList = plantDataCsvReader.readPlantsFromCsv();

        for(CSVPlantDataSchema plantFromCsv : plantList) {
            Plant plant = new Plant();
            plant.setId(Integer.valueOf(plantFromCsv.getID()));
            plant.setName(plantFromCsv.getPlantName());
            try {
                plantRepository.createPlant(plant);
            } catch (AlreadyExistingElementException e) {
                logger.info("Plant " + plant.getName() + " already exists.");
            }
        }
    }

    public void importPlantGroups() {
        List<CsvPlantGroupDataSchema> groupsList = plantGroupDataCsvReader.readPlantGroupsFromCsv();

        for(CsvPlantGroupDataSchema groupFromCsv : groupsList) {
            PlantGroup plantGroup = new PlantGroup();
            plantGroup.setId(Integer.valueOf(groupFromCsv.getID()));
            plantGroup.setName(groupFromCsv.getGroupName());
            try {
                plantGroupRepository.createPlantGroup(plantGroup);
            } catch (AlreadyExistingElementException e) {
                logger.info("Plant group " + plantGroup.getName() + " already exists.");
            }
        }
    }

    public void importPlantToGroupRelations() {
        List<CsvPlantToGroupRelDataSchema> plantToGroupRelationList = plantToGroupRelationsDataCsvReader.readPlantToGroupRelationsFromCsv();

        for(CsvPlantToGroupRelDataSchema plantToGroupRelFromCsv : plantToGroupRelationList) {
            Plant2Group plant2Group = new Plant2Group();
            plant2Group.setPlantGroupId(Integer.valueOf(plantToGroupRelFromCsv.getPlantGroupId()));
            plant2Group.setPlantGroupName(plantToGroupRelFromCsv.getPlantGroupName());
            plant2Group.setPlantId(Integer.valueOf(plantToGroupRelFromCsv.getPlantId()));
            plant2Group.setPlantName(plantToGroupRelFromCsv.getPlantName());
            try {
                plantToGroupRelationRepository.createPlantToGroupRelation(plant2Group);
            } catch (AlreadyExistingElementException e) {
                logger.info("Relation of plant " + plant2Group.getPlantName() + " to group " + plant2Group.getPlantGroupName() + " already exists.");
            }
        }
    }

    public void importPlantNeighbours() {
        List<CsvPlantNeighbourDataSchema> plantNeighbourList = plantNeighbourDataCSVReader.readPlantNeighboursFromCsv();

        for(CsvPlantNeighbourDataSchema plantNeighboursFromCsv : plantNeighbourList) {
            PlantNeighbour plantNeighbour = new PlantNeighbour();
            plantNeighbour.setPlantOneId(Integer.valueOf(plantNeighboursFromCsv.getPlantOneId()));
            plantNeighbour.setPlantOneName(plantNeighboursFromCsv.getPlantOneName());

            if(!plantNeighboursFromCsv.getPlantTwoId().isEmpty()) {
                plantNeighbour.setPlantTwoId(Integer.valueOf(plantNeighboursFromCsv.getPlantTwoId()));
            }
            plantNeighbour.setPlantTwoName(plantNeighboursFromCsv.getPlantTwoName());

            if (!plantNeighboursFromCsv.getGroupId().isEmpty()) {
                plantNeighbour.setGroupId(Integer.valueOf(plantNeighboursFromCsv.getGroupId()));
            }

            plantNeighbour.setGroupName(plantNeighboursFromCsv.getGroupName());

            plantNeighbour.setGoodNeighbour(Boolean.valueOf(plantNeighboursFromCsv.getGoodNeighbour()));
            plantNeighbour.setBadNeighbour(Boolean.valueOf(plantNeighboursFromCsv.getBadNeighbour()));

            plantNeighbour.setComment(plantNeighboursFromCsv.getComment());

            try {
                plantNeighbourRepository.createPlantNeighbour(plantNeighbour);
            } catch (AlreadyExistingElementException e) {
                logger.info("Neighbours of plant " + plantNeighbour.getPlantOneName() + " already exists.");
            }
        }
    }
}
