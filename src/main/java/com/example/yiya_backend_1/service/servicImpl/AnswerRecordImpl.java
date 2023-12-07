package com.example.yiya_backend_1.service.servicImpl;

import com.example.yiya_backend_1.entity.AnswerRecord;
import com.example.yiya_backend_1.mapper.AnswerRecordMapper;
import com.example.yiya_backend_1.mapper.PaperMapper;
import com.example.yiya_backend_1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
/**
 * 答题记录服务实现类
 *
 * @Author: Adrin
 */
@Service
public class AnswerRecordImpl {
    @Autowired
    private AnswerRecordMapper answerRecordMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PaperMapper paperMapper;
    /**
     * 保存答题记录
     *
     * @param uid        用户ID
     * @param pid        试卷ID
     * @param testDate   测试日期
     * @param answerSheet 用户答题卡
     * @return 0表示成功，1表示试卷不存在，2表示用户不存在
     */
    public int saveAnswerRecord(Long uid, Long pid, String testDate, String answerSheet){
        if(paperMapper.getPaperById(pid)!=null){
            if (userMapper.selectByUid(uid)!=null){
                String correctAnswer= paperMapper.getCorrectAnswerById(pid);
                int correctCnt=calculateCorrectCnt(answerSheet,correctAnswer);
                System.out.println("------------正确答案个数------------------");
                System.out.println(correctCnt);
                AnswerRecord answerRecord=new AnswerRecord();
                answerRecord.setUid(uid);
                answerRecord.setPid(pid);
                answerRecord.setTestDate(testDate);
                answerRecord.setCorrectCnt(correctCnt);
                answerRecord.setAnswerSheet(answerSheet);
                answerRecordMapper.insert(answerRecord);
                return 0;
            }else {
                return 2;
            }
        }
        return 1;
    }
    /**
     * 计算答题卡的正确个数
     *
     * @param answerSheet   用户答题卡
     * @param correctAnswer 正确答案
     * @return 正确个数
     * @throws IllegalArgumentException 当答题卡和正确答案为空时抛出异常
     */
    public int calculateCorrectCnt(String answerSheet,String correctAnswer){
        if(answerSheet!=null&&correctAnswer!=null){
            int len=Math.min(answerSheet.length(),correctAnswer.length());
            int count=0;
            for(int i=0;i<len;i++){
                if(answerSheet.charAt(i)==correctAnswer.charAt(i)){
                    count++;
                }
            }
            return count;
        }
        throw new IllegalArgumentException("答题卡和正确答案不能为空");
    }
    /**
     * 获取用户在指定试卷上的正确个数
     *
     * @param uid 用户ID
     * @param pid 试卷ID
     * @return 用户在试卷上的正确个数，-1表示试卷不存在，-2表示用户不存在
     */
    public Integer getCorrectCntByUidAndPid(Long uid,Long pid){
         if(answerRecordMapper.getCorrectCntByUidAndPid(uid,pid)!=null){
             return answerRecordMapper.getCorrectCntByUidAndPid(uid,pid);
         } else if (paperMapper.getPaperById(pid)==null) {
             return -1;
         }
         return -2;
    }

}
