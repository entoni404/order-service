package com.example.orderservice.Controller;

import com.example.orderservice.Model.Order;
import com.example.orderservice.Model.OrderEntity;
import com.example.orderservice.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
     OrderService orderService;

    @GetMapping("/all") public List<Order> getAllOrder(){
        return orderService.getAllOrders();
    }

    @GetMapping("/get/{orderNumber}") public Order getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/{status}") public List<Order> getOrderByStatus(@PathVariable String status){
        return orderService.getOrderByStatus(status);
    }

    @PostMapping("/create") @ResponseStatus(HttpStatus.CREATED) public String saveOrder(@RequestBody Order order){
        orderService.saveOrder(order);
        return "Order Added";
    }

    @DeleteMapping("/delete/{id}") public String deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return "Order Deleted";
    }

    @DeleteMapping("/deleteAll") public String deleteAllOrders(){
        orderService.deleteAllOrders();
        return "All Orders Deleted";
    }

    @PutMapping("/update/{id}") public String updateOrderStatus(@RequestBody Order order, @PathVariable Long id){
        orderService.updateOrderStatus(order, id);
        return "Order Status Updated";
    }


}
