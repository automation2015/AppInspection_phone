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

import static auto.cn.appinspection.commons.AppNetConfig.DB_NAME;

public class DbHelper {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster master;
    private DaoSession session;
    private Context mContext;
    private PlanListDao planListDao;
    private AreaListDao areaListDao;
    private EquiplistDao equiplistDao;
    private PartListDao partListDao;
    private ItemListDao itemListDao;
    private ContentListDao contentListDao;
    private static DbHelper mInstance;

    private DbHelper(Context context,String dbName) {
        this.mContext = context;
        mHelper = new DaoMaster.DevOpenHelper(mContext, dbName, null);
    }

    public static DbHelper getInstance(Context context,String dbName) {
        if (mInstance == null) {
            synchronized (DbHelper.class) {
                if (mInstance == null) {
                    mInstance = new DbHelper(context,dbName);
                }
            }
        }
        return mInstance;
    }
    //打开数据库
    public void openDb() {
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
        }else{
            UIUtils.toast("您的数据库没有数据，请先存储数据！",false);
        }
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
     * 会自动判定是插入还是替换
     * @param planData
     */
    public long insertOrReplace(PlanList planData){
        long count = planListDao.insertOrReplace(planData);
        return count;
    }
    /**插入一条记录，表里面要没有与之相同的记录
     * @param planData
     */
    public long insert(PlanList planData){
        long count = planListDao.insert(planData);
        return  count;
    }

    /**
     * 更新数据
     * @param planData
     */
    public void update(PlanList planData){
        PlanList planList = planListDao.queryBuilder().where(PlanListDao.Properties.Id.eq(planData.getId())).build().unique();//拿到之前的记录
        if(planList !=null){
            planList.setPLAN_NAME("张三");
            planListDao.update(planList);
        }
    }
    /**
     * 按条件查询数据
     */
    public List<PlanList> searchByWhere(String wherecluse){
        List<PlanList>personInfors = (List<PlanList>) planListDao.queryBuilder().where(PlanListDao.Properties.PLAN_ID.eq(wherecluse)).build().unique();
        return personInfors;
    }
    /**
     * 查询所有数据
     */
    public List<PlanList> searchAll(){
        List<PlanList>planLists=planListDao.queryBuilder().list();
        return planLists;
    }

    /**
     * 一对多查询所有数据，未使用懒加载
     */
    public List<PlanList> queryOneToMany(){
        List<PlanList> planLists = planListDao.queryBuilder().list();
        Log.d("TAG", "queryOneToMany() called planLists"+planLists.toString());
        for (PlanList planList : planLists) {
            List<AreaList> areaLists = planList.getAreas();
            for (AreaList areaList :
                    areaLists) {

                List<Equiplist> equiplists=areaList.getEquips();
                for (Equiplist equiplist :equiplists) {

                    List<PartList> partLists=equiplist.getParts();
                    for (PartList partList :partLists) {
                        List<ItemList> itemLists=partList.getItems();

                        for (ItemList itemList:itemLists) {
                            List<ContentList> contentLists=itemList.getContents();

                            for (ContentList contentList :
                                    contentLists) {

                            }
                        }
                    }
                }
            }
        }
        return planLists;
    }

    /**
     *清除所有数据
     */
    public void clearAll(){
        session.clear();
    }

    /**
     * 删除数据
     */
    public void delete(String wherecluse){
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
                @Override
                public void run() {
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
