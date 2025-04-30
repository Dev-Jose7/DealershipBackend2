package com.cesde.dealership.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Debes indicar un tipo de venta")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeSale type;

    @NotNull(message = "Debes digitar una fecha")
    @Column(name = "date_sale", nullable = false)
    private LocalDate dateSale;

    @NotNull(message = "Debes digitar el valor de la venta")
    @Min(1)
    @Column(nullable = false)
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_plate_number", referencedColumnName = "plate_number", nullable = false) // "plate_number" indica que se hace referencia a una columna que no es la primaria (id) de Car.
    private Car car;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeSale getType() {
        return type;
    }

    public void setType(TypeSale type) {
        this.type = type;
    }

    public LocalDate getDateSale() {
        return dateSale;
    }

    public void setDateSale(LocalDate dateSale) {
        this.dateSale = dateSale;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", type=" + type +
                ", dateSale=" + dateSale +
                ", cost=" + cost +
                ", customer=" + customer +
                ", car=" + car +
                '}';
    }
}
