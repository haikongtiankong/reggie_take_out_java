package com.example.takeoutorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.takeoutorder.entity.AddressBook;
import com.example.takeoutorder.mapper.AddressBookMapper;
import com.example.takeoutorder.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
