package ru.collection.doll_collection.service;

import ru.collection.doll_collection.dto.doll.DollOutputDto;

import java.util.List;

public interface DollSumService {
    double getSumAllDolls(List<DollOutputDto> allDolls);
}
