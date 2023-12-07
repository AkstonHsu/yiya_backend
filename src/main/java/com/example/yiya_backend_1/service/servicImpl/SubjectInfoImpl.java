package com.example.yiya_backend_1.service.servicImpl;

import com.example.yiya_backend_1.entity.AdminToSubject;
import com.example.yiya_backend_1.entity.SubjectInfo;
import com.example.yiya_backend_1.mapper.SubjectInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 被试个人信息服务实现类
 *
 * @Author: Adrin
 */
@Service
public class SubjectInfoImpl {
    @Autowired
    SubjectInfoMapper subjectInfoMapper;
    /**
     * 根据用户ID获取被试个人信息
     *
     * @param uid 用户ID
     * @return 被试个人信息，如果不存在则返回null
     */
    public SubjectInfo getSubjectInfo(long uid){
        return subjectInfoMapper.getSubjectInfoByUid(uid);
    }
    /**
     * 插入被试个人信息
     *
     * @param uid         用户ID
     * @param subjectInfo 被试个人信息对象
     * @return 插入成功则返回被试个人信息对象，否则返回null
     */
    public SubjectInfo insertSubjectInfo(long uid,SubjectInfo subjectInfo){
        if(subjectInfoMapper.getSubjectInfoByUid(uid)==null){
            subjectInfo.setUid(uid);
            subjectInfoMapper.insertSubjectInfo(subjectInfo);
            return subjectInfo;
        }
        return null;
    }
    /**
     * 更新被试个人信息
     *
     * @param subjectInfo 被试个人信息对象
     * @return 更新成功则返回被试个人信息对象，否则返回null
     */
    public SubjectInfo updateSubjectInfo(SubjectInfo subjectInfo){
        if(subjectInfoMapper.getSubjectInfoByUid(subjectInfo.getUid())!=null){
            subjectInfoMapper.updateSubjectInfo(subjectInfo);
            return subjectInfo;
        }
        return null;
    }
    /**
     * 获取所有被试个人信息
     *
     * @return 所有被试个人信息列表
     */
    public List<AdminToSubject>getAllSubjectInfo(){
        return subjectInfoMapper.getAllSubjectInfo();
    }
}
