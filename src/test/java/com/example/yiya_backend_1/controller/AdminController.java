package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.DoctorInfo;
import com.example.yiya_backend_1.entity.SubjectInfo;
import com.example.yiya_backend_1.service.servicImpl.DoctorInfoImpl;
import com.example.yiya_backend_1.service.servicImpl.SubjectInfoImpl;
import com.example.yiya_backend_1.utils.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private DoctorInfoImpl doctorInfoImpl;

    @Mock
    private SubjectInfoImpl subjectInfoImpl;

    @Test
    void testGetAllDoctorInfoController() {
        // 设置Mock对象的行为
        List<DoctorInfo> mockDoctorInfos = Collections.singletonList(new DoctorInfo());
        when(doctorInfoImpl.getAllDoctors()).thenReturn(mockDoctorInfos);

        // 调用Controller的获取所有医生信息方法
        Result<List<DoctorInfo>> result = adminController.getAllDoctorInfoController();

        // 验证行为
        assertNotNull(result);
        assertEquals(mockDoctorInfos, result.getData());
        assertEquals("查询所有医生成功", result.getMsg());
    }

    @Test
    void testSearchDoctorController() {
        // 设置Mock对象的行为
        List<DoctorInfo> mockDoctorInfos = Collections.singletonList(new DoctorInfo());
        when(doctorInfoImpl.searchDoctors(anyString(), anyString(), anyString())).thenReturn(mockDoctorInfos);

        // 调用Controller的搜索医生信息方法
        Result<List<DoctorInfo>> result = adminController.searchDoctorController("testDoctor", "Male", "Professor");

        // 验证行为
        assertNotNull(result);
        assertEquals(mockDoctorInfos, result.getData());
        assertEquals("搜索医生信息成功", result.getMsg());
    }

    @Test
    void testDeleteDoctorController() {
        // 设置Mock对象的行为
        when(doctorInfoImpl.deleteDoctor(anyLong())).thenReturn(true);

        // 调用Controller的删除医生信息方法
        Result<String> result = adminController.deleteDoctorController(1L);

        // 验证行为
        assertNotNull(result);
        assertNull(result.getData());
        assertEquals("删除医生信息成功", result.getMsg());
    }

    // 类似的方式可以测试其他方法，如 getAllSubjectInfoController、searchSubjectController、deleteSubject 等
}
