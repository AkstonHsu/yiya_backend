package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.DoctorInfo;
import com.example.yiya_backend_1.mapper.DoctorInfoMapper;
import com.example.yiya_backend_1.service.servicImpl.DoctorInfoImpl;
import com.example.yiya_backend_1.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 医生信息控制器
 *
 * @Author: Adrin
 */
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorInfoMapper doctorInfoMapper;
    @Autowired
    private DoctorInfoImpl doctorInfoImpl;
    /**
     * 获取医生信息接口
     *
     * @param uid 医生用户的ID
     * @return 医生信息查询结果
     */
    @GetMapping("/get")
    public Result<DoctorInfo>getDoctorInfoController(@RequestParam long uid){
        DoctorInfo doctorInfo=doctorInfoImpl.getDoctorInfo(uid);
        if(doctorInfo!=null){
            return Result.success(doctorInfo,"查询医生信息成功");
        }
        return Result.error("404","无法找到该医生信息");
    }

    /**
     * 新增医生信息接口
     *
     * @param doctorInfo 医生个人信息
     * @return 新增医生信息结果
     */
    @PostMapping ("/new")
    public Result<DoctorInfo>newDoctorInfoController(@RequestBody DoctorInfo doctorInfo){
        Long uid=doctorInfo.getUid();
        DoctorInfo doctorInfotmp=doctorInfoImpl.inserDoctorInfo(uid,doctorInfo);
        if (doctorInfotmp!=null){
            return Result.success(doctorInfotmp,"新增医生信息成功");
        }
        return Result.error("400","该医生信息已存在，需要更新操作");
    }
    /**
     * 更新医生信息接口
     *
     * @param doctorInfo 医生个人信息
     * @return 更新医生信息结果
     */
    @PostMapping("/update")
    public Result<DoctorInfo>updateDoctorInfoController(@RequestBody DoctorInfo doctorInfo){
        Long uid=doctorInfo.getUid();
        DoctorInfo doctorInfotmp=doctorInfoImpl.updateDoctorInfo(uid,doctorInfo);
        if(doctorInfotmp!=null){

            return Result.success(doctorInfotmp,"更新医生信息成功");
        }
        return Result.error("404","更新被试个人信息失败");
    }


}
