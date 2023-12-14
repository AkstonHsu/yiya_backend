package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.DoctorInfo;
import com.example.yiya_backend_1.mapper.DoctorInfoMapper;
import com.example.yiya_backend_1.service.servicImpl.DoctorInfoImpl;
import com.example.yiya_backend_1.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorInfoMapper doctorInfoMapper;
    @Autowired
    private DoctorInfoImpl doctorInfoImpl;

    @GetMapping("/get")
    public Result<DoctorInfo>getDoctorInfoController(@RequestParam long uid){
        DoctorInfo doctorInfo=doctorInfoImpl.getDoctorInfo(uid);
        if(doctorInfo!=null){
            return Result.success(doctorInfo,"查询医生信息成功");
        }
        return Result.error("404","无法找到该医生信息");
    }
    @PutMapping ("/new")
    public Result<DoctorInfo>newDoctorInfoController(@RequestBody DoctorInfo doctorInfo){
        Long uid=doctorInfo.getUid();
        DoctorInfo doctorInfotmp=doctorInfoImpl.inserDoctorInfo(uid,doctorInfo);
        if (doctorInfotmp!=null){
            return Result.success(doctorInfotmp,"新增医生信息成功");
        }
        return Result.error("400","该医生信息已存在，需要更新操作");
    }
    @PutMapping("/update")
    public Result<DoctorInfo>updateDoctorInfoController(@RequestBody DoctorInfo doctorInfo){
        Long uid=doctorInfo.getUid();
        DoctorInfo doctorInfotmp=doctorInfoImpl.updateDoctorInfo(uid,doctorInfo);
        if(doctorInfotmp!=null){
            return Result.success(doctorInfotmp,"更新医生信息成功");
        }
        return Result.error("404","更新被试个人信息失败");
    }
}