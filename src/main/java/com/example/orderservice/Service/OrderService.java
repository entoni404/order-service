package com.example.orderservice.Service;

import com.example.orderservice.Manager.OrderManager;
import com.example.orderservice.Model.Order;
import com.example.orderservice.Model.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderService {

    private final OrderManager orderManager;

    public List<Order> getAllOrders() {
        return orderManager.getAllOrders().stream().map(this::mapToOrder).collect(Collectors.toList());
    }

    public List<Order> getOrderByStatus(Order order){
        return orderManager.getOrdersByStatus(mapToOrderEntity(order)).stream().map(this::mapToOrder).collect(Collectors.toList());
    }


    public Order getOrderByOrderNumber(Long orderNumber) {
        return mapToOrder(orderManager.getOrderByOrderNumber(orderNumber));
    }

    public void saveOrder(Order order) {
        orderManager.saveOrder(mapToOrderEntity(order));
    }

    public void deleteOrder(Long orderNumber) {
        orderManager.deleteOrder(orderNumber);
    }

    public void deleteAllOrders(){
        orderManager.deleteAllOrders();
    }

    public void updateOrderStatus(Order order, Long orderNumber) {
        orderManager.updateOrderStatus(mapToOrderEntity(order), orderNumber);
    }

    private OrderEntity mapToOrderEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setSubmit(order.getSubmit());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setDeadline(order.getDeadline());
        return orderEntity;
    }

    private Order mapToOrder(OrderEntity orderEntity) {
        Order order = new Order();
        order.setId(orderEntity.getId());
        order.setSubmit(orderEntity.getSubmit());
        order.setStatus(orderEntity.getStatus());
        order.setDeadline(orderEntity.getDeadline());
        return order;
    }

}
