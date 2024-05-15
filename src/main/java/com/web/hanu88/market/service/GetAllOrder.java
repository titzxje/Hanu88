package com.web.hanu88.market.service;

import com.web.hanu88.market.model.Order;
import com.web.hanu88.market.repository.OrderRepository;
import com.web.hanu88.share.entity.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllOrder {
    private OrderRepository orderRepository;

    public Result<?> getAllOrder(){
        List<Order> order = orderRepository.findAll();
        return Result.success(order);
    }
}
