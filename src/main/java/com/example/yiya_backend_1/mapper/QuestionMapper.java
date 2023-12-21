package com.example.yiya_backend_1.mapper;

import com.example.yiya_backend_1.entity.Option;
import com.example.yiya_backend_1.entity.Paper;
import com.example.yiya_backend_1.entity.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
//    @Select("SELECT * FROM question WHERE pid = #{pid}")
//    List<Question> getQuestionByPaperId(long pid);
    @Select("SELECT * FROM question WHERE FIND_IN_SET(qid, #{questionList}) > 0")
    List<Question> getQuestionsByPaperId(@Param("questionList") String questionList);


    @Select("SELECT * FROM question")
    List<Question>getAllQuestions();

    @Select({
            "<script>",
            "SELECT * FROM question",
            "<where>",
            "   <if test='questionType != null'>",
            "       AND questionType = #{questionType}",
            "   </if>",
            "   <if test='questionTitle != null and questionTitle.trim() neq \"\"'>",
            "       AND questionTitle LIKE CONCAT('%', #{questionTitle}, '%')",
            "   </if>",
            "</where>",
            "</script>"
    })
    List<Question> searchQuestions(
            @Param("questionType") Integer questionType,
            @Param("questionTitle") String questionTitle
    );
    @Select({
            "<script>",
            "SELECT * FROM question",
            "<where>",
            "   <if test='questionTitle != null and questionTitle.trim() neq \"\"'>",
            "       AND questionTitle LIKE CONCAT('%', #{questionTitle}, '%')",
            "   </if>",
            "</where>",
            "</script>"
    })
    List<Question> searchQuestionsWithoutType(
            @Param("questionTitle") String questionTitle
    );

    @Delete("DELETE FROM question WHERE qid = #{qid}")
    int deleteQuestion(@Param("qid") long qid);

    @Insert("INSERT INTO question (questionType, questionTitle) VALUES ( #{questionType}, #{questionTitle})")
    int insertQuestionType2(Question question);

    @Select("SELECT * FROM question WHERE qid = #{qid}")
    Question getQuestionById(Long qid);

    @Select("SELECT correctAnswer FROM question WHERE FIND_IN_SET(qid, #{questionList}) > 0")
    List<String> getCorrectAnswersByQuestionList(@Param("questionList") String questionList);
}
