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

        order.setStatus("Awaiting_Approval");
        orderRepository.save(order);
    }

    // GET ALL ORDERS
    public List<OrderEntity> getAllOrders(){
        return orderRepository.findAll();
    }


            // FILTERING BY STATUS - Newest Submitted Day
    public List<OrderEntity> getOrdersByStatus(String status){
                 return orderRepository.findOrdersByStatus(status);
    }


    // GET ORDER BY ORDER NUMBER - ALL INFO GIVEN
    public OrderEntity getOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("The order does not exist"));
    }

    //DELETE ORDER by id
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }


    // Delete all orders
    public void deleteAllOrders(){
        orderRepository.deleteAll();
    }


    // UPDATE ORDER STATUS
    public void updateOrderStatus(OrderEntity order, Long id){
        OrderEntity currentOrder = getOrderById(id);
        currentOrder.setStatus(order.getStatus());
        orderRepository.save(currentOrder);
    }

}
