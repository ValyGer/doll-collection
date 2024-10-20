package ru.collection.doll_collection.client;

import ru.collection.doll_collection.dto.DollInputDto;
import ru.collection.doll_collection.dto.DollInputUpdateDto;
import ru.collection.doll_collection.dto.DollOutputDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DollManagerClient {

    List<DollOutputDto> getAllDolls();

    DollOutputDto createDoll(DollInputDto dollInputDto) throws IOException;

    DollOutputDto getDollById(Integer dollId);

    DollOutputDto updateDollById(Integer dollId, DollInputUpdateDto dollInputUpdateDto) throws IOException;

    void deleteDollById(Integer dollId);


    // методы обработки картинок
    Optional<byte[]> findMyImage(Integer dollId);

    Optional<byte[]> findPromImage(Integer dollId);

    // метод для получения данных для записи файла
    void createDataFile();
}
