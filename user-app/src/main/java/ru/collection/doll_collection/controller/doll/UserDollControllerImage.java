package ru.collection.doll_collection.controller.doll;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.collection.doll_collection.client.DollUserClient;

@RestController
@RequestMapping("user/dolls")
@RequiredArgsConstructor
public class UserDollControllerImage {

    private final DollUserClient dollUserClient;

    // Работа с картинкой
    @GetMapping(value = "/{dollId}/myImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findMyImage(@PathVariable("dollId") Integer dollId) {
        return dollUserClient.findMyImage(dollId).orElseThrow(RuntimeException::new);
    }

    @GetMapping(value = "/{dollId}/promImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findPromImage(@PathVariable("dollId") Integer dollId) {
        return dollUserClient.findPromImage(dollId).orElseThrow(RuntimeException::new);
    }
}

