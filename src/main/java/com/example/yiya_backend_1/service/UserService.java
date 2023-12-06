package com.example.yiya_backend_1.service;
import  com.example.yiya_backend_1.entity.User;

/**
 * @author aaa
 */
public interface UserService {
    public int loginService(String uname,String password);
    public int registerService(User user);
}
