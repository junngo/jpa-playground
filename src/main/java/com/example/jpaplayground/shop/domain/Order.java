package com.example.jpaplayground.shop.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name  = "orders")
@Entity
public class Order {

    @GeneratedValue
    @Column(name = "order_id")
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // Order(Many) <-> Member(One)
    @JoinColumn(name = "member_id")     // Foreign Key
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // 스프링부트의 'SpringPhysicalNamingStrategy'에 의해서
    //1. 카멜케이스 -> 언더스코어(memberpoint -> member_point)
    //2. .(점) -> _(언더스코어)
    //3. 대문자 -> 소문자 로 변경이된다
    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

    // 연관관계 편의 메서드(양방향으로 묶는 메서드 - 원자성 유지)
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
