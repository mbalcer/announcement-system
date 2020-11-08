package pl.mbalcer.announcementsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mbalcer.announcementsystem.model.Role;
import pl.mbalcer.announcementsystem.model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private Role role;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    public static UserDTO build(User user) {
        return new UserDTO(user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getAddress(),
                user.getPhoneNumber());
    }
}
