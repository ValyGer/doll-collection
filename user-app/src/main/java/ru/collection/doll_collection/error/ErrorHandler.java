package ru.collection.doll_collection.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Исключение: данные не найдены
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError notFoundException(final NotFoundException e) {
        StringWriter out = new StringWriter();
        e.printStackTrace(new PrintWriter(out));
        String stackTrace = out.toString();
        return new ApiError(HttpStatus.NOT_FOUND, "Запрашиваемый объект не найден", e.getMessage(),
                List.of(stackTrace), LocalDateTime.now().format(formatter));
    }

    // Исключение: обработка валидации
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError errorInvalidRequestException(InvalidRequestException e) {
        StringWriter out = new StringWriter();
        e.printStackTrace(new PrintWriter(out));
        String stackTrace = out.toString();
        return new ApiError(HttpStatus.BAD_REQUEST, "Отправлен невалидный запрос", e.getMessage(),
                List.of(stackTrace), LocalDateTime.now().format(formatter));
    }

    // Исключение: неизвестная ошибка
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleThrowable(final Throwable e) {
        StringWriter out = new StringWriter();
        e.printStackTrace(new PrintWriter(out));
        String stackTrace = out.toString();
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "Упс.... кажется у нас произошла непредвиденная ошибка.",
                e.getMessage(),
                List.of(stackTrace), LocalDateTime.now().format(formatter));
    }
}
