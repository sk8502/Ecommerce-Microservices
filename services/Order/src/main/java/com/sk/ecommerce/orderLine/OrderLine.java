package com.sk.ecommerce.orderLine;

import com.fasterxml.jackson.databind.node.DoubleNode;
import com.sk.ecommerce.order.OrderEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderEntity order;

    private Integer productId;
    private Double quantity;


}
