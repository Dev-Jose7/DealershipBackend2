package com.cesde.dealership.service.impl;

import com.cesde.dealership.model.Sale;
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

    @Override
    public Sale createSale(Sale sale) {
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
        updatedSale.setId(sale.getId());
        return saleRepository.save(updatedSale);
    }

    @Override
    public void deleteSale(Sale sale) {
        saleRepository.delete(sale);
    }
}
