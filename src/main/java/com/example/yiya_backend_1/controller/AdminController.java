package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.AdminToSubject;
import com.example.yiya_backend_1.entity.DoctorInfo;
import com.example.yiya_backend_1.entity.SubjectInfo;
import com.example.yiya_backend_1.mapper.DoctorInfoMapper;
import com.example.yiya_backend_1.service.servicImpl.DoctorInfoImpl;
import com.example.yiya_backend_1.service.servicImpl.SubjectInfoImpl;
import com.example.yiya_backend_1.utils.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private SubjectInfoImpl subjectInfoImpl;
    @Autowired
    private DoctorInfoImpl doctorInfoImpl;

    @GetMapping("/doctor/getAll")
    public Result<List<DoctorInfo>>getAllDoctorInfoController(){
        List<DoctorInfo> doctorInfos=doctorInfoImpl.getAllDoctors();
        if(doctorInfos!=null){
            return Result.success(doctorInfos,"查询所有医生成功");
        }
        return Result.error("404","没有医生信息");
    }
    @GetMapping("/doctor/search")
    public Result<List<DoctorInfo>>searchDoctorController(@RequestParam String doctorName,@RequestParam String sex,@RequestParam String professionalTitle){
        List<DoctorInfo>doctorInfos=doctorInfoImpl.searchDoctors(doctorName,sex,professionalTitle);
        if (doctorInfos!=null){
            return Result.success(doctorInfos,"搜索医生信息成功");
        }
        return Result.error("404","该医生不存在");
    }
    @DeleteMapping("/doctor/delete")
    public Result<String> deleteDoctorController(@RequestParam long uid) {
        boolean success = doctorInfoImpl.deleteDoctor(uid);
        if (success) {
            return Result.success(null, "删除医生信息成功");
        } else {
            return Result.error("404", "删除医生信息失败");
        }
    }

//    @GetMapping("/getDoctorsByPage")
//    public Result<List<DoctorInfo>> getDoctorsByPage(@RequestParam int page, @RequestParam int pageSize) {
//        List<DoctorInfo> doctors = doctorInfoImpl.getDoctorsByPage(page, pageSize);
//        return Result.success(doctors, "分页查询医生信息成功");
//    }
//
//    @GetMapping("/searchDoctorsByName")
//    public Result<List<DoctorInfo>> searchDoctors(
//            @RequestParam(required = false) String doctorName,
//            @RequestParam int page,
//            @RequestParam int pageSize
//    ) {
//        List<DoctorInfo> doctors = doctorInfoImpl.searchDoctorsByName(doctorName, page, pageSize);
//        return Result.success(doctors, "模糊查询医生信息成功");
//    }


//    @GetMapping("/getSubjectsByPage")
//    public Result<List<SubjectInfo>> getSubjectsWithTestCountByPage(
//            @RequestParam int page,
//            @RequestParam int pageSize
//    ) {
//        List<SubjectInfo> subjects = subjectInfoImpl.getSubjectsWithTestCountByPage(page, pageSize);
//        return Result.success(subjects, "分页查询受试者信息成功");
//    }
//
//    @GetMapping("/getSubjectsByName")
//    public Result<List<SubjectInfo>> getSubjectsByName(
//            @RequestParam String name,
//            @RequestParam int page,
//            @RequestParam int pageSize
//    ) {
//        List<SubjectInfo> subjects = subjectInfoImpl.getSubjectsWithTestCountByPageAndName(name, page, pageSize);
//        return Result.success(subjects, "带有模糊查询的分页查询成功");
//    }

    @GetMapping("/subject/getAll")
    public Result<List<SubjectInfo>>getAllSubjectInfoController(){
        List<SubjectInfo>subjectInfos= subjectInfoImpl.getAllSubjectInfo();
        if (subjectInfos!=null){
            return Result.success(subjectInfos,"查询所有被试成功");
        }
        return Result.error("404","没有被试信息");
    }

    @GetMapping("/subject/search")
    public Result<List<SubjectInfo>>searchSubjectController(@RequestParam String childrenName,@RequestParam String sex,@RequestParam String languageDevelopment){
        List<SubjectInfo>subjectInfos=subjectInfoImpl.searchSubjectInfo(childrenName,sex,languageDevelopment);
        if (subjectInfos!=null){
            return Result.success(subjectInfos,"搜索被试信息成功");
        }
        else return Result.error("404","该被试信息不存在");
    }
    @DeleteMapping("/subject/delete")
    public Result<String> deleteSubject(@RequestParam long uid) {
        boolean success = subjectInfoImpl.deleteSubject(uid);
        if (success) {
            return Result.success(null, "删除被试信息成功");
        } else {
            return Result.error("404", "删除被试信息失败");
        }
    }

}
