package auto.cn.appinspection.beans;

/**
 * 上传到服务器的巡检记录数据详情模型
 * //非空null字段 22
 * SELECT TOP 1000
 * [EWRI_ID],[EWRI_EWR_ID] ,[EWRI_START_DATE] ,[EWRI_ORG_NAME]
 * ,[EWRI_EIA_ID],[EWRI_AREA],[EWRI_MEI_ID],[EWRI_EQUIPMENT]
 * ,[EWRI_EIP_ID],[EWRI_PART] ,[EWRI_EII_ID],[EWRI_ITEM]
 * ,[EWRI_EIC_ID],[EWRI_CONTENT],[EWRI_IS_QUANTIFY],[EWRI_WAY]
 * ,[EWRI_STANDARD],[EWRI_VALUE],[EWRI_UNIT],[EWRI_IS_DEFECT]
 * ,[EWRI_EQUIPMENT_STATUS],[EWRI_PART_ID]
 * FROM [App_Inspection].[dbo].[PL_WORK_RECORD_ITEM] order by EWRI_START_DATE desc;
 */

public class UploadRecordItemBean {
    private String EWRI_ID,EWRI_EWR_ID,EWRI_START_DATE,EWRI_ORG_NAME,
            EWRI_EIA_ID,EWRI_AREA,EWRI_MEI_ID,EWRI_EQUIPMENT,
            EWRI_EIP_ID,EWRI_PART,EWRI_EII_ID,EWRI_ITEM,
            EWRI_EIC_ID,EWRI_CONTENT,EWRI_IS_QUANTIFY,EWRI_WAY,
            EWRI_STANDARD,EWRI_VALUE,EWRI_UNIT,EWRI_IS_DEFECT,
            EWRI_EQUIPMENT_STATUS,EWRI_PART_ID;

    @Override
    public String toString() {
        return "UploadRecordItemBean{" +
                "WRI_ID='" + EWRI_ID + '\'' +
                ", EWRI_EWR_ID='" + EWRI_EWR_ID + '\'' +
                ", EWRI_START_DATE='" + EWRI_START_DATE + '\'' +
                ", EWRI_ORG_NAME='" + EWRI_ORG_NAME + '\'' +
                ", EWRI_EIA_ID='" + EWRI_EIA_ID + '\'' +
                ", EWRI_AREA='" + EWRI_AREA + '\'' +
                ", EWRI_MEI_ID='" + EWRI_MEI_ID + '\'' +
                ", EWRI_EQUIPMENT='" + EWRI_EQUIPMENT + '\'' +
                ", EWRI_EIP_ID='" + EWRI_EIP_ID + '\'' +
                ", EWRI_PART='" + EWRI_PART + '\'' +
                ", EWRI_EII_ID='" + EWRI_EII_ID + '\'' +
                ", EWRI_ITEM='" + EWRI_ITEM + '\'' +
                ", EWRI_EIC_ID='" + EWRI_EIC_ID + '\'' +
                ", EWRI_CONTENT='" + EWRI_CONTENT + '\'' +
                ", EWRI_IS_QUANTIFY='" + EWRI_IS_QUANTIFY + '\'' +
                ", EWRI_WAY='" + EWRI_WAY + '\'' +
                ", EWRI_STANDARD='" + EWRI_STANDARD + '\'' +
                ", EWRI_VALUE='" + EWRI_VALUE + '\'' +
                ", EWRI_UNIT='" + EWRI_UNIT + '\'' +
                ", EWRI_IS_DEFECT='" + EWRI_IS_DEFECT + '\'' +
                ", EWRI_EQUIPMENT_STATUS='" + EWRI_EQUIPMENT_STATUS + '\'' +
                ", EWRI_PART_ID='" + EWRI_PART_ID + '\'' +
                '}';
    }

    public String getEWRI_ID() {
        return EWRI_ID;
    }

    public void setEWRI_ID(String EWRI_ID) {
        this.EWRI_ID = EWRI_ID;
    }

    public String getEWRI_EWR_ID() {
        return EWRI_EWR_ID;
    }

    public void setEWRI_EWR_ID(String EWRI_EWR_ID) {
        this.EWRI_EWR_ID = EWRI_EWR_ID;
    }

    public String getEWRI_START_DATE() {
        return EWRI_START_DATE;
    }

    public void setEWRI_START_DATE(String EWRI_START_DATE) {
        this.EWRI_START_DATE = EWRI_START_DATE;
    }

    public String getEWRI_ORG_NAME() {
        return EWRI_ORG_NAME;
    }

