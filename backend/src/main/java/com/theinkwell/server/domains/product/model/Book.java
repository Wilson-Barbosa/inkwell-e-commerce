package com.theinkwell.server.domains.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
public class Book extends Product{
    
    private String author;
    private Integer numberOfPages;
    private String isbn;
    private String publisher;
    private Integer edition;

}
