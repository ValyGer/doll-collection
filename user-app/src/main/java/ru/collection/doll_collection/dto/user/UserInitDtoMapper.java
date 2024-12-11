package ru.collection.doll_collection.dto.user;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.collection.doll_collection.entity.User;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class UserInitDtoMapper {

    public User userInitDtoToUser(UserInitDto userInitDto) {
        User user = new User();
        user.setUserEmail(userInitDto.getUserEmail());
        user.setUserName(userInitDto.getUserName());
        user.setPassword(userInitDto.getPassword());

        Optional.ofNullable(userInitDto.getImage())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> user.setImage(image.getOriginalFilename()));

        return user;
    }
}
