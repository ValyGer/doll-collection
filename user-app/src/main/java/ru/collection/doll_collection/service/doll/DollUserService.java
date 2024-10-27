package ru.collection.doll_collection.service.doll;

import ru.collection.doll_collection.dto.doll.DollOutDto;

import java.util.List;

public interface DollUserService {

    List<DollOutDto> getAllDolls();

    DollOutDto getDollById(Integer dollId);
}
