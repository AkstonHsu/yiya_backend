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
                        question.getCorrectAnswer(),
                        question.getOrder(),
                        options
                );
                completeQuestions.add(completeQuestion);
            }
            String doctorName=doctorInfoMapper.getDoctorNameByDid(paper.getDid());
            paper.setDoctorName(doctorName);
            // 构建完整试卷对象
            CompletePaper completePaper = new CompletePaper(
                    paper.getPid(),
                    paper.getTitle(),
                    paper.getDescription(),
                    paper.getSource(),
                    paper.getPaperAudio(),
                    paper.getAmount(),
                    paper.getDoctorName(),
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

    public boolean deletePaper(long pid) {
        // 执行删除操作，这里假设删除成功返回true，否则返回false
        int affectedRows = paperMapper.deleteAnswerRecord(pid);
        int af= paperMapper.deletePaper(pid);
        return affectedRows > 0;
    }

    public List<Paper>searchPaper(String title,String source){
        return paperMapper.searchPaper(title,source);
    }
//    /**
//     * 保存音频文件到相对于项目的路径
//     *
//     * @param audioFile 音频文件
//     * @param pid       试卷ID
//     * @return 是否保存成功
//     */
//    private boolean saveAudioFile(MultipartFile audioFile, long pid) {
//        try {
//            // 创建文件保存路径，包含试卷ID
//            String fileName = StringUtils.cleanPath(Objects.requireNonNull(audioFile.getOriginalFilename()));
//            Path filePath = Paths.get(staticDir, "test", "temp", String.valueOf(pid), fileName);
//
//            // 创建目录
//            Files.createDirectories(filePath.getParent());
//
//            // 保存文件
//            audioFile.transferTo(filePath.toFile());
//
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 上传试卷，包括保存音频文件和插入试卷信息
//     *
//     * @param paper     试卷信息
//     * @param audioFile 试卷音频文件
//     * @return 是否上传成功
//     */
//    public boolean uploadPaper(Paper paper, MultipartFile audioFile) {
//        try {
//            // 保存音频文件
//            if (saveAudioFile(audioFile, paper.getPid())) {
//                // 设置试卷的音频路径
//                paper.setPaperAudio("http://localhost:8082/test/temp/" + String.valueOf(paper.getPid()) + "/" + Objects.requireNonNull(audioFile.getOriginalFilename()));
//            } else {
//                // 处理文件保存失败的情况
//                return false;
//            }
//
//            // 调用Mapper插入试卷
//            int rowsInserted = paperMapper.insertPaper(paper);
//
//            // 返回插入结果
//            return rowsInserted > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
