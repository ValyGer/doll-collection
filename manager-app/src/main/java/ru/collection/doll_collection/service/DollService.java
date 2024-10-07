package ru.collection.doll_collection.service;

import ru.collection.doll_collection.dto.DollDto;
import ru.collection.doll_collection.dto.DollNewDto;
import ru.collection.doll_collection.dto.DollUpdateDto;

import java.util.List;

public interface DollService {
    List<DollDto> findAllDolls();

    DollDto createDoll(DollNewDto dollNewDto);

    void deleteDollById(Integer dollId);

    DollDto updateDollById(Integer dollId, DollUpdateDto dollUpdateDto);

    DollDto findDollById(Integer dollId);
}
