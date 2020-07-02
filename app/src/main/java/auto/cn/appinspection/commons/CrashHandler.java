package auto.cn.appinspection.commons;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;
import auto.cn.appinspection.utils.UIUtils;


/**
 * 程序中未捕获的全局异常的捕获（单例）
 * 解决两个问题：
 * 1、当出现未捕获的异常时，能够给用户一个相对友好的提示；
 * 2、在出现异常时，能够将异常信息发送到后台，便于在后续的版本中解决bug
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    //系统默认的处理未捕获异常的处理器,就是黑框提示
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    //单例模式：（懒汉式）懒汉式与饿汉式相比延迟了实例化
    //本身实例化未捕获异常的处理器的操作、异常的处理就是系统在一个单独的线程中完成的，
    //不涉及多线程的问题，不存在线程安全问题，使用懒汉式更好。
    private CrashHandler() {
    }
    private static CrashHandler crashHandler=null;
    public static CrashHandler getInstance() {
        if (crashHandler == null) {
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }
    public void init() {
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将当前类设置为出现未捕获异常的处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    //一旦系统出现未捕获的异常，就会对调用用如下回调方法
    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        //Log.e("tag","亲，出现了未捕获的异常了"+ex.getMessage());
        new Thread() {
            @Override
            public void run() {
                //Looper.prepare()和 Looper.loop()之间的操作就是在主线程中执行的
                //在android系统中，默认情况下，一个线程中是不可以调用looper进行消息处理的，除非是主线程
                Looper.prepare();
                Toast.makeText(UIUtils.getContext(), "亲，出现了未捕获的异常了", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        //收集异常信息
        collectionException(ex);
        try {
            Thread.sleep(2000);
            //移除当前的Activity
            ActivityManager.getInstance().removeCurrent();
            //结束当前进程
            Process.killProcess(Process.myPid());
            //结束虚拟机
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void collectionException(Throwable ex){
        final String exMessage = ex.getMessage();
        //手机具体的客户的手机、系统的信息
        final String message=Build.DEVICE+":"+Build.MODEL+":"+Build.PRODUCT+":"+Build.MANUFACTURER;
        //发送给后台此异常信息

        new Thread(){
            @Override
            public void run() {
                //需要按照指定的url，访问后台的sevlet，将异常信息发送过去，类似于注册操作
                Log.e("tag","exMassage="+exMessage+"massage="+message);
            }
        }.start();
    }
}
