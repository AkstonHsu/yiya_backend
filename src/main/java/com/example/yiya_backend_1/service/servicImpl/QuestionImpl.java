package com.example.yiya_backend_1.service.servicImpl;

import com.example.yiya_backend_1.entity.*;
import com.example.yiya_backend_1.mapper.OptionMapper;
import com.example.yiya_backend_1.mapper.PaperMapper;
import com.example.yiya_backend_1.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionImpl {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private OptionMapper optionMapper;
    @Autowired
    private PaperMapper  paperMapper;

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
                    question.getCorrectAnswer(),
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
                    question.getCorrectAnswer(),
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
                    question.getCorrectAnswer(),
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

    public List<CompleteQuestion> getQuestionByPaper(long pid){
        Paper paper=paperMapper.getPaperById(pid);
        String questionList=paper.getQuestionList();
        List<Question>questions=questionMapper.getQuestionsByPaperId(questionList);
        List<CompleteQuestion>completeQuestions=new ArrayList<>();
        for (Question question:questions){
            List<Option>options=optionMapper.getOptionByQuestionId(question.getQid());
            // 构建完整问题对象
            CompleteQuestion completeQuestion=new CompleteQuestion(
                    question.getQid(),
                    question.getQuestionType(),
                    question.getQuestionTitle(),
                    question.getQuestionAudio(),
                    question.getCorrectAnswer(),
                    question.getOrder(),
                    options
            );
            completeQuestions.add(completeQuestion);
        }
        return completeQuestions;
    }

    public int addQuestion(long pid, long qid){
        String questionList= paperMapper.getQuestionListByPid(pid);
        Question question=questionMapper.getQuestionById(qid);
        if(question!=null){
            List<String> questionIds = null;
            if (questionList == null) {
                questionList = String.valueOf(qid);
            } else {
                // 如果questionList不为null，检查是否已经包含了qid
                questionIds = new ArrayList<>(Arrays.asList(questionList.split(",")));
                if (!questionIds.contains(String.valueOf(qid))) {
                    // 如果不包含，将qid添加到questionList中
                    questionList =  questionList +  "," + qid;
                    questionIds.add(String.valueOf(qid));
                } else {
                    //已经包含该问题
                    return 1;
                }
            }
            int questionCount = questionIds.size();
            List<String>correctAnswers=questionMapper.getCorrectAnswersByQuestionList(questionList);
            String correctAnswerString = String.join("", correctAnswers);
            paperMapper.updatePaper(pid,questionList,questionCount,correctAnswerString);
            //增加问题成功
            return 0;
        }
        //加入的问题不存在
        return 2;
    }

    public int removeQuestion(long pid, long qid) {
        String questionList = paperMapper.getQuestionListByPid(pid);
        Question question = questionMapper.getQuestionById(qid);
        if (question != null) {
            if (questionList != null) {
                List<String> questionIds = new ArrayList<>(Arrays.asList(questionList.split(",")));
                if (questionIds.contains(String.valueOf(qid))) {
                    // 如果包含，将qid从questionList中移除
                    questionList = questionList.replace(String.valueOf(qid), "").replace(",,", ",").replaceAll("^,|,$", "");
                    questionIds.remove(String.valueOf(qid));

                    int questionCount = questionIds.size();
                    List<String> correctAnswers = questionMapper.getCorrectAnswersByQuestionList(questionList);
                    String correctAnswerString = String.join("", correctAnswers);

                    paperMapper.updatePaper(pid, questionList, questionCount, correctAnswerString);

                    // 删除问题成功
                    return 0;
                } else {
                    // 该试题不存在于试卷中，无法删除
                    return 1;
                }
            } else {
                // 试卷中没有问题，无法删除
                return 1;
            }
        }
        // 要删除的问题不存在
        return 2;
    }


    public CompleteQuestion getCompleteQuestionByQid(long qid){
        Question question=questionMapper.getQuestionById(qid);

            List<Option>options=optionMapper.getOptionByQuestionId(question.getQid());
            // 构建完整问题对象
            CompleteQuestion completeQuestion=new CompleteQuestion(
                    question.getQid(),
                    question.getQuestionType(),
                    question.getQuestionTitle(),
                    question.getQuestionAudio(),
                    question.getCorrectAnswer(),
                    question.getOrder(),
                    options
            );
        return completeQuestion;
    }
}
