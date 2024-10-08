package ru.collection.doll_collection.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("./manager-app/src/main/resources/image")
    private String bucket;

    @Override
    @SneakyThrows
    public void upload(String imagePath, InputStream content) {
        Path fullImagePath = Path.of(bucket, imagePath);

        try(content) {
            Files.createDirectories(fullImagePath.getParent());
            Files.write(fullImagePath, content.readAllBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    @Override
    @SneakyThrows
    public Optional<byte[]> get(String imagePath) {
        Path fullPath = Path.of(bucket, imagePath);
        return Files.exists(fullPath) ? Optional.of(Files.readAllBytes(fullPath)) :Optional.empty();
    }
}