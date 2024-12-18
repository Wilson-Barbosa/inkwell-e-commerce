package com.theinkwell.server.domains.user.model;

import java.util.List;

import com.theinkwell.server.domains.product.model.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Table
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor 
@ToString(callSuper = true)
public class Admin extends Person{
    
    @OneToMany(mappedBy = "createdBy")
    private List<Product> createdProducts;

}
