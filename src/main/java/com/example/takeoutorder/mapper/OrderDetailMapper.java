package com.example.takeoutorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.takeoutorder.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
