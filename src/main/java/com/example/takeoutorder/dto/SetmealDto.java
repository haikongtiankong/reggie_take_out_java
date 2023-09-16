package com.example.takeoutorder.dto;

import com.example.takeoutorder.entity.Setmeal;
import com.example.takeoutorder.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes;
    private String categoryName;
}
