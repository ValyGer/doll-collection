package ru.collection.doll_collection.service.doll;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.collection.doll_collection.client.DollUserClient;
import ru.collection.doll_collection.dto.doll.DollOutDto;
import ru.collection.doll_collection.mapping_service.doll.DollMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DollUserServiceImpl implements DollUserService {

    private final DollUserClient dollUserClient;
    private final DollMapping dollMapping;


    @Override
    @SneakyThrows
    public List<DollOutDto> getAllDolls() {
        return dollUserClient.getAllDolls().stream().map(dollMapping::dollToDollDto).collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public DollOutDto getDollById(Integer dollId) {
        return dollMapping.dollToDollDto(dollUserClient.getDollById(dollId));
    }
}
