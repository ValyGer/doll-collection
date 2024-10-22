package ru.collection.doll_collection.service;

import ru.collection.doll_collection.dto.DollOutDto;

import java.util.List;

public interface DollUserService {

    List<DollOutDto> getAllDolls();

    DollOutDto getDollById(Integer dollId);
}