    public void setEWRI_ORG_NAME(String EWRI_ORG_NAME) {
        this.EWRI_ORG_NAME = EWRI_ORG_NAME;
    }

    public String getEWRI_EIA_ID() {
        return EWRI_EIA_ID;
    }

    public void setEWRI_EIA_ID(String EWRI_EIA_ID) {
        this.EWRI_EIA_ID = EWRI_EIA_ID;
    }

    public String getEWRI_AREA() {
        return EWRI_AREA;
    }

    public void setEWRI_AREA(String EWRI_AREA) {
        this.EWRI_AREA = EWRI_AREA;
    }

    public String getEWRI_MEI_ID() {
        return EWRI_MEI_ID;
    }

    public void setEWRI_MEI_ID(String EWRI_MEI_ID) {
        this.EWRI_MEI_ID = EWRI_MEI_ID;
    }

    public String getEWRI_EQUIPMENT() {
        return EWRI_EQUIPMENT;
    }

    public void setEWRI_EQUIPMENT(String EWRI_EQUIPMENT) {
        this.EWRI_EQUIPMENT = EWRI_EQUIPMENT;
    }

    public String getEWRI_EIP_ID() {
        return EWRI_EIP_ID;
    }

    public void setEWRI_EIP_ID(String EWRI_EIP_ID) {
        this.EWRI_EIP_ID = EWRI_EIP_ID;
    }

    public String getEWRI_PART() {
        return EWRI_PART;
    }

    public void setEWRI_PART(String EWRI_PART) {
        this.EWRI_PART = EWRI_PART;
    }

    public String getEWRI_EII_ID() {
        return EWRI_EII_ID;
    }

    public void setEWRI_EII_ID(String EWRI_EII_ID) {
        this.EWRI_EII_ID = EWRI_EII_ID;
    }

    public String getEWRI_ITEM() {
        return EWRI_ITEM;
    }

    public void setEWRI_ITEM(String EWRI_ITEM) {
        this.EWRI_ITEM = EWRI_ITEM;
    }

    public String getEWRI_EIC_ID() {
        return EWRI_EIC_ID;
    }

    public void setEWRI_EIC_ID(String EWRI_EIC_ID) {
        this.EWRI_EIC_ID = EWRI_EIC_ID;
    }

    public String getEWRI_CONTENT() {
        return EWRI_CONTENT;
    }

    public void setEWRI_CONTENT(String EWRI_CONTENT) {
        this.EWRI_CONTENT = EWRI_CONTENT;
    }

    public String getEWRI_IS_QUANTIFY() {
        return EWRI_IS_QUANTIFY;
    }

    public void setEWRI_IS_QUANTIFY(String EWRI_IS_QUANTIFY) {
        this.EWRI_IS_QUANTIFY = EWRI_IS_QUANTIFY;
    }

    public String getEWRI_WAY() {
        return EWRI_WAY;
    }

    public void setEWRI_WAY(String EWRI_WAY) {
        this.EWRI_WAY = EWRI_WAY;
    }

    public String getEWRI_STANDARD() {
        return EWRI_STANDARD;
    }

    public void setEWRI_STANDARD(String EWRI_STANDARD) {
        this.EWRI_STANDARD = EWRI_STANDARD;
    }

    public String getEWRI_VALUE() {
        return EWRI_VALUE;
    }

    public void setEWRI_VALUE(String EWRI_VALUE) {
        this.EWRI_VALUE = EWRI_VALUE;
    }

    public String getEWRI_UNIT() {
        return EWRI_UNIT;
    }

    public void setEWRI_UNIT(String EWRI_UNIT) {
        this.EWRI_UNIT = EWRI_UNIT;
    }

    public String getEWRI_IS_DEFECT() {
        return EWRI_IS_DEFECT;
    }

    public void setEWRI_IS_DEFECT(String EWRI_IS_DEFECT) {
        this.EWRI_IS_DEFECT = EWRI_IS_DEFECT;
    }

    public String getEWRI_EQUIPMENT_STATUS() {
        return EWRI_EQUIPMENT_STATUS;
    }

    public void setEWRI_EQUIPMENT_STATUS(String EWRI_EQUIPMENT_STATUS) {
        this.EWRI_EQUIPMENT_STATUS = EWRI_EQUIPMENT_STATUS;
    }

    public String getEWRI_PART_ID() {
        return EWRI_PART_ID;
    }

    public void setEWRI_PART_ID(String EWRI_PART_ID) {
        this.EWRI_PART_ID = EWRI_PART_ID;
    }
}
