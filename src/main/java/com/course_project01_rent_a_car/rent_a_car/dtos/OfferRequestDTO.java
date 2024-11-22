package com.course_project01_rent_a_car.rent_a_car.dtos;

//package com.example.rentacar.dto;

public class OfferRequestDTO {

    private Long userId;
    private Long carId;
    private int days;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}

