package auto.cn.appinspection.atys;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseActivity;
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
    }
    @OnClick(R.id.tv_plan_query)
    public void addPlan(){
       goToActivity(AtyPlan.class,null);
    }
    @OnClick(R.id.tv_plan_check)
    public void planCheck(){
        goToActivity(AtyPlanCheck.class,null);
    }
    @OnClick(R.id.iv_title_back)
    public void back() {
        removeAll();
        goToActivity(MainActivity.class, null);
    }
}
