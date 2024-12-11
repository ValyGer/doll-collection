package ru.collection.doll_collection.mapping_service;

import org.springframework.stereotype.Component;
import ru.collection.doll_collection.client.BadRequestException;
import ru.collection.doll_collection.dto.doll.*;

import java.io.IOException;
import java.util.Collections;

@Component
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
                сheckAndConvertStringToInt(dollInputDto.getYear()),
                stringConversion(dollInputDto.getBrand()),
                stringConversion(dollInputDto.getRuler()),
                stringConversion(dollInputDto.getSeries()),
                stringConversion(dollInputDto.getNamePerson()),
                stringConversion(dollInputDto.getDescription()),
                сheckAndConvertStringToLong(dollInputDto.getPrice()),
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

        String nameMyImage = null;
        if (!dollInputUpdateDto.getMyImage().getName().isBlank()) {
            nameMyImage = dollInputUpdateDto.getMyImage().getOriginalFilename();
        }

        String namePromImage = null;
        if (!dollInputUpdateDto.getPromImage().getName().isBlank()) {
            namePromImage = dollInputUpdateDto.getPromImage().getOriginalFilename();
        }

        return new DollRequestClient(
                сheckAndConvertStringToInt(dollInputUpdateDto.getYear()),
                stringConversion(dollInputUpdateDto.getBrand()),
                stringConversion(dollInputUpdateDto.getRuler()),
                stringConversion(dollInputUpdateDto.getSeries()),
                stringConversion(dollInputUpdateDto.getNamePerson()),
                stringConversion(dollInputUpdateDto.getDescription()),
                сheckAndConvertStringToLong(dollInputUpdateDto.getPrice()),
                myImage,
                nameMyImage,
                prodImage,
                namePromImage
        );
    }

    @Override
    public DollInputWithErrorDto dollInputToDollInputWithError(DollOutputDto dollOutputDto, DollInputUpdateDto dollInputUpdateDto) {
        DollInputWithErrorDto dollInputWithErrorDto = new DollInputWithErrorDto();
        dollInputWithErrorDto.setId(dollOutputDto.getId());
        dollInputWithErrorDto.setYear(!dollOutputDto.getYear().toString().equals(dollInputUpdateDto.getYear())
                ? dollInputUpdateDto.getYear() : dollOutputDto.getYear().toString());

        if (!dollOutputDto.getBrand().equals(dollInputUpdateDto.getBrand()))
            dollInputWithErrorDto.setBrand(dollInputUpdateDto.getBrand());
        else dollInputWithErrorDto.setBrand(dollOutputDto.getBrand());

        dollInputWithErrorDto.setRuler(!dollOutputDto.getRuler().equals(dollInputUpdateDto.getRuler())
                ? dollInputUpdateDto.getRuler() : dollOutputDto.getRuler());

        dollInputWithErrorDto.setSeries(!dollOutputDto.getSeries().equals(dollInputUpdateDto.getSeries())
                ? dollInputUpdateDto.getSeries() : dollOutputDto.getSeries());

        dollInputWithErrorDto.setNamePerson(!dollOutputDto.getNamePerson().equals(dollInputUpdateDto.getNamePerson())
                ? dollInputUpdateDto.getNamePerson() : dollOutputDto.getNamePerson());

        dollInputWithErrorDto.setDescription(!dollOutputDto.getDescription().equals(dollInputUpdateDto.getDescription())
                ? dollInputUpdateDto.getDescription() : dollOutputDto.getDescription());

        dollInputWithErrorDto.setPrice(!dollOutputDto.getPrice().toString().equals(dollInputUpdateDto.getPrice())
                ? dollInputUpdateDto.getPrice() : dollOutputDto.getPrice().toString());

        dollInputWithErrorDto.setMyImage(dollOutputDto.getMyImage() == null ? null : dollOutputDto.getMyImage());
        dollInputWithErrorDto.setPromImage(dollOutputDto.getPromImage() == null ? null : dollOutputDto.getPromImage());
        return dollInputWithErrorDto;
    }

    // Не самая красивая функция!
    private String stringConversion(String str) {
        if (!(str == null) && (!str.isBlank())) {
            StringBuilder stringBuilder = new StringBuilder(str.trim().toLowerCase());
            char firstChar = stringBuilder.charAt(0);
            stringBuilder.deleteCharAt(0);
            return Character.toString(firstChar).toUpperCase() + stringBuilder.toString();
        }
        return str;
    }

    private Integer сheckAndConvertStringToInt(String strYear) {
        try {
            return Integer.parseInt(strYear.trim());
        } catch (IllegalArgumentException exception) {
            throw new BadRequestException(Collections.singletonList("В поле год надо ввести число"));
        }
    }

    private Long сheckAndConvertStringToLong(String strYear) {
        try {
            return Long.parseLong(strYear.trim());
        } catch (IllegalArgumentException exception) {
            throw new BadRequestException(Collections.singletonList("В поле цена надо ввести число"));
        }
    }
}
