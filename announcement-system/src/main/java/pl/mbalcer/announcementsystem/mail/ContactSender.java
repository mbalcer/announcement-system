package pl.mbalcer.announcementsystem.mail;

import org.springframework.stereotype.Component;
import pl.mbalcer.announcementsystem.model.ContactMail;

@Component
public interface ContactSender {
    boolean sendEmail(ContactMail contactMail);
}
