package ru.collection.doll_collection.mapping_service;

import ru.collection.doll_collection.dto.doll.DollDto;
import ru.collection.doll_collection.dto.doll.DollNewDto;
import ru.collection.doll_collection.entity.Doll;

public interface DollMapping {

    DollDto doolToDollDto(Doll doll);

    Doll doolNewDtoToDoll(DollNewDto dollNewDto);
}
