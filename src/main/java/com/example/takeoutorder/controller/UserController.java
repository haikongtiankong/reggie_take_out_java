package com.example.takeoutorder.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.takeoutorder.common.R;
import com.example.takeoutorder.entity.User;
import com.example.takeoutorder.service.UserService;
import com.example.takeoutorder.utils.SMSUtils;
import com.example.takeoutorder.utils.ValidateCodeUtils;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        //获取手机号
        String phone = user.getPhone();
        if(StringUtils.isNotEmpty(phone)) {
            //生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            //调用阿里云提供的短信服务API完成发送短信
            //SMSUtils.sendMessage("DZY", "SMS_286330512", phone, code);
            phone="13880812725";
            code="1111";

            //保存验证码到session
            log.info(code);
            session.setAttribute(phone, code);
            return R.success("手机验证码发送成功");
        }
        return R.error("手机验证码发送失败");
    }

    @PostMapping("/login")
    @Transactional
    public R<User> login(@RequestBody Map user, HttpSession session) {
        //获取手机号
        String phone = user.get("phone").toString();
        String code = user.get("code").toString();
        log.info(phone + "  " + code);
        if(session.getAttribute(phone) != null && session.getAttribute(phone).equals(code)) {
            //判断是否为新用户，如果是，自动完成注册
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User one = userService.getOne(queryWrapper);
            if(one == null) {
                one = new User();
                one.setPhone(phone);
                userService.save(one);
            }
            session.setAttribute("user", one.getId());
            return R.success(one);
        }
        return R.error("登录失败,验证码有误");
    }
}
