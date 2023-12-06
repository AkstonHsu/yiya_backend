package com.example.yiya_backend_1.service.servicImpl;

import com.example.yiya_backend_1.entity.*;
import com.example.yiya_backend_1.mapper.OptionMapper;
import com.example.yiya_backend_1.mapper.PaperMapper;
import com.example.yiya_backend_1.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaperImpl {
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private OptionMapper optionMapper;

    public CompletePaper getCompletePaperById(long pid){
        Paper paper =paperMapper.getPaperById(pid);
        if(paper!=null){
            List<Question>questions=questionMapper.getQuestionByPaperId(pid);
            List<CompleteQuestion>completeQuestions=new ArrayList<>();
            for (Question question:questions){
                List<Option>options=optionMapper.getOptionByQuestionId(question.getQid());
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
            CompletePaper completePaper = new CompletePaper(
                    paper.getPid(),
                    paper.getTitle(),
                    paper.getDescription(),
                    paper.getSource(),
                    paper.getAmount(),
                    completeQuestions
            );
            return completePaper;
        }

        return null;
    }
}
