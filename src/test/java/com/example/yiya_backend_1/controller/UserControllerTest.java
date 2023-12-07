package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.controller.UserController;
import com.example.yiya_backend_1.entity.User;
import com.example.yiya_backend_1.service.servicImpl.UserServiceImpl;
import com.example.yiya_backend_1.utils.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    @Test
    void testRegisterController() {
        // 设置Mock对象的行为
        User mockUser = new User();
        mockUser.setUname("testUser");
        when(userService.registerService(any(User.class))).thenReturn(200); // 假设注册成功

        // 调用Controller的注册方法
        Result<User> result = userController.registController(mockUser);

        // 验证行为
        assertNotNull(result);
        assertEquals("testUser", result.getData().getUname());
        assertEquals("注册成功", result.getMsg());
    }

    @Test
    void testLoginController() {
        // 设置Mock对象的行为
        User mockUser = new User();
        mockUser.setUname("testUser");
        when(userService.loginService(eq("testUser"), anyString())).thenReturn(mockUser); // 假设登录成功

        // 调用Controller的登录方法
        Result<User> result = userController.loginController("testUser", "password123");

        // 验证行为
        assertNotNull(result);
        assertEquals("testUser", result.getData().getUname());
        assertEquals("登陆成功", result.getMsg());
    }
}
