package ru.collection.doll_collection.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;
import ru.collection.doll_collection.dto.DollDto;

import java.util.List;

@RequiredArgsConstructor
public class DollUserClientImpl implements DollUserClient {

    private final RestClient restClient;

    @Override
    public List<DollDto> getAllDolls() {
        return null;
    }

    @Override
    public DollDto getDollById(Integer dollId) {
        return null;
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
