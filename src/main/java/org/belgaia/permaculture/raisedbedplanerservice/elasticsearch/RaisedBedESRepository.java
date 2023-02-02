package org.belgaia.permaculture.raisedbedplanerservice.elasticsearch;

import org.belgaia.permaculture.raisedbedplanerservice.beans.RaisedBed;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaisedBedESRepository extends ElasticsearchRepository<RaisedBed, String> {
    RaisedBed findByName(String name);
}
