package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.CompleteQuestion;
import com.example.yiya_backend_1.entity.Question;
import com.example.yiya_backend_1.service.servicImpl.QuestionImpl;
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
class QuestionControllerTest {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private QuestionImpl questionImpl;

    @Test
    void testGetAllQuestionController() {
        // 设置Mock对象的行为
        List<CompleteQuestion> mockCompleteQuestions = Collections.singletonList(new CompleteQuestion());
        when(questionImpl.getAllQuestion()).thenReturn(mockCompleteQuestions);

        // 调用Controller的获取所有试题方法
        Result<List<CompleteQuestion>> result = questionController.getAllQuestionController();

        // 验证行为
        assertNotNull(result);
        assertEquals(mockCompleteQuestions, result.getData());
        assertEquals("获取所有试题成功", result.getMsg());
    }

    @Test
    void testGetOneQuestionController() {
        // 设置Mock对象的行为
        CompleteQuestion mockCompleteQuestion = new CompleteQuestion();
        when(questionImpl.getCompleteQuestionByQid(anyLong())).thenReturn(mockCompleteQuestion);

        // 调用Controller的获取一道完整试题方法
        Result<CompleteQuestion> result = questionController.getOneQuestionController(1L);

        // 验证行为
        assertNotNull(result);
        assertEquals(mockCompleteQuestion, result.getData());
        assertEquals("获取一道完整试题成功", result.getMsg());
    }

    @Test
    void testSearchQuestionController() {
        // 设置Mock对象的行为
        List<CompleteQuestion> mockCompleteQuestions = Collections.singletonList(new CompleteQuestion());
        when(questionImpl.searchQuestions(anyInt(), anyString())).thenReturn(mockCompleteQuestions);

        // 调用Controller的搜索试题方法
        Result<List<CompleteQuestion>> result = questionController.searchQuestionController(1, "testQuestion");

        // 验证行为
        assertNotNull(result);
        assertEquals(mockCompleteQuestions, result.getData());
        assertEquals("查询试题成功", result.getMsg());
    }

    // 类似的方式可以测试其他方法，如 deleteQuestionController、newQuestionController、getPaperQuestionController 等
}
