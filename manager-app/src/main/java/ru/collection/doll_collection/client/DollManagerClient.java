package ru.collection.doll_collection.client;

import ru.collection.doll_collection.dto.DollDto;
import ru.collection.doll_collection.dto.DollNewDto;
import ru.collection.doll_collection.dto.DollUpdateDto;

import java.util.List;
import java.util.Optional;

public interface DollManagerClient {

    List<DollDto> getAllDolls();

    DollDto createDoll(DollNewDto dollNewDto);

    DollDto getDollById(Integer dollId);

    DollDto updateDollById(Integer dollId, DollUpdateDto dollUpdateDto);

    void deleteDollById(Integer dollId);


    // методы обработки картинок
    byte[] findMyImage(Integer dollId);

    byte[] findPromImage(Integer dollId);

    // метод для получения данных для записи файла
    void createDataFile();
}
