package com.course_project01_rent_a_car.rent_a_car.repos;

//package com.example.rentacar.repository;

import com.course_project01_rent_a_car.rent_a_car.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByLocationAndIsDeletedFalse(String location);
}

