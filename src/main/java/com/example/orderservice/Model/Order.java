package com.example.orderservice.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter

public class Order {

    private Long id;
    private LocalDate submit;
    private String status;
    private LocalDate deadline;

    // Comment out when the Items service is created and than use restTemplate
    //private Item items;
}
