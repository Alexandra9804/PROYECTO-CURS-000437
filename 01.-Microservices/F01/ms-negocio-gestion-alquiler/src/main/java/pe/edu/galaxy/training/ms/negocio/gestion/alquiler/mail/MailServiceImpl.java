package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.notification.username}")
    private String mailFromUserName;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    public void sendMail(String toEmail, String subject, String body) {
        try {
            log.info("Enviando notificación a {}", toEmail);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper =
                    new MimeMessageHelper(message, StandardCharsets.UTF_8.toString());

            messageHelper.setSubject(subject);
            messageHelper.setText(body, true);
            messageHelper.setFrom(mailFromUserName);
            messageHelper.setTo(toEmail);

            mailSender.send(message);

            log.info("Mail enviado exitosamente");

        } catch (MessagingException ex) {
            log.error("Error al enviar notificación a {}", toEmail, ex);
        }
    }
}
