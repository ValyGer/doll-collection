package ru.collection.doll_collection.mapping_service;

import ru.collection.doll_collection.dto.DollInputDto;
import ru.collection.doll_collection.dto.DollInputUpdateDto;
import ru.collection.doll_collection.dto.DollRequestClient;

import java.io.IOException;

public interface DtoMapping {
    DollRequestClient dollInputToDollRequestClient(DollInputDto dollInputDto) throws IOException;

    DollRequestClient dollInputUpdateToDollRequestClient(DollInputUpdateDto dollInputUpdateDto) throws IOException;
}
