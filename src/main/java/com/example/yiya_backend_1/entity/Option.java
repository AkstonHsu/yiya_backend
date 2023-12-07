package com.example.yiya_backend_1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Option {
    @TableId(type = IdType.AUTO)
    private Long qid;
    private Long oid;
    private String optionLetter;
    private String content;
    private String src;
    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getOptionLetter() {
        return optionLetter;
    }

    public void setOptionLetter(String optionLetter) {
        this.optionLetter = optionLetter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "Option{" +
                "qid=" + qid +
                ", oid=" + oid +
                ", optionLetter='" + optionLetter + '\'' +
                ", content='" + content + '\'' +
                ", src='" + src + '\'' +
                '}';
    }
}
