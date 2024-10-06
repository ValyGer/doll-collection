package ru.collection.doll_collection.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.collection.doll_collection.mapping_service.DollMapping;
import ru.collection.doll_collection.repository.DollRepository;

@Service
@RequiredArgsConstructor
public class DollServiceImpl implements DollService{

    private final DollRepository dollRepository;
    private final DollMapping dollMapping;
}
