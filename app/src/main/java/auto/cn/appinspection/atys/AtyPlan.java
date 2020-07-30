package auto.cn.appinspection.atys;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.fragments.AreaFragment;
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
    private String[] mTitles = new String[]{"计划列表", "区域列表"};
    private int curPosition;//默认选中的Fragment的位置
    private Fragment mContent;//记录刚刚选中的Fragment
    private FragmentPagerAdapter mAdapter;
    private FragmentManager fragmentManager;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_plan;
    }

    @Override
    protected void initTitle() {
        tvTitle.setText("计划管理");
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setBackgroundResource(R.mipmap.icon_add);
        ivTitleSetting.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        fragmentManager = getSupportFragmentManager();
        mAdapter = new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                AreaFragment checkHistoryFragment = null;
                PlanFragment planFragment = null;
                if (position == 1) {
                    if (checkHistoryFragment != null) {
                        return checkHistoryFragment;
                    } else {
                        return new AreaFragment();
                    }
                } else {
                    if (planFragment != null) {
                        return planFragment;
                    } else {
                        return new PlanFragment();
                    }
                }
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        };
        mainVp.setAdapter(mAdapter);
        mainTablayout.setupWithViewPager(mainVp);
        mainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        tvTitle.setText("计划管理");
                        ivTitleBack.setVisibility(View.VISIBLE);
                        ivTitleSetting.setVisibility(View.VISIBLE);
                        ivTitleSetting.setBackgroundResource(R.mipmap.icon_add);
                        break;
                    case 1:
                        tvTitle.setText("区域列表");
                        ivTitleBack.setVisibility(View.VISIBLE);
                        ivTitleSetting.setVisibility(View.GONE);
                        break;
                    default:
                        tvTitle.setText("计划管理 ");
                        ivTitleBack.setVisibility(View.VISIBLE);
                        ivTitleSetting.setVisibility(View.VISIBLE);
                        ivTitleSetting.setBackgroundResource(R.mipmap.icon_add);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @OnClick(R.id.iv_title_back)
    public void back() {
        removeAll();
        goToActivity(AtyPlanManager.class, null);
    }

}



