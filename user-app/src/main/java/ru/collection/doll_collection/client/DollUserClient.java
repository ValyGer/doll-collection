package ru.collection.doll_collection.client;

import ru.collection.doll_collection.dto.doll.Doll;

import java.util.List;
import java.util.Optional;

public interface DollUserClient {

    List<Doll> getAllDolls();

    Doll getDollById(Integer dollId);

    // методы обработки картинок
    Optional<byte[]> findMyImage(Integer dollId);

    Optional<byte[]> findPromImage(Integer dollId);
}
