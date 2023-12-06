package com.example.yiya_backend_1.mapper;

import com.example.yiya_backend_1.entity.AdminToSubject;
import com.example.yiya_backend_1.entity.SubjectInfo;
import com.example.yiya_backend_1.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SubjectInfoMapper {
    @Select("SELECT * FROM subjectinfo WHERE uid = #{uid}")
    SubjectInfo getSubjectInfoByUid(@Param("uid") Long uid);

    @Insert("INSERT INTO subjectinfo (uid, childrenname, sex, birthday, healthy, languageDevelopment, dialect, academicRecord, residence, urbanOrRural, sibling, contacts, accompany, side, openTime, cochlearBrand) " +
            "VALUES (#{uid}, #{childrenname}, #{sex}, #{birthday}, #{healthy}, #{languageDevelopment}, #{dialect}, #{academicRecord}, #{residence}, #{urbanOrRural}, #{sibling}, #{contacts}, #{accompany}, #{side}, #{openTime}, #{cochlearBrand})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSubjectInfo(SubjectInfo subjectInfo);

    @Update("UPDATE subjectinfo SET childrenname = #{childrenname}, sex = #{sex}, birthday = #{birthday}, " +
            "healthy = #{healthy}, languageDevelopment = #{languageDevelopment}, dialect = #{dialect}, " +
            "academicRecord = #{academicRecord}, residence = #{residence}, urbanOrRural = #{urbanOrRural}, " +
            "sibling = #{sibling}, contacts = #{contacts}, accompany = #{accompany}, side = #{side}, " +
            "openTime = #{openTime}, cochlearBrand = #{cochlearBrand} WHERE uid = #{uid}")
    int updateSubjectInfo(SubjectInfo subjectInfo);

    @Select("SELECT uname FROM subjectinfo WHERE uid = #{uid}")
    String getUnameByUid(Long uid);

    @Select("SELECT u.uname, s.childrenname, s.sex, s.birthday, COUNT(t.uid) AS testcount " +
            "FROM user u " +
            "JOIN subjectinfo s ON u.uid = s.uid " +
            "LEFT JOIN subjecttest t ON u.uid = t.uid " +
            "GROUP BY u.uname, s.childrenname, s.sex, s.birthday")
    List<AdminToSubject> getAllSubjectInfo();
}
