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

    private int onlineConsultationCnt;

    private int paperProduceCnt;

    private String professionalTitle;
    private String department;
    private String graduateSchool;
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

    public int getOnlineConsultationCnt() {
        return onlineConsultationCnt;
    }

    public void setOnlineConsultationCnt(int onlineConsultationCnt) {
        this.onlineConsultationCnt = onlineConsultationCnt;
    }

    public int getPaperProduceCnt() {
        return paperProduceCnt;
    }

    public void setPaperProduceCnt(int paperProduceCnt) {
        this.paperProduceCnt = paperProduceCnt;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    @Override
    public String toString() {
        return "DoctorInfo{" +
                "did=" + did +
                ", uid=" + uid +
                ", doctorName='" + doctorName + '\'' +
                ", sex='" + sex + '\'' +
                ", workPlace='" + workPlace + '\'' +
                ", onlineConsultationCnt=" + onlineConsultationCnt +
                ", paperProduceCnt=" + paperProduceCnt +
                ", professionalTitle='" + professionalTitle + '\'' +
                ", department='" + department + '\'' +
                ", graduateSchool='" + graduateSchool + '\'' +
                '}';
    }
}
