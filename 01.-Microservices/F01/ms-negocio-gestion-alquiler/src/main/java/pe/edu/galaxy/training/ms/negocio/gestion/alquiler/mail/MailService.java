package pe.edu.galaxy.training.ms.negocio.gestion.alquiler.mail;

@FunctionalInterface
public interface MailService {
    void sendMail(String toEmail, String subject, String body);
}
