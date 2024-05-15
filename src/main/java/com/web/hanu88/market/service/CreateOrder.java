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

@Service
@AllArgsConstructor
public class CreateOrder {
    private OrderRepository orderRepository;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Input {
        public String name;
        public String description;
        public String url;
        public long pricePoint;
        public Effect effect;
        public long totalEffectTime;
    }
    public Result<?> createOrder(Input input){
        Order newOrder = new Order(input.name,input.description,input.url,input.effect,input.pricePoint,Status.CREATED, input.totalEffectTime);
        orderRepository.save(newOrder);
        return Result.success(null);
    }

}
