package ru.collection.doll_collection.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.collection.doll_collection.dto.DollDto;
import ru.collection.doll_collection.dto.DollNewDto;
import ru.collection.doll_collection.dto.DollUpdateDto;
import ru.collection.doll_collection.entity.Doll;
import ru.collection.doll_collection.mapping_service.DollMapping;
import ru.collection.doll_collection.repository.DollRepository;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.NoSuchElementException;
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
        log.info("Получение списка всех кукол");
        return dollRepository.findAll().stream()
                .map(dollMapping::doolToDollDto)
                .collect(Collectors.toList());
    }

    // Сохранение новой куклы
    @Transactional
    @SneakyThrows
    @Override
    public DollDto createDoll(DollNewDto dollNewDto) {
        Doll doll = dollRepository.save(dollMapping.doolNewDtoToDoll(dollNewDto));
        if (dollNewDto.getMyImage().length != 0)
            imageService.upload(dollNewDto.getNameMyImage(), new ByteArrayInputStream(dollNewDto.getMyImage()));
        if (dollNewDto.getPromImage().length != 0)
            imageService.upload(dollNewDto.getNamePromImage(), new ByteArrayInputStream(dollNewDto.getPromImage()));
        log.info("Кукла сохранена. Присвоен id = {}", doll.getId());
        return dollMapping.doolToDollDto(doll);
    }

    // Получение куклы по id
    @Override
    public DollDto findDollById(Integer dollId) {
        log.info("Вызов метода поиска куклы по id");
        return dollMapping.doolToDollDto(findDollByIdAndReportOptional(dollId).get());
    }

    @Transactional
    @SneakyThrows
    @Override
    public DollDto updateDollById(Integer dollId, DollUpdateDto dollUpdateDto) {
        log.info("Вызов метода поиска куклы по id");
        Doll doll = findDollByIdAndReportOptional(dollId).get();
        if (dollUpdateDto.getYear() != null)
            doll.setYear(dollUpdateDto.getYear());
        if ((dollUpdateDto.getBrand() != null) && (!dollUpdateDto.getBrand().isBlank()))
            doll.setBrand(dollUpdateDto.getBrand());
        if ((dollUpdateDto.getRuler() != null) && (!dollUpdateDto.getRuler().isBlank()))
            doll.setRuler(dollUpdateDto.getRuler());
        if ((dollUpdateDto.getSeries() != null) && (!dollUpdateDto.getSeries().isBlank()))
            doll.setSeries(dollUpdateDto.getSeries());
        if ((dollUpdateDto.getNamePerson() != null) && (!dollUpdateDto.getNamePerson().isBlank()))
            doll.setNamePerson(dollUpdateDto.getNamePerson());
        if ((dollUpdateDto.getDescription() != null) && (!dollUpdateDto.getDescription().isBlank()))
            doll.setDescription(dollUpdateDto.getDescription());
        if ((dollUpdateDto.getPrice() != null) )
            doll.setPrice(dollUpdateDto.getPrice());

        if ((dollUpdateDto.getNameMyImage() != null) && (!dollUpdateDto.getNameMyImage().isBlank()))
            doll.setMyImage(dollUpdateDto.getNameMyImage());
        if ((dollUpdateDto.getNamePromImage() != null) && (!dollUpdateDto.getNamePromImage().isBlank()))
            doll.setPromImage(dollUpdateDto.getNamePromImage());

        if (dollUpdateDto.getMyImage().length != 0)
            imageService.upload(dollUpdateDto.getNameMyImage(),
                    new ByteArrayInputStream(dollUpdateDto.getMyImage()));
        if (dollUpdateDto.getPromImage().length != 0)
            imageService.upload(dollUpdateDto.getNamePromImage(),
                    new ByteArrayInputStream(dollUpdateDto.getPromImage()));

        Doll dollSave = dollRepository.save(doll);
        log.info("Данные куклы с id = {} успешно обновлены.", dollId);
        return dollMapping.doolToDollDto(dollSave);
    }

    @Transactional
    @Override
    public void deleteDollById(Integer dollId) {
        log.info("Вызов метода поиска куклы по id");
        findDollByIdAndReportOptional(dollId);
        dollRepository.deleteById(dollId);
        log.info("Кукла с id = {} успешно удалена", dollId);
    }

    private Optional<Doll> findDollByIdAndReportOptional(Integer dollId) {
        Optional<Doll> doll = dollRepository.findById(dollId);
        if (doll.isPresent()) {
            log.info("Кукла с id = {} найдена", dollId);
            return doll;
        } else {
            log.warn("Куклы с id = {} не найдена", dollId);
            throw new NoSuchElementException("Кукла с id = " + dollId + " не найдена");
        }
    }

    //-----------------------------------------------------------------------------------------------------------------
    //Обработка картинок

    @Override
    public Optional<byte[]> findMyImage(Integer dollId) {
        return findDollByIdAndReportOptional(dollId).map(Doll::getMyImage).filter(StringUtils::hasText).flatMap(imageService::get);
    }

    @Override
    public Optional<byte[]> findPromImage(Integer dollId) {
        return findDollByIdAndReportOptional(dollId).map(Doll::getPromImage).filter(StringUtils::hasText).flatMap(imageService::get);
    }

    //-----------------------------------------------------------------------------------------------------------------
    //Для получения данных для записи в файл
    @Override
    public List<Doll> findAllDataDolls() {
        log.info("Получение списка всех кукол");
        return dollRepository.findAll();
    }
}
