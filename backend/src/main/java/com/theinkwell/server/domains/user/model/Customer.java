package com.theinkwell.server.domains.user.model;

import java.time.LocalDate;
import java.util.List;

import com.theinkwell.server.domains.cart.model.ShoppingCart;
import com.theinkwell.server.domains.product.model.Review;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Table
@Inheritance(strategy = InheritanceType.JOINED)
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
@ToString(callSuper = true)
public class Customer extends Person {
    
    private String firstName;
    private String lastName;
    private LocalDate dob;

    @OneToMany(mappedBy = "author")
    private List<Review> reviews;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private ShoppingCart shoppingCart;
}
