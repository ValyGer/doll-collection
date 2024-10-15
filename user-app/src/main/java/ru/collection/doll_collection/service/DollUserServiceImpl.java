package ru.collection.doll_collection.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.collection.doll_collection.client.DollUserClient;
import ru.collection.doll_collection.dto.DollDto;
import ru.collection.doll_collection.mapping_service.DollMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DollUserServiceImpl implements DollUserService{

    private final DollUserClient dollUserClient;
    private final DollMapping dollMapping;


    @Override
    public List<DollDto> getAllDolls() {
        return dollUserClient.getAllDolls().stream().map(dollMapping::dollToDollDto).collect(Collectors.toList());
    }

    @Override
    public DollDto getDollById(Integer dollId) {
            return dollMapping.dollToDollDto(dollUserClient.getDollById(dollId));
    }
}
