package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.User;
import com.example.yiya_backend_1.mapper.UserMapper;
import com.example.yiya_backend_1.service.servicImpl.UserServiceImpl;
import com.example.yiya_backend_1.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings({"all"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    UserServiceImpl userServiceImpl;
//    @GetMapping("/query")
//    public List query(String uname){
//        System.out.println("------selectAllUser--------");
//        List<User> list = userMapper.selectList(null);
//        return list;
//    }

    @PostMapping("/register")
    public Result<User>registController(@RequestBody User user){
        int code=userServiceImpl.registerService(user);
        if(code==402){
            return Result.error("402","用户名已存在");
        }else{
            return Result.success(user,"注册成功");
        }
    }
    @PostMapping("/login")
    public Result<User>loginController(@RequestParam String uname,@RequestParam String password){
        User user= userServiceImpl.loginService(uname,password);
        if(user!=null){
            return Result.success(user,"登陆成功");
        }
        return Result.error("400","账号或者密码错误");
    }
}
