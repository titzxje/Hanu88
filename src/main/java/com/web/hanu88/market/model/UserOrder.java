package com.web.hanu88.market.model;

import com.web.hanu88.share.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "user_order")
@NoArgsConstructor
@Getter
@Setter
public class UserOrder extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long accountId;
    private long orderId;
    private Date startAt;
    private Date expiredAt;

    public static UserOrder create(Order order, long accountId) {
        Date currentDate = new Date();
        Date expiredAt = new Date(currentDate.getTime() + (order.getTotalEffectTime() * 60 * 60 * 1000));
        UserOrder userOrder = new UserOrder();
        userOrder.accountId = accountId;
        userOrder.orderId = order.getId();
        userOrder.startAt = currentDate;
        userOrder.expiredAt = expiredAt;
        return userOrder;
    }
}
