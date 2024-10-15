package ru.collection.doll_collection.client;

import ru.collection.doll_collection.dto.DollDto;

import java.util.List;

public interface DollUserClient {

    List<DollDto> getAllDolls();

    DollDto getDollById(Integer dollId);

    // методы обработки картинок
    byte[] findMyImage(Integer dollId);

    byte[] findPromImage(Integer dollId);
}
