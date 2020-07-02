package auto.cn.appinspection.atys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.beans.DownLoadBean;
import auto.cn.appinspection.commons.ActivityManager;
import auto.cn.appinspection.commons.AppNetConfig;
import auto.cn.appinspection.net.AppUpdater;
import auto.cn.appinspection.net.INetCallback;
import auto.cn.appinspection.ui.UpdateVersionShowDialog;
import auto.cn.appinspection.utils.DownloadUtils;
import auto.cn.appinspection.utils.UIUtils;
import butterknife.Bind;

public class AtyWelcome extends BaseActivity {
    @Bind(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @Bind(R.id.tv_welcome_title)
    TextView tvWelcomeTitle;
    @Bind(R.id.rl_welcome)
    RelativeLayout rlWelcome;

    private boolean connect;
    private long startTime;
    private static final int TO_MAIN = 1;
    private static final int DOWNLOAD_VERSION_SUCCESS = 2;
    private static final int DOWNLOAD_APK_FAIL = 3;
    private static final int DOWNLOAD_APK_SUCCESS = 4;

   // private UpdateInfo updateInfo;
    private ProgressDialog dialog;
    private File apkFile;
    private Handler handler = new Handler()

    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TO_MAIN:
                    finish();
                    goToActivity(MainActivity.class,null);
                   // startActivity(new Intent(AtyWelcome.this, MainActivity.class));
                    break;
//                case DOWNLOAD_VERSION_SUCCESS:
//                    //获取当前应用的版本信息
//                    String version = getVersion();
//                    //更新页面显示的版本信息
//                    //tvWelcomeVersion.setText(version);
//                    //比较服务器获取的最新的版本跟本应用的版本是否一致
//                    if(version.equals(updateInfo.version)){
//                        UIUtils.toast("当前应用已经是最新版本",false);
//                        toMain();
//                    }else{
//                        new AlertDialog.Builder(AtyWelcome.this)
//                                .setTitle("下载最新版本")
//                                .setMessage(updateInfo.desc)
//                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        //下载服务器保存的应用数据
//                                        downloadApk();
//                                    }
//                                })
//                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        toMain();
//                                    }
//                                })
//                                .show();
//                    }
//
//                    break;
//                case DOWNLOAD_APK_FAIL:
//                    UIUtils.toast("联网下载数据失败",false);
//                    toMain();
//                    break;
//                case DOWNLOAD_APK_SUCCESS:
//                    UIUtils.toast("下载应用数据成功",false);
//                    dialog.dismiss();
//                    installApk();//安装下载好的应用
//                    finish();//结束当前的welcomeActivity的显示
//                    break;
            }
        }
    };
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
        //联网更新应用
        updateApkFile();
    }
    //设置启动动画
    private void setAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);//0：完全透明 1：完全不透明
        alphaAnimation.setDuration(3000);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());//设置动画的变化率,加速显示
        rlWelcome.startAnimation(alphaAnimation);
        //使用handler
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(AtyWelcome.this, MainActivity.class);
//                startActivity(intent);
//                //结束Activity的显示，并从栈空间中移除
//                ActivityManager.getInstance().remove(AtyWelcome.this);
//            }
//        }, 3000);
    }
    //判断手机是否联网
    public boolean isConnect() {
        boolean connected = false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            connected = networkInfo.isConnected();
        }
        return connected;
    }
    private void updateApkFile() {
        //获取系统当前时间
        startTime = System.currentTimeMillis();
        //1.判断手机是否可以联网
        boolean connect = isConnect();
        if (!connect) {//没有移动网络
            UIUtils.toast("当前没有移动数据网络", false);
            toMain();
        } else {//有移动网络
            //联网获取服务器的最新版本数据
            AppUpdater.getsInstance().getmNetManager().get("http://59.110.162.30/app_updater_version.json", new INetCallback() {
                @Override
                public void success(String response) {
                    Log.e("tag", "response=" + response);
                    //1.解析json
                    DownLoadBean bean=DownLoadBean.parse(response);
                    if(bean==null){
                        Toast.makeText(AtyWelcome.this,"版本检测接口返回数据异常！",Toast.LENGTH_SHORT).show();
                        toMain();
                        return;
                    }
                    //检测：是否需要弹框
                    try {
                        long versionCode = Long.parseLong(bean.getVersionCode());
                        if(versionCode<=DownloadUtils.getVersionCode(AtyWelcome.this)){
                            Toast.makeText(AtyWelcome.this,"已经是最新版本，无需更新！！",Toast.LENGTH_SHORT).show();
                            toMain();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Toast.makeText(AtyWelcome.this,"版本检测接口返回版本号异常！",Toast.LENGTH_SHORT).show();
                        toMain();
                    }
                    //3.弹框
                    UpdateVersionShowDialog.show(AtyWelcome.this,bean);
                    //2.版本匹配
                    //3.弹框
                    //4.点击下载
                   // handler.sendEmptyMessage(DOWNLOAD_VERSION_SUCCESS);
                }

                @Override
                public void failed(Throwable throwable) {
                    Toast.makeText(AtyWelcome.this,"版本更新接口请求失败！",Toast.LENGTH_SHORT).show();
                    throwable.printStackTrace();
                    toMain();
                }
            },AtyWelcome.this);
            /**
             *
             AsyncHttpClient client = new AsyncHttpClient();
             String url = AppNetConfig.UPDATE;
             client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
            //解析json数据
            updateInfo = JSON.parseObject(content, UpdateInfo.class);
            handler.sendEmptyMessage(DOWNLOAD_VERSION_SUCCESS);
            }

            @Override
            public void onFailure(Throwable error, String content) {
            UIUtils.toast("联网请求数据失败", false);
            toMain();
            }
            });

             */
        }
    }
    //进入主界面
    public void toMain() {
        long currentTime = System.currentTimeMillis();
        long delayTime = 3000 - (currentTime - startTime);
        if (delayTime < 0) {
            delayTime = 0;
        }
        handler.sendEmptyMessageDelayed(TO_MAIN, delayTime);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppUpdater.getsInstance().getmNetManager().cancel(this);
    }
/**
     * 当前版本号
     * @return

    private String getVersion() {
        String version = "未知版本";
        PackageManager manager = getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace(); //如果找不到对应的应用包信息, 就返回"未知版本"
        }
        return version;
    }
    private void installApk() {
        Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
        intent.setData(Uri.parse("file:" + apkFile.getAbsolutePath()));
        startActivity(intent);
    }
//    //下载文件
//    private void downloadApk() {
//        //初始化水平进度条的dialog
//        dialog = new ProgressDialog(this);
//        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        dialog.setCancelable(false);
//        dialog.show();
//        //初始化数据要保持的位置
//        File filesDir;
//        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//            filesDir = this.getExternalFilesDir("");
//        }else{
//            filesDir = this.getFilesDir();
//        }
//        apkFile = new File(filesDir,"update.apk");
//
//        //启动一个分线程联网下载数据：
//        new Thread(){
//            public void run(){
//                String path = updateInfo.apkUrl;
//                InputStream is = null;
//                FileOutputStream fos = null;
//                HttpURLConnection conn = null;
//                try {
//                    URL url = new URL(path);
//                    conn = (HttpURLConnection) url.openConnection();
//
//                    conn.setRequestMethod("GET");
//                    conn.setConnectTimeout(5000);
//                    conn.setReadTimeout(5000);
//
//                    conn.connect();
//
//                    if(conn.getResponseCode() == 200){
//                        dialog.setMax(conn.getContentLength());//设置dialog的最大值
//                        is = conn.getInputStream();
//                        fos = new FileOutputStream(apkFile);
//
//                        byte[] buffer = new byte[1024];
//                        int len;
//                        while((len = is.read(buffer)) != -1){
//                            //更新dialog的进度
//                            dialog.incrementProgressBy(len);
//                            fos.write(buffer,0,len);
//
//                            SystemClock.sleep(1);
//                        }
//
//                        handler.sendEmptyMessage(DOWNLOAD_APK_SUCCESS);
//
//                    }else{
//                        handler.sendEmptyMessage(DOWNLOAD_APK_FAIL);
//
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }finally{
//                    if(conn != null){
//                        conn.disconnect();
//                    }
//                    if(is != null){
//                        try {
//                            is.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if(fos != null){
//                        try {
//                            fos.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }.start();
//    }
     */
}
