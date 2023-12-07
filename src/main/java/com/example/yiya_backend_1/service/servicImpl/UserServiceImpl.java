package com.example.yiya_backend_1.service.servicImpl;


import com.example.yiya_backend_1.entity.User;
import com.example.yiya_backend_1.mapper.UserMapper;
import com.example.yiya_backend_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    UserMapper userMapper;

//    @Override
    public int registerService(User user){
        User userE=userMapper.serchByUname(user.getUname());
        if(userE!=null){
            return 402;
        }else{
            userMapper.insertUser(user);
            return 201;
        }
    }

    public User loginService(String uname,String password){
        User user= userMapper.findByUnameAndPassword(uname, password);
        if(user!=null){
            user.setPassword("");
        }
        return user;

    }
}
