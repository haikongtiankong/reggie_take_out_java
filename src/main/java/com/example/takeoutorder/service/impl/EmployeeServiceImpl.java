package com.example.takeoutorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeoutorder.entity.Employee;
import com.example.takeoutorder.mapper.EmployeeMapper;
import com.example.takeoutorder.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
