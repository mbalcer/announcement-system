package pl.mbalcer.announcementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

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

    @ManyToOne
    private Category category;

    @ManyToOne
    private Place place;

    

}
