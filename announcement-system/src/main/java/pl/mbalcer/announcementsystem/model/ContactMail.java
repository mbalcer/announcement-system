package pl.mbalcer.announcementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactMail {
    private String from;
    private String name;
    private String messageFromUser;
    private Announcement announcement;
}
