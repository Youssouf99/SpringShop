package com.ecommerceapi.ecommerceapi.entites;

import com.ecommerceapi.ecommerceapi.enums.StateCommand;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Date dateCreated;
    private double priceTotal;
    @Enumerated(EnumType.STRING)
    private StateCommand stateCommand;
    @ManyToOne
    private Customer customer;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "product_commands",
            joinColumns = @JoinColumn(name = "commands_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    private List<Product> products = new ArrayList<>();

}

