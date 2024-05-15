package com.web.hanu88.market.controller;

import com.web.hanu88.market.model.Effect;
import com.web.hanu88.market.model.Status;
import com.web.hanu88.market.service.CreateOrder;
import com.web.hanu88.market.service.DeleteOrder;
import com.web.hanu88.market.service.GetAllOrder;
import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_auth.model.Role;
import com.web.hanu88.user_auth.model.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
public class OrderController {
    @Autowired
    private CreateOrder createOrder;
    @Autowired
    private DeleteOrder deleteOrder;
    @Autowired
    private GetAllOrder getAllOrder;
    @Autowired
    private Environment env;
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

    @PostMapping(value = "/createOrder")
    public Result<?> createOrder(@RequestHeader("access-token") String accessToken, @RequestBody Input input){
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, env.getProperty("auth.secret"));
        if (Objects.equals(userData.get("role").toString(), String.valueOf(Role.ADMIN))){
            CreateOrder.Input input1 = new CreateOrder.Input(input.name,input.description,input.url,input.pricePoint,input.effect, input.totalEffectTime);
            return createOrder.createOrder(input1);
        }
        return Result.failed("Only admin can call this api");
    }
    @DeleteMapping(value = "/deleteOrder")
    public Result<?> deleteOrder(@RequestHeader("access-token") String accessToken, @RequestBody DeleteOrder.Input input) {
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, env.getProperty("auth.secret"));
        if (Objects.equals(userData.get("role").toString(), String.valueOf(Role.ADMIN))) {
            return deleteOrder.deleteOrder(input);
        }
        return Result.failed("Only admin can call this api");
    }
    @GetMapping(value = "/getAllOrder")
    public Result<?> getAllOrder(){
        return getAllOrder.getAllOrder();
    }

}
