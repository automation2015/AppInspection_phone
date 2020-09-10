package auto.cn.appinspection.commons;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import auto.cn.appinspection.utils.UIUtils;
import auto.cn.greendaogenerate.AreaList;
import auto.cn.greendaogenerate.AreaListDao;
import auto.cn.greendaogenerate.ContentList;
import auto.cn.greendaogenerate.ContentListDao;
import auto.cn.greendaogenerate.DaoMaster;
import auto.cn.greendaogenerate.DaoSession;
import auto.cn.greendaogenerate.Equiplist;
import auto.cn.greendaogenerate.EquiplistDao;
import auto.cn.greendaogenerate.ItemList;
import auto.cn.greendaogenerate.ItemListDao;
import auto.cn.greendaogenerate.PartList;
import auto.cn.greendaogenerate.PartListDao;
import auto.cn.greendaogenerate.PlanList;
import auto.cn.greendaogenerate.PlanListDao;
import de.greenrobot.dao.query.QueryBuilder;

public class DbHelper {
    //GreenDao管理类
    private volatile static DbHelper mInstance;
    //创建数据库的工具
    private DaoMaster.DevOpenHelper mHelper;
    //它里边实际上是保存数据库的对象
    private DaoMaster master;
    //管理gen里生成的所有的Dao对象里边带有基本的增删改查的方法
    private DaoSession session;
    //上下文对象
    private Context mContext;
    private SQLiteDatabase db;
    private PlanListDao planListDao;
    private AreaListDao areaListDao;
    private EquiplistDao equiplistDao;
    private PartListDao partListDao;
    private ItemListDao itemListDao;
    private ContentListDao contentListDao;


    private DbHelper(Context context, String dbName) {
        this.mContext = context;
        mHelper = new DaoMaster.DevOpenHelper(mContext, dbName, null);
    }
    /**
     * 单例模式获得操作数据库对象
     * @return
     */
    public static DbHelper getInstance(Context context, String dbName) {
        if (mInstance == null) {
            synchronized (DbHelper.class) {
                if (mInstance == null) {
                    mInstance = new DbHelper(context, dbName);
                }
            }
        }
        return mInstance;
    }
    /**
     * 判断是否有存在数据库，如果没有则创建
     * @return
     */
    public DaoMaster getMaster() {
        if (master == null) {
            mHelper = new DaoMaster.DevOpenHelper(mContext, Constant.DB_NAME, null);
            master = new DaoMaster(mHelper.getWritableDatabase());
        }
        return master;
    }
    /**
     * 完成对数据库的添加、删除、修改、查询操作，
     * @return
     */
    public DaoSession getSession() {
        if (session == null) {
            if (master == null) {
                master = getMaster();
            }
            session = master.newSession();
        }
        return session;
    }
    //打开数据库
    public boolean openDb() {
        if (mHelper != null) {
            db = mHelper.getWritableDatabase();
            master = new DaoMaster(db);
            session = master.newSession();
            planListDao = session.getPlanListDao();
            areaListDao = session.getAreaListDao();
            equiplistDao = session.getEquiplistDao();
            partListDao = session.getPartListDao();
            itemListDao = session.getItemListDao();
            contentListDao = session.getContentListDao();
            return true;
        } else {
            UIUtils.toast("请先创建数据库！", false);
            return false;
        }
    }

    /**
     * 清除数据库中的所有数据
     */
    public boolean clearDb() {
        if (mHelper != null) {
            if (session != null) {
                session.clear();
                if (planListDao != null)
                    planListDao.deleteAll();
                if (areaListDao != null)
                    areaListDao.deleteAll();
                if (equiplistDao != null)
                    equiplistDao.deleteAll();
                if (partListDao != null)
                    partListDao.deleteAll();
                if (itemListDao != null)
                    itemListDao.deleteAll();
                if (contentListDao != null)
                    contentListDao.deleteAll();
            }
                return true;
        }
       //if(planListDao.detach())
         return false;
    }

