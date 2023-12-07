package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.controller.SubjectController;
import com.example.yiya_backend_1.entity.SubjectInfo;
import com.example.yiya_backend_1.service.servicImpl.SubjectInfoImpl;
import com.example.yiya_backend_1.utils.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SubjectControllerTest {

    @InjectMocks
    private SubjectController subjectController;

    @Mock
    private SubjectInfoImpl subjectInfoService;

    @Test
    void testGetInfoController() {
        // 设置Mock对象的行为
        long mockUid = 1L;
        SubjectInfo mockSubjectInfo = new SubjectInfo();
        mockSubjectInfo.setUid(mockUid);
        when(subjectInfoService.getSubjectInfo(anyLong())).thenReturn(mockSubjectInfo); // 假设获取信息成功

        // 调用Controller的获取信息方法
        Result<SubjectInfo> result = subjectController.getInfoController(mockUid);

        // 验证行为
        assertNotNull(result);
        assertEquals(mockUid, result.getData().getUid());
        assertEquals("查询被试个人信息成功", result.getMsg());
    }

    @Test
    void testNewInfoController() {
        // 设置Mock对象的行为
        long mockUid = 1L;
        SubjectInfo mockSubjectInfo = new SubjectInfo();
        mockSubjectInfo.setUid(mockUid);
        when(subjectInfoService.insertSubjectInfo(anyLong(), any(SubjectInfo.class))).thenReturn(mockSubjectInfo); // 假设新增信息成功

        // 调用Controller的新增信息方法
        Result<SubjectInfo> result = subjectController.newInfoController(mockUid, mockSubjectInfo);

        // 验证行为
        assertNotNull(result);
        assertEquals(mockUid, result.getData().getUid());
        assertEquals("新增被试个人信息成功", result.getMsg());
    }

    @Test
    void testUpdateInfoController() {
        // 设置Mock对象的行为
        SubjectInfo mockSubjectInfo = new SubjectInfo();
        mockSubjectInfo.setUid(1L);
        when(subjectInfoService.updateSubjectInfo(any(SubjectInfo.class))).thenReturn(mockSubjectInfo); // 假设更新信息成功

        // 调用Controller的更新信息方法
        Result<SubjectInfo> result = subjectController.updateInfoController(mockSubjectInfo);

        // 验证行为
        assertNotNull(result);
        assertEquals(1L, result.getData().getUid());
        assertEquals("更新被试个人信息成功", result.getMsg());
    }
}
