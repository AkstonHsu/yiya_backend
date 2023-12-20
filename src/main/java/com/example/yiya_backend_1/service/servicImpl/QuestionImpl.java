package com.example.yiya_backend_1.service.servicImpl;

import com.example.yiya_backend_1.entity.CompleteQuestion;
import com.example.yiya_backend_1.entity.DoctorInfo;
import com.example.yiya_backend_1.entity.Option;
import com.example.yiya_backend_1.entity.Question;
import com.example.yiya_backend_1.mapper.OptionMapper;
import com.example.yiya_backend_1.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionImpl {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private OptionMapper optionMapper;

    public List<CompleteQuestion> getAllQuestion(){
        List<Question>questions=questionMapper.getAllQuestions();
        List<CompleteQuestion>completeQuestions=new ArrayList<>();
        for (Question question:questions){
            List<Option>options=optionMapper.getOptionByQuestionId(question.getQid());
            // 构建完整问题对象
            CompleteQuestion completeQuestion=new CompleteQuestion(
                    question.getQid(),
                    question.getQuestionType(),
                    question.getQuestionTitle(),
                    question.getQuestionAudio(),
                    question.getOrder(),
                    options
            );
            completeQuestions.add(completeQuestion);
        }
        return completeQuestions;
    }

    public List<CompleteQuestion> searchQuestions(int questionType,String questionTitle){
        List<Question>questions=questionMapper.searchQuestions(questionType,questionTitle);
        List<CompleteQuestion>completeQuestions=new ArrayList<>();
        for (Question question:questions){
            List<Option>options=optionMapper.getOptionByQuestionId(question.getQid());
            // 构建完整问题对象
            CompleteQuestion completeQuestion=new CompleteQuestion(
                    question.getQid(),
                    question.getQuestionType(),
                    question.getQuestionTitle(),
                    question.getQuestionAudio(),
                    question.getOrder(),
                    options
            );
            completeQuestions.add(completeQuestion);
        }
        return completeQuestions;
    }
    public List<CompleteQuestion> searchQuestionsWithoutType(String questionTitle){
        List<Question>questions=questionMapper.searchQuestionsWithoutType(questionTitle);
        List<CompleteQuestion>completeQuestions=new ArrayList<>();
        for (Question question:questions){
            List<Option>options=optionMapper.getOptionByQuestionId(question.getQid());
            // 构建完整问题对象
            CompleteQuestion completeQuestion=new CompleteQuestion(
                    question.getQid(),
                    question.getQuestionType(),
                    question.getQuestionTitle(),
                    question.getQuestionAudio(),
                    question.getOrder(),
                    options
            );
            completeQuestions.add(completeQuestion);
        }
        return completeQuestions;
    }

    public boolean deleteQuestion(long qid) {
        // 执行删除操作，这里假设删除成功返回true，否则返回false
        int affectedRows = questionMapper.deleteQuestion(qid);
        return affectedRows > 0;
    }

    public Question insertQuestionType2(Question question){
        questionMapper.insertQuestionType2(question);
        return question;
    }
}
