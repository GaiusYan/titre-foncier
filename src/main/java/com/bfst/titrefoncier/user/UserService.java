package com.bfst.titrefoncier.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDateCreated(LocalDate.now());
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public List<User> getUserByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public User updateUser(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
             User userToUpdate = userOptional.get();

             if (Objects.nonNull(user.getName()) && Objects.equals(userToUpdate.getName(), user.getName()))
                 userToUpdate.setUsername(user.getUsername());

             if (Objects.nonNull(user.getEmail()) && Objects.equals(userToUpdate.getEmail(), user.getEmail()))
                 userToUpdate.setEmail(user.getEmail());

             if (Objects.nonNull(user.getPassword()) && Objects.equals(userToUpdate.getPassword(), user.getPassword()))
                 userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));

             if (Objects.nonNull(user.getRole()) && Objects.equals(userToUpdate.getRole(), user.getRole()))
                 userToUpdate.setRole(user.getRole());

             if (Objects.nonNull(user.getDateModified()) && Objects.equals(userToUpdate.getDateModified(), user.getDateModified()))
                 userToUpdate.setDateModified(LocalDate.now());

             if (Objects.equals(userToUpdate.isEnabled(), user.isEnabled()))
                 userToUpdate.setEnabled(user.isEnabled());

             userToUpdate.setDateCreated(LocalDate.now());
             return userRepository.save(userToUpdate);
        } else return null;
    }

    public List<User> getUserByOrderByDateCreatedDesc() {
        return this.userRepository.findByOrderByDateCreatedDesc();
    }
}
