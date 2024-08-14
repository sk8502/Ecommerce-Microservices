package com.sk.ecommerce.order;


import com.sk.ecommerce.orderLine.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name="customer_order")
public class OrderEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true,  nullable = false)
    private String reference;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentsMethod;

    private String customerId;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLine;
    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false )
    private LocalDateTime lastModifiedDate;

}
