package ru.collection.doll_collection.service;

import ru.collection.doll_collection.dto.doll.DollDto;
import ru.collection.doll_collection.dto.doll.DollNewDto;
import ru.collection.doll_collection.dto.doll.DollUpdateDto;
import ru.collection.doll_collection.entity.Doll;

import java.util.List;
import java.util.Optional;

public interface DollService {
    List<DollDto> findAllDolls();

    DollDto createDoll(DollNewDto dollNewDto);

    void deleteDollById(Integer dollId);

    DollDto updateDollById(Integer dollId, DollUpdateDto dollUpdateDto);

    DollDto findDollById(Integer dollId);


    // методы обработки картинок
    Optional<byte[]> findMyImage(Integer dollId);

    Optional<byte[]> findPromImage(Integer dollId);

    // метод для получения данных для записи файла
    List<Doll> findAllDataDolls();
}
