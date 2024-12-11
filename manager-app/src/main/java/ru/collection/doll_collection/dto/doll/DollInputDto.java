package ru.collection.doll_collection.dto.doll;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DollInputDto {
    private String year;
    private String brand;
    private String ruler;
    private String series;
    private String namePerson;
    private String description;
    private String price;
    private MultipartFile myImage;
    private MultipartFile promImage;
}
