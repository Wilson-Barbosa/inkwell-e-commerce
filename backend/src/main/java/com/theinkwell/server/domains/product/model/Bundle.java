package com.theinkwell.server.domains.product.model;

import java.time.Instant;
import java.util.List;

import com.theinkwell.server.domains.user.model.Admin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

@Entity @Table
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Bundle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Instant createdAt;

    @OneToMany(mappedBy = "bundle")
    private List<BundleItem> bundleItems;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Admin createdBy;
    
}
