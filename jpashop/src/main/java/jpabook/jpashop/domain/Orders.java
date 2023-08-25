package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //멤버와 오더는 다대일
    // 연관관계의 주인은 외래키를 갖고있는 테이블로 하자!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orders")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //(ORDER, CANCEL)

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItems(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrders(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrders(this);
    }


}