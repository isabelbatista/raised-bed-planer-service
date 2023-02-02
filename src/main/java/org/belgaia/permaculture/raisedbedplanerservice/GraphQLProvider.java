package org.belgaia.permaculture.raisedbedplanerservice;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {

    private final Logger logger = LoggerFactory.getLogger(GraphQLProvider.class);

    private GraphQL graphQL;

    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        logger.info("GraphQLProvider:init: registering schema");
        URL url = Resources.getResource("raised-bed-planer.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        //.dataFetcher("raisedBeds", graphQLDataFetchers.getAllRaisedBedsFetcher())
                        //.dataFetcher("raisedBedById", graphQLDataFetchers.getRaisedBedByIdFetcher())
                        //.dataFetcher("raisedBedsWithPlantingsByYear", graphQLDataFetchers.getRaisedBedsWithPlantingsByYearFetcher())
                        //.dataFetcher("plantingByUUID", graphQLDataFetchers.getPlantingByUUIDFetcher())
                        .dataFetcher("importDataFromCsv", graphQLDataFetchers.importDataFromCsv())
                        .dataFetcher("neighbourPlants", graphQLDataFetchers.getNeighbourPlants())
                        .dataFetcher("plantsByGroup", graphQLDataFetchers.getPlantsByGroup()))
               /* .type(newTypeWiring("Mutation")
                        .dataFetcher("createRaisedBed", graphQLDataFetchers.createRaisedBedFetcher())
                        .dataFetcher("createPlantingDesign", graphQLDataFetchers.createPlantingDesignFetcher())
                        .dataFetcher("updatePlantingStatus", graphQLDataFetchers.updatePlantingStatus()))
                */
                .build();
    }

}
