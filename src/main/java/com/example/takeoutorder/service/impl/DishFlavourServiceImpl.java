package com.example.takeoutorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeoutorder.entity.DishFlavour;
import com.example.takeoutorder.mapper.DishFlavourMapper;
import com.example.takeoutorder.service.DishFlavourService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavourServiceImpl extends ServiceImpl<DishFlavourMapper, DishFlavour> implements DishFlavourService  {
}
