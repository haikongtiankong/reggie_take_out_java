package com.example.takeoutorder.dto;

import com.example.takeoutorder.entity.Dish;
import com.example.takeoutorder.entity.DishFlavour;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {
    private List<DishFlavour> flavors = new ArrayList<>();
    private String categoryName;
    private Integer copies;
}
