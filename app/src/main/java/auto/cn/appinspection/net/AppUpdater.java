package auto.cn.appinspection.net;
public class AppUpdater {
    /**使用者只需要通过这个类，实现全部功能；
     * 接口隔离具体的实现，比如可以使用new OKHttpNetManager()实现接口，也可以使用其他的类实现，只需要修改实现类
     * 的代码，还不需要改动其他部分
     *多个开发者可以并行开发
     **/
    //网络请求，下载的能力，可以使用的框架非常多，一旦日后有新的更好的框架，仅仅需要替换框架即可
    //okHttp,vollery,httpClient,httpUrlConn
    private INetManager mNetManager=new OKHttpNetManager();
    public INetManager getmNetManager(){
        return  mNetManager;
    }
    private static AppUpdater sInstance=new AppUpdater();
    public static AppUpdater getsInstance(){
        return sInstance;
    }
}
