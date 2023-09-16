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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

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

            //session.setAttribute(phone, code);
            //将生成的验证码缓存到Redis中，并且设置有效期为5分钟
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

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

        //从Redis中获取缓存验证码
        Object codeInRedis = redisTemplate.opsForValue().get(phone);

        if(codeInRedis != null && codeInRedis.equals(code)) {
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

            //如果用户登录成功则删除验证码
            redisTemplate.delete(phone);

            return R.success(one);
        }
        return R.error("登录失败,验证码有误");
    }
}
