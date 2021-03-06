package auto.cn.greendaogenerate;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
//1、增加字段，提示找不到：修改Schema version
public class MyClass {
    public static void main(String[] args) {
        Schema schema = new Schema(4, "auto.cn.greendaogenerate");

        //添加plan实体
        Entity plan = schema.addEntity("PlanList");
        plan.addIdProperty();
        plan.addStringProperty("PLAN_ID");
        plan.addStringProperty("PLAN_NAME");
        plan.addStringProperty("PLAN_ORG_NAME");
        plan.addStringProperty("PLAN_PART_NAME");

        plan.addStringProperty("PLAN_PART_ID");
        plan.addIntProperty("PLAN_NUM");
        plan.addIntProperty("PLAN_CYCLE_TYPE");
        plan.addStringProperty("PLAN_LAST_DATE");

        plan.addStringProperty("PLAN_LAST_USER_NAME");
        plan.addStringProperty("PLAN_CREATE_DATE");
        plan.addStringProperty("Valid_Flag");
        plan.addStringProperty("Shift");

        plan.addStringProperty("CODE_NAME");
        plan.addStringProperty("ES_CLASS_NAME");//第1班
        plan.addStringProperty("ES_START_DATE");//计划开始时间
        plan.addStringProperty("ES_END_DATE");//计划结束时间

        plan.addStringProperty("ES_USER_NAME");//计划施行人，巡检甲班
        plan.addStringProperty("UPLOAD_TIME");//计划上传时间
        plan.addBooleanProperty("PLAN_FINISH");//计划完成
        //添加area实体
        Entity area = schema.addEntity("AreaList");
        area.addIdProperty();
        area.addIntProperty("PL_AREA_ID");
        area.addStringProperty("PL_AREA_NAME");
        area.addStringProperty("PL_AREA_LABEL");
        area.addStringProperty("PL_AREA_CREATE_ID");

        area.addStringProperty("PL_AREA_CREATE_DATE");
        area.addIntProperty("Valid_Flag");
        area.addStringProperty("PlanId");
        area.addStringProperty("AREA_NORNAL");//区域检修或正常

        area.addBooleanProperty("AREA_FINISH");//区域巡检完成

        // area表中增加计划外键
        Property planFk = area.addLongProperty("fk_plan").getProperty();
        // 区域一一
        area.addToOne(plan, planFk);
        //计划一多
        plan.addToMany(area, planFk).setName("areas");
        //添加equip实体

        Entity equip = schema.addEntity("Equiplist");
        equip.addIdProperty();
        equip.addStringProperty("EL_NAME");
        equip.addStringProperty("EL_Depart_Name");
        equip.addStringProperty("EL_ID");
        equip.addStringProperty("EL_Type_Name");

        equip.addStringProperty("EL_EIS_ID");
        equip.addStringProperty("EL_EIS_NAME");
        equip.addStringProperty("EL_VALID_FLAG");
        equip.addIntProperty("PL_AREA_ID");

        equip.addBooleanProperty("EQUIP_FINISH");//设备巡检完成
        Property areaFk = equip.addLongProperty("fk_area").getProperty();
        equip.addToOne(area, areaFk);
        area.addToMany(equip, areaFk).setName("equips");
        //part实体
        Entity part = schema.addEntity("PartList");
        part.addIdProperty();
        part.addStringProperty("PART_ID");
        part.addStringProperty("PART_BZ_ID");
        part.addStringProperty("PART_NAME");
        part.addStringProperty("PART_CREATE_DATE");
        part.addStringProperty("Valid_Flag");

        part.addBooleanProperty("PART_FINISH");//部位巡检完成
//
        Property equipFk = part.addLongProperty("fk_equip").getProperty();
        part.addToOne(equip, equipFk);
        equip.addToMany(part, equipFk).setName("parts");
        //item实体
        Entity item = schema.addEntity("ItemList");
        item.addIdProperty();
        item.addStringProperty("ITEM_ID");
        item.addStringProperty("ITEM_PART_ID");
        item.addStringProperty("ITEM_PL_BZ_ID");
        item.addStringProperty("ITEM_NAME");

        item.addStringProperty("ITEM_CREATE_DATE");
        item.addStringProperty("Valid_Flag");
        item.addBooleanProperty("ITEM_FINISH");//条目巡检完成
        Property partFk = item.addLongProperty("fk_part").getProperty();
        item.addToOne(equip, partFk);
        equip.addToMany(item, partFk).setName("items");
        //content实体
        Entity content = schema.addEntity("ContentList");
        content.addIdProperty();
        content.addStringProperty("CONTENT_ID");
        content.addStringProperty("CONTENT_PL_BZ_ID");
        content.addStringProperty("CONTENT_PART_ID");
        content.addStringProperty("CONTENT_ITEM_ID");

        content.addStringProperty("CONTENT_NAME");
        content.addStringProperty("CONTENT_CONTENT_TYPE");
        content.addIntProperty("CONTENT_SORT");
        content.addStringProperty("CONTENT_WAY");

        content.addIntProperty("CONTENT_IS_USE");
        content.addStringProperty("CONTENT_STANDARD");
        content.addIntProperty("CONTENT_IS_PHOTO");
        content.addIntProperty("CONTENT_IS_PHOTO_EXCEPTION");

        content.addStringProperty("CONTENT_CREATE_DATE");
        content.addStringProperty("CONTENT_ALARM_H1");
        content.addStringProperty("CONTENT_ALARM_H2");
        content.addStringProperty("CONTENT_ALARM_STYLE");

        content.addStringProperty("Valid_Flag");
        content.addStringProperty("CODE_NAME");
        content.addStringProperty("TEMP_VALUE");//温度测量值
        content.addStringProperty("PHOTO_PATH");//异常照片文件路径

        content.addBooleanProperty("CONTENT_FINISH");//内容项目巡检完成
        content.addStringProperty("CONTENT_STATUS");//巡检数据正常 异常
        Property itemFk = content.addLongProperty("fk_item").getProperty();
        content.addToOne(item, itemFk);
        item.addToMany(content, itemFk).setName("contents");
        try {
            new DaoGenerator().generateAll(schema, "app/src/main/java/auto/cn/appinspection/db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

