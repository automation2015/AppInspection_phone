package auto.cn.appinspection.atys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.commons.AppNetConfig;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AtyPlanCheck extends BaseActivity {
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_plan_check;
    }

    @Override
    protected void initTitle() {
        tvTitle.setText("设备巡检");
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String planId = intent.getExtras().getString(AppNetConfig.KEY_PLANID);
        Log.e("TAG", "initData() called"+planId);

        tv.setText(planId);
    }

    @OnClick(R.id.iv_title_back)
    public void back() {
        removeAll();
        goToActivity(MainActivity.class, null);
    }

    //启动Activity并传递参数
    public static void toActivity(Context context, String planId) {
        Intent intent = new Intent(context, AtyPlanCheck.class);
        intent.putExtra(AppNetConfig.KEY_PLANID, planId);
        context.startActivity(intent);
    }
}
