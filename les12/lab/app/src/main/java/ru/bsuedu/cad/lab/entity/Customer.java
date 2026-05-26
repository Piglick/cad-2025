package ru.bsuedu.cad.lab.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {
    @Id
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(Integer customerId, String name, String email, String phone, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}