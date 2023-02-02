package org.belgaia.permaculture.raisedbedplanerservice.jdbc;

import org.belgaia.permaculture.raisedbedplanerservice.AlreadyExistingElementException;
import org.belgaia.permaculture.raisedbedplanerservice.beans.PlantGroup;

import java.util.List;

public interface PlantGroupRepository {
    List<PlantGroup> getAllPlantGroups();
    PlantGroup getPlantGroupByName(String name);
    PlantGroup createPlantGroup(PlantGroup plant) throws AlreadyExistingElementException;
}
