package auto.cn.appinspection.atys;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.commons.AppNetConfig;
import auto.cn.appinspection.commons.DbHelper;
import auto.cn.appinspection.utils.UIUtils;
import butterknife.Bind;
import butterknife.OnClick;

public class AtyPlanManager extends BaseActivity {


    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.tv_plan_query)
    TextView tvPlanQuery;
    @Bind(R.id.tv_plan_check)
    TextView tvPlanCheck;
    @Bind(R.id.tv_plan_history)
    TextView tvPlanHistory;
    @Bind(R.id.tv_plan_upload)
    TextView tvPlanUpload;
    @Bind(R.id.tv_plan_delete)
    TextView tvPlanDelete;
    private DbHelper dbHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_plan_manager;
    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setVisibility(View.GONE);
        tvTitle.setText("计划管理");
    }

    @Override
    public void initData() {
        dbHelper = DbHelper.getInstance(this, AppNetConfig.DB_NAME);
    }

    @OnClick(R.id.iv_title_back)
    public void back() {
        removeAll();
        goToActivity(MainActivity.class, null);
    }

    @OnClick(R.id.tv_plan_query)
    public void addPlan() {
        goToActivity(AtyPlan.class, null);
    }

    @OnClick(R.id.tv_plan_check)
    public void planCheck() {
        goToActivity(AtyPlanCheck.class, null);
    }

    @OnClick(R.id.tv_plan_delete)
    public void deleteDb() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("您确定清除所有的数据库数据吗？")
                .setIcon(R.mipmap.icon_delete)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dbHelper != null) {
                            dbHelper.openDb();
                            boolean isClear = dbHelper.clearDb();
                            if (isClear) {
                                UIUtils.toast("数据清除成功！", false);
                            } else {
                                UIUtils.toast("数据清除失败！", false);
                            }
                        } else {
                            UIUtils.toast("你的数据库是空的，不需要清理！", false);
                        }
                    }

                })
                .setNegativeButton("取消", null)
                .show();

    }
}
