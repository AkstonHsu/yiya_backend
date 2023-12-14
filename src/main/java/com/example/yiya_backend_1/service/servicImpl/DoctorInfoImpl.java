package com.example.yiya_backend_1.service.servicImpl;

import com.example.yiya_backend_1.entity.DoctorInfo;
import com.example.yiya_backend_1.entity.SubjectInfo;
import com.example.yiya_backend_1.mapper.DoctorInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorInfoImpl {
    @Autowired
    DoctorInfoMapper doctorInfoMapper;
    public DoctorInfo getDoctorInfo(long uid){return doctorInfoMapper.getDoctorInfoByUid(uid);}

    public DoctorInfo inserDoctorInfo(long uid, DoctorInfo doctorInfo){
        if(doctorInfoMapper.getDoctorInfoByUid(uid)==null){
            doctorInfo.setUid(uid);
            doctorInfoMapper.insertDoctorInfoByUid(doctorInfo);
            return doctorInfo;
        }
        return null;
    }
    public DoctorInfo updateDoctorInfo(long uid,DoctorInfo doctorInfo){
        if(doctorInfoMapper.getDoctorInfoByUid(uid)!=null){
            doctorInfoMapper.updateDoctorInfoByUid(doctorInfo);
            return doctorInfo;
        }
        return null;
    }
}
