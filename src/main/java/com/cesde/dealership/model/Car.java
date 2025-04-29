package com.cesde.dealership.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Debes digitar el número de placa")
    @Size(max = 10, message = "La placa no puede ser mayor a 10 carácteres")
    @Column(name = "plate_number", nullable = false, length = 10, unique = true)
    private String plateNumber;

    @NotNull(message = "Debes digitar la marca del vehiculo")
    @Size(max = 15, message = "La marca no puede contar mas de 15 carácteres")
    @Column(nullable = false, length = 15)
    private String brand;

    @NotNull(message = "Debes digitar el modelo del vehiculo")
    @Size(max = 15, message = "El modelo no puede contar con más de 15 carácteres")
    @Column(nullable = false, length = 15)
    private String model;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Sale> sales = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", plateNumber='" + plateNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
