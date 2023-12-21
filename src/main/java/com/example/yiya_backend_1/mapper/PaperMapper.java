package com.example.yiya_backend_1.mapper;

import com.example.yiya_backend_1.entity.CompletePaper;
import com.example.yiya_backend_1.entity.Paper;
import org.apache.ibatis.annotations.*;

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

    @Insert("INSERT INTO paper (did, title, description, source , ageLimit) " +
            "VALUES (#{did}, #{title}, #{description}, #{source} , #{ageLimit})")
    @Options(useGeneratedKeys = true, keyProperty = "pid")
    int insertPaper(Paper paper);

    @Select("SELECT questionList FROM paper WHERE pid = #{pid}")
    String getQuestionListByPid(Long pid);
    @Update("UPDATE paper SET questionList = #{questionList}, amount = #{amount}, correctAnswer = #{correctAnswer} WHERE pid = #{pid}")
    void updatePaper(@Param("pid") Long pid, @Param("questionList") String questionList,
                     @Param("amount") Integer amount, @Param("correctAnswer") String correctAnswer);

}
