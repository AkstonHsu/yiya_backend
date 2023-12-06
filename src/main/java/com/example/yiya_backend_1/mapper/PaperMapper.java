package com.example.yiya_backend_1.mapper;

import com.example.yiya_backend_1.entity.CompletePaper;
import com.example.yiya_backend_1.entity.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PaperMapper {
    @Select("SELECT * FROM paper WHERE pid = #{pid}")
    Paper getPaperById(Long pid);
    @Select("SELECT p.pid, p.title, p.description, p.source, p.amount, q.qid, q.pid as question_pid, q.question_type, q.question_title, q.question_audio, q.order, o.oid, o.qid as option_qid, o.option_letter, o.content\n" +
            "FROM paper p\n" +
            "LEFT JOIN question q ON p.pid = q.pid\n" +
            "LEFT JOIN option o ON q.qid = o.qid\n" +
            "WHERE p.pid = #{pid}\n" +
            "ORDER BY q.order, o.option_letter;\n")
    CompletePaper getcompletePaperById(long pid);

}
