package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.AnswerRecord;
import com.example.yiya_backend_1.entity.CompletePaper;
import com.example.yiya_backend_1.entity.Paper;
import com.example.yiya_backend_1.service.servicImpl.AnswerRecordImpl;
import com.example.yiya_backend_1.service.servicImpl.PaperImpl;
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
class PaperControllerTest {

    @InjectMocks
    private PaperController paperController;

    @Mock
    private PaperImpl paperImpl;

    @Mock
    private AnswerRecordImpl answerRecordImpl;

    @Test
    void testGetPaperController() {
        // 设置Mock对象的行为
        CompletePaper mockCompletePaper = new CompletePaper();
        when(paperImpl.getCompletePaperById(anyLong())).thenReturn(mockCompletePaper);

        // 调用Controller的获取试卷方法
        Result<CompletePaper> result = paperController.getPaperController(1L);

        // 验证行为
        assertNotNull(result);
        assertEquals(mockCompletePaper, result.getData());
        assertEquals("获取试卷成功", result.getMsg());
    }

    @Test
    void testGetCorrectCntController() {
        // 设置Mock对象的行为
        when(answerRecordImpl.getCorrectCntByUidAndPid(anyLong(), anyLong())).thenReturn(5);

        // 调用Controller的获取用户最近作答的试卷的正确个数方法
        Result<Integer> result = paperController.getCorrectCntController(1L, 2L);

        // 验证行为
        assertNotNull(result);
        assertEquals(5, result.getData());
        assertEquals("获取测试信息成功", result.getMsg());
    }

    @Test
    void testGetAllPapersController() {
        // 设置Mock对象的行为
        List<Paper> mockPapers = Collections.singletonList(new Paper());
        when(paperImpl.getAllPapers()).thenReturn(mockPapers);

        // 调用Controller的查询所有试卷方法
        Result<List<Paper>> result = paperController.getAllPapersController();

        // 验证行为
        assertNotNull(result);
        assertEquals(mockPapers, result.getData());
        assertEquals("查询所有试卷成功", result.getMsg());
    }

    // 类似的方式可以测试其他方法，如 submitAnswerController、getAllPaperByUid、newPaperController 等
}
