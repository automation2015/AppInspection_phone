package auto.cn.appinspection.beans;

import java.util.List;

public class PlanBean {


    /**
     * id : 46
     * PLAN_ID : jh20190306001
     * PLAN_NAME : 白班35KV电气室点检计划
     * PLAN_ORG_NAME :
     * PLAN_PART_NAME : 电气岗位点检员
     * PLAN_PART_ID : 10
     * PLAN_NUM : 1
     * PLAN_CYCLE_TYPE : 1
     * PLAN_LAST_DATE : 2020/8/3 8:36:29
     * PLAN_LAST_USER_NAME :
     * PLAN_CREATE_DATE : 2019/3/6 0:00:00
     * Valid_Flag : True
     * Shift : 每日1班
     * CODE_NAME : 0
     * areaList : [{"PL_AREA_ID":1,"PL_AREA_NAME":"35KV四楼主电室（MCC）","PL_AREA_LABEL":"YXBD04","PL_AREA_CREATE_ID":"1","PL_AREA_CREATE_DATE":"2017/8/1 22:59:24","Valid_Flag":1,"PlanId":"jh20190306001","EquipList":[{"EL_NAME":"35KV四楼主电室MCC柜（RHF、BD、漩流井）","EL_Depart_Name":"中型运行车间","EL_ID":"xgyxdq012","EL_Type_Name":"电气通用","EL_EIS_ID":"bz20170801021","EL_EIS_NAME":"主电室MCC柜点检标准2","EL_VALID_FLAG":"1","PL_AREA_ID":1,"PartList":[{"id":21,"PART_ID":"bw20170801021","PART_BZ_ID":"bz20170801021","PART_NAME":"MCC柜","PART_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True"}],"ItemList":[{"id":73,"ITEM_ID":"xm20170801073","ITEM_PART_ID":"bw20170801021","ITEM_PL_BZ_ID":"bz20170801021","ITEM_NAME":"电器柜","ITEM_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True","ContentList":[{"id":91,"CONTENT_ID":"nr20170801091","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"元器件运行是否正常、有无异音异味","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"113","CONTENT_IS_USE":1,"CONTENT_STANDARD":"运行正常、无异音异味","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视、闻味"},{"id":92,"CONTENT_ID":"nr20170801092","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"仪表指示灯是否正常","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"电流、电压、信号指示正常","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"},{"id":93,"CONTENT_ID":"nr20170801093","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"室内环境温度","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"114","CONTENT_IS_USE":1,"CONTENT_STANDARD":"温度低于40度，湿度小于50，无异常气味","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"35","CONTENT_ALARM_H2":"45","CONTENT_ALARM_STYLE":"高报","Valid_Flag":"True","CODE_NAME":"测量、目视"},{"id":94,"CONTENT_ID":"nr20170801094","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"按钮与手柄是否缺失","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"按钮与手柄是无缺失","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"}]}]}]},{"PL_AREA_ID":2,"PL_AREA_NAME":"35KV四楼辅传动室（S120）","PL_AREA_LABEL":"YXBD05","PL_AREA_CREATE_ID":"1","PL_AREA_CREATE_DATE":"2017/8/1 22:59:24","Valid_Flag":1,"PlanId":"jh20190306001","EquipList":[{"EL_NAME":"35KV四楼辅传动柜（RHF区、BD区、FM区）","EL_Depart_Name":"中型运行车间","EL_ID":"xgyxdq013","EL_Type_Name":"粗轧区","EL_EIS_ID":"bz20170801022","EL_EIS_NAME":"西门子S120系统点检标准","EL_VALID_FLAG":"1","PL_AREA_ID":2,"PartList":[{"id":22,"PART_ID":"bw20170801022","PART_BZ_ID":"bz20170801022","PART_NAME":"传动室变频器系统","PART_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True"}],"ItemList":[{"id":74,"ITEM_ID":"xm20170801074","ITEM_PART_ID":"bw20170801022","ITEM_PL_BZ_ID":"bz20170801022","ITEM_NAME":"水冷空调","ITEM_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True","ContentList":[{"id":95,"CONTENT_ID":"nr20170801095","CONTENT_PL_BZ_ID":"bz20170801022","CONTENT_PART_ID":"bw20170801022","CONTENT_ITEM_ID":"xm20170801074","CONTENT_NAME":"风机电机运行情况","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"115","CONTENT_IS_USE":1,"CONTENT_STANDARD":"运行平稳，无异音","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视,听音"}]},{"id":75,"ITEM_ID":"xm20170801075","ITEM_PART_ID":"bw20170801022","ITEM_PL_BZ_ID":"bz20170801022","ITEM_NAME":"室温","ITEM_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True","ContentList":[{"id":96,"CONTENT_ID":"nr20170801096","CONTENT_PL_BZ_ID":"bz20170801022","CONTENT_PART_ID":"bw20170801022","CONTENT_ITEM_ID":"xm20170801075","CONTENT_NAME":"运行环境","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"114","CONTENT_IS_USE":1,"CONTENT_STANDARD":"温度低于40度，湿度小于50，无异常气味","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"35","CONTENT_ALARM_H2":"45","CONTENT_ALARM_STYLE":"高报","Valid_Flag":"True","CODE_NAME":"测量、目视"}]},{"id":76,"ITEM_ID":"xm20170801076","ITEM_PART_ID":"bw20170801022","ITEM_PL_BZ_ID":"bz20170801022","ITEM_NAME":"变频器","ITEM_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True","ContentList":[{"id":97,"CONTENT_ID":"nr20170801097","CONTENT_PL_BZ_ID":"bz20170801022","CONTENT_PART_ID":"bw20170801022","CONTENT_ITEM_ID":"xm20170801076","CONTENT_NAME":"CU控制器面板是否有报警","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"面板无报警，电压电流正常","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"},{"id":98,"CONTENT_ID":"nr20170801098","CONTENT_PL_BZ_ID":"bz20170801022","CONTENT_PART_ID":"bw20170801022","CONTENT_ITEM_ID":"xm20170801076","CONTENT_NAME":"风机","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"运行平稳无异音","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"}]},{"id":77,"ITEM_ID":"xm20170801077","ITEM_PART_ID":"bw20170801022","ITEM_PL_BZ_ID":"bz20170801022","ITEM_NAME":"功率柜","ITEM_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True","ContentList":[{"id":99,"CONTENT_ID":"nr20170801099","CONTENT_PL_BZ_ID":"bz20170801022","CONTENT_PART_ID":"bw20170801022","CONTENT_ITEM_ID":"xm20170801077","CONTENT_NAME":"柜面指示灯是否正常","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"指示灯正常，与现场实际情况相符合","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"},{"id":100,"CONTENT_ID":"nr20170801100","CONTENT_PL_BZ_ID":"bz20170801022","CONTENT_PART_ID":"bw20170801022","CONTENT_ITEM_ID":"xm20170801077","CONTENT_NAME":"仪表指示是否正常","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"仪表指示正常","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"},{"id":101,"CONTENT_ID":"nr20170801101","CONTENT_PL_BZ_ID":"bz20170801022","CONTENT_PART_ID":"bw20170801022","CONTENT_ITEM_ID":"xm20170801077","CONTENT_NAME":"风机运行是否正常","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"运行平稳无异音","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"}]}]}]},{"PL_AREA_ID":3,"PL_AREA_NAME":"35KV一楼主电室（MCC）","PL_AREA_LABEL":"YXBD03","PL_AREA_CREATE_ID":"1","PL_AREA_CREATE_DATE":"2017/8/1 22:59:24","Valid_Flag":1,"PlanId":"jh20190306001","EquipList":[{"EL_NAME":"35KV一楼主电室MCC柜（RHF、BD、漩流井）","EL_Depart_Name":"中型运行车间","EL_ID":"xgyxdq011","EL_Type_Name":"电气通用","EL_EIS_ID":"bz20170801020","EL_EIS_NAME":"主电室MCC柜点检标准1","EL_VALID_FLAG":"1","PL_AREA_ID":3,"PartList":[{"id":20,"PART_ID":"bw20170801020","PART_BZ_ID":"bz20170801020","PART_NAME":"MCC柜","PART_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True"}],"ItemList":[{"id":71,"ITEM_ID":"xm20170801071","ITEM_PART_ID":"bw20170801020","ITEM_PL_BZ_ID":"bz20170801020","ITEM_NAME":"电器柜","ITEM_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True","ContentList":[{"id":85,"CONTENT_ID":"nr20170801085","CONTENT_PL_BZ_ID":"bz20170801020","CONTENT_PART_ID":"bw20170801020","CONTENT_ITEM_ID":"xm20170801071","CONTENT_NAME":"元器件运行是否正常、有无异音异味","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"113","CONTENT_IS_USE":1,"CONTENT_STANDARD":"运行正常、无异音异味","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视、闻味"},{"id":86,"CONTENT_ID":"nr20170801086","CONTENT_PL_BZ_ID":"bz20170801020","CONTENT_PART_ID":"bw20170801020","CONTENT_ITEM_ID":"xm20170801071","CONTENT_NAME":"仪表指示灯是否正常","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"电流、电压、信号指示正常","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"},{"id":87,"CONTENT_ID":"nr20170801087","CONTENT_PL_BZ_ID":"bz20170801020","CONTENT_PART_ID":"bw20170801020","CONTENT_ITEM_ID":"xm20170801071","CONTENT_NAME":"室内环境温度","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"114","CONTENT_IS_USE":1,"CONTENT_STANDARD":"温度低于40度，湿度小于50，无异常气味","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"35","CONTENT_ALARM_H2":"45","CONTENT_ALARM_STYLE":"高报","Valid_Flag":"True","CODE_NAME":"测量、目视"},{"id":88,"CONTENT_ID":"nr20170801088","CONTENT_PL_BZ_ID":"bz20170801020","CONTENT_PART_ID":"bw20170801020","CONTENT_ITEM_ID":"xm20170801071","CONTENT_NAME":"按钮与手柄是否缺失","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"按钮与手柄是无缺失","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"}]},{"id":72,"ITEM_ID":"xm20170801072","ITEM_PART_ID":"bw20170801020","ITEM_PL_BZ_ID":"bz20170801020","ITEM_NAME":"变频器、软起柜","ITEM_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True","ContentList":[{"id":89,"CONTENT_ID":"nr20170801089","CONTENT_PL_BZ_ID":"bz20170801020","CONTENT_PART_ID":"bw20170801020","CONTENT_ITEM_ID":"xm20170801072","CONTENT_NAME":"面板是否有报警","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"面板无报警，电压电流正常","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"},{"id":90,"CONTENT_ID":"nr20170801090","CONTENT_PL_BZ_ID":"bz20170801020","CONTENT_PART_ID":"bw20170801020","CONTENT_ITEM_ID":"xm20170801072","CONTENT_NAME":"运行是否正常、无异音异味","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"113","CONTENT_IS_USE":1,"CONTENT_STANDARD":"运行正常、无异音异味","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视、闻味"}]}]}]},{"PL_AREA_ID":4,"PL_AREA_NAME":"BD主传动室（S150）","PL_AREA_LABEL":"YXBD13","PL_AREA_CREATE_ID":"1","PL_AREA_CREATE_DATE":"2017/8/1 22:59:24","Valid_Flag":1,"PlanId":"jh20190306001","EquipList":[{"EL_NAME":"BD传动室变频器系统（S150）","EL_Depart_Name":"中型运行车间","EL_ID":"xgyxdq049","EL_Type_Name":"粗轧区","EL_EIS_ID":"bz20170801023","EL_EIS_NAME":"西门子S150系统点检标准","EL_VALID_FLAG":"1","PL_AREA_ID":4,"PartList":[{"id":23,"PART_ID":"bw20170801023","PART_BZ_ID":"bz20170801023","PART_NAME":"变频器柜","PART_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True"}],"ItemList":[{"id":78,"ITEM_ID":"xm20170801078","ITEM_PART_ID":"bw20170801023","ITEM_PL_BZ_ID":"bz20170801023","ITEM_NAME":"变频器","ITEM_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True","ContentList":[{"id":102,"CONTENT_ID":"nr20170801102","CONTENT_PL_BZ_ID":"bz20170801023","CONTENT_PART_ID":"bw20170801023","CONTENT_ITEM_ID":"xm20170801078","CONTENT_NAME":"面板是否有报警","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"面板无报警，电压电流正常","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"},{"id":103,"CONTENT_ID":"nr20170801103","CONTENT_PL_BZ_ID":"bz20170801023","CONTENT_PART_ID":"bw20170801023","CONTENT_ITEM_ID":"xm20170801078","CONTENT_NAME":"水冷系统","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"运行平稳无异音，控制柜内面板无报警","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"}]},{"id":79,"ITEM_ID":"xm20170801079","ITEM_PART_ID":"bw20170801023","ITEM_PL_BZ_ID":"bz20170801023","ITEM_NAME":"室温","ITEM_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True","ContentList":[{"id":104,"CONTENT_ID":"nr20170801104","CONTENT_PL_BZ_ID":"bz20170801023","CONTENT_PART_ID":"bw20170801023","CONTENT_ITEM_ID":"xm20170801079","CONTENT_NAME":"运行环境","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"114","CONTENT_IS_USE":1,"CONTENT_STANDARD":"温度低于40度，湿度小于50，无异常气味","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"35","CONTENT_ALARM_H2":"45","CONTENT_ALARM_STYLE":"高报","Valid_Flag":"True","CODE_NAME":"测量、目视"}]}]}]}]
     */

