package ru.collection.doll_collection.dto.user;


import lombok.*;
import ru.collection.doll_collection.constant.Role;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDtoWithRole {
    private long id;
    private String userEmail;
    private String userName;
    private Role userRole;
    private String image;
}
