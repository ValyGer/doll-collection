package ru.collection.doll_collection.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.collection.doll_collection.client.DollManagerClient;

@RestController
@RequestMapping("dolls")
@RequiredArgsConstructor
public class DollControllerForEmailAndImage {

    private final DollManagerClient dollManagerClient;

    // Работа с картинкой
    @GetMapping(value = "/{dollId}/myImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findMyImage(@PathVariable("dollId") Integer dollId) {
        return dollManagerClient.findMyImage(dollId);
    }

    @GetMapping(value = "/{dollId}/update/myImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findMyImageForUpdate(@PathVariable("dollId") Integer dollId) {
        return dollManagerClient.findMyImage(dollId);
    }

    @GetMapping(value = "/{dollId}/promImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findPromImage(@PathVariable("dollId") Integer dollId) {
        return dollManagerClient.findPromImage(dollId);
    }

    @GetMapping(value = "/{dollId}/update/promImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findPromImageForUpdate(@PathVariable("dollId") Integer dollId) {
        return dollManagerClient.findPromImage(dollId);
    }
}
