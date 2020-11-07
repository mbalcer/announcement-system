package pl.mbalcer.announcementsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mbalcer.announcementsystem.model.ERole;
import pl.mbalcer.announcementsystem.model.Role;
import pl.mbalcer.announcementsystem.model.User;
import pl.mbalcer.announcementsystem.repository.RoleRepository;
import pl.mbalcer.announcementsystem.repository.UserRepository;

import javax.annotation.PostConstruct;

@Service
public class InitService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public InitService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        Role userRole = new Role(0l, ERole.ROLE_USER);
        Role adminRole = new Role(0l, ERole.ROLE_ADMIN);
        userRole = roleRepository.save(userRole);
        adminRole = roleRepository.save(adminRole);

        User admin = new User(0l, "admin", "admin@admin.pl", passwordEncoder.encode("admin"), true, adminRole);
        User user = new User(0l, "mateusz", "mateusz@wp.pl", passwordEncoder.encode("mati123"), true, userRole);

        admin = userRepository.save(admin);
        user = userRepository.save(user);
    }
}
