package ru.collection.doll_collection.mapping_service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.collection.doll_collection.dto.DollDto;
import ru.collection.doll_collection.dto.DollNewDto;
import ru.collection.doll_collection.entity.Doll;

import java.util.Optional;
import java.util.function.Predicate;

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
        dollDto.setPromImage(doll.getPromImage());
        dollDto.setMyImage(doll.getMyImage());
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

        Optional.ofNullable(dollNewDto.getMyImage())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> doll.setMyImage(image.getOriginalFilename()));
        Optional.ofNullable(dollNewDto.getPromImage())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> doll.setPromImage(image.getOriginalFilename()));

        return doll;
    }
}
