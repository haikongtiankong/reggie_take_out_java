package com.example.takeoutorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeoutorder.common.CustomException;
import com.example.takeoutorder.entity.Category;
import com.example.takeoutorder.entity.Dish;
import com.example.takeoutorder.entity.Setmeal;
import com.example.takeoutorder.mapper.CategoryMapper;
import com.example.takeoutorder.service.CategoryService;
import com.example.takeoutorder.service.DishService;
import com.example.takeoutorder.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    DishService dishService;

    @Autowired
    SetmealService setmealService;

    /**
     * 根据id删除分类，删除之前需要进行判断该类别是否关联菜品
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper<Dish>();
        LambdaQueryWrapper<Setmeal> setmealQueryWrapper = new LambdaQueryWrapper<Setmeal>();
        //查询当前分类是否关联菜品，如果关联，抛出一个业务异常
        dishQueryWrapper.eq(Dish::getCategoryId, id);
        long count = dishService.count(dishQueryWrapper);
        if(count > 0) {
            //抛出异常
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }
        //查询当前分类是否关联套餐，如果关联，抛出一个业务异常
        setmealQueryWrapper.eq(Setmeal::getCategoryId, id);
        long count2 = setmealService.count(setmealQueryWrapper);
        if(count2 > 0) {
            //抛出异常
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }
        //正常删除分类
        super.removeById(id);
    }
}