    /**
     * 打开输出日志的操作,默认是关闭的
     */
    public void setDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }
    /**
     * 关闭所有的操作,数据库开启的时候，使用完毕了必须要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }

    public void closeDaoSession() {
        if (session != null) {
            session.clear();
            session = null;
        }
    }

    /**
     * 插入计划，会自动判定是插入还是替换
     *
     * @param planData
     */
    public long insertOrReplace(PlanList planData) {
        long count = planListDao.insertOrReplace(planData);
        return count;
    }

    /**
     * 插入area，会自动判定是插入还是替换
     *
     * @param areaData
     */
    public long insertOrReplace(AreaList areaData) {
        long count = areaListDao.insertOrReplace(areaData);
        return count;
    }

    /**
     * 插入equip，会自动判定是插入还是替换
     *
     * @param equipData
     */
    public long insertOrReplace(Equiplist equipData) {
        long count = equiplistDao.insertOrReplace(equipData);
        return count;
    }

    /**
     * part，会自动判定是插入还是替换
     *
     * @param partData
     */
    public long insertOrReplace(PartList partData) {
        long count = partListDao.insertOrReplace(partData);
        return count;
    }

    /**
     * part，会自动判定是插入还是替换
     *
     * @param itemData
     */
    public long insertOrReplace(ItemList itemData) {
        long count = itemListDao.insertOrReplace(itemData);
        return count;
    }

    /**
     * content，会自动判定是插入还是替换
     *
     * @param contentData
     */
    public long insertOrReplace(ContentList contentData) {
        long count = contentListDao.insertOrReplace(contentData);
        return count;
    }

    /**
     * 插入一条记录，表里面要没有与之相同的记录
     *
     * @param planData
     */
    public long insert(PlanList planData) {
        long count = planListDao.insert(planData);
        return count;
    }

    /**
     * 更新数据
     *
     * @param planData
     */
    public void update(PlanList planData) {
        PlanList planList = planListDao.queryBuilder().where(PlanListDao.Properties.Id.eq(planData.getId())).build().unique();//拿到之前的记录
        if (planList != null) {
            planList.setPLAN_NAME("张三");
            planListDao.update(planList);
        }
    }

    /**
     * 按条件查询数据
     */
    public PlanList queryPlanById(String wherecluse) {
        PlanList planList =  planListDao.queryBuilder().where(PlanListDao.Properties.PLAN_ID.eq(wherecluse)).build().unique();
        return planList;
    }

    /**
     * 查询所有数据
     */
    public List<PlanList> searchAll() {
        List<PlanList> planLists = planListDao.queryBuilder().list();
        return planLists;
    }

    /**
     * 一对多查询所有数据，未使用懒加载
     */
    public List<PlanList> queryAllRecord() {
        List<PlanList> planLists = planListDao.queryBuilder().list();
        Log.d("TAG", "queryOneToMany() called planLists" + planLists.toString());
        for (int i = 0; i < planLists.size(); i++) {
            List<AreaList> areaLists = planLists.get(i).getAreas();
            for (int j = 0; j < areaLists.size(); j++) {
                List<Equiplist> equiplists = areaLists.get(j).getEquips();
                for (int k = 0; k < equiplists.size(); k++) {
                    List<ItemList> itemLists = equiplists.get(k).getItems();
                    List<PartList> partLists = equiplists.get(k).getParts();
                    for (int l = 0; l < itemLists.size(); l++) {
                        List<ContentList> contentLists = itemLists.get(l).getContents();
                    }
                }
            }
        }
        return planLists;
    }
        /**
         * 获取计划  未使用懒加载
         * @return
         */
        public List<PlanList> getAllPlan() {
            List<PlanList> planLists = planListDao.queryBuilder().list();
            return planLists;
        }

    /**
     * 根据PlanId获取区域
     * @return
     */
    public List<AreaList> getAreaByPlanId(String planId) {
        PlanList plan = planListDao.queryBuilder().where(PlanListDao.Properties.PLAN_ID.eq(planId)).unique();
        List<AreaList> areaLists = plan.getAreas();
        return areaLists;
    }

    /**
     * 根据AREA_LABEL查询
     */
    public AreaList queryAreaByAreaLable(String areaLable) {
        AreaList area = areaListDao.queryBuilder().where(AreaListDao.Properties.PL_AREA_LABEL.eq(areaLable)).unique();
        return area;
    }

    /**
     * 获取设备
     *
     * @return
     */
    public List<Equiplist> getEquipByAreaId(int areaId) {
        AreaList areaList = areaListDao.queryBuilder().where(AreaListDao.Properties.PL_AREA_ID.eq(areaId)).unique();
        List<Equiplist> equiplists = areaList.getEquips();
        return equiplists;
    }

    /**
     * 获取部位
     *
     * @return
     */
    public List<PartList> getPartByEquipId(int equipId) {
        Equiplist equiplist = equiplistDao.queryBuilder().where(EquiplistDao.Properties.EL_EIS_ID.eq(equipId)).unique();
        List<PartList> partlists = equiplist.getParts();
        return partlists;
    }

    /**
     * 获取Item
     *
     * @return
     */
//    public List<ItemList> getItemByEquipId(int partId) {
//       PartList partlist = partListDao.queryBuilder().where(PartListDao.Properties.PART_ID.eq(partId)).unique();
//        List<ItemList> itemLists = partlist.getItems();
//        return itemLists;
//    }

    /**
     * 获取Content
     *
     * @return
     */
    public List<ContentList> getContentByItemId(int itemId) {
        ItemList itemList = itemListDao.queryBuilder().where(ItemListDao.Properties.ITEM_ID.eq(itemId)).unique();
        List<ContentList> contentLists = itemList.getContents();
        return contentLists;
    }

    /**
     * 删除数据
     */
    public void delete(String wherecluse) {
        planListDao.queryBuilder().where(PlanListDao.Properties.PLAN_ID.eq(wherecluse)).buildDelete().executeDeleteWithoutDetachingEntities();
    }
    /**
     * 完成对数据库中bookshelfModel 表的插入操作
     * @param bookshelfModel
     * @return flag

    public boolean insertBookshelfModel(DbBookshelf bookshelfModel) {
    boolean flag = manager.getDaoSession().insertOrReplace(bookshelfModel) != -1 ? true : false;
    return flag;
    }

    /**
     * 插入多条记录，需要开辟新的线程
     * @param bookshelfModels
     * @return flag

    public boolean insertMultBookshelfModel(final List<DbBookshelf> bookshelfModels) {
    boolean flag = false;

    try {
    manager.getDaoSession().runInTx(new Runnable() {
    @Override public void run() {
    for (DbBookshelf bookshelfModel : bookshelfModels) {
    manager.getDaoSession().insertOrReplace(bookshelfModel);
    }
    }
    });
    flag = true;
    } catch (Exception e) {
    e.printStackTrace();
    }
    return flag;
    }
     */
}
