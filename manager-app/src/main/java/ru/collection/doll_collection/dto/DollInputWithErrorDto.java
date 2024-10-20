package ru.collection.doll_collection.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DollInputWithErrorDto {
    private Integer id;
    private String year;
    private String brand;
    private String ruler;
    private String series;
    private String namePerson;
    private String description;
    private String price;
    private String promImage;
    private String myImage;
}
