package com.example.yiya_backend_1.mapper;

import com.example.yiya_backend_1.entity.Option;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OptionMapper {
    @Select("SELECT * FROM qoption WHERE qid = #{qid}")
    List<Option> getOptionByQuestionId(long qid);
}
