package ru.collection.doll_collection.mapping_service;

import ru.collection.doll_collection.dto.DollInputDto;
import ru.collection.doll_collection.dto.DollInputUpdateDto;
import ru.collection.doll_collection.dto.DollRequestClient;

import java.io.IOException;

public class DollInputDtoMapping implements DtoMapping {

    public DollRequestClient dollInputToDollRequestClient(DollInputDto dollInputDto) throws IOException {
        byte[] myImage = new byte[0];
        byte[] prodImage = new byte[0];
        if (!dollInputDto.getMyImage().isEmpty()) {
            myImage = dollInputDto.getMyImage().getBytes();
        }
        if (!dollInputDto.getPromImage().isEmpty()) {
            prodImage = dollInputDto.getPromImage().getBytes();
        }

        return new DollRequestClient(
                dollInputDto.getYear(),
                stringConversion(dollInputDto.getBrand()),
                stringConversion(dollInputDto.getRuler()),
                stringConversion(dollInputDto.getSeries()),
                stringConversion(dollInputDto.getNamePerson()),
                stringConversion(dollInputDto.getDescription()),
                dollInputDto.getPrice(),
                myImage,
                dollInputDto.getMyImage().getOriginalFilename(),
                prodImage,
                dollInputDto.getPromImage().getOriginalFilename()
        );
    }

    @Override
    public DollRequestClient dollInputUpdateToDollRequestClient(DollInputUpdateDto dollInputUpdateDto) throws IOException {
        byte[] myImage = new byte[0];
        byte[] prodImage = new byte[0];
        if (!dollInputUpdateDto.getMyImage().isEmpty()) {
            myImage = dollInputUpdateDto.getMyImage().getBytes();
        }
        if (!dollInputUpdateDto.getPromImage().isEmpty()) {
            prodImage = dollInputUpdateDto.getPromImage().getBytes();
        }

        return new DollRequestClient(
                dollInputUpdateDto.getYear(),
                stringConversion(dollInputUpdateDto.getBrand()),
                stringConversion(dollInputUpdateDto.getRuler()),
                stringConversion(dollInputUpdateDto.getSeries()),
                stringConversion(dollInputUpdateDto.getNamePerson()),
                stringConversion(dollInputUpdateDto.getDescription()),
                dollInputUpdateDto.getPrice(),
                myImage,
                dollInputUpdateDto.getMyImage().getOriginalFilename(),
                prodImage,
                dollInputUpdateDto.getPromImage().getOriginalFilename()
        );
    }

    // Не самая красивая функция!
    private String stringConversion(String str) {
        StringBuilder stringBuilder = new StringBuilder(str.trim().toLowerCase());
        char firstChar = stringBuilder.charAt(0);
        stringBuilder.replace(0, 1, Character.toString(firstChar).toUpperCase());
        return stringBuilder.toString();
    }
}
