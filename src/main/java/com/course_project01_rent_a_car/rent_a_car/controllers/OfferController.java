package com.course_project01_rent_a_car.rent_a_car.controllers;

import com.course_project01_rent_a_car.rent_a_car.dtos.*;
import com.course_project01_rent_a_car.rent_a_car.services.*;
import com.course_project01_rent_a_car.rent_a_car.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody OfferRequestDTO offerRequestDTO) {
        Offer offer = offerService.createOffer(offerRequestDTO);
        return new ResponseEntity<>(offer, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Offer>> getOffersByUserId(@PathVariable Long userId) {
        List<Offer> offers = offerService.getOffersByUser(userId);
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long offerId) {
        Offer offer = offerService.getOfferById(offerId);
        return ResponseEntity.ok(offer);
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<String> deleteOffer(@PathVariable Long offerId) {
        offerService.deleteOffer(offerId);
        return ResponseEntity.ok("Offer deleted successfully");
    }

    @PatchMapping("/accept/{offerId}")
    public ResponseEntity<Offer> acceptOffer(@PathVariable Long offerId) {
        Offer acceptedOffer = offerService.acceptOffer(offerId);
        return ResponseEntity.ok(acceptedOffer);
    }

}

