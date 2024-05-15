package com.web.hanu88.market.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_profile")
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
