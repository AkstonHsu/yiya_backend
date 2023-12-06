package com.example.yiya_backend_1.service.servicImpl;

import com.example.yiya_backend_1.entity.AdminToSubject;
import com.example.yiya_backend_1.entity.SubjectInfo;
import com.example.yiya_backend_1.mapper.SubjectInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectInfoImpl {
    @Autowired
    SubjectInfoMapper subjectInfoMapper;

    public SubjectInfo getSubjectInfo(long uid){
        return subjectInfoMapper.getSubjectInfoByUid(uid);
    }

    public SubjectInfo insertSubjectInfo(long uid,SubjectInfo subjectInfo){
        if(subjectInfoMapper.getSubjectInfoByUid(uid)==null){
            subjectInfo.setUid(uid);
            subjectInfoMapper.insertSubjectInfo(subjectInfo);
            return subjectInfo;
        }
        return null;
    }

    public SubjectInfo updateSubjectInfo(SubjectInfo subjectInfo){
        if(subjectInfoMapper.getSubjectInfoByUid(subjectInfo.getUid())!=null){
            subjectInfoMapper.updateSubjectInfo(subjectInfo);
            return subjectInfo;
        }
        return null;
    }
    public List<AdminToSubject>getAllSubjectInfo(){
        return subjectInfoMapper.getAllSubjectInfo();
    }
}
