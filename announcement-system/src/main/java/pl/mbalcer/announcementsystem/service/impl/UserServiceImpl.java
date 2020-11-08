package pl.mbalcer.announcementsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.mbalcer.announcementsystem.model.User;
import pl.mbalcer.announcementsystem.payload.UserDTO;
import pl.mbalcer.announcementsystem.payload.response.MessageResponse;
import pl.mbalcer.announcementsystem.repository.UserRepository;
import pl.mbalcer.announcementsystem.security.service.UserDetailsImpl;
import pl.mbalcer.announcementsystem.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        log.info("Request to get all users");
        return userRepository.findAll().stream().map(user -> UserDTO.build(user)).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> findOneById(Long id) {
        log.info("Request to get user by id: " + id);
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent())
            return ResponseEntity.ok(UserDTO.build(userOptional.get()));
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> findOneByUsername(String username) {
        log.info("Request to get user by username: " + username);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent())
            return ResponseEntity.ok(UserDTO.build(userOptional.get()));
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> update(UserDTO userRequest, Long id) {
        log.info("Request to update user: " + userRequest);
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            return ResponseEntity.notFound().build();
        User user = userOptional.get();
        if(!userDetails.getUsername().equals(user.getUsername()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("Error: You can't update another user"));
        if (userRequest.getEmail() != null) user.setEmail(userRequest.getEmail());
        if (userRequest.getFirstName() != null) user.setFirstName(userRequest.getFirstName());
        if (userRequest.getLastName() != null) user.setLastName(userRequest.getLastName());
        if (userRequest.getAddress() != null) user.setAddress(userRequest.getAddress());
        if (userRequest.getPhoneNumber() != null) user.setPhoneNumber(userRequest.getPhoneNumber());

        user = userRepository.save(user);
        return ResponseEntity.ok(UserDTO.build(user));
    }
}
