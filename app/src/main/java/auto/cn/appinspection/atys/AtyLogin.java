package auto.cn.appinspection.atys;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.beans.UserBean;
import auto.cn.appinspection.commons.AppNetConfig;
import auto.cn.appinspection.utils.UIUtils;
import butterknife.Bind;
import butterknife.OnClick;

public class AtyLogin extends BaseActivity {
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.tv_login_username)
    TextView tvLoginUsername;
    @Bind(R.id.et_login_username)
    EditText etLoginUsername;
    @Bind(R.id.rl_login)
    RelativeLayout rlLogin;
    @Bind(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @Bind(R.id.et_login_pwd)
    EditText etLoginPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;
private AsyncHttpClient client;
    @Override
    protected int getLayoutId() {
        return R.layout.aty_login;
    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitle.setText("用户登录");
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initData() {
     client=new AsyncHttpClient();
    }
    @OnClick(R.id.btn_login)
    public void login(View view){
        String username = etLoginUsername.getText().toString().trim();
        String password=etLoginPwd.getText().toString().trim();
        if(TextUtils.isEmpty(username)){
            UIUtils.toast("用户名不能为空！",false);
            return;
        }
        if(TextUtils.isEmpty(password)){
            UIUtils.toast("密码不能为空！",false);
            return;
        }
        //TODO 测试方便，MD5密码校验暂时放在webApi，后期改为客户端校验
        String url=AppNetConfig.USERLOGIN+"?username="+username+"&password="+password;
           // params.put("password",MD5Utils.MD5(password));
        client.get(url,new AsyncHttpResponseHandler(){
                @Override
                public void onSuccess(String content) {//200 404
                    //UIUtils.toast("success",false);
                    //解析Json
                    if(!TextUtils.isEmpty(content)) {
                        UserBean user = new Gson().fromJson(content, UserBean.class);
                        boolean isLogin=user.getIsLogin();
                        if(isLogin){
                            //保存用户信息
                            saveUser(user);
                            //重新加载界面
                            removeAll();
                            goToActivity(MainActivity.class,null);
                        }else {
                            UIUtils.toast("服务器异常，请刷新后重新登录！",false);
                        }
                    }else{
                        UIUtils.toast("您输入的用户名或密码不正确，请检查确认后重新登录！",false);
                        etLoginPwd.setText("");
                        etLoginUsername.setText("");
                    }
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    UIUtils.toast("连接服务器失败，错误信息"+error.getMessage()+"，请检查网络是否连接！",false);

                }
            });
        }
    @OnClick(R.id.iv_title_back)
    public void back(){
        removeAll();
        goToActivity(MainActivity.class,null);
    }
}
