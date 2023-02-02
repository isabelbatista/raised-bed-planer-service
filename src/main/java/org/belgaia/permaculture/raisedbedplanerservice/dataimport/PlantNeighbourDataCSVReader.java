package org.belgaia.permaculture.raisedbedplanerservice.dataimport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlantNeighbourDataCSVReader {

    private final Logger logger = LoggerFactory.getLogger(PlantNeighbourDataCSVReader.class);

    // TODO: remove this and get the file name and path from the request parameter list
    private static final String FILE_NAME = "src/main/resources/datasheets/plantNeighbours.csv";

    // TODO: get the name and path of the CSV file to read
    // TODO: read the CSV line by line
    // TODO: convert lines to objects and lists
    // TODO: return objects and lists to the caller

    public List<CsvPlantNeighbourDataSchema> readPlantNeighboursFromCsv() {

        List<CsvPlantNeighbourDataSchema> plantNeighboursList = new ArrayList<>();

        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(FILE_NAME));

            // create csv bean reader
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CsvPlantNeighbourDataSchema.class)
                    .withSeparator(';')
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();

            // iterate through data sheet
            for (CsvPlantNeighbourDataSchema plantNeighbour : (Iterable<CsvPlantNeighbourDataSchema>) csvToBean) {
                if(!plantNeighbour.getPlantOneId().isEmpty() && (!plantNeighbour.getPlantTwoId().isEmpty() || !plantNeighbour.getGroupId().isEmpty())) {
                    logger.info("Found plant neighbours " + plantNeighbour.getPlantOneName() + " and " + plantNeighbour.getPlantTwoName());
                    plantNeighboursList.add(plantNeighbour);
                }
            }

            // close the reader
            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return plantNeighboursList;
    }
}
