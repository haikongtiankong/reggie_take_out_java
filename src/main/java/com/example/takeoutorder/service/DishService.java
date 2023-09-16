package com.example.takeoutorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.takeoutorder.dto.DishDto;
import com.example.takeoutorder.entity.Dish;

public interface DishService extends IService<Dish> {
    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表dish, dish_flavour
    public void saveWithFlavour(DishDto dishDto);

    //根据id查询菜品信息和对应的口味信息
    public DishDto getByIdWithFlavour(Long id);

    //更新菜品信息，同时更新对应的口味信息
    public void updateWithFlavour(DishDto dishDto);
}
