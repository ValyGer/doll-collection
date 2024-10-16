package ru.collection.doll_collection.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ApiError {

    private HttpStatus httpStatus;
    private String s;
    private Object message;
    private List<String> stackTrace;
    private String format;
}