package com.theinkwell.server.domains.product.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.theinkwell.server.domains.cart.model.ProductCart;
import com.theinkwell.server.domains.order.model.OrderItem;
import com.theinkwell.server.domains.user.model.Admin;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Product")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public abstract class Product implements Serializable {
    
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    private String title;
    private String description;
    private List<String> imagesList;
    private Integer quantity;
    private BigDecimal price;
    private Double discount;
    private Instant createdAt;
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin createdBy;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @OneToMany(mappedBy = "product")
    private List<ProductCategory> productCategories;

    @OneToMany(mappedBy = "product")
    private List<ProductCart> productCarts;
    
    @OneToMany(mappedBy = "product")
    private List<BundleItem> bundleItems;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

}
