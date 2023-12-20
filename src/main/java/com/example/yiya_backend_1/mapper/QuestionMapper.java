package com.example.yiya_backend_1.mapper;

import com.example.yiya_backend_1.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
//    @Select("SELECT * FROM question WHERE pid = #{pid}")
//    List<Question> getQuestionByPaperId(long pid);
    @Select("SELECT * FROM question WHERE FIND_IN_SET(qid, #{questionList}) > 0")
    List<Question> getQuestionsByPaperId(@Param("questionList") String questionList);
}
