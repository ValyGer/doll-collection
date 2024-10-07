package ru.collection.doll_collection.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DollNewDto {
    private Integer id;
    private Integer year;
    private String brand;
    private String ruler;
    private String series;
    private String namePerson;
    private String description;
    private Long price;
}
