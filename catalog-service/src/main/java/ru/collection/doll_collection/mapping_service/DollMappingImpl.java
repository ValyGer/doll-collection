package ru.collection.doll_collection.mapping_service;

import org.springframework.stereotype.Service;
import ru.collection.doll_collection.dto.doll.DollDto;
import ru.collection.doll_collection.dto.doll.DollNewDto;
import ru.collection.doll_collection.entity.Doll;

@Service
public class DollMappingImpl implements DollMapping {

    @Override
    public DollDto doolToDollDto(Doll doll) {
        DollDto dollDto = new DollDto();
        dollDto.setId(doll.getId());
        dollDto.setYear(doll.getYear());
        dollDto.setBrand(doll.getBrand());
        dollDto.setRuler(doll.getRuler());
        dollDto.setSeries(doll.getSeries());
        dollDto.setNamePerson(doll.getNamePerson());
        dollDto.setDescription(doll.getDescription());
        dollDto.setPrice(doll.getPrice());

        if (doll.getPromImage().isBlank()) {
            dollDto.setPromImage(null);
        } else {
            dollDto.setPromImage(doll.getPromImage());
        }

        if (doll.getMyImage().isBlank()) {
            dollDto.setMyImage(null);
        } else {
            dollDto.setMyImage(doll.getMyImage());
        }
        return dollDto;
    }

    @Override
    public Doll doolNewDtoToDoll(DollNewDto dollNewDto) {
        Doll doll = new Doll();
        doll.setYear(dollNewDto.getYear());
        doll.setBrand(dollNewDto.getBrand());
        doll.setRuler(dollNewDto.getRuler());
        doll.setSeries(dollNewDto.getSeries());
        doll.setNamePerson(dollNewDto.getNamePerson());
        doll.setDescription(dollNewDto.getDescription());
        doll.setPrice(dollNewDto.getPrice());

        doll.setMyImage(dollNewDto.getNameMyImage());
        doll.setPromImage(dollNewDto.getNamePromImage());

        return doll;
    }
}
