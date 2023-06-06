package com.tobi.orderAndProductService.Repository;

import com.tobi.orderAndProductService.Model.Enitity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    @Modifying
    @Query(value = "UPDATE product SET quantity = ?2, product_stock_level = ?3 WHERE name = ?1",
            nativeQuery = true)
    void updateProduct(String name, int quantity, String productStockLevel);
}
