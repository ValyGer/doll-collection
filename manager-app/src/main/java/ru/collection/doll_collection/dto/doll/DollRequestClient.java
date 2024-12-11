package ru.collection.doll_collection.dto.doll;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DollRequestClient {
    private Integer year;
    private String brand;
    private String ruler;
    private String series;
    private String namePerson;
    private String description;
    private Long price;

    private byte[] myImage;
    private String nameMyImage;
    private byte[] promImage;
    private String namePromImage;
}
