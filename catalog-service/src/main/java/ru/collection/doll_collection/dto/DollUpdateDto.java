package ru.collection.doll_collection.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DollUpdateDto {
    private Integer id;
    @NotNull(message = "Нужно ввести год, это обязательное поле")
    @Min(value = 1900, message = "Думаю, у нас нет кукол выпущенных раньше 1900 года))")
    private Integer year;
    @NotBlank(message = "Нужно ввести бренд, это обязательное поле")
    @Size(max = 256, message = "Очень длинное название бренда")
    private String brand;
    @NotBlank(message = "Нужно ввести линейку, это обязательное поле")
    @Size(max = 256, message = "Очень длинное название линейки")
    private String ruler;
    @NotBlank(message = "Нужно ввести серию, это обязательное поле")
    @Size(max = 256, message = "Очень длинное название серии")
    private String series;
    @Size(max = 256, message = "Очень длинное имя")
    private String namePerson;
    @Size(max = 1024, message = "Давай сделаем описание поменьше")
    private String description;
    @Min(value = 0, message = "Стоимость должна быть положительной")
    private Long price;

    private byte[] myImage;
    private String nameMyImage;
    private byte[] promImage;
    private String namePromImage;
}
