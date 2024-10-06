package ru.collection.doll_collection.service;

import ru.collection.doll_collection.dto.DollDto;

public interface DollService {
    Object findAllDolls();

    DollDto createDoll(DollDto dollDto);

    void deleteDollById(Long dollId);

    void updateDollById(Long dollId, DollDto dollDto);

    Object findDollById(Long userId);
}
