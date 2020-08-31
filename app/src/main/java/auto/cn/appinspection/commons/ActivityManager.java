package auto.cn.appinspection.commons;

import android.app.Activity;

import java.util.Stack;

/**
 * 统一应用程序中所有的Activity的栈管理（单例）
 * 涉及到Activity的添加、删除指定、删除当前、删除所有、返回栈大小的方法
 */
public class ActivityManager {
    //提供栈的对象
    private Stack<Activity> activityStack = new Stack<>();
    private static ActivityManager activityManager = new ActivityManager();
    //单例模式：饿汉式
    private ActivityManager() {
    }
    public static ActivityManager getInstance() {
        return activityManager;
    }

    //activity的添加
    public void add(Activity activity) {
        if (activity != null) {
            activityStack.add(activity);
        }
    }

    //删除指定的Activity
    public void remove(Activity activity) {
        //栈空间先进后出，使用正向遍历会出问题
        if (activity != null) {
            for (int i = activityStack.size() - 1; i >= 0; i--) {
                Activity currentActivity = activityStack.get(i);
                if (currentActivity.getClass().equals(activity.getClass())) {
                    currentActivity.finish();//销毁当前的activity的显示
                    activityStack.remove(i);//从栈空间移除
                }
            }
        }
    }
    //删除当前的Activity
    public void removeCurrent() {
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    //删除所有的Activity
    public void removeAll() {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity = activityStack.get(i);
            activity.finish();
            activityStack.remove(activity);
        }
    }
    //获取当前的Activity
    public Activity  getCurActivity() {
        if(activityStack.size()>0&&activityStack!=null) {
            Activity activity = activityStack.lastElement();
            return activity;
        }else {
            return null;
        }
    }
    //返回栈的大小
    public int getSize() {
        return activityStack.size();
    }
}

