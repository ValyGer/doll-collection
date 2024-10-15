package ru.collection.doll_collection.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DollInputDto {
    private Integer year;
    private String brand;
    private String ruler;
    private String series;
    private String namePerson;
    private String description;
    private Long price;
    private MultipartFile promImage;
    private MultipartFile myImage;
}
