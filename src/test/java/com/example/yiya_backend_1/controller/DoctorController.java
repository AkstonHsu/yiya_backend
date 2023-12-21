package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.DoctorInfo;
import com.example.yiya_backend_1.service.servicImpl.DoctorInfoImpl;
import com.example.yiya_backend_1.utils.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DoctorControllerTest {

    @InjectMocks
    private DoctorController doctorController;

    @Mock
    private DoctorInfoImpl doctorInfoImpl;

    @Test
    void testGetDoctorInfoController() {
        // 设置Mock对象的行为
        long uid = 1L;
        DoctorInfo mockDoctorInfo = new DoctorInfo();
        mockDoctorInfo.setUid(uid);
        when(doctorInfoImpl.getDoctorInfo(uid)).thenReturn(mockDoctorInfo);

        // 调用Controller的获取医生信息方法
        Result<DoctorInfo> result = doctorController.getDoctorInfoController(uid);

        // 验证行为
        assertNotNull(result);
        assertEquals(mockDoctorInfo, result.getData());
        assertEquals("查询医生信息成功", result.getMsg());
    }

    @Test
    void testNewDoctorInfoController() {
        // 设置Mock对象的行为
        DoctorInfo inputDoctorInfo = new DoctorInfo();
        inputDoctorInfo.setUid(1L);
        when(doctorInfoImpl.inserDoctorInfo(any(Long.class), any(DoctorInfo.class))).thenReturn(inputDoctorInfo);

        // 调用Controller的新增医生信息方法
        Result<DoctorInfo> result = doctorController.newDoctorInfoController(inputDoctorInfo);

        // 验证行为
        assertNotNull(result);
        assertEquals(inputDoctorInfo, result.getData());
        assertEquals("新增医生信息成功", result.getMsg());
    }

    @Test
    void testUpdateDoctorInfoController() {
        // 设置Mock对象的行为
        DoctorInfo inputDoctorInfo = new DoctorInfo();
        inputDoctorInfo.setUid(1L);
        when(doctorInfoImpl.updateDoctorInfo(any(Long.class), any(DoctorInfo.class))).thenReturn(inputDoctorInfo);

        // 调用Controller的更新医生信息方法
        Result<DoctorInfo> result = doctorController.updateDoctorInfoController(inputDoctorInfo);

        // 验证行为
        assertNotNull(result);
        assertEquals(inputDoctorInfo, result.getData());
        assertEquals("更新医生信息成功", result.getMsg());
    }
}
