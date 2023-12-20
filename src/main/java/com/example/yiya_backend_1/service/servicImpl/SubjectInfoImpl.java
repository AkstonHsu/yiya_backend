package com.example.yiya_backend_1.service.servicImpl;

import com.example.yiya_backend_1.entity.AdminToSubject;
import com.example.yiya_backend_1.entity.SubjectInfo;
import com.example.yiya_backend_1.mapper.SubjectInfoMapper;
import com.example.yiya_backend_1.utils.AgeCalculator;
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
    public List<SubjectInfo> getSubjectsWithTestCountByPage(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        List<SubjectInfo> subjects = subjectInfoMapper.getSubjectsWithTestCountByPage(start, pageSize);

        // 对于每个受试者，计算年龄并设置到实体类中
        for (SubjectInfo subject : subjects) {
            int age = AgeCalculator.calculateAge(subject.getBirthday());
            subject.setAge(age);
        }

        return subjects;
    }
    public List<SubjectInfo> getSubjectsWithTestCountByPageAndName(String name, int page, int pageSize) {
        int start = (page - 1) * pageSize;
        return subjectInfoMapper.getSubjectsWithTestCountByPageAndName(name, start, pageSize);
    }
    public boolean deleteSubject(long uid) {
        // 执行删除操作，这里假设删除成功返回true，否则返回false
        int affectedRows = subjectInfoMapper.deleteSubject(uid);
        return affectedRows > 0;
    }

    public List<SubjectInfo>getAllSubjectInfo(){
        List<SubjectInfo>subjects= subjectInfoMapper.getAllSubjectsWithTestCount();
        for (SubjectInfo subject : subjects) {
            int age = AgeCalculator.calculateAge(subject.getBirthday());
            subject.setAge(age);
        }
        return subjects;
    }

    public List<SubjectInfo>searchSubjectInfo(String childrenname,String sex, String languageDevelopment){
        List<SubjectInfo>subjects= subjectInfoMapper.searchSubjectInfo(childrenname,sex,languageDevelopment);
        for (SubjectInfo subject : subjects) {
            int age = AgeCalculator.calculateAge(subject.getBirthday());
            subject.setAge(age);
        }
        return subjects;
    }
}
