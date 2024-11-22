package com.course_project01_rent_a_car.rent_a_car.repos;

//package com.example.rentacar.repository;

import com.course_project01_rent_a_car.rent_a_car.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

