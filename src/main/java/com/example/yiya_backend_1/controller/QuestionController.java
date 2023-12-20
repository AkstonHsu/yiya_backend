package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.CompleteQuestion;
import com.example.yiya_backend_1.entity.Question;
import com.example.yiya_backend_1.mapper.QuestionMapper;
import com.example.yiya_backend_1.service.servicImpl.QuestionImpl;
import com.example.yiya_backend_1.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RestController
public class QuestionController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionImpl questionImpl;
    @GetMapping("/getAll")
    public Result<List<CompleteQuestion>>getAllQuestionController(){
        List<CompleteQuestion>completeQuestions=questionImpl.getAllQuestion();
        if (completeQuestions!=null){
            return Result.success(completeQuestions,"获取所有试题成功");
        }
        return Result.error("404","题库中没有试题");
    }

    @GetMapping("/search")
    public Result<List<CompleteQuestion>>searchQuestionController(@RequestParam(required = false) Integer questionType,@RequestParam String questionTitle){
        if(questionType==null){
            List<CompleteQuestion>completeQuestions=questionImpl.searchQuestionsWithoutType(questionTitle);
            if (completeQuestions!=null){
                return Result.success(completeQuestions,"查询试题成功");
            }
            return Result.error("404","该试题不存在");
        }
        else{
            List<CompleteQuestion>completeQuestions=questionImpl.searchQuestions(questionType,questionTitle);
            if (completeQuestions!=null){
                return Result.success(completeQuestions,"查询试题成功");
            }
            return Result.error("404","该试题不存在");
        }

    }

    @DeleteMapping("/delete")
    public Result<String> deleteQuestionController(@RequestParam long qid) {
        boolean success = questionImpl.deleteQuestion(qid);
        if (success) {
            return Result.success(null, "删除题目成功");
        } else {
            return Result.error("404", "删除失败,该题目不存在");
        }
    }

    @PostMapping("/new")
    public Result<Question>newQuestionController(@RequestBody Question question){
        Question questiontmp=questionImpl.insertQuestionType2(question);
        if(questiontmp!=null){
            return Result.success(questiontmp,"新增题目成功");
        }
        return Result.error("400","新增题目失败");
    }
}
