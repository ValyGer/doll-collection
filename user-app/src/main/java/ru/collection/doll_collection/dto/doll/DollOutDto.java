package ru.collection.doll_collection.dto.doll;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DollOutDto {
    private Integer id;
    private Integer year;
    private String brand;
    private String ruler;
    private String series;
    private String namePerson;
    private String description;
    private String myImage;
    private String promImage;
}
