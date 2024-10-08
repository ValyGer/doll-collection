package ru.collection.doll_collection.reportService.emailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendEmailWithFile() throws MessagingException {
        String recipient = "valentin.gerasimov.data@mail.ru";
        log.info("Получение адресата письма.");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        // заполняем атрибуты письма
        mimeMessageHelper.setFrom("valentin.gerasimov.data@mail.ru");
        mimeMessageHelper.setTo(recipient);
        mimeMessageHelper.setSubject("Data from app DOLL Collection");
        mimeMessageHelper.setText("Выгрузка обновленных данных БД");
        log.info("Сгенерированы атрибуты письма.");

        // добавляем файл
        ClassPathResource classPathResource = new ClassPathResource("./manager-app/src/main/resources/data/data_db.csv");
        mimeMessageHelper.addAttachment(Objects.requireNonNull(classPathResource.getFilename()), classPathResource);
        log.info("Отправляемый файл добавлен в письмо успешно. Имя файла: {}", classPathResource.getFilename());

        mailSender.send(message);
        log.info("Письмо успешно отправлено!");
    }
}