    private String id;
    private String PLAN_ID;
    private String PLAN_NAME;
    private String PLAN_ORG_NAME;
    private String PLAN_PART_NAME;
    private String PLAN_PART_ID;
    private String PLAN_NUM;
    private String PLAN_CYCLE_TYPE;
    private String PLAN_LAST_DATE;
    private String PLAN_LAST_USER_NAME;
    private String PLAN_CREATE_DATE;
    private String Valid_Flag;
    private String Shift;
    private String CODE_NAME;
    private List<AreaListBean> areaList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPLAN_ID() {
        return PLAN_ID;
    }

    public void setPLAN_ID(String PLAN_ID) {
        this.PLAN_ID = PLAN_ID;
    }

    public String getPLAN_NAME() {
        return PLAN_NAME;
    }

    public void setPLAN_NAME(String PLAN_NAME) {
        this.PLAN_NAME = PLAN_NAME;
    }

    public String getPLAN_ORG_NAME() {
        return PLAN_ORG_NAME;
    }

    public void setPLAN_ORG_NAME(String PLAN_ORG_NAME) {
        this.PLAN_ORG_NAME = PLAN_ORG_NAME;
    }

    public String getPLAN_PART_NAME() {
        return PLAN_PART_NAME;
    }

    public void setPLAN_PART_NAME(String PLAN_PART_NAME) {
        this.PLAN_PART_NAME = PLAN_PART_NAME;
    }

