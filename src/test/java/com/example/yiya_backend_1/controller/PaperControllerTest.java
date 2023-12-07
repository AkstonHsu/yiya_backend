package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.controller.PaperController;
import com.example.yiya_backend_1.entity.AnswerRecord;
import com.example.yiya_backend_1.entity.CompletePaper;
import com.example.yiya_backend_1.entity.Paper;
import com.example.yiya_backend_1.service.servicImpl.AnswerRecordImpl;
import com.example.yiya_backend_1.service.servicImpl.PaperImpl;
import com.example.yiya_backend_1.utils.Result;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(PaperController.class)
public class PaperControllerTest {

    @Mock
    private PaperImpl paperImpl;

    @Mock
    private AnswerRecordImpl answerRecordImpl;

    @InjectMocks
    private PaperController paperController;

    @Test
    public void testGetPaperController() {
        // 模拟数据
        long pid = 1;
        CompletePaper completePaper = new CompletePaper();
        when(paperImpl.getCompletePaperById(pid)).thenReturn(completePaper);


        // 调用控制器方法
        Result<CompletePaper> result = paperController.getPaperController(pid);

        // 验证结果
        assertEquals("获取试卷成功", result.getMsg());
        assertEquals(completePaper, result.getData());

        // 验证方法是否被调用
        verify(paperImpl, times(1)).getCompletePaperById(pid);
    }

//    @Test
//    public void testGetAllPapersController() {
//        // 模拟数据
//        when(paperImpl.getAllPapers()).thenReturn();
//
//        // 调用控制器方法
//        Result<List<Paper>> result = paperController.getAllPapersController();
//
//        // 验证结果
//        assertEquals("查询所有试卷成功", result.getMsg());
//        assertEquals(1, result.getData().size());
//
//        // 验证方法是否被调用
//        verify(paperImpl, times(1)).getAllPapers();
//    }

    @Test
    public void testSubmitAnswerController() {
        // 模拟数据
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setUid(1L);
        answerRecord.setPid(1L);
        answerRecord.setTestDate(new Date());
        answerRecord.setAnswerSheet("BA");
        long uid = answerRecord.getUid();
        long pid = answerRecord.getPid();
        when(paperImpl.getCorrectAnswerById(pid)).thenReturn("查询成功");
        when(answerRecordImpl.saveAnswerRecord(uid, pid, answerRecord.getTestDate(), answerRecord.getAnswerSheet()))
                .thenReturn(0);

        // 调用控制器方法
        Result<Integer> result = paperController.submitAnswerController(answerRecord);

        // 验证结果
        assertEquals("提交答案成功", result.getMsg());
        assertEquals(200, result.getData());

        // 验证方法是否被调用
        verify(paperImpl, times(1)).getCorrectAnswerById(pid);
        verify(answerRecordImpl, times(1))
                .saveAnswerRecord(uid, pid, answerRecord.getTestDate(), answerRecord.getAnswerSheet());
    }

}
