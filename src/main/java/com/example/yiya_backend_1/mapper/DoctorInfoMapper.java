package com.example.yiya_backend_1.mapper;

import com.example.yiya_backend_1.entity.DoctorInfo;
import com.example.yiya_backend_1.entity.Paper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DoctorInfoMapper {
    @Select("SELECT doctorName FROM doctorinfo WHERE did = #{did}")
    String getDoctorNameByDid(Long did);

    @Select("SELECT * FROM doctorinfo WHERE uid = #{uid}")
    DoctorInfo getDoctorInfoByUid(Long uid);

    @Insert("INSERT INTO doctorinfo (uid, doctorName, sex, workPlace) VALUES (#{uid}, #{doctorName}, #{sex}, #{workPlace})")
    int insertDoctorInfoByUid(DoctorInfo doctorInfo);

    @Update("UPDATE doctorinfo SET doctorName = #{doctorName}, sex = #{sex}, workPlace = #{workPlace} WHERE uid = #{uid}")
    int updateDoctorInfoByUid(DoctorInfo doctorInfo);
}

