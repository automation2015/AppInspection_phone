package auto.cn.appinspection.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import auto.cn.appinspection.R;
import auto.cn.appinspection.adapters.CommonBaseAdapter;
import auto.cn.appinspection.adapters.ViewHolder;
import auto.cn.appinspection.beans.PlanBean;
import auto.cn.appinspection.beans.UserBean;
import auto.cn.appinspection.utils.UIUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AtyLoginTest extends Activity {

    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.tv_login_username_test)
    TextView tvLoginUsernameTest;
    @Bind(R.id.et_login_username_test)
    EditText etLoginUsernameTest;
    @Bind(R.id.rl_login_test)
    RelativeLayout rlLoginTest;
    @Bind(R.id.tv_login_pwd_test)
    TextView tvLoginPwdTest;
    @Bind(R.id.et_login_pwd_test)
    EditText etLoginPwdTest;
    @Bind(R.id.btn_doget_asyn)
    Button btnDogetAsyn;
    @Bind(R.id.btn_dopost_asyn)
    Button btnDopostAsyn;
    @Bind(R.id.btn_doget_okhttp)
    Button btnDogetOkhttp;
    @Bind(R.id.btn_dopost_okhttp)
    Button btnDopostOkhttp;
    @Bind(R.id.lv_fragment_plan)
    ListView lvFragmentPlan;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    private OkHttpClient okHttpClient;
    private Request request;
    private Request.Builder builder;
    private String mBaseUrl = "http://172.16.36.92:8080/Imooc_okhttp1/";
    private String url1 = "http://172.16.36.92:21663/api/user/loginG?username=admin&password=1";
    private String url2 = "http://172.16.36.92:7000/api/user/loginG?username=admin&password=1";
    private String urlGet2 = "http://172.16.36.92:21663/api/user/userLogin?username=admin&password=1";
    private String urlGet1 = "http://172.16.36.92:7000/api/user/userLogin?username=admin&password=1";
    private String urlPost1 = "http://172.16.36.92:21663/api/user/loginP";
    private String urlPost2 = "http://172.16.36.92:21663/api/user/loginbymodel";
    private String urlGetPlan = "http://172.16.36.92:21663/api/GetAllPlan/?username=巡检乙班&rolename=电气岗位点检员";
    private String urlGetPlan1 = "http://172.16.36.92:21663/api/GetAllPlan/?username=巡检甲班&rolename=电气岗位点检员";
    private String urlPostUserTest = "http://172.16.36.92:21663/api/upload";
    private String urlGetUserTest = "http://localhost:52681/api/user/get";
    private AsyncHttpClient asyncHttpClient;
    private List<PlanBean> mDatas;
    private CommonBaseAdapter<PlanBean> planAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_test);
        ButterKnife.bind(this);
        initTitle();
        initData();
        mDatas = new ArrayList<>();
        planAdapter = new CommonBaseAdapter<PlanBean>(AtyLoginTest.this, mDatas, R.layout.item_fragment_plan_lv) {
            @Override
            public void convert(ViewHolder holder, PlanBean planBean) {
                holder.setText(R.id.tv_plan_id, planBean.getPLAN_ID())
                        .setText(R.id.tv_plan_name, planBean.getPLAN_NAME());
            }
        };
        lvFragmentPlan.setAdapter(planAdapter);
        swiperefresh.setColorSchemeColors(Color.BLUE);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reConnect();
                swiperefresh.setRefreshing(false);
            }
        });
    }
    @OnClick(R.id.btn_doget_user)
    public void doGetLogin() {

        asyncHttpClient.get(urlGetUserTest, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                Log.e("TAG", "onSuccess: " + content);
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                Log.e("TAG", "onFailure: " + error.getMessage());
            }
        });

    }

    @OnClick(R.id.btn_dopost_user)
    public void doPostLogin() {
        //3.将Request封装为Call

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                "{username:hyman,password:1234567}");
        Request request = builder.post(requestBody)
                .url(urlPostUserTest)
                .build();
        //4.执行Call
        try {
            excuteCall(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.btn_doget_okhttp)
    public void doGetByOkHttpLogin() {
        doGetByOkHttp1(urlGetUserTest);
    }

    @OnClick(R.id.btn_dopost_asyn)
    public void doPostByAsynLogin() {
        doPostByAsnc(urlPostUserTest);
    }

    @OnClick(R.id.btn_dopost_okhttp)
    public void doPostByOkHttpLogin() {
        doPostByOkHttp(urlPostUserTest);
    }

    private void doPostByOkHttp(String urlPostUserTest) {
        OkHttpClient okHttpClient1 = new OkHttpClient();
        okHttpClient1.newBuilder().build();

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        UserLoginTest user=new UserLoginTest("username","111");
        HashMap<String,String> map=new HashMap<>();
        map.put("LoginName","admin");
        map.put("LoginPwd","111111");
        String postStr = gson.toJson(map);
        RequestBody requestBody = RequestBody.create(mediaType, postStr);
        Request request = new Request.Builder().post(requestBody).url(urlPostUserTest).build();
        okHttpClient1.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: "+e.getLocalizedMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG", "onResponse: "+response.body().toString() );
            }
        });

    }

    private void doGetByAsnc(String url) {
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {//200 404
                //UIUtils.toast("success",false);
                //解析Json
                if (!TextUtils.isEmpty(content)) {
                    UserBean user = new Gson().fromJson(content, UserBean.class);
                    boolean isLogin = user.getIsLogin();
                    if (isLogin) {
                        //保存用户信息
                        //saveUser(user);
                        //重新加载界面
                        // removeAll();
                        // goToActivity(MainActivity.class, null);
                    } else {
                        UIUtils.toast("服务器异常，请刷新后重新登录！", false);
                    }
                } else {
                    UIUtils.toast("您输入的用户名或密码不正确，请检查确认后重新登录！", false);

                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                UIUtils.toast("连接服务器失败，错误信息" + error.getMessage() + "，请检查网络是否连接！", false);
            }
        });

    }

    private void doPostByAsnc(String url) {

        RequestParams params = new RequestParams();
        UserLoginTest user = new UserLoginTest("admin", "1");
        String json = new Gson().toJson(user);
        params.put("user", json);
        //params.put("password", "1");
        asyncHttpClient.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {//200 404
                //UIUtils.toast("success",false);
                //解析Json
                if (!TextUtils.isEmpty(content)) {
                    if (content.equals("1")) {
                        UIUtils.toast("succ" + content, false);
                    }
                    UIUtils.toast("content" + content, false);

                } else {
                    UIUtils.toast("您输入的用户名或密码不正确，请检查确认后重新登录！", false);
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                UIUtils.toast("连接服务器失败，错误信息" + error.getMessage() + "，请检查网络是否连接！", false);
            }
        });
    }

    private void doGetByOkHttp1(String url) {
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

    private void doPostByOkHttp1(String url) {
        UserLoginTest user = new UserLoginTest("admin", "1");
        String json = new Gson().toJson(user);
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("user", "json");
        //formBuilder.add("password", "1");
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain charset=utf-8"), "{username:hyman,password:1234567}");
        // RequestBody requestBody = formBuilder.build();
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
    private void excuteCall(Request request) throws IOException {
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure() called with: call = [" + call + "], e = [" + e + "]");
                UIUtils.toast("连接服务器失败，错误信息，请检查网络是否连接！", false);

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
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitle.setText("用户登录");
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    public void initData() {
        okHttpClient = new OkHttpClient();
        builder = new Request.Builder();
        asyncHttpClient = new AsyncHttpClient();

    }

    private void connect(String url) {
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                //解析获取的数据
                List<PlanBean> beanList = parseDatas(content);
                mDatas.addAll(beanList);
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });

    }

    //重新联网获取最新计划数据
    private void reConnect() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlGetPlan, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                mDatas.clear();
                List<PlanBean> beanList = parseDatas(content);
                mDatas.addAll(beanList);
                Log.e("TAG", "onSuccess() called with: content = [" + content + "]");
                planAdapter.notifyDataSetChanged();
                UIUtils.toast("刷新成功,数据已经是最新数据了。", false);
                swiperefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Throwable error, String content) {
                UIUtils.toast("刷新失败,请检查你的网络连接！", false);
            }
        });
    }

    //解析获取的数据
    private List<PlanBean> parseDatas(String content) {
        List<PlanBean> data = new ArrayList<>();
        if (!TextUtils.isEmpty(content)) {
            Gson gson = new Gson();
            // PlanBean1 planBean1 = gson.fromJson(content, PlanBean1.class);
            Type listType = new TypeToken<List<PlanBean>>() {
            }.getType();
            data = gson.fromJson(content, listType);
        } else {
            UIUtils.toast(",您好，本班没有您需要点检的工作计划。", false);
        }
        return data;
    }

    @OnClick(R.id.btn_doget_asyn)
    public void doGetByAsynLogin(View view) {
        //TODO 测试方便，MD5密码校验暂时放在webApi，后期改为客户端校验
        // String url=Constant.USERLOGIN+"?username="+username+"&password="+password;
        // params.put("password",MD5Utils.MD5(password));
        doGetByAsnc(urlGet2);
    }

    @OnClick(R.id.iv_title_back)
    public void back() {
        //removeAll();
        //goToActivity(MainActivity.class, null);
    }



}
