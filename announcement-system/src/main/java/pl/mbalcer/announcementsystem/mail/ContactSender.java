package pl.mbalcer.announcementsystem.mail;

import org.springframework.stereotype.Component;

@Component
public interface ContactSender {
    boolean sendEmail(ContactMail contactMail);
}
