package com.cesde.dealership.service;

import com.cesde.dealership.model.Car;

import java.util.List;
import java.util.Optional;

public interface ICarService {
    Car createCar(Car car);
    List<Car> getCars();
    Optional<Car> getCarById(Integer id);
    Optional<Car> getCarByPlateNumber(String plateNumber);
    Car updateCar(Car car, Car updatedCar);
    void deleteCar(Car car);
}
