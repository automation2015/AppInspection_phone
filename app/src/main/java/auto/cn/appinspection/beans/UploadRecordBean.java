package auto.cn.appinspection.beans;

/**
 * 上传到服务器的巡检记录模型
 * //非空null字段 14
 * SELECT TOP 1000
 * [EWR_ID] ,[EWR_START_DATE],[EWR_END_DATE],[EWR_ORG_NAME],
 * [EWR_EIP_ID],[EWR_PLAN],[EWR_CYCLE_NAME],[EWR_CLASS_ID],
 * [EWR_CLASS_NAME],[EWR_PART_ID],[EWR_PART_NAME],[EWR_USER_NAME],
 * [EWR_EQUIPMENT_INFO] ,[EWR_UPLOAD_DATE]
 *   FROM [App_Inspection].[dbo].[PL_WORK_RECORD] order by EWR_START_DATE desc;
 *
 */
public class UploadRecordBean {
    private String EWR_ID,EWR_START_DATE,EWR_END_DATE,EWR_ORG_NAME,
            EWR_EIP_ID,EWR_PLAN,EWR_CYCLE_NAME, EWR_CLASS_ID,
            EWR_CLASS_NAME,EWR_PART_ID,EWR_PART_NAME,EWR_USER_NAME,
            EWR_EQUIPMENT_INFO,EWR_UPLOAD_DATE;

    public String getEWR_ID() {
        return EWR_ID;
    }

    public void setEWR_ID(String EWR_ID) {
        this.EWR_ID = EWR_ID;
    }

    public String getEWR_START_DATE() {
        return EWR_START_DATE;
    }

    public void setEWR_START_DATE(String EWR_START_DATE) {
        this.EWR_START_DATE = EWR_START_DATE;
    }

    public String getEWR_END_DATE() {
        return EWR_END_DATE;
    }

    public void setEWR_END_DATE(String EWR_END_DATE) {
        this.EWR_END_DATE = EWR_END_DATE;
    }

    public String getEWR_ORG_NAME() {
        return EWR_ORG_NAME;
    }

    public void setEWR_ORG_NAME(String EWR_ORG_NAME) {
        this.EWR_ORG_NAME = EWR_ORG_NAME;
    }

    public String getEWR_EIP_ID() {
        return EWR_EIP_ID;
    }

    public void setEWR_EIP_ID(String EWR_EIP_ID) {
        this.EWR_EIP_ID = EWR_EIP_ID;
    }

    public String getEWR_PLAN() {
        return EWR_PLAN;
    }

    public void setEWR_PLAN(String EWR_PLAN) {
        this.EWR_PLAN = EWR_PLAN;
    }



    public String getEWR_CYCLE_NAME() {
        return EWR_CYCLE_NAME;
    }

    public void setEWR_CYCLE_NAME(String EWR_CYCLE_NAME) {
        this.EWR_CYCLE_NAME = EWR_CYCLE_NAME;
    }

    public String getEWR_CLASS_ID() {
        return EWR_CLASS_ID;
    }

    public void setEWR_CLASS_ID(String EWR_CLASS_ID) {
        this.EWR_CLASS_ID = EWR_CLASS_ID;
    }

    public String getEWR_CLASS_NAME() {
        return EWR_CLASS_NAME;
    }

    public void setEWR_CLASS_NAME(String EWR_CLASS_NAME) {
        this.EWR_CLASS_NAME = EWR_CLASS_NAME;
    }

    public String getEWR_PART_ID() {
        return EWR_PART_ID;
    }

    public void setEWR_PART_ID(String EWR_PART_ID) {
        this.EWR_PART_ID = EWR_PART_ID;
    }

    public String getEWR_PART_NAME() {
        return EWR_PART_NAME;
    }

    public void setEWR_PART_NAME(String EWR_PART_NAME) {
        this.EWR_PART_NAME = EWR_PART_NAME;
    }

    public String getEWR_USER_NAME() {
        return EWR_USER_NAME;
    }

    public void setEWR_USER_NAME(String EWR_USER_NAME) {
        this.EWR_USER_NAME = EWR_USER_NAME;
    }

    public String getEWR_EQUIPMENT_INFO() {
        return EWR_EQUIPMENT_INFO;
    }

    public void setEWR_EQUIPMENT_INFO(String EWR_EQUIPMENT_INFO) {
        this.EWR_EQUIPMENT_INFO = EWR_EQUIPMENT_INFO;
    }

    public String getEWR_UPLOAD_DATE() {
        return EWR_UPLOAD_DATE;
    }

    public void setEWR_UPLOAD_DATE(String EWR_UPLOAD_DATE) {
        this.EWR_UPLOAD_DATE = EWR_UPLOAD_DATE;
    }

    @Override
    public String toString() {
        return "UploadRecordBean{" +
                "EWR_ID='" + EWR_ID + '\'' +
                ", EWR_START_DATE='" + EWR_START_DATE + '\'' +
                ", EWR_END_DATE='" + EWR_END_DATE + '\'' +
                ", EWR_ORG_NAME='" + EWR_ORG_NAME + '\'' +
                ", EWR_EIP_ID='" + EWR_EIP_ID + '\'' +
                ", EWR_PLAN='" + EWR_PLAN + '\'' +
                ", EWR_CYCLE_NAME='" + EWR_CYCLE_NAME + '\'' +
                ", EWR_CLASS_ID='" + EWR_CLASS_ID + '\'' +
                ", EWR_CLASS_NAME='" + EWR_CLASS_NAME + '\'' +
                ", EWR_PART_ID='" + EWR_PART_ID + '\'' +
                ", EWR_PART_NAME='" + EWR_PART_NAME + '\'' +
                ", EWR_USER_NAME='" + EWR_USER_NAME + '\'' +
                ", EWR_EQUIPMENT_INFO='" + EWR_EQUIPMENT_INFO + '\'' +
                ", EWR_UPLOAD_DATE='" + EWR_UPLOAD_DATE + '\'' +
                '}';
    }
}
