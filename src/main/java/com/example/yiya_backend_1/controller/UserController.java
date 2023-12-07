package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.User;
import com.example.yiya_backend_1.mapper.UserMapper;
import com.example.yiya_backend_1.service.servicImpl.UserServiceImpl;
import com.example.yiya_backend_1.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 用户控制器
 */
@SuppressWarnings({"all"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    UserServiceImpl userServiceImpl;
    /**
     * 用户注册接口
     *
     * @param user 注册用户的信息
     * @return 注册结果
     */

    @PostMapping("/register")
    public Result<User>registController(@RequestBody User user){
        int code=userServiceImpl.registerService(user);
        if(code==402){
            return Result.error("402","用户名已存在");
        }else{
            return Result.success(user,"注册成功");
        }
    }
    /**
     * 用户登录接口
     *
     * @param uname    用户名
     * @param password 密码
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<User>loginController(@RequestParam String uname,@RequestParam String password){
        User user= userServiceImpl.loginService(uname,password);
        if(user!=null){
            return Result.success(user,"登陆成功");
        }
        return Result.error("400","账号或者密码错误");
    }


}
