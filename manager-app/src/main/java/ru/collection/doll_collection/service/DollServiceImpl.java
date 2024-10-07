package ru.collection.doll_collection.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.collection.doll_collection.dto.DollDto;
import ru.collection.doll_collection.dto.DollNewDto;
import ru.collection.doll_collection.dto.DollUpdateDto;
import ru.collection.doll_collection.entity.Doll;
import ru.collection.doll_collection.mapping_service.DollMapping;
import ru.collection.doll_collection.repository.DollRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DollServiceImpl implements DollService {

    private final DollRepository dollRepository;
    private final DollMapping dollMapping;

    // Получение списка всех кукол
    @Override
    public List<DollDto> findAllDolls() {
        List<Doll> dolls = new ArrayList<>();
        log.info("Получение списка всех кукол");
        return dollRepository.findAll().stream()
                .map(dollMapping::doolToDollDto)
                .collect(Collectors.toList());
    }

    // Сохранение новой куклы
    @Override
    public DollDto createDoll(DollNewDto dollNewDto) {
        Doll doll = dollRepository.save(dollMapping.doolNewDtoToDoll(dollNewDto));
        log.info("Кукла сохранена. Присвоен id = {}", doll.getId());
        return dollMapping.doolToDollDto(doll);
    }

    // Получение куклы по id
    @Override
    public DollDto findDollById(Integer dollId) {
        log.info("Вызов метода поиска куклы по id");
        return dollMapping.doolToDollDto(validDollInDb(dollId));
    }


    @Override
    public DollDto updateDollById(Integer dollId, DollUpdateDto dollUpdateDto) {
        log.info("Вызов метода поиска куклы по id");
        Doll doll = validDollInDb(dollId);
        if (dollUpdateDto.getYear() != null) {
            doll.setYear(dollUpdateDto.getYear());
        }
        if (dollUpdateDto.getBrand() != null) {
            doll.setBrand(dollUpdateDto.getBrand());
        }
        if (dollUpdateDto.getRuler() != null) {
            doll.setRuler(dollUpdateDto.getRuler());
        }
        if (dollUpdateDto.getSeries() != null) {
            doll.setSeries(dollUpdateDto.getSeries());
        }
        if (dollUpdateDto.getNamePerson() != null) {
            doll.setNamePerson(dollUpdateDto.getNamePerson());
        }
        if (dollUpdateDto.getDescription() != null) {
            doll.setDescription(dollUpdateDto.getDescription());
        }
        if (dollUpdateDto.getPrice() != null) {
            doll.setPrice(dollUpdateDto.getPrice());
        }
        Doll dollSave = dollRepository.save(doll);
        log.info("Данные куклы с id = {} успешно обновлены.", dollId);
        return dollMapping.doolToDollDto(dollSave);
    }

    @Override
    public void deleteDollById(Integer dollId) {
        log.info("Вызов метода поиска куклы по id");
        validDollInDb(dollId);
        dollRepository.deleteById(dollId);
        log.info("Кукла с id = {} успешно удалена", dollId);
    }

    private Doll validDollInDb(Integer dollId) {
        Optional<Doll> doll = dollRepository.findById(dollId);
        if (doll.isPresent()) {
            log.info("Кукла с id = {} найдена", dollId);
            return doll.get();
        } else {
            log.warn("Куклы с id = {} не найдена", dollId);
            throw new RuntimeException("Куклы с id = " + dollId + " не найдена");
        }
    }
}
