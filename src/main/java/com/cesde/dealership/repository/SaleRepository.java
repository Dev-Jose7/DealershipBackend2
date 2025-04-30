package com.cesde.dealership.repository;

import com.cesde.dealership.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

}
