package pl.mbalcer.announcementsystem.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.mbalcer.announcementsystem.payload.UserDTO;

import java.util.List;

@Service
public interface UserService {
    List<UserDTO> findAll();

    ResponseEntity<?> findOneById(Long id);

    ResponseEntity<?> findOneByUsername(String username);

    ResponseEntity<?> update(UserDTO user, String username);

    ResponseEntity<?> delete(String username);

    ResponseEntity<?> changeRole(UserDTO userDTO);
}
