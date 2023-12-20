package com.example.yiya_backend_1.service.servicImpl;

import com.example.yiya_backend_1.entity.*;
import com.example.yiya_backend_1.mapper.*;
import com.example.yiya_backend_1.utils.AgeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 试卷服务实现类
 *
 * 该类提供了与试卷相关的服务，包括获取所有试卷、根据试卷ID获取完整试卷信息、
 * 根据试卷ID获取正确答案等功能。
 *
 * @Author: Adrin
 */
@Service
public class PaperImpl {

    private String staticDir="E:\\带学作业\\大三上\\软件工程\\yiya\\yiya-backend-second\\src\\main\\resources\\static";
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private SubjectInfoMapper subjectInfoMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private OptionMapper optionMapper;
    @Autowired
    private DoctorInfoMapper doctorInfoMapper;
    /**
     * 获取所有试卷
     *
     * @return 所有试卷的列表，如果没有试卷则返回null
     */
    public List<Paper>getAllPapers(){
        if(paperMapper.getAllPaper()!=null){
            return paperMapper.getAllPaper();
        }
        return null;
    }
    /**
     * 根据试卷ID获取正确答案
     *
     * @param pid 试卷ID
     * @return 正确答案，如果不存在则返回null
     */
    public String getCorrectAnswerById(Long pid){
        if(paperMapper.getCorrectAnswerById(pid)!=null){
            return paperMapper.getCorrectAnswerById(pid);
        }
        return null;
    }
    /**
     * 根据试卷ID获取完整试卷信息
     *
     * @param pid 试卷ID
     * @return 完整试卷信息，如果不存在则返回null
     */
    public CompletePaper getCompletePaperById(long pid){
        Paper paper =paperMapper.getPaperById(pid);
        if(paper!=null){
            // 根据试卷ID获取所有问题信息
            List<Question>questions=questionMapper.getQuestionsByPaperId(paper.getQuestionList());
            List<CompleteQuestion>completeQuestions=new ArrayList<>();
            // 遍历每个问题，获取问题的选项信息
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
            // 构建完整试卷对象
            CompletePaper completePaper = new CompletePaper(
                    paper.getPid(),
                    paper.getTitle(),
                    paper.getDescription(),
                    paper.getSource(),
                    paper.getPaperAudio(),
                    paper.getAmount(),
                    completeQuestions
            );
            return completePaper;
        }

        return null;
    }

    public List<Paper>getAllPaperByUid(long uid){
        SubjectInfo subjectInfo=subjectInfoMapper.getSubjectInfoByUid(uid);
        if(subjectInfo!=null){
            int userAge= AgeCalculator.calculateAge(subjectInfo.getBirthday());
            System.out.println("---------------userAge:   --------------------");
            System.out.println(userAge);
            List<Paper>papers = paperMapper.getPapersByAgeLimit(userAge);
            for(Paper paper:papers){
                String doctorName=doctorInfoMapper.getDoctorNameByDid(paper.getDid());
                paper.setDoctorName(doctorName);
            }
            return papers;
        }
       return null;
    }

    public Paper newPaper(Paper paper){
        paperMapper.insertPaper(paper);
        return paper;
    }
}
