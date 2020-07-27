package auto.cn.appinspection.commons;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import auto.cn.greendaogenerate.AreaListDao;
import auto.cn.greendaogenerate.ContentListDao;
import auto.cn.greendaogenerate.DaoMaster;
import auto.cn.greendaogenerate.DaoSession;
import auto.cn.greendaogenerate.EquiplistDao;
import auto.cn.greendaogenerate.ItemListDao;
import auto.cn.greendaogenerate.PartListDao;
import auto.cn.greendaogenerate.PlanListDao;

public class DbHelper {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private Context mContext;
    private PlanListDao planListDao;
    private AreaListDao areaListDao;
    private EquiplistDao equiplistDao;
    private PartListDao partListDao;
    private ItemListDao itemListDao;
    private ContentListDao contentListDao;
    private static DbHelper mInstance;
    private DbHelper(Context context,String dbName){
        this.mContext=context;
        mHelper=new DaoMaster.DevOpenHelper(context,dbName,null);
       if(mHelper!=null){
           db=mHelper.getWritableDatabase();
           mDaoMaster=new DaoMaster(db);
           mDaoSession=mDaoMaster.newSession();
           planListDao=mDaoSession.getPlanListDao();
           areaListDao=mDaoSession.getAreaListDao();
           equiplistDao=mDaoSession.getEquiplistDao();
           partListDao=mDaoSession.getPartListDao();
           itemListDao=mDaoSession.getItemListDao();
           contentListDao=mDaoSession.getContentListDao();
       }
    }
    public static DbHelper getInstance(Context context,String dbName){
        if(mInstance==null){
            synchronized (DbHelper.class){
                if (mInstance==null){
                    mInstance=new DbHelper(context,dbName);
                }
            }
        }
        return mInstance;
    }
}
