package ru.collection.doll_collection.mapping_service;

import ru.collection.doll_collection.dto.doll.Doll;
import ru.collection.doll_collection.dto.doll.DollOutDto;

public interface DollMapping {

    DollOutDto dollToDollDto(Doll doll);
}
