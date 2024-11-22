package com.course_project01_rent_a_car.rent_a_car.services;

//package com.example.rentacar.service;

import com.course_project01_rent_a_car.rent_a_car.entities.Car;
import com.course_project01_rent_a_car.rent_a_car.repos.CarRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CarService {

    private JdbcTemplate db;
    private CarRepository carRepository;

    public CarService(JdbcTemplate jdbc, CarRepository carRepository) {
        this.db = jdbc;
        this.carRepository = carRepository;
    }

    public boolean createCar(Car car) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO CAR (model, location, price_per_day, is_deleted)")
                .append(" VALUES ('")
                .append(car.getModel())
                .append("', '")
                .append(car.getLocation())
                .append("', ")
                .append(car.getPricePerDay())
                .append(", false)");

        this.db.execute(query.toString());
        return true;
    }

    public List<Car> getCarsByLocation(String location) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM CAR WHERE location = '")
                .append(location)
                .append("' AND is_deleted = false"); // Променено от 0 на false

        return this.db.query(query.toString(), new RowMapper<Car>() {
            @Override
            public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
                Car car = new Car();
                car.setId(rs.getLong("id"));
                car.setModel(rs.getString("model"));
                car.setLocation(rs.getString("location"));
                car.setPricePerDay(rs.getDouble("price_per_day"));
                car.setDeleted(rs.getBoolean("is_deleted"));
                return car;
            }
        });
    }


    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();  // Извличаме всички коли чрез репозиторията
    }

    public Car updateCar(Long id, Car updatedCar) {
        Car car = getCarById(id);
        car.setModel(updatedCar.getModel());
        car.setLocation(updatedCar.getLocation());
        car.setPricePerDay(updatedCar.getPricePerDay());
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        Car car = getCarById(id);
        car.setDeleted(true);
        carRepository.save(car);
    }
}

