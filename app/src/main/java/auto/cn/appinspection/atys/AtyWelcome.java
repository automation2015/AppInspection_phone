package auto.cn.appinspection.atys;

import android.content.Intent;
import android.os.Handler;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.commons.ActivityManager;
import butterknife.Bind;

public class AtyWelcome extends BaseActivity {
    @Bind(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @Bind(R.id.tv_welcome_title)
    TextView tvWelcomeTitle;
    @Bind(R.id.rl_welcome)
    RelativeLayout rlWelcome;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.aty_welcome;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public void initData() {
        //提供启动动画
        setAnimation();
    }

    private void setAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);//0：完全透明 1：完全不透明
        alphaAnimation.setDuration(3000);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());//设置动画的变化率,加速显示
        rlWelcome.startAnimation(alphaAnimation);
        //使用handler
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AtyWelcome.this, MainActivity.class);
                startActivity(intent);
                //结束Activity的显示，并从栈空间中移除
                ActivityManager.getInstance().remove(AtyWelcome.this);
            }
        }, 3000);
    }
}
