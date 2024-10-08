package ru.collection.doll_collection.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
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
    private final ImageService imageService;

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
        saveOnDisk(dollNewDto.getMyImage());
        saveOnDisk(dollNewDto.getPromImage());
        log.info("Кукла сохранена. Присвоен id = {}", doll.getId());
        return dollMapping.doolToDollDto(doll);
    }

    // Получение куклы по id
    @Override
    public DollDto findDollById(Integer dollId) {
        log.info("Вызов метода поиска куклы по id");
        return dollMapping.doolToDollDto(validDollInDb(dollId).get());
    }

    @Override
    public DollDto updateDollById(Integer dollId, DollUpdateDto dollUpdateDto) {
        log.info("Вызов метода поиска куклы по id");
        Doll doll = validDollInDb(dollId).get();
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

        if (!dollUpdateDto.getMyImage().isEmpty()) {
            saveOnDisk(dollUpdateDto.getMyImage());
            doll.setMyImage(dollUpdateDto.getMyImage().getOriginalFilename());
        }
        if (!dollUpdateDto.getPromImage().isEmpty()) {
            saveOnDisk(dollUpdateDto.getPromImage());
            doll.setPromImage(dollUpdateDto.getPromImage().getOriginalFilename());
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

    private Optional<Doll> validDollInDb(Integer dollId) {
        Optional<Doll> doll = dollRepository.findById(dollId);
        if (doll.isPresent()) {
            log.info("Кукла с id = {} найдена", dollId);
            return doll;
        } else {
            log.warn("Куклы с id = {} не найдена", dollId);
            throw new RuntimeException("Куклы с id = " + dollId + " не найдена");
        }
    }

    @SneakyThrows
    private void saveOnDisk(MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    //-----------------------------------------------------------------------------------------------------------------
    //Обработка картинок

    @Override
    public Optional<byte[]> findMyImage(Integer dollId) {
        return validDollInDb(dollId).map(Doll::getMyImage).filter(StringUtils::hasText).flatMap(imageService::get);
    }

    @Override
    public Optional<byte[]> findPromImage(Integer dollId) {
        return validDollInDb(dollId).map(Doll::getPromImage).filter(StringUtils::hasText).flatMap(imageService::get);
    }
}
