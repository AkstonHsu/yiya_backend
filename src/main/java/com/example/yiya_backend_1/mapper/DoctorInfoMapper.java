package com.example.yiya_backend_1.mapper;

import com.example.yiya_backend_1.entity.DoctorInfo;
import com.example.yiya_backend_1.entity.Paper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DoctorInfoMapper {
    @Select("SELECT doctorName FROM doctorinfo WHERE did = #{did}")
    String getDoctorNameByDid(Long did);

    @Select("SELECT * FROM doctorinfo WHERE uid = #{uid}")
    DoctorInfo getDoctorInfoByUid(Long uid);

    @Insert("INSERT INTO doctorinfo (uid, doctorName, sex, workPlace, professionalTitle, department, graduateSchool) VALUES (#{uid}, #{doctorName}, #{sex}, #{workPlace}, #{professionalTitle}, #{department}, #{graduateSchool})")
    int insertDoctorInfoByUid(DoctorInfo doctorInfo);

    @Update({
            "UPDATE doctorinfo",
            "SET doctorName = #{doctorName},",
            "    sex = #{sex},",
            "    workPlace = #{workPlace},",
            "    professionalTitle = #{professionalTitle},",
            "    department = #{department},",
            "    graduateSchool = #{graduateSchool}",
            "WHERE uid = #{uid}"
    })
    int updateDoctorInfoByUid(DoctorInfo doctorInfo);


    @Select("SELECT * FROM doctorinfo")
    List<DoctorInfo> getAllDoctors();

    @Select("SELECT * FROM doctorinfo LIMIT #{start}, #{pageSize}")
    List<DoctorInfo> getDoctorsByPage(@Param("start") int start, @Param("pageSize") int pageSize);

//    @Select({
//            "<script>",
//            "SELECT * FROM doctorinfo",
//            "<where>",
//            "   <if test='doctorName != null and doctorName.trim() neq \"\"'>",
//            "       AND doctorName LIKE CONCAT('%', #{doctorName}, '%')",
//            "   </if>",
//            "</where>",
//            "LIMIT #{start}, #{pageSize}",
//            "</script>"
//    })
//    List<DoctorInfo> searchDoctors(
//            @Param("doctorName") String doctorName,
//            @Param("start") int start,
//            @Param("pageSize") int pageSize
//    );
    @Delete("DELETE FROM doctorinfo WHERE uid = #{uid}")
    int deleteDoctor(@Param("uid") long uid);

    @Select({
            "<script>",
            "SELECT * FROM doctorinfo",
            "<where>",
            "   <if test='doctorName != null and doctorName.trim() neq \"\"'>",
            "       AND doctorName LIKE CONCAT('%', #{doctorName}, '%')",
            "   </if>",
            "   <if test='sex != null and sex.trim() neq \"\"'>",
            "       AND sex = #{sex}",
            "   </if>",
            "   <if test='professionalTitle != null and professionalTitle.trim() neq \"\"'>",
            "       AND professionalTitle LIKE CONCAT('%', #{professionalTitle}, '%')",
            "   </if>",
            "</where>",
            "</script>"
    })
    List<DoctorInfo> searchDoctors(
            @Param("doctorName") String doctorName,
            @Param("sex") String sex,
            @Param("professionalTitle") String professionalTitle
    );

    @Select("SELECT did FROM doctorinfo WHERE uid = #{uid}")
    Long getDidByUid(Long uid);
}

