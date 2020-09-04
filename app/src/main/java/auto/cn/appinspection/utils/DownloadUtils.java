package auto.cn.appinspection.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class DownloadUtils {
    //获取版本
    public static long getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            //版本适配
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                //版本适配，如果android版本>=android9
                return packageInfo.getLongVersionCode();
            } else {
                return packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //安装apk文件
    public static void installApk(Activity activity, File apkFile) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//android 7.0
            //fileprovider:AndroidManifest中定义的“authorities”
            uri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileprovider", apkFile);
            //添加读写权限
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(apkFile);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        activity.startActivity(intent);

        //INSTALL PERMISSION的适配：Manifest文件中增加权限<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"></uses-permission>

    }

    //文件MD5比对
    public static String getFileMd5(File targetFile) {
        if (targetFile == null && !targetFile.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(targetFile);
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        byte[] result = digest.digest();
        BigInteger bitInt = new BigInteger(1, result);
        return bitInt.toString(16);
    }
}
