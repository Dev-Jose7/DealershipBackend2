package com.cesde.dealership.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Debes digitar tu nombre completo")
    @Size(max = 50, message = "El nombre no puede contener más de 50 carácteres")
    @Column(nullable = false, length = 50)
    private String fullname;

    @NotNull(message = "Debes digitar un correo electrónico")
    @Size(max = 50, message = "El correo no puede ser mayor de 50 caracteres")
    @Email(message = "Digite un correo valido")
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @NotNull(message = "Debes digitar una contraseña")
    @Size(max = 50, message = "La contraseña no puede contar con mas de 50 carácteres")
    @Column(nullable = false, length = 50)
    private String password;

    // Relación uno a muchos: un carro puede estar asociado a muchas ventas.
    // 'mappedBy = "customer"' indica que la propiedad 'customer' en la entidad Sale es la que gestiona la relación.
    // 'cascade = CascadeType.ALL' permite que al eliminar un cliente también se eliminen sus ventas asociadas.
    // 'fetch = FetchType.EAGER' fuerza a que se carguen las ventas inmediatamente al consultar el cliente.
    // @JsonManagedReference indica el lado que será serializado en JSON, evita ciclos infinitos.
    // Es decir, sales se encontrará en el JSON de la respuesta cuando se consulte un registro de la tabla Customer
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("customer-sales")
    private List<Sale> sales = new ArrayList<>();

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getFullname(){
        return this.fullname;
    }

    public void setFullname(String fullname){
        this.fullname = fullname;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sales=" + sales +
                '}';
    }
}