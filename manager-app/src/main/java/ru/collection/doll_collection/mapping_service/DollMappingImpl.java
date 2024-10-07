package ru.collection.doll_collection.mapping_service;

import org.springframework.stereotype.Service;
import ru.collection.doll_collection.dto.DollDto;
import ru.collection.doll_collection.dto.DollUpdateDto;
import ru.collection.doll_collection.dto.DollNewDto;
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
        return dollDto;
    }

    @Override
    public Doll doolNewDtoToDoll(DollNewDto dollNewDto) {
        Doll doll = new Doll();
        doll.setId(dollNewDto.getId());
        doll.setYear(dollNewDto.getYear());
        doll.setBrand(dollNewDto.getBrand());
        doll.setRuler(dollNewDto.getRuler());
        doll.setSeries(dollNewDto.getSeries());
        doll.setNamePerson(dollNewDto.getNamePerson());
        doll.setDescription(dollNewDto.getDescription());
        doll.setPrice(dollNewDto.getPrice());
        return doll;
    }

    @Override
    public Doll doolInputDtoToDoll(DollUpdateDto dollUpdateDto) {
        Doll doll = new Doll();
        doll.setId(dollUpdateDto.getId());
        doll.setYear(dollUpdateDto.getYear());
        doll.setBrand(dollUpdateDto.getBrand());
        doll.setRuler(dollUpdateDto.getRuler());
        doll.setSeries(dollUpdateDto.getSeries());
        doll.setNamePerson(dollUpdateDto.getNamePerson());
        doll.setDescription(dollUpdateDto.getDescription());
        doll.setPrice(dollUpdateDto.getPrice());
        return doll;
    }
}
