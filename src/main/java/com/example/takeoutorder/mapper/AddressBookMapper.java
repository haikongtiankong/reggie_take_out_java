package com.example.takeoutorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.takeoutorder.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
