package com.example.takeoutorder.controller;

import com.example.takeoutorder.entity.Orders;
import com.example.takeoutorder.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.takeoutorder.common.R;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    OrdersService ordersService;

    @PostMapping("/submit")
    public R<String>  submit(@RequestBody Orders orders) {
        ordersService.submit(orders);
        return R.success("下单成功");
    }
}
