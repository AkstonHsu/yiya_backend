package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.AdminToSubject;
import com.example.yiya_backend_1.entity.DoctorInfo;
import com.example.yiya_backend_1.entity.SubjectInfo;
import com.example.yiya_backend_1.mapper.DoctorInfoMapper;
import com.example.yiya_backend_1.service.servicImpl.DoctorInfoImpl;
import com.example.yiya_backend_1.service.servicImpl.SubjectInfoImpl;
import com.example.yiya_backend_1.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private SubjectInfoImpl subjectInfoImpl;
    @Autowired
    private DoctorInfoImpl doctorInfoImpl;

    @GetMapping("/getAllDoctors")
    public Result<List<DoctorInfo>>getAllDoctorInfoController(){
        List<DoctorInfo> doctorInfos=doctorInfoImpl.getAllDoctors();
        if(doctorInfos!=null){
            return Result.success(doctorInfos,"查询所有医生成功");
        }
        return Result.error("404","没有医生信息");
    }
    @GetMapping("/getDoctorsByPage")
    public Result<List<DoctorInfo>> getDoctorsByPage(@RequestParam int page, @RequestParam int pageSize) {
        List<DoctorInfo> doctors = doctorInfoImpl.getDoctorsByPage(page, pageSize);
        return Result.success(doctors, "分页查询医生信息成功");
    }

    @GetMapping("/searchDoctorsByName")
    public Result<List<DoctorInfo>> searchDoctors(
            @RequestParam(required = false) String doctorName,
            @RequestParam int page,
            @RequestParam int pageSize
    ) {
        List<DoctorInfo> doctors = doctorInfoImpl.searchDoctorsByName(doctorName, page, pageSize);
        return Result.success(doctors, "模糊查询医生信息成功");
    }

    @DeleteMapping("/deleteDoctor")
    public Result<String> deleteDoctor(@RequestParam long did) {
        boolean success = doctorInfoImpl.deleteDoctor(did);
        if (success) {
            return Result.success(null, "删除医生信息成功");
        } else {
            return Result.error("404", "删除医生信息失败");
        }
    }

    @GetMapping("/getSubjectsByPage")
    public Result<List<SubjectInfo>> getSubjectsWithTestCountByPage(
            @RequestParam int page,
            @RequestParam int pageSize
    ) {
        List<SubjectInfo> subjects = subjectInfoImpl.getSubjectsWithTestCountByPage(page, pageSize);
        return Result.success(subjects, "分页查询受试者信息成功");
    }

    @GetMapping("/getSubjectsByName")
    public Result<List<SubjectInfo>> getSubjectsByName(
            @RequestParam String name,
            @RequestParam int page,
            @RequestParam int pageSize
    ) {
        List<SubjectInfo> subjects = subjectInfoImpl.getSubjectsWithTestCountByPageAndName(name, page, pageSize);
        return Result.success(subjects, "带有模糊查询的分页查询成功");
    }
    @DeleteMapping("/deleteSubject")
    public Result<String> deleteSubject(@RequestParam long uid) {
        boolean success = subjectInfoImpl.deleteSubject(uid);
        if (success) {
            return Result.success(null, "删除被试信息成功");
        } else {
            return Result.error("404", "删除被试信息失败");
        }
    }
}
