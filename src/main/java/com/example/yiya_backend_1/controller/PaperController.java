package com.example.yiya_backend_1.controller;

import com.example.yiya_backend_1.entity.AnswerRecord;
import com.example.yiya_backend_1.entity.CompletePaper;
import com.example.yiya_backend_1.entity.Paper;
import com.example.yiya_backend_1.mapper.PaperMapper;
import com.example.yiya_backend_1.service.servicImpl.AnswerRecordImpl;
import com.example.yiya_backend_1.service.servicImpl.PaperImpl;
import com.example.yiya_backend_1.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
public class PaperController {
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private PaperImpl paperImpl;
    @Autowired
    AnswerRecordImpl answerRecordImpl;
    @GetMapping("/get")
    public Result<CompletePaper>getPaperController(@RequestParam long pid){
        CompletePaper completePaper=paperImpl.getCompletePaperById(pid);
        if (completePaper!=null){
            return Result.success(completePaper,"获取试卷成功");
        }
        return Result.error("404","该试卷不存在");
    }
    @GetMapping("/getResult")
    public Result<Integer>getCorrectCntController(@RequestParam long uid,@RequestParam long pid){
        Integer correctCnt= answerRecordImpl.getCorrectCntByUidAndPid(uid,pid);
        if(correctCnt>=0){
            return Result.success(correctCnt,"获取测试信息成功");
        } else if (correctCnt==-1) {
            return Result.error("404","该试卷不存在");
        }
        return Result.error("404","该用户没有做过测试");
    }
    @GetMapping("/getAll")
    public Result<List<Paper>>getAllPapersController(){
       List<Paper> list = paperImpl.getAllPapers();
       if(list!=null){
           return Result.success(list,"查询所有试卷成功");
       }
       return Result.error("404","库中没有试卷");
    }
    @PostMapping("/submit")
    public Result<Integer>submitAnswerController(@RequestBody AnswerRecord answerRecord){

        Long uid =answerRecord.getUid();
        Long pid = answerRecord.getPid();
        Date testDate=answerRecord.getTestDate();
        String answerSheet=answerRecord.getAnswerSheet();

        String correctAnswer=paperImpl.getCorrectAnswerById(pid);
        if(correctAnswer!=null){
            int code=answerRecordImpl.saveAnswerRecord(uid,pid,testDate,answerSheet);
            if (code==0){

                return Result.success(200);
            } else if (code==2) {
                return Result.error("404","该用户不存在");
            }
            return Result.error("404","试卷不存在");
        }

        return Result.error("404","正确答案不存在");

    }
}
