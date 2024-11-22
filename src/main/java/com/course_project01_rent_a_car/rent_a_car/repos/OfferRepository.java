package com.course_project01_rent_a_car.rent_a_car.repos;

//package com.example.rentacar.repository;

import com.course_project01_rent_a_car.rent_a_car.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByUserId(Long userId);
}

