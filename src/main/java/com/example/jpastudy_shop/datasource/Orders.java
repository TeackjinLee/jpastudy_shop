package com.example.jpastudy_shop.datasource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;  // 주문회원

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems =
            new ArrayList<OrderItem>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태

    //생성자
    public Orders(Long id, Date orderDate, OrderStatus status){
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
    }

    //==연관관계 메소드==//
    public void setMember(Member member){
        //기존관계 제거
        if(this.member != null){
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // Getter, Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

