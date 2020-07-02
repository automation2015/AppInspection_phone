package auto.cn.appinspection.bases;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.loopj.android.http.AsyncHttpClient;

import auto.cn.appinspection.beans.UserBean;
import auto.cn.appinspection.commons.ActivityManager;
import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏顶部的状态栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //将当前的Activity添加到AvtivityManager中
        ActivityManager.getInstance().add(this);
        initTitle();
        initData();
    }
    protected abstract int getLayoutId();

    protected abstract void initTitle();

    public abstract void initData() ;
    //启动新的Activity
    public void goToActivity(Class Activity,Bundle bundle){
        Intent intent=new Intent(this,Activity);
        //传递数据
        if(bundle!=null&& bundle.size()!=0){
            intent.putExtra("data",bundle);
        }
        startActivity(intent);
    }
    //销毁当前Activity
    public void removeCurrentActivity(){
        ActivityManager.getInstance().removeCurrent();

    }
    //销毁所有的Activity
    public void removeAll(){
        ActivityManager.getInstance().removeAll();

    }
    public AsyncHttpClient client=new AsyncHttpClient();
    //存储用户信息
    public void saveUser(UserBean user){
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("username",user.getUsername());
        edit.putString("rolename",user.getRolename());
        edit.putBoolean("isLogin",user.getIsLogin());
        edit.putString("loginTime",user.getLoginTime());

        edit.commit();//必须提交，否则保存不成功

    }
    //读取用户信息
    public UserBean readUser(){
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        UserBean user=new UserBean();
        user.setUsername(sp.getString("username",""));
        user.setRolename(sp.getString("rolename",""));
        user.setIsLogin(sp.getBoolean("isLogin",false));
        user.setLoginTime(sp.getString("loginTime",""));

        return user;
    }
}
