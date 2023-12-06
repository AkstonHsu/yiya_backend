package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.SubjectInfo;
import com.example.yiya_backend_1.mapper.SubjectInfoMapper;
import com.example.yiya_backend_1.service.servicImpl.SubjectInfoImpl;
import com.example.yiya_backend_1.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings({"all"})
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectInfoMapper subjectInfoMapper;
    @Autowired
    private SubjectInfoImpl subjectInfoImpl;

    @GetMapping("/get")
    public Result<SubjectInfo>getInfoController(@RequestParam long uid){
        SubjectInfo subjectInfo=subjectInfoImpl.getSubjectInfo(uid);
        if(subjectInfo!=null){
            return Result.success(subjectInfo,"查询被试个人信息成功");
        }
        return Result.error("404","无法找到该被试信息");
    }
    @PostMapping("/new")
    public Result<SubjectInfo>newInfoController(@RequestParam long uid,@RequestBody SubjectInfo subjectInfo){
        SubjectInfo subjectInfoTmp=subjectInfoImpl.insertSubjectInfo(uid, subjectInfo);
        if(subjectInfoTmp!=null){
            return Result.success(subjectInfoTmp,"新增被试个人信息成功");
        }
        return Result.error("400","该被试信息已经存在，需要使用更新操作");
    }

    @PostMapping("/update")
    public Result<SubjectInfo>updateInfoController(@RequestBody SubjectInfo subjectInfo){
        SubjectInfo subjectInfoTmp=subjectInfoImpl.updateSubjectInfo(subjectInfo);
        if (subjectInfoTmp!=null){
            return Result.success(subjectInfoTmp,"更新被试个人信息成功");
        }
        return Result.error("404","更新被试个人信息失败");
    }
}
