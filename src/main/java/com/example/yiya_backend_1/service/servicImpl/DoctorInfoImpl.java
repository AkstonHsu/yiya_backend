package com.example.yiya_backend_1.service.servicImpl;

import com.example.yiya_backend_1.entity.DoctorInfo;
import com.example.yiya_backend_1.entity.SubjectInfo;
import com.example.yiya_backend_1.mapper.DoctorInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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



    public List<DoctorInfo> getAllDoctors() {
        return doctorInfoMapper.getAllDoctors();
    }

    public List<DoctorInfo> getDoctorsByPage(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        return doctorInfoMapper.getDoctorsByPage(start, pageSize);
    }

//    public List<DoctorInfo> searchDoctorsByName(String doctorName, int page, int pageSize) {
//        int start = (page - 1) * pageSize;
//        return doctorInfoMapper.searchDoctors(doctorName, start, pageSize);
//    }

    public boolean deleteDoctor(long uid) {
        // 执行删除操作，这里假设删除成功返回true，否则返回false
        int affectedRows = doctorInfoMapper.deleteDoctor(uid);
        return affectedRows > 0;
    }

    public List<DoctorInfo>searchDoctors(String doctorName,String sex,String professionalTitle){
        return doctorInfoMapper.searchDoctors(doctorName,sex,professionalTitle);
    }
}
