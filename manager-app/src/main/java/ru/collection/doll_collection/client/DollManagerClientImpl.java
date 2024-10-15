package ru.collection.doll_collection.client;


import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.collection.doll_collection.dto.DollInputDto;
import ru.collection.doll_collection.dto.DollInputUpdateDto;
import ru.collection.doll_collection.dto.DollOutputDto;
import ru.collection.doll_collection.dto.DollRequestClient;
import ru.collection.doll_collection.mapping_service.DtoMapping;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class DollManagerClientImpl implements DollManagerClient {

    private static final ParameterizedTypeReference<List<DollOutputDto>> PARAMETERIZED_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };

    private final RestClient restClient;
    private final DtoMapping dtoMapping;

    @Override
    public List<DollOutputDto> getAllDolls() {
        return this.restClient
                .get()
                .uri("dolls")
                .retrieve()
                .body(PARAMETERIZED_TYPE_REFERENCE);
    }

    @Override
    public DollOutputDto createDoll(DollInputDto dollInputDto) throws IOException {
        DollRequestClient dollRequestClient = dtoMapping.dollInputToDollRequestClient(dollInputDto);
        try {
            return this.restClient
                    .post()
                    .uri("dolls")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(dollRequestClient)
                    .retrieve()
                    .body(DollOutputDto.class);
        } catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public DollOutputDto getDollById(Integer dollId) {
        try {
            return this.restClient
                    .get()
                    .uri("dolls/{dollId}", dollId)
                    .retrieve()
                    .body(DollOutputDto.class);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchElementException(exception);
        }
    }

    @Override
    public DollOutputDto updateDollById(Integer dollId, DollInputUpdateDto dollInputUpdateDto) throws IOException {
        DollRequestClient dollRequestClient = dtoMapping.dollInputUpdateToDollRequestClient(dollInputUpdateDto);
        try {
            return this.restClient
                    .patch()
                    .uri("dolls/{dollId}", dollId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(dollRequestClient)
                    .retrieve()
                    .body(DollOutputDto.class);
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
                    .uri("dolls/{dollId}/myImage", dollId)
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
                    .uri("dolls/{dollId}/promImage", dollId)
                    .retrieve()
                    .body(byte[].class);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchElementException(exception);
        }
    }

    @Override
    public void createDataFile() {
        try {
            this.restClient
                    .post()
                    .uri("dolls/createResponse")
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchElementException(exception);
        }
    }
}
