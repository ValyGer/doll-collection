package ru.collection.doll_collection.mapping_service;

import ru.collection.doll_collection.dto.Doll;
import ru.collection.doll_collection.dto.DollDto;

public interface DollMapping {

    DollDto dollToDollDto(Doll doll);
}
