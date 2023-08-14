package com.example.orderservice.Controller;

import com.example.orderservice.Model.Order;
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

    @GetMapping("/get/{orderNumber}") public Order getOrderByOrderNumber(@PathVariable Long orderNumber){
        return orderService.getOrderByOrderNumber(orderNumber);
    }

    @GetMapping("/byStatus") public List<Order> getOrderByStatus(@RequestBody Order order){
        return orderService.getOrderByStatus(order);
    }

    @PostMapping("/create") @ResponseStatus(HttpStatus.CREATED) public String saveOrder(@RequestBody Order order){
        orderService.saveOrder(order);
        return "Order Added";
    }

    @DeleteMapping("/delete/{orderNumber}") public String deleteOrder(@PathVariable Long orderNumber){
        orderService.deleteOrder(orderNumber);
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
