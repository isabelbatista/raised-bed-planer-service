package org.belgaia.permaculture.raisedbedplanerservice.jdbc;

import org.belgaia.permaculture.raisedbedplanerservice.AlreadyExistingElementException;
import org.belgaia.permaculture.raisedbedplanerservice.beans.Plant;
import org.belgaia.permaculture.raisedbedplanerservice.beans.Plant2Group;

import java.util.List;

public interface PlantToGroupRelRepository {
    List<Plant2Group> getAllPlantToGroupRelations();
    List<Plant2Group> getPlantsByGroup(Integer plantGroupId);
    Boolean createPlantToGroupRelation(Plant2Group plant2Group) throws AlreadyExistingElementException;
}
