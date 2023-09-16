package com.example.takeoutorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeoutorder.entity.OrderDetail;
import com.example.takeoutorder.mapper.OrderDetailMapper;
import com.example.takeoutorder.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
