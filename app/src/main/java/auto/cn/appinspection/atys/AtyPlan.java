package auto.cn.appinspection.atys;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.fragments.CheckHistoryFragment;
import auto.cn.appinspection.fragments.PlanFragment;
import butterknife.Bind;
import butterknife.OnClick;

public class AtyPlan extends BaseActivity {


    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.main_tablayout)
    TabLayout mainTablayout;
    @Bind(R.id.main_vp)
    ViewPager mainVp;
    private String[] mTitles = new String[]{"计划下载", "计划查询"};
    private int position;//默认选中的Fragment的位置
    private Fragment mContent;//记录刚刚选中的Fragment

    @Override
    protected int getLayoutId() {
        return R.layout.aty_plan;
    }

    @Override
    protected void initTitle() {
        tvTitle.setText("计划管理");
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        mainVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                CheckHistoryFragment checkHistoryFragment = null;
                PlanFragment planFragment = null;
                if (position == 1) {
                    if (checkHistoryFragment != null) {
                        return checkHistoryFragment;
                    } else {
                        return new CheckHistoryFragment();
                    }
                } else {
                    if(planFragment!=null){
                        return planFragment;
                    }else {
                        return new PlanFragment();
                    }
                }
            }
            @Override
            public int getCount() {
                return mTitles.length;
            }
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
        mainTablayout.setupWithViewPager(mainVp);
    }

    @OnClick(R.id.iv_title_back)
    public void back() {
        removeAll();
        goToActivity(MainActivity.class, null);
    }

    @Override
    protected void onResume() {
        //TODO 读取数据库的计划列表
        super.onResume();
    }
}



