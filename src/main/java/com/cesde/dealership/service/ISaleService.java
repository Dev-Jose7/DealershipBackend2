package com.cesde.dealership.service;

import com.cesde.dealership.model.Sale;

import java.util.List;
import java.util.Optional;

public interface ISaleService {
    Sale createSale(Sale sale);
    List<Sale> getSales();
    Optional<Sale> getSaleById(Integer id);
    Sale updateSale(Sale sale, Sale updatedSale);
    void deleteSale(Sale sale);
}
