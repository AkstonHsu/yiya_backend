package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.AdminToSubject;
import com.example.yiya_backend_1.service.servicImpl.SubjectInfoImpl;
import com.example.yiya_backend_1.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private SubjectInfoImpl subjectInfoImpl;

    @GetMapping("/getsubject")
    public Result<List<AdminToSubject>> getAllSubjectInfo(){
        List<AdminToSubject>adminToSubjects=subjectInfoImpl.getAllSubjectInfo();
        if(!adminToSubjects.isEmpty()){
            return Result.success(adminToSubjects,"查询所有被试信息成功");
        }
        return Result.error("404","未找到任何被试信息");
    }
}
