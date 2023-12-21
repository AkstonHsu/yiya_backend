package com.example.yiya_backend_1.service.servicImpl;


import com.example.yiya_backend_1.entity.User;
import com.example.yiya_backend_1.mapper.DoctorInfoMapper;
import com.example.yiya_backend_1.mapper.UserMapper;
import com.example.yiya_backend_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 用户服务实现类
 * @Author: Adrin
 */
@Service
public class UserServiceImpl {
    @Autowired
    UserMapper userMapper;

    @Autowired
    DoctorInfoMapper doctorInfoMapper;
    /**
     * 用户注册服务
     *
     * @param user 用户对象
     * @return 注册结果码，201表示成功，402表示用户名已存在
     */
    public int registerService(User user){
        User userE=userMapper.serchByUname(user.getUname());
        if(userE!=null){
            return 402;
        }else{
            userMapper.insertUser(user);
            return 201;
        }
    }
    /**
     * 用户登录服务
     *
     * @param uname    用户名
     * @param password 密码
     * @return 登录成功返回用户对象，否则返回null
     */
    public User loginService(String uname,String password){
        User user= userMapper.findByUnameAndPassword(uname, password);
        if(user!=null){
            if(user.getRole()==2){
                Long did=doctorInfoMapper.getDidByUid(user.getUid());
                if(did!=null){
                    user.setDid(did);
                }
            }
            user.setPassword("");
        }
        return user;

    }
}
