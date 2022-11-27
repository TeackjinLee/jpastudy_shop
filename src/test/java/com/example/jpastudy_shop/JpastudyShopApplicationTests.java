package com.example.jpastudy_shop;

import com.example.jpastudy_shop.datasource.Item;
import com.example.jpastudy_shop.datasource.Member;
import com.example.jpastudy_shop.datasource.OrderItem;
import com.example.jpastudy_shop.datasource.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static com.example.jpastudy_shop.datasource.OrderStatus.*;
import static org.assertj.core.util.DateUtil.now;

@SpringBootTest
class JpastudyShopApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpastudy_shop");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득
        tx.begin();
        Member member1 = new Member(null,"세종시","이름","새롬남로","123123");
        em.persist(member1);

        Orders orders1 = new Orders(null, now(), ORDER);
        orders1.setMember(member1);
        em.persist(orders1);

        Orders orders2 = new Orders(null, now(), ORDER);
        orders2.setMember(member1);
        em.persist(orders2);

        Item item1 = new Item(null, "java의정석", 32000, 32);
        em.persist(item1);

        Item item2 = new Item(null, "군주론", 12000, 30);
        em.persist(item2);

        OrderItem orderItem1 = new OrderItem(null, 2, 50000);
        orderItem1.setItem(item1);
        orderItem1.setOrder(orders2);
        em.persist(orderItem1);

        OrderItem orderItem2 = new OrderItem(null, 3, 90000);
        orderItem2.setItem(item2);
        orderItem2.setOrder(orders1);
        em.persist(orderItem2);

        tx.commit();
    }

}
