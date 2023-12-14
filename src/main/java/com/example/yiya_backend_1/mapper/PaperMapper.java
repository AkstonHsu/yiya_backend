package com.example.yiya_backend_1.mapper;

import com.example.yiya_backend_1.entity.CompletePaper;
import com.example.yiya_backend_1.entity.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaperMapper {
    @Select("SELECT * FROM paper WHERE pid = #{pid}")
    Paper getPaperById(Long pid);
    @Select("SELECT pid, did, title, description, source, amount, paperAudio, ageLimit FROM paper")
    List<Paper> getAllPaper();
    @Select("SELECT correctAnswer FROM paper WHERE pid = #{pid}")
    String getCorrectAnswerById(Long pid);

    @Select("SELECT pid, did, title, description, source, amount, paperAudio, ageLimit FROM paper WHERE ageLimit <= #{ageLimit}")
    List<Paper> getPapersByAgeLimit(int ageLimit);
}
