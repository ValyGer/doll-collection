package ru.collection.doll_collection.mapping_service;

import org.springframework.stereotype.Component;
import ru.collection.doll_collection.dto.DollDto;
import ru.collection.doll_collection.entity.Doll;

@Component
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
        return dollDto;
    }

    @Override
    public Doll doolDtoToDoll(DollDto dollDto) {
        Doll doll = new Doll();
        doll.setId(dollDto.getId());
        doll.setYear(dollDto.getYear());
        doll.setBrand(dollDto.getBrand());
        doll.setRuler(dollDto.getRuler());
        doll.setSeries(dollDto.getSeries());
        doll.setNamePerson(dollDto.getNamePerson());
        doll.setDescription(dollDto.getDescription());
        doll.setPrice(dollDto.getPrice());
        return doll;
    }
}
