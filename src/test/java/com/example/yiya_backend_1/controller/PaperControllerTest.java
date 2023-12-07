package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.service.servicImpl.PaperImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.yiya_backend_1.entity.Paper;
import com.example.yiya_backend_1.utils.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PaperControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;  // Spring Boot 自动配置的 ObjectMapper

    @Autowired
    private PaperImpl paperImpl;

    @Test
    public void testGetAllPapersController() throws Exception {
        // 模拟数据
        List<Paper> mockPaperList = Collections.singletonList(new Paper(/* your paper data */));
        when(paperImpl.getAllPapers()).thenReturn(mockPaperList);

        // 执行请求并验证结果
        String response = mockMvc.perform(get("/test/getAll"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // 解析返回的 JSON
        Result<List<Paper>> result = objectMapper.readValue(response, new TypeReference<Result<List<Paper>>>() {});

        // 验证结果
        assertEquals("查询所有试卷成功", result.getMsg());
        assertEquals(mockPaperList, result.getData());
    }
}
