package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.SubjectInfo;
import com.example.yiya_backend_1.entity.SubjectWithAnswerRecord;
import com.example.yiya_backend_1.mapper.SubjectInfoMapper;
import com.example.yiya_backend_1.service.servicImpl.AnswerRecordImpl;
import com.example.yiya_backend_1.service.servicImpl.SubjectInfoImpl;
import com.example.yiya_backend_1.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 被试个人信息控制器
 */
@SuppressWarnings({"all"})
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectInfoMapper subjectInfoMapper;
    @Autowired
    private SubjectInfoImpl subjectInfoImpl;
    @Autowired
    private AnswerRecordImpl answerRecordImpl;
    /**
     * 获取被试个人信息接口
     *
     * @param uid 被试用户的ID
     * @return 被试个人信息查询结果
     */
    @GetMapping("/get")
    public Result<SubjectInfo>getInfoController(@RequestParam long uid){
        // 调用被试信息服务，获取被试个人信息
        SubjectInfo subjectInfo=subjectInfoImpl.getSubjectInfo(uid);
        if(subjectInfo!=null){
            // 查询成功
            return Result.success(subjectInfo,"查询被试个人信息成功");
        }
        return Result.error("404","无法找到该被试信息");
    }
    /**
     * 新增被试个人信息接口
     *
     * @param uid         被试用户的ID
     * @param subjectInfo 被试个人信息
     * @return 新增被试个人信息结果
     */

    @PostMapping("/new")
    public Result<SubjectInfo>newInfoController(@RequestParam long uid,@RequestBody SubjectInfo subjectInfo){
        // 调用被试信息服务，新增被试个人信息
        SubjectInfo subjectInfoTmp=subjectInfoImpl.insertSubjectInfo(uid, subjectInfo);
        if(subjectInfoTmp!=null){

            return Result.success(subjectInfoTmp,"新增被试个人信息成功");
        }
        return Result.error("400","该被试信息已经存在，需要使用更新操作");
    }
    /**
     * 更新被试个人信息接口
     *
     * @param subjectInfo 被试个人信息
     * @return 更新被试个人信息结果
     */
    @PostMapping("/update")
    public Result<SubjectInfo>updateInfoController(@RequestBody SubjectInfo subjectInfo){
        // 调用被试信息服务，更新被试个人信息
        SubjectInfo subjectInfoTmp=subjectInfoImpl.updateSubjectInfo(subjectInfo);
        if (subjectInfoTmp!=null){
            return Result.success(subjectInfoTmp,"更新被试个人信息成功");
        }
        return Result.error("404","更新被试个人信息失败");
    }
    /**
     * 获取所有被试测试结果接口
     *
     * @return 所有被试测试结果
     */
    @GetMapping("/test/get")
    public Result<List<SubjectWithAnswerRecord>>getAllSubjectWithRecordController(){
        List<SubjectWithAnswerRecord>subjectWithAnswerRecords=answerRecordImpl.getAllSubjectWithAnswerRecord();
        if(subjectWithAnswerRecords!=null){
            return Result.success(subjectWithAnswerRecords,"成功查询所有被试测试结果");
        }
        return Result.error("404","被试测试结果不存在");
    }
    /**
     * 搜索被试测试结果接口
     *
     * @param childrenName         孩子姓名
     * @param sex                  性别
     * @param languageDevelopment  语言发展
     * @return 符合条件的被试测试结果
     */
    @GetMapping("/test/search")
    public Result<List<SubjectWithAnswerRecord>>searchSubjectWithRecordController(@RequestParam String childrenName,@RequestParam String sex,@RequestParam String languageDevelopment){
        List<SubjectWithAnswerRecord>subjectWithAnswerRecords=answerRecordImpl.searchSubjectWithAnswerRecord(childrenName,sex,languageDevelopment);
        if(subjectWithAnswerRecords!=null){
            return Result.success(subjectWithAnswerRecords,"成功查询所有被试测试结果");
        }
        return Result.error("404","被试测试结果不存在");
    }
}
