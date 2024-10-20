package ru.collection.doll_collection.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.collection.doll_collection.dto.DollDto;
import ru.collection.doll_collection.dto.DollNewDto;
import ru.collection.doll_collection.dto.DollUpdateDto;
import ru.collection.doll_collection.reportService.emailService.EmailServiceImpl;
import ru.collection.doll_collection.reportService.fileService.FileGenerator;
import ru.collection.doll_collection.service.DollService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("dolls")
public class DollRestController {

    private final DollService dollService;
    private final FileGenerator fileGenerator;
    private final EmailServiceImpl emailService;


    // Получение списка всех кукол
    @GetMapping
    public ResponseEntity<List<DollDto>> fineAllDolls() {
        return ResponseEntity.ok().body(dollService.findAllDolls());
    }

    // Создание новой куклы
    @PostMapping
    public ResponseEntity<?> createDoll(@Valid @RequestBody DollNewDto dollNewDto,
                                        BindingResult bindingResult,
                                        UriComponentsBuilder uriComponentsBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            DollDto dollDto = dollService.createDoll(dollNewDto);
            return ResponseEntity.created(uriComponentsBuilder
                            .replacePath("/api/dolls/{dollId}")
                            .build(Map.of("dollId", dollDto.getId())))
                    .body(dollDto);
        }
    }

    @GetMapping("/{dollId}")
    public ResponseEntity<?> findDollById(@PathVariable("dollId") Integer dollId) {
        return ResponseEntity.ok().body(dollService.findDollById(dollId));
    }

    // Изменение данных куклы
    @PatchMapping("/{dollId}")
    public ResponseEntity<?> updateDollById(@PathVariable("dollId") Integer dollId,
                                            @Valid @RequestBody DollUpdateDto dollUpdateDto,
                                            BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(dollService.updateDollById(dollId, dollUpdateDto));
        }
    }

    // Удаление куклы
    @DeleteMapping("/{dollId}")
    public ResponseEntity<Void> deleteDollById(@PathVariable("dollId") Integer dollId) {
        dollService.deleteDollById(dollId);
        return ResponseEntity.noContent().build();
    }

    // Обработка ошибки 404
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchElementException(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage()));
    }


    // Работа с картинкой
    @GetMapping(value = "/{dollId}/myImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findMyImage(@PathVariable("dollId") Integer dollId) {
        Optional<byte[]> optional = dollService.findMyImage(dollId);
        return optional.orElseGet(() -> new byte[0]);
    }

    @GetMapping(value = "/{dollId}/update/myImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findMyImageForUpdate(@PathVariable("dollId") Integer dollId) {
        Optional<byte[]> optional = dollService.findMyImage(dollId);
        return optional.orElseGet(() -> new byte[0]);
    }

    @GetMapping(value = "/{dollId}/promImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findPromImage(@PathVariable("dollId") Integer dollId) {
        Optional<byte[]> optional = dollService.findPromImage(dollId);
        return optional.orElseGet(() -> new byte[0]);
    }

    @GetMapping(value = "/{dollId}/update/promImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findPromImageForUpdate(@PathVariable("dollId") Integer dollId) {
        Optional<byte[]> optional = dollService.findPromImage(dollId);
        return optional.orElseGet(() -> new byte[0]);
    }

    // Создание и генерация отчета
    @PostMapping(value = "/createResponse")
    public void getResponseFile() throws MessagingException {
        fileGenerator.writeDataToFile();
        emailService.sendEmailWithFile();
    }
}