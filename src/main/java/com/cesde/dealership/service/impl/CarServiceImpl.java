package com.cesde.dealership.service.impl;

import com.cesde.dealership.model.Car;
import com.cesde.dealership.repository.CarRepository;
import com.cesde.dealership.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getCarById(Integer id) {
        return carRepository.findById(id);
    }

    @Override
    public Optional<Car> getCarByPlateNumber(String plateNumber) {
        return carRepository.findByPlateNumber(plateNumber);
    }

    @Override
    public Car updateCar(Car car, Car updatedCar) {
        updatedCar.setId(car.getId());
        return carRepository.save(updatedCar);
    }

    @Override
    public void deleteCar(Car car) {
        carRepository.delete(car);
    }
}
