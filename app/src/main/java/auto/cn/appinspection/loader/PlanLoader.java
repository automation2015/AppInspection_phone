package auto.cn.appinspection.loader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import java.util.List;

import auto.cn.appinspection.commons.Constant;
import auto.cn.appinspection.commons.DbHelper;
import auto.cn.greendaogenerate.PlanList;

/**
 * 自定义Loader
 */
public class PlanLoader extends AsyncTaskLoader<List<PlanList>> {
    private DbHelper dbHelper;
    private Context context;
    public PlanLoader( Context context) {
        super(context);
        this.context=context;
    }

    @Nullable
    @Override
    public List<PlanList> loadInBackground() {
        dbHelper=DbHelper.getInstance(context,Constant.DB_NAME);
        dbHelper.openDb();
        dbHelper.setDebug();
        //List<PlanList> allPlan = dbHelper.getAllPlan();
        List<PlanList> allPlan = dbHelper.queryAllRecord();
        return allPlan;
    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        //如果Loader启动了，强制启动LoaderInBackground
        if(isStarted()){
            forceLoad();
        }
    }
}
