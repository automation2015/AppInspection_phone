package auto.cn.appinspection.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import auto.cn.appinspection.R;
import auto.cn.appinspection.atys.AtyLogin;
import auto.cn.appinspection.atys.GestureEditActivity;
import auto.cn.appinspection.atys.GestureVerifyActivity;
import auto.cn.appinspection.atys.MainActivity;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.bases.BaseFragment;
import auto.cn.appinspection.beans.UserBean;
import auto.cn.appinspection.utils.BitmapUtils;
import auto.cn.appinspection.utils.LogUtil;
import auto.cn.appinspection.utils.UIUtils;
import butterknife.Bind;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static auto.cn.appinspection.commons.Constant.CAMERA;
import static auto.cn.appinspection.commons.Constant.PICTURE;

public class MeFragment extends BaseFragment {
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @Bind(R.id.tv_me_name)
    TextView tvMeName;
    @Bind(R.id.rl_icon)
    RelativeLayout rlIcon;
    @Bind(R.id.tv_me_username)
    TextView tvMeUsername;
    @Bind(R.id.tv_me_rolename)
    TextView tvMeRolename;
    @Bind(R.id.tv_me_logintime)
    TextView tvMeLogintime;
    @Bind(R.id.tv_me_changeuser)
    TextView tvMeChangeuser;
    @Bind(R.id.tv_me_exit)
    TextView tvMeExit;
    @Bind(R.id.toggle_me)
    ToggleButton toggleMe;
    @Bind(R.id.tv_me_reset)
    TextView tvMeReset;
    private SharedPreferences sp;

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    public void initTitle() {
        ivTitleBack.setVisibility(View.GONE);
        tvTitle.setText("我的信息");
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData(String content) {
        //初始化SharedPreferences
        sp = this.getActivity().getSharedPreferences("secret_protect", Context.MODE_PRIVATE);
        //获取当前设置手势密码的ToggleButton的状态
        getGestureStatus();
        //判断用户是否已经登录isLogin();
        isLogin();
        //设置手势密码
        setGesturePassword();
        //重置手势密码
        resetGesture();


    }
    private void getGestureStatus() {
        boolean isOpen = sp.getBoolean("isOpen", false);
        toggleMe.setChecked(isOpen);
    }
    private void setGesturePassword() {
        toggleMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    UIUtils.toast("开启了手势密码", false);
//                    sp.edit().putBoolean("isOpen", true).commit();
                    String inputCode = sp.getString("inputCode", "");
                    if (TextUtils.isEmpty(inputCode)) {//之前没有设置过
                        new AlertDialog.Builder(MeFragment.this.getActivity())
                                .setTitle("设置手势密码")
                                .setMessage("是否现在设置手势密码")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        UIUtils.toast("现在设置手势密码", false);
                                        sp.edit().putBoolean("isOpen", true).commit();
//                                            toggleMore.setChecked(true);
                                        //开启新的activity:
                                        ((BaseActivity) MeFragment.this.getActivity()).goToActivity(GestureEditActivity.class, null);
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        UIUtils.toast("取消了现在设置手势密码", false);
                                        sp.edit().putBoolean("isOpen", false).commit();
                                        toggleMe.setChecked(false);
                                    }
                                })
                                .show();
                    } else {
                        UIUtils.toast("开启手势密码", false);
                        sp.edit().putBoolean("isOpen", true).commit();
//                        toggleMore.setChecked(true);
                    }
                } else {
                    UIUtils.toast("关闭了手势密码", false);
                    sp.edit().putBoolean("isOpen", false).commit();
//                    toggleMore.setChecked(false);
                }
            }
        });
    }
    private void resetGesture() {
        tvMeReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = toggleMe.isChecked();
                if (checked) {
                    ((BaseActivity) MeFragment.this.getActivity()).goToActivity(GestureEditActivity.class, null);
                } else {
                    UIUtils.toast("手势密码操作已关闭，请开启后再设置", false);
                }
            }
        });
    }

    private void isLogin() {
        //查看本地是否有用户的登录信息
        //TODO 测试过程中偶发空指针，原因未知
        SharedPreferences sp = this.getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String username = sp.getString("username", "");
        if (TextUtils.isEmpty(username)) {
            //本地没有保存过用户信息，给出提示：登录
            doLogin();
        } else {
            //已经登录过，则直接加载用户的信息并显示
            doUser();
        }
    }

    private void doUser() {
        //1.读取本地保存的用户信息
        UserBean user = ((BaseActivity) this.getActivity()).readUser();
        //2.获取对象信息，并设置给相应的视图显示
        tvMeUsername.setText(user.getUsername());
        tvMeRolename.setText(user.getRolename());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        tvMeLogintime.setText(sdf.format(new Date()));
        //判断本地是否已经保存头像的图片，如果有，则不再执行联网操作
//        if (isExist) {
//            return;
//        }
        String bitmapPath=readImage();
        if(!TextUtils.isEmpty(bitmapPath)){
            Picasso.with(this.getActivity()).load(new File(bitmapPath)).transform(new Transformation(){
                @Override
                public Bitmap transform(Bitmap source) {
                    Bitmap bitmap =makeIcon(source);
                    return bitmap;
                }
                @Override
                public String key() {
                    return "";

            }}).into(ivMeIcon);
        }else {
            Picasso.with(this.getActivity()).load(R.mipmap.icon_shangang).transform(new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {//下载以后的内存中的bitmap对象
                    Bitmap bitmap =makeIcon(source);
                    return bitmap;
                }

                @Override
                public String key() {
                    //此方法没有什么作用，但是要保证返回值不为null，否则报错
                    return "";
                }
            }).into(ivMeIcon);
        }
