package com.example.takeoutorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.takeoutorder.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
