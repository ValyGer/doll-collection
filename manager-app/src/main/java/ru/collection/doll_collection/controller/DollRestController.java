package ru.collection.doll_collection.controller;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.collection.doll_collection.reportService.emailService.EmailServiceImpl;
import ru.collection.doll_collection.reportService.fileService.FileGenerator;
import ru.collection.doll_collection.service.DollService;

@RestController
@AllArgsConstructor
@RequestMapping("/dolls")
public class DollRestController {

    private final DollService dollService;
    private final FileGenerator fileGenerator;
    private final EmailServiceImpl emailService;

    @GetMapping(value = "/{dollId}/myImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findMyImage(@PathVariable("dollId") Integer dollId) {
        return dollService.findMyImage(dollId).orElseThrow(RuntimeException::new);
    }

    @GetMapping(value = "/{dollId}/update/myImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findMyImageForUpdate(@PathVariable("dollId") Integer dollId) {
        return dollService.findMyImage(dollId).orElseThrow(RuntimeException::new);
    }

    @GetMapping(value = "/{dollId}/promImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findPromImage(@PathVariable("dollId") Integer dollId) {
        return dollService.findPromImage(dollId).orElseThrow(RuntimeException::new);
    }

    @GetMapping(value = "/{dollId}/update/promImage", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findPromImageForUpdate(@PathVariable("dollId") Integer dollId) {
        return dollService.findPromImage(dollId).orElseThrow(RuntimeException::new);
    }

    @GetMapping(value = "/createResponse")
    public void getResponseFile() throws MessagingException{
        fileGenerator.writeDataToFile();
        emailService.sendEmailWithFile();
    }
}

