package com.web.hanu88.market.service;

import com.web.hanu88.market.model.Order;
import com.web.hanu88.market.model.UserOrder;
import com.web.hanu88.market.repository.OrderRepository;
import com.web.hanu88.market.repository.UserOrderRepository;
import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_profile.model.UserProfile;
import com.web.hanu88.user_profile.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BuyService {
    private final OrderRepository orderRepository;
    private final UserOrderRepository userOrderRepository;
    private final UserProfileRepository userProfileRepository;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Input {
        public long accountId;
        public long orderId;
    }

    public Result<?> purchase(Input input) throws Exception {
        UserProfile userProfile = this.userProfileRepository.findByAccountId(input.accountId);
        Order order = this.orderRepository.findById(input.orderId);
        if (userProfile.getBalancePoint() < order.getPricePoint()) {
            return Result.failed("Not Enough balance to purchase");
        }
        userProfile.decreaseBalance(order.getPricePoint());
        UserOrder userOrder = UserOrder.create(order, input.accountId);
        this.userOrderRepository.save(userOrder);
        return Result.success("Your transaction is successfull with user order id: " + userOrder.getId());
    }
}
