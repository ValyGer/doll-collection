package ru.collection.doll_collection.mapping_service;

import org.springframework.stereotype.Service;
import ru.collection.doll_collection.dto.Doll;
import ru.collection.doll_collection.dto.DollDto;

@Service
public class DollMappingImpl implements DollMapping {
    @Override
    public DollDto dollToDollDto(Doll doll) {
        return new DollDto(
                doll.getId(),
                doll.getYear(),
                doll.getBrand(),
                doll.getRuler(),
                doll.getSeries(),
                doll.getNamePerson(),
                doll.getDescription(),
                doll.getMyImage(),
                doll.getPromImage()
        );
    }
}
