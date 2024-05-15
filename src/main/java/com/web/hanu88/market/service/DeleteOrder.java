package com.web.hanu88.market.service;

import com.web.hanu88.market.model.Effect;
import com.web.hanu88.market.model.Order;
import com.web.hanu88.market.model.Status;
import com.web.hanu88.market.repository.OrderRepository;
import com.web.hanu88.share.entity.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteOrder {
    private OrderRepository orderRepository;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Input {
        public long id;
    }

    public Result<?> deleteOrder(Input input){
        Order order = orderRepository.findById(input.id);
        if (order == null){
            return Result.failed("This order not found");
        }
        order.setStatus(Status.DELETED);
        orderRepository.save(order);
        return Result.success(null);
    }
}
