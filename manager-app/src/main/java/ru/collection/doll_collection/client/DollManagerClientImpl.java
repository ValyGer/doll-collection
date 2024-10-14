package ru.collection.doll_collection.client;


import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.collection.doll_collection.dto.DollDto;
import ru.collection.doll_collection.dto.DollNewDto;
import ru.collection.doll_collection.dto.DollUpdateDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class DollManagerClientImpl implements DollManagerClient {

    private static final ParameterizedTypeReference<List<DollDto>> PARAMETERIZED_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };

    private final RestClient restClient;

    @Override
    public List<DollDto> getAllDolls() {
        return this.restClient
                .get()
                .uri("dolls")
                .retrieve()
                .body(PARAMETERIZED_TYPE_REFERENCE);
    }

    @Override
    public DollDto createDoll(DollNewDto dollNewDto) {
        try {
            return this.restClient
                    .post()
                    .uri("dolls")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(dollNewDto)
                    .retrieve()
                    .body(DollDto.class);
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public DollDto getDollById(Integer dollId) {
        try {
            return this.restClient
                    .get()
                    .uri("dolls/{dollId}", dollId)
                    .retrieve()
                    .body(DollDto.class);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchElementException(exception);
        }
    }

    @Override
    public DollDto updateDollById(Integer dollId, DollUpdateDto dollUpdateDto) {
        try {
            return this.restClient
                    .patch()
                    .uri("dolls/{dollId}", dollId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(dollUpdateDto)
                    .retrieve()
                    .body(DollDto.class);
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public void deleteDollById(Integer dollId) {
        try {
            this.restClient
                    .delete()
                    .uri("dolls/{dollId}", dollId)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchElementException(exception);
        }
    }


// Работа с картинкой

    @Override
    public byte[] findMyImage(Integer dollId) {
        try {
            return this.restClient
                    .get()
                    .uri("dolls//{dollId}/myImage", dollId)
                    .retrieve()
                    .body(byte[].class);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchElementException(exception);
        }
    }

    @Override
    public byte[] findPromImage(Integer dollId) {
        try {
            return this.restClient
                    .get()
                    .uri("dolls//{dollId}/promImage", dollId)
                    .retrieve()
                    .body(byte[].class);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchElementException(exception);
        }
    }

    @Override
    public void createDataFile() {

    }
}
