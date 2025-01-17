package com.theinkwell.server.domains.user.model;

import java.util.List;

import com.theinkwell.server.domains.product.model.Bundle;
import com.theinkwell.server.domains.product.model.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Admin")
@PrimaryKeyJoinColumn(name = "person_id")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Admin extends Person{

    private String name;
    
    @OneToMany(mappedBy = "createdBy")
    private List<Product> createdProducts;

    @OneToMany(mappedBy = "createdBy")
    private List<Bundle> createdBundles;

}
