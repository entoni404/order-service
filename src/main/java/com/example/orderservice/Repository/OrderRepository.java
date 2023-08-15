package com.example.orderservice.Repository;

import com.example.orderservice.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    List<OrderEntity> findOrdersByStatus(String status);


}