    public String getPLAN_PART_ID() {
        return PLAN_PART_ID;
    }

    public void setPLAN_PART_ID(String PLAN_PART_ID) {
        this.PLAN_PART_ID = PLAN_PART_ID;
    }

    public String getPLAN_NUM() {
        return PLAN_NUM;
    }

    public void setPLAN_NUM(String PLAN_NUM) {
        this.PLAN_NUM = PLAN_NUM;
    }

    public String getPLAN_CYCLE_TYPE() {
        return PLAN_CYCLE_TYPE;
    }

    public void setPLAN_CYCLE_TYPE(String PLAN_CYCLE_TYPE) {
        this.PLAN_CYCLE_TYPE = PLAN_CYCLE_TYPE;
    }

    public String getPLAN_LAST_DATE() {
        return PLAN_LAST_DATE;
    }

    public void setPLAN_LAST_DATE(String PLAN_LAST_DATE) {
        this.PLAN_LAST_DATE = PLAN_LAST_DATE;
    }

    public String getPLAN_LAST_USER_NAME() {
        return PLAN_LAST_USER_NAME;
    }

    public void setPLAN_LAST_USER_NAME(String PLAN_LAST_USER_NAME) {
        this.PLAN_LAST_USER_NAME = PLAN_LAST_USER_NAME;
    }

