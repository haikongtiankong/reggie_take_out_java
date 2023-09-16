package com.example.takeoutorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.takeoutorder.dto.SetmealDto;
import com.example.takeoutorder.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
