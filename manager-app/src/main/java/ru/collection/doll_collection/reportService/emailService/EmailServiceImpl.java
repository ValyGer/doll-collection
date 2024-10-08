package ru.collection.doll_collection.reportService.emailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
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
        File file = new File("./manager-app/src/main/resources/data/data_db.csv");
        mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getName()), file);
        log.info("Отправляемый файл добавлен в письмо успешно. Имя файла: {}", file.getName());

        mailSender.send(message);
        log.info("Письмо успешно отправлено!");
    }
}
