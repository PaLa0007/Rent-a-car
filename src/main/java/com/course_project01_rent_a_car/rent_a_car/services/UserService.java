package com.course_project01_rent_a_car.rent_a_car.services;

import com.course_project01_rent_a_car.rent_a_car.entities.*;
import com.course_project01_rent_a_car.rent_a_car.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Конструктор за инжектиране на UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. Вземане на всички потребители (GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 2. Вземане на потребител по ID (GET)
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    // 3. Създаване на нов потребител (POST)
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // 4. Актуализиране на потребител по ID (PUT)
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Актуализиране на данни в потребителя
        existingUser.setName(updatedUser.getName());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setAge(updatedUser.getAge());
        existingUser.setHasIncidents(updatedUser.isHasIncidents());

        return userRepository.save(existingUser);
    }

    // 5. Софт изтриване на потребител по ID (DELETE)
    public String deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return "User not found";
        }

        User user = userOptional.get();

        if (user.isDeleted()) {
            return "User has already been deleted";
        }

        // Маркиране на потребителя за изтриване (софт изтриване)
        user.setDeleted(true);
        userRepository.save(user);

        return "User with ID " + id + " has been deleted successfully";
    }
}