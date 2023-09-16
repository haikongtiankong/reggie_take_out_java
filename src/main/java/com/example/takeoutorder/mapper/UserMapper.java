package com.example.takeoutorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.takeoutorder.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
