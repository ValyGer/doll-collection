package ru.collection.doll_collection.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.collection.doll_collection.dto.Doll;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public byte[] findMyImage(Integer dollId) {
        return new byte[0];
    }

    @Override
    public byte[] findPromImage(Integer dollId) {
        return new byte[0];
    }
}
