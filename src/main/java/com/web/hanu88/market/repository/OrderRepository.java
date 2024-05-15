package com.web.hanu88.market.repository;

import com.web.hanu88.market.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findById(long id);
}
