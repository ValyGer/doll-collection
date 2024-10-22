package ru.collection.doll_collection.mapping_service;

import ru.collection.doll_collection.dto.Doll;
import ru.collection.doll_collection.dto.DollOutDto;

public interface DollMapping {

    DollOutDto dollToDollDto(Doll doll);
}