    public String getPLAN_CREATE_DATE() {
        return PLAN_CREATE_DATE;
    }

    public void setPLAN_CREATE_DATE(String PLAN_CREATE_DATE) {
        this.PLAN_CREATE_DATE = PLAN_CREATE_DATE;
    }

    public String getValid_Flag() {
        return Valid_Flag;
    }

    public void setValid_Flag(String Valid_Flag) {
        this.Valid_Flag = Valid_Flag;
    }

    public String getShift() {
        return Shift;
    }

    public void setShift(String Shift) {
        this.Shift = Shift;
    }

    public String getCODE_NAME() {
        return CODE_NAME;
    }

    public void setCODE_NAME(String CODE_NAME) {
        this.CODE_NAME = CODE_NAME;
    }

    public List<AreaListBean> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaListBean> areaList) {
        this.areaList = areaList;
    }

    public static class AreaListBean {
        /**
         * PL_AREA_ID : 1
         * PL_AREA_NAME : 35KV四楼主电室（MCC）
         * PL_AREA_LABEL : YXBD04
         * PL_AREA_CREATE_ID : 1
         * PL_AREA_CREATE_DATE : 2017/8/1 22:59:24
         * Valid_Flag : 1
         * PlanId : jh20190306001
         * EquipList : [{"EL_NAME":"35KV四楼主电室MCC柜（RHF、BD、漩流井）","EL_Depart_Name":"中型运行车间","EL_ID":"xgyxdq012","EL_Type_Name":"电气通用","EL_EIS_ID":"bz20170801021","EL_EIS_NAME":"主电室MCC柜点检标准2","EL_VALID_FLAG":"1","PL_AREA_ID":1,"PartList":[{"id":21,"PART_ID":"bw20170801021","PART_BZ_ID":"bz20170801021","PART_NAME":"MCC柜","PART_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True"}],"ItemList":[{"id":73,"ITEM_ID":"xm20170801073","ITEM_PART_ID":"bw20170801021","ITEM_PL_BZ_ID":"bz20170801021","ITEM_NAME":"电器柜","ITEM_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True","ContentList":[{"id":91,"CONTENT_ID":"nr20170801091","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"元器件运行是否正常、有无异音异味","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"113","CONTENT_IS_USE":1,"CONTENT_STANDARD":"运行正常、无异音异味","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视、闻味"},{"id":92,"CONTENT_ID":"nr20170801092","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"仪表指示灯是否正常","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"电流、电压、信号指示正常","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"},{"id":93,"CONTENT_ID":"nr20170801093","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"室内环境温度","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"114","CONTENT_IS_USE":1,"CONTENT_STANDARD":"温度低于40度，湿度小于50，无异常气味","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"35","CONTENT_ALARM_H2":"45","CONTENT_ALARM_STYLE":"高报","Valid_Flag":"True","CODE_NAME":"测量、目视"},{"id":94,"CONTENT_ID":"nr20170801094","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"按钮与手柄是否缺失","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"按钮与手柄是无缺失","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"}]}]}]
         */

        private int PL_AREA_ID;
        private String PL_AREA_NAME;
        private String PL_AREA_LABEL;
        private String PL_AREA_CREATE_ID;
        private String PL_AREA_CREATE_DATE;
        private int Valid_Flag;
        private String PlanId;
        private List<EquipListBean> EquipList;

        public int getPL_AREA_ID() {
            return PL_AREA_ID;
        }

        public void setPL_AREA_ID(int PL_AREA_ID) {
            this.PL_AREA_ID = PL_AREA_ID;
        }

        public String getPL_AREA_NAME() {
            return PL_AREA_NAME;
        }

        public void setPL_AREA_NAME(String PL_AREA_NAME) {
            this.PL_AREA_NAME = PL_AREA_NAME;
        }

        public String getPL_AREA_LABEL() {
            return PL_AREA_LABEL;
        }

        public void setPL_AREA_LABEL(String PL_AREA_LABEL) {
            this.PL_AREA_LABEL = PL_AREA_LABEL;
        }

        public String getPL_AREA_CREATE_ID() {
            return PL_AREA_CREATE_ID;
        }

        public void setPL_AREA_CREATE_ID(String PL_AREA_CREATE_ID) {
            this.PL_AREA_CREATE_ID = PL_AREA_CREATE_ID;
        }

        public String getPL_AREA_CREATE_DATE() {
            return PL_AREA_CREATE_DATE;
        }

        public void setPL_AREA_CREATE_DATE(String PL_AREA_CREATE_DATE) {
            this.PL_AREA_CREATE_DATE = PL_AREA_CREATE_DATE;
        }

        public int getValid_Flag() {
            return Valid_Flag;
        }

        public void setValid_Flag(int Valid_Flag) {
            this.Valid_Flag = Valid_Flag;
        }

        public String getPlanId() {
            return PlanId;
        }

        public void setPlanId(String PlanId) {
            this.PlanId = PlanId;
        }

        public List<EquipListBean> getEquipList() {
            return EquipList;
        }

        public void setEquipList(List<EquipListBean> EquipList) {
            this.EquipList = EquipList;
        }

        public static class EquipListBean {
            /**
             * EL_NAME : 35KV四楼主电室MCC柜（RHF、BD、漩流井）
             * EL_Depart_Name : 中型运行车间
             * EL_ID : xgyxdq012
             * EL_Type_Name : 电气通用
             * EL_EIS_ID : bz20170801021
             * EL_EIS_NAME : 主电室MCC柜点检标准2
             * EL_VALID_FLAG : 1
             * PL_AREA_ID : 1
             * PartList : [{"id":21,"PART_ID":"bw20170801021","PART_BZ_ID":"bz20170801021","PART_NAME":"MCC柜","PART_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True"}]
             * ItemList : [{"id":73,"ITEM_ID":"xm20170801073","ITEM_PART_ID":"bw20170801021","ITEM_PL_BZ_ID":"bz20170801021","ITEM_NAME":"电器柜","ITEM_CREATE_DATE":"2017/8/1 0:00:00","Valid_Flag":"True","ContentList":[{"id":91,"CONTENT_ID":"nr20170801091","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"元器件运行是否正常、有无异音异味","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"113","CONTENT_IS_USE":1,"CONTENT_STANDARD":"运行正常、无异音异味","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视、闻味"},{"id":92,"CONTENT_ID":"nr20170801092","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"仪表指示灯是否正常","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"电流、电压、信号指示正常","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"},{"id":93,"CONTENT_ID":"nr20170801093","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"室内环境温度","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"114","CONTENT_IS_USE":1,"CONTENT_STANDARD":"温度低于40度，湿度小于50，无异常气味","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"35","CONTENT_ALARM_H2":"45","CONTENT_ALARM_STYLE":"高报","Valid_Flag":"True","CODE_NAME":"测量、目视"},{"id":94,"CONTENT_ID":"nr20170801094","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"按钮与手柄是否缺失","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"按钮与手柄是无缺失","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"}]}]
             */

            private String EL_NAME;
            private String EL_Depart_Name;
            private String EL_ID;
            private String EL_Type_Name;
            private String EL_EIS_ID;
            private String EL_EIS_NAME;
            private String EL_VALID_FLAG;
            private int PL_AREA_ID;
            private List<PartListBean> PartList;
            private List<ItemListBean> ItemList;

            public String getEL_NAME() {
                return EL_NAME;
            }

            public void setEL_NAME(String EL_NAME) {
                this.EL_NAME = EL_NAME;
            }

            public String getEL_Depart_Name() {
                return EL_Depart_Name;
            }

            public void setEL_Depart_Name(String EL_Depart_Name) {
                this.EL_Depart_Name = EL_Depart_Name;
            }

            public String getEL_ID() {
                return EL_ID;
            }

            public void setEL_ID(String EL_ID) {
                this.EL_ID = EL_ID;
            }

            public String getEL_Type_Name() {
                return EL_Type_Name;
            }

            public void setEL_Type_Name(String EL_Type_Name) {
                this.EL_Type_Name = EL_Type_Name;
            }

            public String getEL_EIS_ID() {
                return EL_EIS_ID;
            }

            public void setEL_EIS_ID(String EL_EIS_ID) {
                this.EL_EIS_ID = EL_EIS_ID;
            }

            public String getEL_EIS_NAME() {
                return EL_EIS_NAME;
            }

            public void setEL_EIS_NAME(String EL_EIS_NAME) {
                this.EL_EIS_NAME = EL_EIS_NAME;
            }

            public String getEL_VALID_FLAG() {
                return EL_VALID_FLAG;
            }

            public void setEL_VALID_FLAG(String EL_VALID_FLAG) {
                this.EL_VALID_FLAG = EL_VALID_FLAG;
            }

            public int getPL_AREA_ID() {
                return PL_AREA_ID;
            }

            public void setPL_AREA_ID(int PL_AREA_ID) {
                this.PL_AREA_ID = PL_AREA_ID;
            }

            public List<PartListBean> getPartList() {
                return PartList;
            }

            public void setPartList(List<PartListBean> PartList) {
                this.PartList = PartList;
            }

            public List<ItemListBean> getItemList() {
                return ItemList;
            }

            public void setItemList(List<ItemListBean> ItemList) {
                this.ItemList = ItemList;
            }

            public static class PartListBean {
                /**
                 * id : 21
                 * PART_ID : bw20170801021
                 * PART_BZ_ID : bz20170801021
                 * PART_NAME : MCC柜
                 * PART_CREATE_DATE : 2017/8/1 0:00:00
                 * Valid_Flag : True
                 */

                private int id;
                private String PART_ID;
                private String PART_BZ_ID;
                private String PART_NAME;
                private String PART_CREATE_DATE;
                private String Valid_Flag;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getPART_ID() {
                    return PART_ID;
                }

                public void setPART_ID(String PART_ID) {
                    this.PART_ID = PART_ID;
                }

                public String getPART_BZ_ID() {
                    return PART_BZ_ID;
                }

                public void setPART_BZ_ID(String PART_BZ_ID) {
                    this.PART_BZ_ID = PART_BZ_ID;
                }

                public String getPART_NAME() {
                    return PART_NAME;
                }

                public void setPART_NAME(String PART_NAME) {
                    this.PART_NAME = PART_NAME;
                }

                public String getPART_CREATE_DATE() {
                    return PART_CREATE_DATE;
                }

                public void setPART_CREATE_DATE(String PART_CREATE_DATE) {
                    this.PART_CREATE_DATE = PART_CREATE_DATE;
                }

                public String getValid_Flag() {
                    return Valid_Flag;
                }

                public void setValid_Flag(String Valid_Flag) {
                    this.Valid_Flag = Valid_Flag;
                }
            }

            public static class ItemListBean {
                /**
                 * id : 73
                 * ITEM_ID : xm20170801073
                 * ITEM_PART_ID : bw20170801021
                 * ITEM_PL_BZ_ID : bz20170801021
                 * ITEM_NAME : 电器柜
                 * ITEM_CREATE_DATE : 2017/8/1 0:00:00
                 * Valid_Flag : True
                 * ContentList : [{"id":91,"CONTENT_ID":"nr20170801091","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"元器件运行是否正常、有无异音异味","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"113","CONTENT_IS_USE":1,"CONTENT_STANDARD":"运行正常、无异音异味","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视、闻味"},{"id":92,"CONTENT_ID":"nr20170801092","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"仪表指示灯是否正常","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"电流、电压、信号指示正常","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"},{"id":93,"CONTENT_ID":"nr20170801093","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"室内环境温度","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"114","CONTENT_IS_USE":1,"CONTENT_STANDARD":"温度低于40度，湿度小于50，无异常气味","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"35","CONTENT_ALARM_H2":"45","CONTENT_ALARM_STYLE":"高报","Valid_Flag":"True","CODE_NAME":"测量、目视"},{"id":94,"CONTENT_ID":"nr20170801094","CONTENT_PL_BZ_ID":"bz20170801021","CONTENT_PART_ID":"bw20170801021","CONTENT_ITEM_ID":"xm20170801073","CONTENT_NAME":"按钮与手柄是否缺失","CONTENT_CONTENT_TYPE":"1","CONTENT_SORT":0,"CONTENT_WAY":"105","CONTENT_IS_USE":1,"CONTENT_STANDARD":"按钮与手柄是无缺失","CONTENT_IS_PHOTO":0,"CONTENT_IS_PHOTO_EXCEPTION":1,"CONTENT_CREATE_DATE":"2017/8/1 0:00:00","CONTENT_ALARM_H1":"轻微","CONTENT_ALARM_H2":"严重","CONTENT_ALARM_STYLE":"定性","Valid_Flag":"True","CODE_NAME":"目视"}]
                 */

                private int id;
                private String ITEM_ID;
                private String ITEM_PART_ID;
                private String ITEM_PL_BZ_ID;
                private String ITEM_NAME;
                private String ITEM_CREATE_DATE;
                private String Valid_Flag;
                private List<ContentListBean> ContentList;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getITEM_ID() {
                    return ITEM_ID;
                }

                public void setITEM_ID(String ITEM_ID) {
                    this.ITEM_ID = ITEM_ID;
                }

                public String getITEM_PART_ID() {
                    return ITEM_PART_ID;
                }

                public void setITEM_PART_ID(String ITEM_PART_ID) {
                    this.ITEM_PART_ID = ITEM_PART_ID;
                }

                public String getITEM_PL_BZ_ID() {
                    return ITEM_PL_BZ_ID;
                }

                public void setITEM_PL_BZ_ID(String ITEM_PL_BZ_ID) {
                    this.ITEM_PL_BZ_ID = ITEM_PL_BZ_ID;
                }

                public String getITEM_NAME() {
                    return ITEM_NAME;
                }

                public void setITEM_NAME(String ITEM_NAME) {
                    this.ITEM_NAME = ITEM_NAME;
                }

                public String getITEM_CREATE_DATE() {
                    return ITEM_CREATE_DATE;
                }

                public void setITEM_CREATE_DATE(String ITEM_CREATE_DATE) {
                    this.ITEM_CREATE_DATE = ITEM_CREATE_DATE;
                }

                public String getValid_Flag() {
                    return Valid_Flag;
                }

                public void setValid_Flag(String Valid_Flag) {
                    this.Valid_Flag = Valid_Flag;
                }

                public List<ContentListBean> getContentList() {
                    return ContentList;
                }

                public void setContentList(List<ContentListBean> ContentList) {
                    this.ContentList = ContentList;
                }

                public static class ContentListBean {
                    /**
                     * id : 91
                     * CONTENT_ID : nr20170801091
                     * CONTENT_PL_BZ_ID : bz20170801021
                     * CONTENT_PART_ID : bw20170801021
                     * CONTENT_ITEM_ID : xm20170801073
                     * CONTENT_NAME : 元器件运行是否正常、有无异音异味
                     * CONTENT_CONTENT_TYPE : 1
                     * CONTENT_SORT : 0
                     * CONTENT_WAY : 113
                     * CONTENT_IS_USE : 1
                     * CONTENT_STANDARD : 运行正常、无异音异味
                     * CONTENT_IS_PHOTO : 0
                     * CONTENT_IS_PHOTO_EXCEPTION : 1
                     * CONTENT_CREATE_DATE : 2017/8/1 0:00:00
                     * CONTENT_ALARM_H1 : 轻微
                     * CONTENT_ALARM_H2 : 严重
                     * CONTENT_ALARM_STYLE : 定性
                     * Valid_Flag : True
                     * CODE_NAME : 目视、闻味
                     */

                    private int id;
                    private String CONTENT_ID;
                    private String CONTENT_PL_BZ_ID;
                    private String CONTENT_PART_ID;
                    private String CONTENT_ITEM_ID;
                    private String CONTENT_NAME;
                    private String CONTENT_CONTENT_TYPE;
                    private int CONTENT_SORT;
                    private String CONTENT_WAY;
                    private int CONTENT_IS_USE;
                    private String CONTENT_STANDARD;
                    private int CONTENT_IS_PHOTO;
                    private int CONTENT_IS_PHOTO_EXCEPTION;
                    private String CONTENT_CREATE_DATE;
                    private String CONTENT_ALARM_H1;
                    private String CONTENT_ALARM_H2;
                    private String CONTENT_ALARM_STYLE;
                    private String Valid_Flag;
                    private String CODE_NAME;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getCONTENT_ID() {
                        return CONTENT_ID;
                    }

                    public void setCONTENT_ID(String CONTENT_ID) {
                        this.CONTENT_ID = CONTENT_ID;
                    }

                    public String getCONTENT_PL_BZ_ID() {
                        return CONTENT_PL_BZ_ID;
                    }

                    public void setCONTENT_PL_BZ_ID(String CONTENT_PL_BZ_ID) {
                        this.CONTENT_PL_BZ_ID = CONTENT_PL_BZ_ID;
                    }

                    public String getCONTENT_PART_ID() {
                        return CONTENT_PART_ID;
                    }

                    public void setCONTENT_PART_ID(String CONTENT_PART_ID) {
                        this.CONTENT_PART_ID = CONTENT_PART_ID;
                    }

                    public String getCONTENT_ITEM_ID() {
                        return CONTENT_ITEM_ID;
                    }

                    public void setCONTENT_ITEM_ID(String CONTENT_ITEM_ID) {
                        this.CONTENT_ITEM_ID = CONTENT_ITEM_ID;
                    }

                    public String getCONTENT_NAME() {
                        return CONTENT_NAME;
                    }

                    public void setCONTENT_NAME(String CONTENT_NAME) {
                        this.CONTENT_NAME = CONTENT_NAME;
                    }

                    public String getCONTENT_CONTENT_TYPE() {
                        return CONTENT_CONTENT_TYPE;
                    }

                    public void setCONTENT_CONTENT_TYPE(String CONTENT_CONTENT_TYPE) {
                        this.CONTENT_CONTENT_TYPE = CONTENT_CONTENT_TYPE;
                    }

                    public int getCONTENT_SORT() {
                        return CONTENT_SORT;
                    }

                    public void setCONTENT_SORT(int CONTENT_SORT) {
                        this.CONTENT_SORT = CONTENT_SORT;
                    }

                    public String getCONTENT_WAY() {
                        return CONTENT_WAY;
                    }

                    public void setCONTENT_WAY(String CONTENT_WAY) {
                        this.CONTENT_WAY = CONTENT_WAY;
                    }

                    public int getCONTENT_IS_USE() {
                        return CONTENT_IS_USE;
                    }

                    public void setCONTENT_IS_USE(int CONTENT_IS_USE) {
                        this.CONTENT_IS_USE = CONTENT_IS_USE;
                    }

                    public String getCONTENT_STANDARD() {
                        return CONTENT_STANDARD;
                    }

                    public void setCONTENT_STANDARD(String CONTENT_STANDARD) {
                        this.CONTENT_STANDARD = CONTENT_STANDARD;
                    }

                    public int getCONTENT_IS_PHOTO() {
                        return CONTENT_IS_PHOTO;
                    }

                    public void setCONTENT_IS_PHOTO(int CONTENT_IS_PHOTO) {
                        this.CONTENT_IS_PHOTO = CONTENT_IS_PHOTO;
                    }

                    public int getCONTENT_IS_PHOTO_EXCEPTION() {
                        return CONTENT_IS_PHOTO_EXCEPTION;
                    }

                    public void setCONTENT_IS_PHOTO_EXCEPTION(int CONTENT_IS_PHOTO_EXCEPTION) {
                        this.CONTENT_IS_PHOTO_EXCEPTION = CONTENT_IS_PHOTO_EXCEPTION;
                    }

                    public String getCONTENT_CREATE_DATE() {
                        return CONTENT_CREATE_DATE;
                    }

                    public void setCONTENT_CREATE_DATE(String CONTENT_CREATE_DATE) {
                        this.CONTENT_CREATE_DATE = CONTENT_CREATE_DATE;
                    }

                    public String getCONTENT_ALARM_H1() {
                        return CONTENT_ALARM_H1;
                    }

                    public void setCONTENT_ALARM_H1(String CONTENT_ALARM_H1) {
                        this.CONTENT_ALARM_H1 = CONTENT_ALARM_H1;
                    }

                    public String getCONTENT_ALARM_H2() {
                        return CONTENT_ALARM_H2;
                    }

                    public void setCONTENT_ALARM_H2(String CONTENT_ALARM_H2) {
                        this.CONTENT_ALARM_H2 = CONTENT_ALARM_H2;
                    }

                    public String getCONTENT_ALARM_STYLE() {
                        return CONTENT_ALARM_STYLE;
                    }

                    public void setCONTENT_ALARM_STYLE(String CONTENT_ALARM_STYLE) {
                        this.CONTENT_ALARM_STYLE = CONTENT_ALARM_STYLE;
                    }

                    public String getValid_Flag() {
                        return Valid_Flag;
                    }

                    public void setValid_Flag(String Valid_Flag) {
                        this.Valid_Flag = Valid_Flag;
                    }

                    public String getCODE_NAME() {
                        return CODE_NAME;
                    }

                    public void setCODE_NAME(String CODE_NAME) {
                        this.CODE_NAME = CODE_NAME;
                    }
                }
            }
        }
    }
}
