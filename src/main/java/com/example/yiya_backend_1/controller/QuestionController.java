package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.CompleteQuestion;
import com.example.yiya_backend_1.entity.Paper;
import com.example.yiya_backend_1.entity.Question;
import com.example.yiya_backend_1.mapper.QuestionMapper;
import com.example.yiya_backend_1.service.servicImpl.QuestionImpl;
import com.example.yiya_backend_1.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 试题相关操作控制器
 */
@RequestMapping("/question")
@RestController
public class QuestionController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionImpl questionImpl;
    /**
     * 获取所有试题接口
     *
     * @return 所有试题查询结果
     */
    @GetMapping("/getAll")
    public Result<List<CompleteQuestion>>getAllQuestionController(){
        List<CompleteQuestion>completeQuestions=questionImpl.getAllQuestion();
        if (completeQuestions!=null){
            return Result.success(completeQuestions,"获取所有试题成功");
        }
        return Result.error("404","题库中没有试题");
    }
    /**
     * 获取单个完整试题接口
     *
     * @param qid 试题ID
     * @return 单个完整试题查询结果
     */
    @GetMapping("/get/one")
    public Result<CompleteQuestion>getOneQuestionController(@RequestParam long qid){
        CompleteQuestion completeQuestion=questionImpl.getCompleteQuestionByQid(qid);
        if(completeQuestion!=null){
            return Result.success(completeQuestion,"获取一道完整试题成功");
        }
        return Result.error("404","该试题不存在");
    }
    /**
     * 搜索试题接口
     *
     * @param questionType 试题类型
     * @param questionTitle 试题标题
     * @return 搜索试题结果
     */
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
    /**
     * 删除试题接口
     *
     * @param qid 试题ID
     * @return 删除试题结果
     */
    @DeleteMapping("/delete")
    public Result<String> deleteQuestionController(@RequestParam long qid) {
        boolean success = questionImpl.deleteQuestion(qid);
        if (success) {
            return Result.success(null, "删除题目成功");
        } else {
            return Result.error("404", "删除失败,该题目不存在");
        }
    }
    /**
     * 新增试题接口
     *
     * @param question 新增的试题
     * @return 新增试题结果
     */
    @PostMapping("/new")
    public Result<Question>newQuestionController(@RequestBody Question question){
        Question questiontmp=questionImpl.insertQuestionType2(question);
        if(questiontmp!=null){
            return Result.success(questiontmp,"新增题目成功");
        }
        return Result.error("400","新增题目失败");
    }
    /**
     * 查询试卷的所有试题接口
     *
     * @param pid 试卷ID
     * @return 查询试卷的所有试题结果
     */
    @GetMapping("/get/paper")
    public Result<List<CompleteQuestion>>getPaperQuestionController(@RequestParam long pid){
        List<CompleteQuestion>completeQuestions=questionImpl.getQuestionByPaper(pid);
        if(completeQuestions!=null){
            return Result.success(completeQuestions,"查询单份试卷题目成功");
        }
        return Result.error("404","查询该试卷题目失败");
    }
    /**
     * 增加试题到试卷接口
     *
     * @param pid 试卷ID
     * @param qid 试题ID
     * @return 增加试题到试卷结果
     */
    @PostMapping("/add")
    public Result<Integer>addQuestionController(@RequestParam long pid, @RequestParam long qid){
        int code=questionImpl.addQuestion(pid,qid);
        if(code==0){
            return Result.success(200,"增加试题成功");
        } else if (code==1) {
            return Result.error("400","该试题已经存在");
        }else {
            return Result.error("404","该试题不存在");
        }
    }

    @DeleteMapping("/remove")
    public Result<Integer> deleteQuestionController(@RequestParam long pid, @RequestParam long qid) {
        int code = questionImpl.removeQuestion(pid,qid);
        if (code == 0) {
            return Result.success(200, "删除试题成功");
        } else if (code == 1) {
            return Result.error("400", "该试题不存在于试卷中，无法删除");
        } else {
            return Result.error("404", "该试题不存在");
        }
    }

}
