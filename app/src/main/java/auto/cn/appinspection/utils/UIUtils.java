package auto.cn.appinspection.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import auto.cn.appinspection.bases.MyApplication;
/**
 * 专门提供为处理一些UI相关的问题而创建的工具类
 * 提供资源获取的通用方法，避免每次都写重复的代码获取结果
 */
public class UIUtils {
    public static Context getContext() {
        return MyApplication.context;
    }

    public static android.os.Handler getHandler() {
        return MyApplication.handler;
    }

    //加载指定viewId的视图对象，并返回
    public static int getColor(int colorId) {
        return getContext().getResources().getColor(colorId);
    }

    //加载指定viewId的对象视图
    public static View getView(int viewId) {
        View view = View.inflate(getContext(), viewId, null);
        return view;
    }

    public static String[] getStringArr(int strArrId) {
        return getContext().getResources().getStringArray(strArrId);
    }

    //将dp转换为px
    public static int dp2px(int dp) {
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);//实现四舍五入
    }

    //将dp转换为px
    public static int px2dp(int px) {
        //获取手机密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);//实现四舍五入
    }
     //保证runnable中的操作在主线程中执行
    public static void runOnUiThread(Runnable runnable) {
        if(isInMainThread()){
            runnable.run();
        }else{
            UIUtils.getHandler().post(runnable);
        }
    }
    //判断当前线程是否是主线程
    private static boolean isInMainThread() {
        int currentThreadId=android.os.Process.myTid();
        return MyApplication.mainThreadId==currentThreadId;
    }

    public static void toast(String message,boolean isLengthLong){
        Toast.makeText(UIUtils.getContext(),message,isLengthLong?Toast.LENGTH_LONG:Toast.LENGTH_SHORT).show();
    }
    //字符格式转Date-Time
    public static  void string2Date(String str){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateTime = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}

