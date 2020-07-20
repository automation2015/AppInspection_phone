package auto.cn.appinspection.atys;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.fragments.HomeFragment;
import auto.cn.appinspection.fragments.MeFragment;
import auto.cn.appinspection.fragments.MoreFragment;
import auto.cn.appinspection.net.AppUpdater;
import auto.cn.appinspection.utils.UIUtils;
import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_fl)
    FrameLayout mainFl;
    @Bind(R.id.iv_main_home)
    ImageView ivMainHome;
    @Bind(R.id.tv_main_home)
    TextView tvMainHome;
    @Bind(R.id.ll_main_home)
    LinearLayout llMainHome;
    @Bind(R.id.iv_main_me)
    ImageView ivMainMe;
    @Bind(R.id.tv_main_me)
    TextView tvMainMe;
    @Bind(R.id.ll_main_me)
    LinearLayout llMainMe;
    @Bind(R.id.iv_main_more)
    ImageView ivMainMore;
    @Bind(R.id.tv_main_more)
    TextView tvMainMore;
    @Bind(R.id.ll_main_more)
    LinearLayout llMainMore;
    private FragmentTransaction transaction;
    private boolean exitFlag = true;
    private HomeFragment homeFragment;
    private MoreFragment moreFragment;
    private MeFragment meFragment;
    private static final int WHAT_RESET_BACK = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public void initData() {
        //默认显示首页
        setSelect(0);
    }
    @OnClick({R.id.ll_main_home, R.id.ll_main_me, R.id.ll_main_more})
    public void showTab(View view) {
        switch (view.getId()) {
            case R.id.ll_main_home:
                setSelect(0);
                break;
            case R.id.ll_main_me:
                setSelect(1);
                break;
            case R.id.ll_main_more:
                setSelect(2);
                break;

        }
    }
    private void setSelect(int i) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        //隐藏所有的fragment
        hideFragment();
//        重置图片和文本颜色
        resetTab();
        switch (i) {
            case 0:
                if (homeFragment == null) {
//             创建对象之后，并不会调用fragment的生命周期方法，而是在commit之后方才调用
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main_fl, homeFragment);
                }
                // 显示当前的fragment,对象创建之后，每次调用需要显示出来
                transaction.show(homeFragment);
                //homeFragment.show();//错误的调用位置
                // 改变图片和文本颜色
                ivMainHome.setImageResource(R.mipmap.bottom02);
                tvMainHome.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                break;
            case 1:
                if (meFragment == null) {
//             创建对象之后，并不会调用fragment的生命周期方法，而是在commit之后方才调用
                    meFragment = new MeFragment();
                    transaction.add(R.id.main_fl, meFragment);
                }
//                改变图片和文本颜色
                ivMainMe.setImageResource(R.mipmap.bottom06);
                tvMainMe.setTextColor(UIUtils.getColor(R.color.home_back_selected01));
//                显示当前的fragment
                transaction.show(meFragment);
                break;

            case 2:
                if (moreFragment == null) {
//             创建对象之后，并不会调用fragment的生命周期方法，而是在commit之后方才调用
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.main_fl,moreFragment);

                }
//                改变图片和文本颜色
                ivMainMore.setImageResource(R.mipmap.bottom08);
                tvMainMore.setTextColor(UIUtils.getColor(R.color.home_back_selected));
//                显示当前的fragment
                transaction.show(moreFragment);
                break;
        }
        transaction.commit();
    }
    private void resetTab() {
        ivMainHome.setImageResource(R.mipmap.bottom01);
        ivMainMe.setImageResource(R.mipmap.bottom05);
        ivMainMore.setImageResource(R.mipmap.bottom07);

        tvMainHome.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainMore.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainMe.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
    }

    private void hideFragment() {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (moreFragment != null) {
            transaction.hide(moreFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }
    }
    //发送延迟消息，实现两次点击退出应用
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_RESET_BACK: {
                    exitFlag = true;
                }
            }
        }
    };
    //重写onKeyUp，实现两次点击退出应用
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && exitFlag) {
            exitFlag = false;
            Toast.makeText(MainActivity.this, "再点击一次，退出当前应用!", Toast.LENGTH_SHORT).show();
            //发送延迟消息
            handler.sendEmptyMessageDelayed(WHAT_RESET_BACK, 2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
    //销毁未被执行的消息，防止内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除所有未被执行的消息
        handler.removeCallbacksAndMessages(WHAT_RESET_BACK);
//将保存在sp中的数据清除
//        SharedPreferences sp =this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
//        sp.edit().clear().commit();//清除数据操作必须提交；提交以后，文件仍存在，只是文件中的数据被清除了
        AppUpdater.getsInstance().getmNetManager().cancel(this);
    }
}
