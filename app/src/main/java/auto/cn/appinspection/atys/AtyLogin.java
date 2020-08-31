package auto.cn.appinspection.atys;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;

import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.beans.UserBean;
import auto.cn.appinspection.utils.UIUtils;
import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    private OkHttpClient okHttpClient;
    private Request request;
    private Request.Builder builder;
    private String mBaseUrl = "http://172.16.36.92:8080/Imooc_okhttp1/";
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
        okHttpClient=new OkHttpClient();
        builder = new Request.Builder();


    }
    @OnClick(R.id.btn_login)
    public void login(View view){

//        String username = etLoginUsername.getText().toString().trim();
//        String password=etLoginPwd.getText().toString().trim();
//        if(TextUtils.isEmpty(username)){
//            UIUtils.toast("用户名不能为空！",false);
//            return;
//        }
//        if(TextUtils.isEmpty(password)){
//            UIUtils.toast("密码不能为空！",false);
//            return;
//        }
        /**

         */
        //TODO 测试方便，MD5密码校验暂时放在webApi，后期改为客户端校验
       // String url=Constant.USERLOGIN+"?username="+username+"&password="+password;
        String url1="http://172.16.36.92:21663/api/user/loginG?username=admin&password=1";
        String url2="http://172.16.36.92:7000/api/user/loginG?username=admin&password=1";
        String url3="http://172.16.36.92:21663/api/user/userLogin?username=admin&password=1";
        String url4="http://172.16.36.92:7000/api/user/userLogin?username=admin&password=1";
           // params.put("password",MD5Utils.MD5(password));
        client.get(url3,new AsyncHttpResponseHandler(){
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
    private void doGetByAsnc(String url){
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
    private void doPostByAsnc(String url){
        RequestParams params = new RequestParams();
        params.put("username", "admin");
        params.put("password", "1");
        client.post(url,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {//200 404
                //UIUtils.toast("success",false);
                //解析Json
                if(!TextUtils.isEmpty(content)) {
                    UIUtils.toast("succ",false);

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
        private void doGetByOkHttp(String url){
            //2.构造Request

            //3.将Request封装为Call
            Request request = builder.get()
                    .url(url)
                    .build();
            //4.执行Call
            try {
                excuteCall(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    private void doPostByOkHttp(String url)
   {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("username", "admin");
        formBuilder.add("password", "1");
        RequestBody requestBody = formBuilder.build();
        Request request = builder.post(requestBody)
                //mBaseUrl + "login"
                //"http://172.16.36.92:7000/api/user/loginP"
                .url(url)
                .build();
        //4.执行Call
        try {
            excuteCall(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.iv_title_back)
    public void back(){
        removeAll();
        goToActivity(MainActivity.class,null);
    }
    private void excuteCall(Request request) throws IOException {
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure() called with: call = [" + call + "], e = [" + e + "]");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                final String res = response.body().string();
                Log.d("TAG", "onResponse() called with: res = [" + res + "]");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //tvResult.setText(res);
                    }
                });
            }
        });
    }
}