//
        //判断一下，是否开启了手势密码。如果开启：先输入手势密码
        SharedPreferences sp = this.getActivity().getSharedPreferences("secret_protect", Context.MODE_PRIVATE);
        boolean isOpen = sp.getBoolean("isOpen", false);
        if(isOpen){
            ((BaseActivity)this.getActivity()).goToActivity(GestureVerifyActivity.class,null);
            return;
        }
    }
    //处理图片的方法：压缩、圆形
    private Bitmap makeIcon(Bitmap source) {
        //压缩处理
        Bitmap bitmap = BitmapUtils.zoom(source, UIUtils.dp2px(62),
                UIUtils.dp2px(62));
        //圆形处理
        bitmap = BitmapUtils.circleBitmap(bitmap);
        //回收bitmap资源
        source.recycle();
        return bitmap;
    }

    private void doLogin() {
        new AlertDialog.Builder(this.getActivity())
                .setTitle("提示")
                .setMessage("您还没有登录哦！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //UIUtils.toast("进入登录页面",false);
                        ((BaseActivity) MeFragment.this.getActivity()).goToActivity(AtyLogin.class, null);
                    }
                })
                .setCancelable(false)
                .show();
    }

    private String readImage() {
        File fileDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //判断sd卡是否绑定
            //路径1：storage/sdcard/Android/data/包名/files
            //fileDir = this.getActivity().getFilesDir();
            fileDir = this.getActivity().getExternalFilesDir("");
        } else {
            //手机内部存储
            //路径：data/data/包名/files
            fileDir = this.getActivity().getFilesDir();
        }
        File file = new File(fileDir, "icon.png");
        if (file.exists()) {
            //存储-->内存
           // Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            //ivMeIcon.setImageBitmap(bitmap);
            //return true;
            return file.getAbsolutePath();
        }
        return null;
    }

    @OnClick({R.id.tv_me_changeuser, R.id.tv_me_exit, R.id.rl_icon})
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        switch (v.getId()) {
            case R.id.tv_me_changeuser:
                ((BaseActivity) this.getActivity()).goToActivity(AtyLogin.class, null);
                break;
            case R.id.tv_me_exit:
                builder.setTitle("提示")
                        .setMessage("您确定退出登录吗？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //1.将保存在sp中的数据清除
                                SharedPreferences sp = ((BaseActivity) MeFragment.this.getActivity()).getSharedPreferences("user_info", Context.MODE_PRIVATE);
                                sp.edit().clear().commit();//清除数据操作必须提交；提交以后，文件仍存在，只是文件中的数据被清除了
                                //2.将本地保存的图片的file删除
                                //TODO 由于图片并未存储在服务器端，暂时设定为不清除
                                /**
                                 File filesDir;
                                 if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//判断sd卡是否挂载
                                 //路径1：storage/sdcard/Android/data/包名/files
                                 filesDir = ((BaseActivity) MeFragment.this.getActivity()).getExternalFilesDir("");

                                 } else {//手机内部存储
                                 //路径：data/data/包名/files
                                 filesDir = ((BaseActivity) MeFragment.this.getActivity()).getFilesDir();

                                 }
                                 File file = new File(filesDir, "icon.png");
                                 if (file.exists()) {
                                 file.delete();//删除存储中的文件
                                 }
                                 */
                                //3.销毁所有的activity
                                ((BaseActivity) MeFragment.this.getActivity()).removeAll();
                                //4.重新进入首页面
                                ((BaseActivity) MeFragment.this.getActivity()).goToActivity(MainActivity.class, null);
                            }
                        })
                        .setCancelable(false)
                        .show();
                break;
            case R.id.rl_icon:
                String[] items = new String[]{"图库", "相机"};
                builder.setTitle("选择来源")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0://图库
