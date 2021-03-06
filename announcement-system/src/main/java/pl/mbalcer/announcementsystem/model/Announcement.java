package pl.mbalcer.announcementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "announcements")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 6, max = 50)
    private String title;

    @Size(max = 4000)
    private String description;
    private Double price;
    private String photoUrl;
    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToOne
    private Category category;

    @ManyToOne
    private Place place;

    @ManyToOne
    private User user;

    public Announcement(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
