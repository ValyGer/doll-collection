package ru.collection.doll_collection.service;

import ru.collection.doll_collection.dto.DollDto;

import java.util.List;

public interface DollUserService {

    List<DollDto> getAllDolls();

    DollDto getDollById(Integer dollId);
}
