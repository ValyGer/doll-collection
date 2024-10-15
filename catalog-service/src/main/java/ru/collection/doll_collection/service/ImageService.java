package ru.collection.doll_collection.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;

@Service
public interface ImageService {
    void upload(String imagePath, InputStream content);

    Optional<byte[]> get(String imagePath);
}