//                                    UIUtils.toast("图库",false);
                                        //启动其他应用的activity:使用隐式意图
                                        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(picture, PICTURE);
                                        break;
                                    case 1://相机
//                                    UIUtils.toast("相机",false);
                                        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        startActivityForResult(camera, CAMERA);
                                        break;
                                }
                            }
                        })
                        .setCancelable(false)
                        .show();
                break;
            case R.id.tv_me_reset:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA && resultCode == RESULT_OK && data != null) {//相机
            //获取intent中的图片对象
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            //对获取到的bitmap进行压缩、圆形处理
            bitmap = BitmapUtils.zoom(bitmap, ivMeIcon.getWidth(), ivMeIcon.getHeight());
            bitmap = BitmapUtils.circleBitmap(bitmap);

            //加载显示
            ivMeIcon.setImageBitmap(bitmap);
            //上传到服务器（省略）

            //保存到本地
            saveImage(bitmap);


        } else if (requestCode == PICTURE && resultCode == RESULT_OK && data != null) {//图库

            //图库
            Uri selectedImage = data.getData();
            //android各个不同的系统版本,对于获取外部存储上的资源，返回的Uri对象都可能各不一样,
            // 所以要保证无论是哪个系统版本都能正确获取到图片资源的话就需要针对各种情况进行一个处理了
            //这里返回的uri情况就有点多了
            //在4.4.2之前返回的uri是:content://media/external/images/media/3951或者file://....
            // 在4.4.2返回的是content://com.android.providers.media.documents/document/image

            String pathResult = getPath(selectedImage);
            //存储--->内存
            Bitmap decodeFile = BitmapFactory.decodeFile(pathResult);
            Bitmap zoomBitmap = BitmapUtils.zoom(decodeFile, ivMeIcon.getWidth(), ivMeIcon.getHeight());
            //bitmap圆形裁剪
            Bitmap circleImage = BitmapUtils.circleBitmap(zoomBitmap);

            //加载显示
            ivMeIcon.setImageBitmap(circleImage);
            //上传到服务器（省略）
            //保存到本地
            saveImage(circleImage);
        }
    }
    //将Bitmap保存到本地的操作

    /**
     * 数据的存储。（5种）
     * Bimap:内存层面的图片对象。
     * 存储--->内存：
     * BitmapFactory.decodeFile(String filePath);
     * BitmapFactory.decodeStream(InputStream is);
     * 内存--->存储：
     * bitmap.compress(Bitmap.CompressFormat.PNG,100,OutputStream os);
     */
    private void saveImage(Bitmap bitmap) {
        File filesDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//判断sd卡是否挂载
            //路径1：storage/sdcard/Android/data/包名/files
            filesDir = this.getActivity().getExternalFilesDir("");
        } else {//手机内部存储
            //路径：data/data/包名/files
            filesDir = this.getActivity().getFilesDir();
        }
        FileOutputStream fos = null;
        try {
            File file = new File(filesDir, "icon.png");
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据系统相册选择的文件获取路径
     *
     * @param uri
     */
    @SuppressLint("NewApi")
    private String getPath(Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        //高于4.4.2的版本
        if (sdkVersion >= 19) {
            LogUtil.e("uri auth: " + uri.getAuthority());
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));
                return getDataColumn(getActivity(), contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(getActivity(), contentUri, selection, selectionArgs);
            } else if (isMedia(uri)) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor actualimagecursor = this.getActivity().managedQuery(uri, proj, null, null, null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();
                return actualimagecursor.getString(actual_image_column_index);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(getActivity(), uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * uri路径查询字段
     * @param context
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isMedia(Uri uri) {
        return "media".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
