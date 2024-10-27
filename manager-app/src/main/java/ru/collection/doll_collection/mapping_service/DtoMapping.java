package ru.collection.doll_collection.mapping_service;

import ru.collection.doll_collection.dto.doll.*;

import java.io.IOException;

public interface DtoMapping {
    DollRequestClient dollInputToDollRequestClient(DollInputDto dollInputDto) throws IOException;

    DollRequestClient dollInputUpdateToDollRequestClient(DollInputUpdateDto dollInputUpdateDto) throws IOException;

    DollInputWithErrorDto dollInputToDollInputWithError(DollOutputDto dollOutputDto, DollInputUpdateDto dollInputUpdateDto);
}
