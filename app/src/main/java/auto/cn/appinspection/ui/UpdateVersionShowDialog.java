package auto.cn.appinspection.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import auto.cn.appinspection.R;
import auto.cn.appinspection.atys.AtyWelcome;
import auto.cn.appinspection.beans.VersionDownLoadBean;
import auto.cn.appinspection.commons.ActivityManager;
import auto.cn.appinspection.net.AppUpdater;
import auto.cn.appinspection.net.INetDownloadCallback;
import auto.cn.appinspection.utils.DownloadUtils;
import auto.cn.appinspection.utils.LogUtil;
import butterknife.ButterKnife;

public class UpdateVersionShowDialog extends DialogFragment {
    private static final String KEY_DOWNLOAD_BEAN = "download_bean";
    private VersionDownLoadBean mDownloadBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mDownloadBean = (VersionDownLoadBean) arguments.getSerializable(KEY_DOWNLOAD_BEAN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_update, container, false);
        bindEvents(view);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置没有标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置背景透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void bindEvents(View view) {
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvContent = view.findViewById(R.id.tv_content);
        final TextView tvUpdate = view.findViewById(R.id.tv_update);
        tvTitle.setText(mDownloadBean.getTitle());
        tvContent.setText(mDownloadBean.getContent());
        tvUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                //设置按钮不可重复点击
                v.setEnabled(false);
                File filesDir;
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    filesDir = getActivity().getExternalFilesDir("");
                } else {
                    filesDir = getActivity().getFilesDir();
                }
                final File targetFile = new File(filesDir, "app.apk");
                //final File targetFile=new File(getActivity().getCacheDir(),"target.apk");
                AppUpdater.getsInstance().getmNetManager().download(mDownloadBean.getUrl(), targetFile, new INetDownloadCallback() {
                    @Override
                    public void success(File apkFile) {
                        //设置按钮可以点击
                        v.setEnabled(true);
                        //安装的代码
                        LogUtil.e("success=" + apkFile.getAbsolutePath()+apkFile.length());
                        dismiss();
                        //TOdO check md5
                        String fileMd5 = DownloadUtils.getFileMd5(targetFile);
                        //logm 快捷键
                        LogUtil.e("success() called with: apkFile = [" + fileMd5 + "]");
                        if (fileMd5 != null && fileMd5.equals(mDownloadBean.getMd5())) {
                            DownloadUtils.installApk(getActivity(), apkFile);
                        } else {
                            Toast.makeText(getActivity(), "md5 检测失败！", Toast.LENGTH_SHORT).show();
                            Activity curActivity = ActivityManager.getInstance().getCurActivity();
                            if (curActivity instanceof AtyWelcome) {
                                ((AtyWelcome) getActivity()).toMain();
                            }
                        }
                    }

                    @Override
                    public void progress(int progress) {
                        //更新界面的代码
                        LogUtil.e("progress=" + progress);
                        tvUpdate.setText(progress + "%");
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        Toast.makeText(getActivity(), "文件下载失败！", Toast.LENGTH_SHORT).show();
                        //设置按钮可以点击
                        v.setEnabled(true);
                        Activity curActivity = ActivityManager.getInstance().getCurActivity();
                        if (curActivity instanceof AtyWelcome) {
                            ((AtyWelcome) getActivity()).toMain();
                        }
                    }
                }, UpdateVersionShowDialog.this);
            }
        });
    }

    //MainActivity满足条件显示dialog时将get获取的信息传递给dialog显示，并给dialog设置一个tag标志
    public static void show(FragmentActivity activity, VersionDownLoadBean bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_DOWNLOAD_BEAN, bean);
        UpdateVersionShowDialog dialog = new UpdateVersionShowDialog();
        dialog.setArguments(bundle);
        dialog.show(activity.getSupportFragmentManager(), "updateVersionShowDialog");
    }

    //dialog消失之后取消下载
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        LogUtil.e("onDismiss() called with: dialog = [onDismiss]");
        AppUpdater.getsInstance().getmNetManager().cancel(this);
        Activity curActivity = ActivityManager.getInstance().getCurActivity();
        if (curActivity instanceof AtyWelcome) {
            ((AtyWelcome) getActivity()).toMain();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
