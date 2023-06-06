package com.tobi.orderAndProductService.Repository;

import com.tobi.orderAndProductService.Model.Enitity.ProductOrdered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderedRepository extends JpaRepository<ProductOrdered,Long>{

}
