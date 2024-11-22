package com.course_project01_rent_a_car.rent_a_car.services;

import com.course_project01_rent_a_car.rent_a_car.entities.*;
import com.course_project01_rent_a_car.rent_a_car.dtos.*;
import com.course_project01_rent_a_car.rent_a_car.repos.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OfferService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;

    public OfferService(CarRepository carRepository, UserRepository userRepository, OfferRepository offerRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
    }

    public Offer createOffer(OfferRequestDTO offerRequestDTO) {
        User user = userRepository.findById(offerRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Car car = carRepository.findById(offerRequestDTO.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        double price = car.getPricePerDay() * offerRequestDTO.getDays();

        Offer offer = new Offer();
        offer.setUser(user);
        offer.setCar(car);
        offer.setDays(offerRequestDTO.getDays());
        offer.setPrice(price);

        return offerRepository.save(offer);
    }


    public List<Offer> getOffersByUser(Long userId) {
        return offerRepository.findByUserId(userId);
    }

    public Offer getOfferById(Long offerId) {
        return offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
    }

    public void deleteOffer(Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        offerRepository.delete(offer);
    }

    public Offer acceptOffer(Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        offer.setStatus(OfferStatus.ACCEPTED);  // Променяме статуса на офертата
        return offerRepository.save(offer);     // Записваме променената оферта
    }

}