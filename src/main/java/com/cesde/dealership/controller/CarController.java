package com.cesde.dealership.controller;

import com.cesde.dealership.model.Car;
import com.cesde.dealership.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private ICarService carService;

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCar(){
        List<Car> carList = carService.getCars();

        if(carList.isEmpty()){
            return new ResponseEntity<>(carList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(carList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Integer id){
        Optional<Car> car = carService.getCarById(id);

        return car.map(carFound -> new ResponseEntity<>(carFound, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car){
        Car newCar = carService.createCar(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Car> editCar(@PathVariable Integer id, @Valid @RequestBody Car newData){
        Optional<Car> carFound = carService.getCarById(id);

        if(carFound.isPresent()){
            Car updatedCar = carService.updateCar(carFound.get(), newData);
            return new ResponseEntity<>(updatedCar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Integer id){
        Optional<Car> carFound = carService.getCarById(id);

        if(carFound.isPresent()){
            carService.deleteCar(carFound.get());
            return new ResponseEntity<>("Vehículo eliminado con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Vehículo no encontrado o no valido", HttpStatus.NOT_FOUND);
        }
    }
}
