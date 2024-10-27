package ru.collection.doll_collection.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.collection.doll_collection.dto.doll.DollOutputDto;

import java.util.List;

@Service
@Slf4j
public class DollSumServiceImpl implements DollSumService{
    @Override
    public double getSumAllDolls(List<DollOutputDto> allDolls) {
       log.info("Вызван метод подсчета стоимости коллекции");
       return allDolls.stream().mapToDouble(DollOutputDto::getPrice).sum();
    }
}
