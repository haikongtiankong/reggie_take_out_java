package com.example.takeoutorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeoutorder.dto.DishDto;
import com.example.takeoutorder.entity.Dish;
import com.example.takeoutorder.entity.DishFlavour;
import com.example.takeoutorder.mapper.DishMapper;
import com.example.takeoutorder.service.DishFlavourService;
import com.example.takeoutorder.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService{

    @Autowired
    private DishFlavourService dishFlavourService;

    @Override
    @Transactional
    public void saveWithFlavour(DishDto dishDto) {
        //保存菜品基本信息到表dish
        this.save(dishDto);
        Long dishId = dishDto.getId();
        //保存菜品口味数据
        List<DishFlavour> flavours = dishDto.getFlavours();
        flavours = flavours.stream().map((item) -> {
           item.setDishId(dishId);
           return item;
        }).collect(Collectors.toList());
        dishFlavourService.saveBatch(flavours);
    }

    @Override
    public DishDto getByIdWithFlavour(Long id) {
        Dish dish = this.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);

        LambdaQueryWrapper<DishFlavour> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavour::getDishId, dish.getId());
        List<DishFlavour> flavours = dishFlavourService.list(queryWrapper);
        dishDto.setFlavours(flavours);

        return dishDto;
    }

    @Override
    public void updateWithFlavour(DishDto dishDto) {
        this.updateById(dishDto);

        LambdaQueryWrapper<DishFlavour> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavour::getDishId, dishDto.getId());
        dishFlavourService.remove(queryWrapper);

        List<DishFlavour> flavours = dishDto.getFlavours();
        flavours = flavours.stream().map((item) -> {
           item.setDishId(dishDto.getId());
           return item;
        }).collect(Collectors.toList());

        dishFlavourService.saveBatch(flavours);
    }
}