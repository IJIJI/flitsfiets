package synapt.flitsfiets.mailing.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import synapt.flitsfiets.common.dto.user.UserBaseDTO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Service
public class MailingService
{
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${spring.mail.senderMail}")
    private String senderMail;


    public void sendTextEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.senderMail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendEmail(String to, String subject, String htmlContent) throws MessagingException
    {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress(this.senderMail));
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject(subject);
        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }

    public void sendTemplateEmail(String to, String subject, String templatePath,
                                  Map<String, String> placeholders) throws MessagingException, IOException
    {
        File resource = resourceLoader.getResource(
                "classpath:" + templatePath).getFile();
        String body = new String(
                Files.readAllBytes(resource.toPath()));

        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            body = body.replace(entry.getKey(), entry.getValue());
        }

        this.sendEmail(to, subject, body);
    }

    @Async
    public void sendRegistrationConfirmation(UserBaseDTO to, String token) throws MessagingException, IOException
    {

        String url = "https://app.flitsfiets.nl/account/verify";
        String link = UriComponentsBuilder.fromUriString(url)
                .queryParam("token", token)
                .queryParam("user", to.getId())
                .build().toUriString();

        sendTemplateEmail(to.getEmail(), "Welcome to BikeFlash! | Confirm your account",
                "mails/account/registration_confirmation.html",
                Map.of(
                        "${name}", to.getName(),
                        "${token}", token,
                        "${link}", link
                ));
    }
}
