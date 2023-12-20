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

    @Select({
            "SELECT s.uid, s.childrenname, s.sex, s.birthday, s.languageDevelopment, s.dialect, s.academicRecord, s.residence, s.urbanOrRural, s.sibling, s.contacts, s.accompany, s.side, s.openTime, s.cochlearBrand, COUNT(ar.aid) AS testCount",
            "FROM subjectinfo s",
            "LEFT JOIN answerrecord ar ON s.uid = ar.uid",
            "GROUP BY s.uid, s.childrenname, s.sex, s.birthday, s.languageDevelopment, s.dialect, s.academicRecord, s.residence, s.urbanOrRural, s.sibling, s.contacts, s.accompany, s.side, s.openTime, s.cochlearBrand",
            "LIMIT #{start}, #{pageSize}"
    })
    List<SubjectInfo> getSubjectsWithTestCountByPage(@Param("start") int start, @Param("pageSize") int pageSize);

    @Select({
            "SELECT s.uid, s.childrenname, s.sex, s.birthday, s.languageDevelopment, s.dialect, s.academicRecord, s.residence, s.urbanOrRural, s.sibling, s.contacts, s.accompany, s.side, s.openTime, s.cochlearBrand, COUNT(ar.aid) AS testCount",
            "FROM subjectinfo s",
            "LEFT JOIN answerrecord ar ON s.uid = ar.uid",
            "WHERE s.childrenname LIKE CONCAT('%', #{name}, '%')",
            "GROUP BY s.uid, s.childrenname, s.sex, s.birthday, s.languageDevelopment, s.dialect, s.academicRecord, s.residence, s.urbanOrRural, s.sibling, s.contacts, s.accompany, s.side, s.openTime, s.cochlearBrand",
            "LIMIT #{start}, #{pageSize}"
    })
    List<SubjectInfo> getSubjectsWithTestCountByPageAndName(
            @Param("name") String name,
            @Param("start") int start,
            @Param("pageSize") int pageSize
    );
    @Delete("DELETE FROM subjectinfo WHERE uid = #{uid}")
    int deleteSubject(@Param("uid") long uid);

    @Select({
            "SELECT s.uid, s.childrenname, s.sex, s.birthday, s.languageDevelopment, s.dialect, s.academicRecord, s.residence, s.urbanOrRural, s.sibling, s.contacts, s.accompany, s.side, s.openTime, s.cochlearBrand, COUNT(ar.aid) AS testCount",
            "FROM subjectinfo s",
            "LEFT JOIN answerrecord ar ON s.uid = ar.uid",
            "GROUP BY s.uid, s.childrenname, s.sex, s.birthday, s.languageDevelopment, s.dialect, s.academicRecord, s.residence, s.urbanOrRural, s.sibling, s.contacts, s.accompany, s.side, s.openTime, s.cochlearBrand"
    })
    List<SubjectInfo> getAllSubjectsWithTestCount();

    @Select({
            "<script>",
            "SELECT s.uid, s.childrenname, s.sex, s.birthday, s.languageDevelopment, s.dialect, s.academicRecord, s.residence, s.urbanOrRural, s.sibling, s.contacts, s.accompany, s.side, s.openTime, s.cochlearBrand, COUNT(ar.aid) AS testCount",
            "FROM subjectinfo s",
            "LEFT JOIN answerrecord ar ON s.uid = ar.uid",
            "WHERE 1=1",
            "<if test='name != null and name.trim() neq \"\"'>",
            "   AND s.childrenname LIKE CONCAT('%', #{name}, '%')",
            "</if>",
            "<if test='sex != null and sex.trim() neq \"\"'>",
            "   AND s.sex = #{sex}",
            "</if>",
            "<if test='languageDevelopment != null and languageDevelopment.trim() neq \"\"'>",
            "   AND s.languageDevelopment LIKE CONCAT('%', #{languageDevelopment}, '%')",
            "</if>",
            "GROUP BY s.uid, s.childrenname, s.sex, s.birthday, s.languageDevelopment, s.dialect, s.academicRecord, s.residence, s.urbanOrRural, s.sibling, s.contacts, s.accompany, s.side, s.openTime, s.cochlearBrand",
            "</script>"
    })
    List<SubjectInfo> searchSubjectInfo(
            @Param("name") String name,
            @Param("sex") String sex,
            @Param("languageDevelopment") String languageDevelopment
    );

}
