package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.CompletePaper;
import com.example.yiya_backend_1.mapper.PaperMapper;
import com.example.yiya_backend_1.service.servicImpl.PaperImpl;
import com.example.yiya_backend_1.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class PaperController {
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private PaperImpl paperImpl;
    @GetMapping("/get")
    public Result<CompletePaper>getPaperController(@RequestParam long pid){
        CompletePaper completePaper=paperImpl.getCompletePaperById(pid);
        if (completePaper!=null){
            return Result.success(completePaper,"获取试卷成功");
        }
        return Result.error("404","该试卷不存在");
    }
}
