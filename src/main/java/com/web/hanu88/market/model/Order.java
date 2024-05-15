package com.web.hanu88.market.model;

import com.web.hanu88.share.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order")
@Getter
@Setter
@NoArgsConstructor
public class Order extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private String name;
    private String description;
    private String url;
    private Effect effect;
    private long pricePoint;
    private Status status;

    public Order(String name, String description, String url, Effect effect, long pricePoint, Status status) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.effect = effect;
        this.pricePoint = pricePoint;
        this.status = status;
    }
}
