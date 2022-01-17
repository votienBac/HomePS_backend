package com.example.HomePS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class OrderService {
    @Id
    @JsonIgnore
    private OrderServicePK pk;
    @Column(nullable = false)
    private Integer quantity;

    private Double totalPrice;

    public OrderService(Bill bill, ExtraService extraService, Integer quantity) {
        pk = new OrderServicePK();
        pk.setBill(bill);
        pk.setExtraService(extraService);
        this.quantity = quantity;
        totalPrice = extraService.getPrice() * quantity;
    }

    @Transient
    public ExtraService getService(){
        return this.pk.getExtraService();
    }

    @Transient
    public Double getTotalPrice(){
        return totalPrice;
    }
}
