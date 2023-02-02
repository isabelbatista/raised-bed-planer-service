package org.belgaia.permaculture.raisedbedplanerservice.elasticsearch;

import org.belgaia.permaculture.raisedbedplanerservice.beans.Planting;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantingESRepository extends ElasticsearchRepository<Planting, String> {
}
