package pl.mbalcer.announcementsystem.controller;

import org.springframework.web.bind.annotation.*;
import pl.mbalcer.announcementsystem.mail.ContactMail;
import pl.mbalcer.announcementsystem.mail.ContactSender;

@CrossOrigin
@RestController
@RequestMapping("/api/mail")
public class MailController {
    private final ContactSender contactSender;

    public MailController(ContactSender contactSender) {
        this.contactSender = contactSender;
    }

    @PostMapping
    public boolean sendMail(@RequestBody ContactMail contactMail) {
        return this.contactSender.sendEmail(contactMail);
    }
}
