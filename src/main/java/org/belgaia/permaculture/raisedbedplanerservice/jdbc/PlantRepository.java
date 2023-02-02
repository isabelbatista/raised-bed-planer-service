package org.belgaia.permaculture.raisedbedplanerservice.jdbc;

import org.belgaia.permaculture.raisedbedplanerservice.AlreadyExistingElementException;
import org.belgaia.permaculture.raisedbedplanerservice.beans.Plant;

import java.util.List;

public interface PlantRepository {
    List<Plant> getAllPlants();
    Plant getPlantByName(String name);
    Plant createPlant(Plant plant) throws AlreadyExistingElementException;
}
