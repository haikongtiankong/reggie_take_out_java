package com.example.takeoutorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.takeoutorder.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
