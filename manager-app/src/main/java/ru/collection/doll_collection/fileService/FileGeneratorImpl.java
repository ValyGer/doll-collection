package ru.collection.doll_collection.fileService;

import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.collection.doll_collection.entity.Doll;
import ru.collection.doll_collection.service.DollService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileGeneratorImpl implements FileGenerator {

    private final DollService dollService;

    @Override
    public void writeDataToFile() {
        File file = new File("./manager-app/src/main/resources/data/data_db.csv");

        try {
            FileWriter outputFile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputFile);

            List<Doll> dollList = dollService.findAllDataDolls();
            List<String[]> stringsDoll = new ArrayList<>();
            for (Doll doll : dollList) {
                String[] dataDoll = new String[]{
                        doll.getId().toString(),
                        doll.getYear().toString(),
                        doll.getBrand(),
                        doll.getBrand(),
                        doll.getRuler(),
                        doll.getSeries(),
                        doll.getNamePerson(),
                        doll.getDescription(),
                        doll.getPrice().toString(),
                        doll.getPromImage(),
                        doll.getMyImage()
                };
                stringsDoll.add(dataDoll);
            }
            writer.writeAll(stringsDoll);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
