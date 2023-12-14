package com.example.yiya_backend_1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("doctorinfo")
public class DoctorInfo {
    @TableId(type = IdType.AUTO)
    private long did;

    private long uid;
    private String doctorName;
    private String sex;
    private String workPlace;

    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    @Override
    public String toString() {
        return "DoctorInfo{" +
                "did=" + did +
                ", uid=" + uid +
                ", doctorName='" + doctorName + '\'' +
                ", sex='" + sex + '\'' +
                ", workPlace='" + workPlace + '\'' +
                '}';
    }
}
