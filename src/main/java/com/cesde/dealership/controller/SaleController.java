package com.cesde.dealership.controller;

import com.cesde.dealership.model.Car;
import com.cesde.dealership.model.Customer;
import com.cesde.dealership.model.Sale;
import com.cesde.dealership.service.ICarService;
import com.cesde.dealership.service.ICustomerService;
import com.cesde.dealership.service.ISaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @Autowired
    private ICarService carService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<Sale>> allSale(){
        List<Sale> saleList = saleService.getSales();

        if(saleList.isEmpty()){
            return new ResponseEntity<>(saleList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(saleList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Integer id){
        Optional<Sale> sale = saleService.getSaleById(id);

        return sale.map(saleFound -> new ResponseEntity<>(saleFound, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSale(@Valid @RequestBody Sale sale){
        Sale newSale = saleService.createSale(sale);
        Optional<Car> carFound = carService.getCarByPlateNumber(sale.getCar().getPlateNumber());
        Optional<Customer> customerFound = customerService.getCustomerById(sale.getCustomer().getId());

        if(carFound.isPresent() && customerFound.isPresent()){
            return new ResponseEntity<>(newSale, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Cliente y/o Carro no encontrados", HttpStatus.NOT_FOUND);
        }


    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Sale> editSale (@PathVariable Integer id, @Valid @RequestBody Sale newData){
        Optional<Sale> saleFound = saleService.getSaleById(id);

        if(saleFound.isPresent()){
            Sale updatedSale = saleService.updateSale(saleFound.get(), newData);
            return new ResponseEntity<>(updatedSale, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSale (@PathVariable Integer id){
        Optional<Sale> sale = saleService.getSaleById(id);

        if(sale.isPresent()){
            saleService.deleteSale(sale.get());
            return new ResponseEntity<>("Cliente eliminado con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado o no valido", HttpStatus.NOT_FOUND);
        }
    }
}
