package com.web.hanu88.market.repository;

import com.web.hanu88.market.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
    List<UserOrder> findAllByAccountId(long accountId);
}
