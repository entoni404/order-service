package com.example.orderservice.Manager;

import com.example.orderservice.Model.Order;
import com.example.orderservice.Model.OrderEntity;
import com.example.orderservice.Repository.OrderRepository;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor

public class OrderManager {

    private final OrderRepository orderRepository;


    // CREATE AN ORDER
    public void saveOrder(OrderEntity order){
        orderRepository.save(order);
    }

    // GET ALL ORDERS
    public List<OrderEntity> getAllOrders(){
        return orderRepository.findAll();
    }


            // FILTERING BY STATUS - Newest Submitted Day
    public List<OrderEntity> getOrdersByStatus(OrderEntity order){
        List<OrderEntity> targetOrders = null;
        for(int i = 0; i < getAllOrders().size(); i++){
            // the status approved can be replaced with a variable which can than return orders by status
            if(order.getStatus()=="Approved"){
                targetOrders.add(order);
            }
        }

        // filtering order by submission date
        Collections.sort(targetOrders, new Comparator<OrderEntity>() {
            @Override
            public int compare(OrderEntity order1, OrderEntity order2) {
                return order2.getSubmit().compareTo(order1.getSubmit());
            }
        });

           return targetOrders;
    }


    // GET ORDER BY ORDER NUMBER - ALL INFO GIVEN
    public OrderEntity getOrderByOrderNumber(Long orderNumber){
        return orderRepository.findById(orderNumber)
                .orElseThrow(() -> new NoSuchElementException("The order does not exist"));
    }

    //DELETE ORDER by id
    public void deleteOrder(Long orderNumber){
        orderRepository.deleteById(orderNumber);
    }


    // Delete all orders
    public void deleteAllOrders(){
        orderRepository.deleteAll();
    }


    // UPDATE ORDER STATUS
    public void updateOrderStatus(OrderEntity order, Long orderNumber){
        OrderEntity currentOrder = getOrderByOrderNumber(orderNumber);
        currentOrder.setStatus(order.getStatus());
        orderRepository.save(currentOrder);
    }

}
