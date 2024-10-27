package ru.collection.doll_collection.service.user;

import java.io.InputStream;
import java.util.Optional;

public interface ImageService {
    void upload(String imagePath, InputStream content);

    Optional<byte[]> get(String imagePath);
}
