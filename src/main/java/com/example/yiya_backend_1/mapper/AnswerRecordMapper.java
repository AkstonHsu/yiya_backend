package com.example.yiya_backend_1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yiya_backend_1.entity.AnswerRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnswerRecordMapper extends BaseMapper<AnswerRecord> {

    @Select("SELECT correct_cnt FROM answerrecord " +
            "WHERE uid = #{uid} AND pid = #{pid} " +
            "ORDER BY test_date DESC LIMIT 1")
    Integer getCorrectCntByUidAndPid(Long uid, Long pid);
    @Select("SELECT * FROM answerrecord WHERE uid = #{uid}")
    List<AnswerRecord>getAnswerRecord(Long uid);

    @Select("SELECT COUNT(*) FROM answerrecord WHERE uid = #{uid}")
    int getTestCountByUserId( long uid);
}
