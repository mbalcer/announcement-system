package pl.mbalcer.announcementsystem.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pl.mbalcer.announcementsystem.model.ContactMail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class ContactMailSender implements ContactSender {
    private JavaMailSender mailSender;

    @Autowired
    public ContactMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean sendEmail(ContactMail contactMail) {
        MimeMessage message = mailSender.createMimeMessage();
        String templateMessage = "Użytkownik <i>" + contactMail.getName() + " (" + contactMail.getFrom() +
                ")</i> przeglądał twoje ogłoszenie <b>" +
                contactMail.getAnnouncement().getTitle() +
                "</b> i napisał do ciebie wiadomość: <br>" +
                "<blockquote>" + contactMail.getMessageFromUser() + "</blockquote>" +
                "<p style='font-size:10px'> Wiadomość została wygenerowana automatycznie. " +
                "Odpowiadając na nią odpowiadasz twórcy wiadomości. </p>";
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setFrom("announcement.page.1@gmail.com");
            mimeMessageHelper.setTo(contactMail.getAnnouncement().getUser().getEmail());
            mimeMessageHelper.setReplyTo(contactMail.getFrom());
            mimeMessageHelper.setSubject("Użytkownik " + contactMail.getName() + " chce się z tobą skontaktować");
            mimeMessageHelper.setText(templateMessage, true);
            this.mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return false;
    }
}
