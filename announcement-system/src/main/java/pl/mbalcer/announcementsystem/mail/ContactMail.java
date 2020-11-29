package pl.mbalcer.announcementsystem.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mbalcer.announcementsystem.model.Announcement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactMail {
    private String from;
    private String name;
    private String messageFromUser;
    private Announcement announcement;
}
