package auto.cn.appinspection.net;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 最新版本仅支持 Android 5.0+ (API level 21+) and on Java 8+.，The OkHttp 3.12.x branch supports Android 2.3+ (API level 9+) and Java 7+.
 * implementation("com.squareup.okhttp3:okhttp:4.7.2")
 */
public class OKHttpNetManager implements INetManager {
    //OkHttpClient，尽量不要重复实例化，通过OkHttpClient.Builder builder创建OkHttpClient实例sOkHttpClient
    private static OkHttpClient sOkHttpClient;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //OkHttpClient有很多设置，
        //比如：https 自签名，Okhttp 握手的错误，需要设置builder的参数builder.sslSocketFactory(),设置证书相关的操作
        builder.connectTimeout(15, TimeUnit.SECONDS);
        sOkHttpClient = builder.build();
    }

    @Override
    public void get(String url, final INetCallback callback, Object tag) {
        //Request.Builder->Request->Call->execute/enqueue
        Request.Builder builder = new Request.Builder();
        final Request request = builder.url(url).get().tag(tag).build();
        Call call = sOkHttpClient.newCall(request);
        // Response response = call.execute();//同步操作，在当前线程中执行任务，返回结果
        //异步操作
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //非ui线程
                sHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        e.printStackTrace();
                        callback.failed(e);
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    final String string = response.body().string();
                    //非ui线程
                    sHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.success(string);
                        }
                    });
                } catch (final Throwable e) {
                    //非ui线程
                    sHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            e.printStackTrace();
                            callback.failed(e);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void download(String url, final File targetFile, final INetDownloadCallback callback, Object tag) {
        //判断文件目录是否存在，不存在则创建目录
        if (!targetFile.exists()) {
            targetFile.getParentFile().mkdirs();
        }
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).get().tag(tag).build();
        Call call = sOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                sHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.failed(e);
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                OutputStream os = null;
                //ctrl+alt+t 包裹try-catch
                try {
                    //获取文件的总的长度
                    final long totalLen = response.body().contentLength();
                    is = response.body().byteStream();
                    os = new FileOutputStream(targetFile);
                    byte[] buffer = new byte[8 * 1024];
                    long curLen = 0;
                    int bufferLen = 0;
                    while (!call.isCanceled()&&(bufferLen = is.read(buffer)) != -1) {
                        os.write(buffer, 0, bufferLen);
                        os.flush();
                        curLen += bufferLen;
                        final long finalCunlen = curLen;
                        sHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                //计算下载进度，转换为float
                                callback.progress((int) (finalCunlen * 1.0f / totalLen * 100));
                            }
                        });
                    }
                    if(call.isCanceled()) return;
                    //为了确保文件能够被访问，需要设置以下权限，如果文件存储在sd卡上，可以不设置
                    try {
                        targetFile.setExecutable(true, false);
                        targetFile.setReadable(true, false);
                        targetFile.setWritable(true, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.success(targetFile);
                        }
                    });
                } catch (final Throwable e) {
                    if(call.isCanceled()) return;
                    e.printStackTrace();
                    sHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.failed(e);
                        }
                    });

                } finally {
                    if (is != null) {
                        is.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                }
            }
        });
    }
     // 点击“后退”按钮取消下载，通过sOkHttpClient.dispatcher().queuedCalls()获取queuedCalls
    //通过sOkHttpClient.dispatcher().runningCalls()获取runningCalls，遍历并取消call
    @Override
    public void cancel(Object tag) {
        List<Call> queuedCalls = sOkHttpClient.dispatcher().queuedCalls();
        if (queuedCalls != null) {
            for (Call call : queuedCalls ) {
                if (tag.equals(call.request().tag())) {
                    Log.d("tag", "cancel() called with: tag = [" + tag + "]");
                    call.cancel();
                }
            }
        }
        List<Call> runningCalls = sOkHttpClient.dispatcher().runningCalls();
       if(runningCalls!=null){
        for (Call call:runningCalls) {
            if(tag.equals(call.request().tag())){
                Log.d("tag", "cancel() called with: tag = [" + tag + "]");
                call.cancel();
            }
        }
        }
    }
}
