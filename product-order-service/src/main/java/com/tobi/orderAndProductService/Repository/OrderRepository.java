package com.tobi.orderAndProductService.Repository;

import com.tobi.orderAndProductService.Model.Enitity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

}
