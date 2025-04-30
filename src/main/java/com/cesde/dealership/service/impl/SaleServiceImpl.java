package com.cesde.dealership.service.impl;

import com.cesde.dealership.model.Car;
import com.cesde.dealership.model.Sale;
import com.cesde.dealership.repository.CarRepository;
import com.cesde.dealership.repository.SaleRepository;
import com.cesde.dealership.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements ISaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public Sale createSale(Sale sale) {
        Optional<Car> car = carRepository.findByPlateNumber(sale.getCar().getPlateNumber());
        sale.setCar(car.get());
        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    @Override
    public Optional<Sale> getSaleById(Integer id) {
        return saleRepository.findById(id);
    }

    @Override
    public Sale updateSale(Sale sale, Sale updatedSale) {
        Optional<Car> car = carRepository.findByPlateNumber(sale.getCar().getPlateNumber());
        updatedSale.setCar(car.get());
        updatedSale.setId(sale.getId());
        return saleRepository.save(updatedSale);
    }

    @Override
    public void deleteSale(Sale sale) {
        saleRepository.delete(sale);
    }
}
