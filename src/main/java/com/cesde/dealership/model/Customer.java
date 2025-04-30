package com.cesde.dealership.model;

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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
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

    public String email(){
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}