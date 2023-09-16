package com.example.takeoutorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.takeoutorder.entity.Category;

public interface CategoryService extends IService<Category> {

    public void remove(Long id);
}
