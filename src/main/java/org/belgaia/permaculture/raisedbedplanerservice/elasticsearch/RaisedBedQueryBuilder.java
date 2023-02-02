package org.belgaia.permaculture.raisedbedplanerservice.elasticsearch;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component
public class RaisedBedQueryBuilder {

    /**
     * Creates a ElasticSearch query for integer values.
     * @param propertyValues    Map of integer property values to filter by.
     * @param nestedPath
     * @return NestedQueryBuilder object for the given values.
     */
    public NestedQueryBuilder nestedBoolQueryForIntegerValues(final Map<String, Integer> propertyValues, final String nestedPath) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Iterator<String> iterator = propertyValues.keySet().iterator();

        while (iterator.hasNext()) {
            String propertyName = iterator.next();
            Integer propertValue = propertyValues.get(propertyName);
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery(propertyName, propertValue);
            boolQueryBuilder.must(matchQuery);
        }
        return QueryBuilders.nestedQuery(nestedPath, boolQueryBuilder, ScoreMode.None);
    }

    /**
     * Creates a ElasticSearch query for String values.
     * @param propertyValues    Map of String property values to filter by.
     * @param nestedPath
     * @return NestedQueryBuilder object for the given values.
     */
    public NestedQueryBuilder nestedBoolQueryForStringValues(final Map<String, String> propertyValues, final String nestedPath) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Iterator<String> iterator = propertyValues.keySet().iterator();

        while (iterator.hasNext()) {
            String propertyName = iterator.next();
            String propertValue = propertyValues.get(propertyName);
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery(propertyName, propertValue);
            boolQueryBuilder.must(matchQuery);
        }
        return QueryBuilders.nestedQuery(nestedPath, boolQueryBuilder, ScoreMode.None);
    }
}
