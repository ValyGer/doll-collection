package ru.collection.doll_collection.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.collection.doll_collection.dto.Doll;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class DollUserClientImpl implements DollUserClient {

    private static final ParameterizedTypeReference<List<Doll>> PARAMETERIZED_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };

    private final RestClient restClient;

    @Override
    public List<Doll> getAllDolls() {
        return this.restClient
                .get()
                .uri("dolls")
                .retrieve()
                .body(PARAMETERIZED_TYPE_REFERENCE);
    }

    @Override
    public Doll getDollById(Integer dollId) {
        try {
            return this.restClient
                    .get()
                    .uri("dolls/{dollId}", dollId)
                    .retrieve()
                    .body(Doll.class);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new NoSuchElementException(exception);
        }
    }

// Работа с картинкой

    @Override
    public Optional<byte[]> findMyImage(Integer dollId) {
        return Optional.ofNullable(this.restClient
                .get()
                .uri("dolls/{dollId}/myImage", dollId)
                .retrieve()
                .body(byte[].class));
    }

    @Override
    public Optional<byte[]> findPromImage(Integer dollId) {
        return Optional.ofNullable(this.restClient
                .get()
                .uri("dolls/{dollId}/promImage", dollId)
                .retrieve()
                .body(byte[].class));
    }
}
