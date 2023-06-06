package com.tobi.orderAndProductService.Model.Enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrdered {
    @jakarta.persistence.Id
    @SequenceGenerator(name = "productOrdered_sequence"
            ,sequenceName = "productOrdered_sequence"
            ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    private String productName;

    private int quantity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;

}
