package ru.collection.doll_collection.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserInitUpdateDto {

    private String userName;
    private String password;
    private MultipartFile image;
}
