package auto.cn.appinspection.atys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AtyAbout extends BaseActivity {

    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Override
    protected int getLayoutId() {
        return R.layout.aty_about;
    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitle.setText("软件介绍");
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initData() {

    }
    @OnClick(R.id.iv_title_back)
    public void back(View view){
        removeCurrentActivity();

    }
}
