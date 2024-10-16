package ru.collection.doll_collection.client;

import ru.collection.doll_collection.dto.Doll;

import java.util.List;

public interface DollUserClient {

    List<Doll> getAllDolls();

    Doll getDollById(Integer dollId);

    // методы обработки картинок
    byte[] findMyImage(Integer dollId);

    byte[] findPromImage(Integer dollId);
}
