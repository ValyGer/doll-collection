package ru.collection.doll_collection.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dolls")
public class Doll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "year_create")
    private Integer year;
    private String brand;
    private String ruler;
    private String series;
    @Column(name = "name_person")
    private String namePerson;
    private String description;
    private Long price;
    @Column(name = "prom_image")
    private String promImage;
    @Column(name = "my_image")
    private String myImage;
}
