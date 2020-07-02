package auto.cn.appinspection.net;

public interface INetCallback {
    void  success(String response);
    void failed(Throwable throwable);
}
