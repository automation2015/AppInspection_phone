package auto.cn.appinspection.atys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AtyPlanDesc extends BaseActivity {

    private static final String KEY_PLAN_NAME ="" ;
    private static final String KEY_PLAN_ID ="" ;
    private static final String KEY_PLAN_SHIFT = "";

    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_plan_desc;
    }

    @Override
    protected void initTitle() {
        tvTitle.setText("巡检记录详情");
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public void initData() {

    }
    @OnClick(R.id.iv_title_back)
    public void back() {
        removeAll();
        goToActivity(AtyPlanManager.class, null);
    }
    //启动Activity并传递参数
    public static void toActivity(Context context, String PlanId, String PlanName,String shift) {
        Intent intent = new Intent(context, AtyPlanDesc.class);
        intent.putExtra(KEY_PLAN_NAME, PlanName);
        intent.putExtra(KEY_PLAN_ID, PlanId);
        intent.putExtra(KEY_PLAN_SHIFT, shift);
        context.startActivity(intent);
    }
}
