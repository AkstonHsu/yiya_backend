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
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
/**
 * 试卷相关操作控制器
 *
 * @Author: Adrin
 */
@RestController
@RequestMapping("/test")
public class PaperController {
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private PaperImpl paperImpl;
    @Autowired
    private AnswerRecordImpl answerRecordImpl;
    /**
     * 获取试卷接口
     *
     * @param pid 试卷ID
     * @return 试卷查询结果
     */
    @GetMapping("/get")
    public Result<CompletePaper>getPaperController(@RequestParam long pid){
        // 调用试卷服务，获取试卷信息
        CompletePaper completePaper=paperImpl.getCompletePaperById(pid);
        if (completePaper!=null){
            return Result.success(completePaper,"获取试卷成功");
        }
        return Result.error("404","该试卷不存在");
    }
    /**
     * 获取用户最近作答的试卷的正确个数接口
     *
     * @param uid 用户ID
     * @param pid 试卷ID
     * @return 获取测试信息结果
     */
    @GetMapping("/getResult")
    public Result<Integer>getCorrectCntController(@RequestParam long uid,@RequestParam long pid){
        // 调用答题记录服务，获取用户最近作答的试卷的正确个数
        Integer correctCnt= answerRecordImpl.getCorrectCntByUidAndPid(uid,pid);
        if(correctCnt>=0){
            return Result.success(correctCnt,"获取测试信息成功");
        } else if (correctCnt==-1) {
            return Result.error("404","该试卷不存在");
        }
        return Result.error("404","该用户没有做过测试");
    }
    /**
     * 查询所有试卷接口
     *
     * @return 所有试卷查询结果
     */
    @GetMapping("/getAll")
    public Result<List<Paper>>getAllPapersController(){
       List<Paper> list = paperImpl.getAllPapers();
       if(list!=null){
           return Result.success(list,"查询所有试卷成功");
       }
       return Result.error("404","库中没有试卷");
    }
    /**
     * 提交答案接口
     *
     * @param answerRecord 包含用户答案的答题记录
     * @return 提交答案结果
     */
    @PostMapping("/submit")
    public Result<Integer>submitAnswerController(@RequestBody AnswerRecord answerRecord){
        // 从答题记录中获取用户信息、试卷信息等
        Long uid =answerRecord.getUid();
        Long pid = answerRecord.getPid();
        String testDate=answerRecord.getTestDate();
        String answerSheet=answerRecord.getAnswerSheet();
        // 调用试卷服务，获取试卷的正确答案
        String correctAnswer=paperImpl.getCorrectAnswerById(pid);
        if(correctAnswer!=null){
            // 调用答题记录服务，保存用户答题记录
            int code=answerRecordImpl.saveAnswerRecord(uid,pid,testDate,answerSheet);
            if (code==0){
                // 提交答案成功
                return Result.success(200);
            } else if (code==2) {
                return Result.error("404","该用户不存在");
            }
            return Result.error("404","试卷不存在");
        }

        return Result.error("404","正确答案不存在");
    }

    /**
     * 根据用户ID获取符合条件的试卷接口
     *
     * @param uid 用户ID
     * @return 符合条件的试卷查询结果
     */
    @GetMapping("/get/uid")
    public Result<List<Paper>>getAllPaperByUid(@RequestParam Long uid){
        List<Paper> papers=paperImpl.getAllPaperByUid(uid);
        if(papers!=null){
            return Result.success(papers,"获取符合条件试卷成功");
        }
        return Result.error("404","符合条件的试卷不存在");
    }
    /**
     * 新增试卷接口
     *
     * @param paper 新增的试卷
     * @return 新增试卷结果
     */
    @PostMapping("/paper/new")
    public Result<Paper>newPaperController(@RequestBody Paper paper){
        Paper newPaper=paperImpl.newPaper(paper);
        if(newPaper!=null){
            return Result.success(newPaper,"新增试卷成功");
        }
        return Result.error("400","新增试卷失败");
    }
    /**
     * 删除试卷接口
     *
     * @param pid 试卷ID
     * @return 删除试卷结果
     */
    @DeleteMapping("/delete")
    public Result<String> deletePaperController(@RequestParam long pid) {
        boolean success = paperImpl.deletePaper(pid);
        if (success) {
            return Result.success(null, "删除试卷成功");
        } else {
            return Result.error("404", "删除失败,该试卷不存在");
        }
    }
    /**
     * 搜索试卷接口
     *
     * @param title  试卷标题
     * @param source 试卷来源
     * @return 搜索试卷结果
     */
    @PostMapping("/paper/search")
    public Result<List<Paper>>searchPaper(@RequestParam String title,@RequestParam String source){
        List<Paper>papers=paperImpl.searchPaper(title,source);
        if (papers!=null){
            return Result.success(papers,"搜索试卷成功");
        }
        return Result.error("404","试卷不存在");
    }
}
