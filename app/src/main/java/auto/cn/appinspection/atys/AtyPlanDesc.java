package auto.cn.appinspection.atys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import auto.cn.appinspection.R;
import auto.cn.appinspection.adapters.CommonBaseAdapter;
import auto.cn.appinspection.adapters.ViewHolder;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.beans.ContentDescBean;
import auto.cn.appinspection.commons.Constant;
import auto.cn.appinspection.commons.DbHelper;
import auto.cn.appinspection.ui.CustomDialog;
import auto.cn.appinspection.utils.UIUtils;
import auto.cn.greendaogenerate.AreaList;
import auto.cn.greendaogenerate.ContentList;
import auto.cn.greendaogenerate.Equiplist;
import auto.cn.greendaogenerate.ItemList;
import auto.cn.greendaogenerate.PartList;
import auto.cn.greendaogenerate.PlanList;
import butterknife.Bind;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoViewAttacher;

public class AtyPlanDesc extends BaseActivity implements View.OnClickListener {

    private static final String KEY_PLAN_NAME = "plan_name";
    private static final String KEY_PLAN_ID = "plan_id";
    private static final String KEY_PLAN_SHIFT = "shift";

    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.lv_plan_desc)
    ListView lvPlanDesc;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @Bind(R.id.tv_desc_plan)
    TextView tvDescPlan;
    @Bind(R.id.tv_desc_shift)
    TextView tvDescShift;

    private List<ContentDescBean> contentDescLists = new ArrayList<>();
    private CommonBaseAdapter<ContentDescBean> mAdapter;
    private DbHelper dbHelper;
    private String planId;
    private ProgressDialog pd;
    private boolean querySuccess;
    private PlanList planList;
    private List<AreaList> areaLists;
    private List<Equiplist> equiplists;
    private List<PartList> partLists;
    private List<ItemList> itemLists;
    private List<ContentList> contentLists;
    private CustomDialog dialog;
    private PhotoViewAttacher mAttecher;
    private ImageView ivPhoto;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_plan_desc;
    }

    @Override
    protected void initTitle() {
        tvTitle.setText("巡检记录详情");
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String planName = intent.getStringExtra(KEY_PLAN_NAME);
        planId = intent.getStringExtra(KEY_PLAN_ID);
        String shift = intent.getStringExtra(KEY_PLAN_SHIFT);
        pd = new ProgressDialog(this);
        pd.setTitle("提示");
        pd.setMessage("数据查询中。。。");

        tvDescPlan.setText(planName);
        tvDescShift.setText(shift);
        //初始化dialog
        dialog=new CustomDialog(this,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT
                ,R.layout.item_photo_dialog,R.style.Theme_dialog,Gravity.CENTER,R.style.pop_anim_style);
        ivPhoto = dialog.findViewById(R.id.iv_photo_dialog);
        //设置下拉刷新控件的颜色
        swiperefresh.setColorSchemeColors(Color.BLUE);
        //设置下拉刷新监听
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //再次查询数据库数据
                queryRecords();
                if (querySuccess) {
                    UIUtils.toast("已经是最新数据了！", false);
                } else {
                    UIUtils.toast("加载数据失败，请重新加载！", false);
                }
                swiperefresh.setRefreshing(false);
            }
        });
        //查询数据库数据
        queryRecords();
        //初始化适配器
        setAdapter();
        //设置适配器
        lvPlanDesc.setAdapter(mAdapter);
    }

    //初始化适配器
    private void setAdapter() {
        mAdapter = new CommonBaseAdapter<ContentDescBean>(this, contentDescLists, R.layout.item_plan_desc) {
            @Override
            public void convert(final ViewHolder holder, final ContentDescBean contentDescBean) {
                if (!contentDescBean.areaIsMaintenance()) {
                    //巡检区域正常使用
                    holder.setBackground(R.id.rl_desc, Color.rgb(255, 255, 255));
                    String contentStatus = contentDescBean.getContentStatus();
                    holder.setText(R.id.tv_desc_content, contentDescBean.getContent())
                            .setText(R.id.tv_desc_standard, contentDescBean.getStandard())
                            .setText(R.id.tv_desc_status, contentStatus)
                            .setText(R.id.tv_desc_area, contentDescBean.getAreaName())
                            .setText(R.id.tv_desc_area_status, "正常")
                            .setText(R.id.tv_desc_equip, contentDescBean.getEquipName())
                            .setText(R.id.tv_desc_part, contentDescBean.getPartName())
                            .setText(R.id.tv_desc_item, contentDescBean.getItemName());
                    //设置温度显示
                    if (!contentDescBean.getCheckWay().equals("114")) {
                        //定性检查
                        holder.setVisiable(R.id.ll_desc_temprature, View.GONE);

                    } else {
                        //定量检查
                        holder.setVisiable(R.id.ll_desc_temprature, View.VISIBLE);
                        holder.setText(R.id.tv_desc_temprature, contentDescBean.getTemperature());
                    }
                    //设置查看异常照片 TODO 有异常，需测试
                    if (!TextUtils.isEmpty(contentStatus) && contentStatus.equals("异常")) {
                        //异常情况，查看照片
                        holder.setVisiable(R.id.btn_desc_photo, View.VISIBLE);


                        //holder.setImageUrl(R.id.iv_desc_photo,"");
                    } else {
                        //正常情况，没有照片
                        holder.setVisiable(R.id.btn_desc_photo, View.GONE);
                    }
                    holder.btnOnClick(R.id.btn_desc_photo, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String photoPath = contentDescBean.getPhotoPath();

                            if (TextUtils.isEmpty(photoPath)) {
                                UIUtils.toast("没有可供显示的异常照片！", false);
                            } else {
                                File file = new File(photoPath);
                                if (file.exists()) {
                                    //加载图片
                                    Picasso.with(AtyPlanDesc.this).load(file).into(ivPhoto);
                                    //缩放
                                    mAttecher=new PhotoViewAttacher(ivPhoto);
                                    //刷新
                                    mAttecher.update();
                                    dialog.show();
                                }
                            }
                        }
                    });

                } else {
                    //巡检区域检修
                    holder.setBackground(R.id.rl_desc, Color.rgb(255, 255, 200));
                    holder.setText(R.id.tv_desc_content, "")
                            .setText(R.id.tv_desc_standard, "")
                            .setText(R.id.tv_desc_temprature, "")
                            .setText(R.id.tv_desc_status, "")
                            .setText(R.id.tv_desc_area, contentDescBean.getAreaName())
                            .setText(R.id.tv_desc_area_status, "检修")
                            .setText(R.id.tv_desc_equip, "")
                            .setText(R.id.tv_desc_part, "")
                            .setText(R.id.tv_desc_item, "");
                }
            }
        };
    }

    //查询数据库数据
    private void queryRecords() {

        new AsyncTask<Void, Void, List<ContentDescBean>>() {
            @Override
            protected List<ContentDescBean> doInBackground(Void... voids) {
                List<ContentDescBean> datas = new ArrayList<>();
                dbHelper = DbHelper.getInstance(AtyPlanDesc.this, Constant.DB_NAME);
                dbHelper.openDb();
                dbHelper.setDebug();
                //根据planId查询数据
                planList = dbHelper.queryPlanById(planId);
                if (planList != null) {
                    areaLists = planList.getAreas();
                    for (int i = 0; i < areaLists.size(); i++) {
                        equiplists = areaLists.get(i).getEquips();
                        for (int j = 0; j < equiplists.size(); j++) {
                            partLists = equiplists.get(j).getParts();
                            itemLists = equiplists.get(j).getItems();
                            for (int m = 0; m < partLists.size(); m++) {
                                for (int k = 0; k < itemLists.size(); k++) {
                                    contentLists = itemLists.get(k).getContents();
                                    for (int l = 0; l < contentLists.size(); l++) {
                                        ContentList contentList = contentLists.get(l);
                                        ContentDescBean contentDescBean = new ContentDescBean();
                                        contentDescBean.setContent(contentList.getCONTENT_NAME());
                                        contentDescBean.setStandard(contentList.getCONTENT_STANDARD());
                                        contentDescBean.setTemperature(contentList.getTEMP_VALUE());
                                        contentDescBean.setPhoto(contentList.getCONTENT_IS_PHOTO() == 1 ? true : false);
                                        contentDescBean.setPhotoPath(contentList.getPHOTO_PATH());
                                        contentDescBean.setAreaName(areaLists.get(i).getPL_AREA_NAME());
                                        contentDescBean.setEquipName(equiplists.get(j).getEL_NAME());
                                        contentDescBean.setPartName(partLists.get(m).getPART_NAME());
                                        contentDescBean.setItemName(itemLists.get(k).getITEM_NAME());
                                        contentDescBean.setCheckWay(contentList.getCONTENT_WAY());
                                        contentDescBean.setContentStatus(contentList.getCONTENT_STATUS());
                                        contentDescBean.setPlanId(planId);
                                        contentDescBean.setAreaId(String.valueOf(areaLists.get(i).getPL_AREA_ID()));
                                        contentDescBean.setEquipId(equiplists.get(j).getEL_ID());
                                        contentDescBean.setPartId(partLists.get(m).getPART_ID());
                                        contentDescBean.setItemId(itemLists.get(k).getITEM_ID());
                                        if (areaLists.get(i).getAREA_NORNAL() != null && areaLists.get(i).getAREA_NORNAL().equals("检修")) {
                                            contentDescBean.setAreaIsMaintenance(true);
                                        } else {
                                            contentDescBean.setAreaIsMaintenance(false);
                                        }
                                        datas.add(contentDescBean);
                                    }

                                }
                            }
                        }
                    }
                    querySuccess = true;
                    return datas;
                } else {
                    querySuccess = false;
                    return null;
                }
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pd.show();
            }

            @Override
            protected void onPostExecute(List<ContentDescBean> datas) {
                super.onPostExecute(datas);
                pd.dismiss();
                contentDescLists.clear();
                contentDescLists.addAll(datas);
                mAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

    //后退按钮点击事件
    @OnClick(R.id.iv_title_back)
    public void back() {
        //removeAll();
        goToActivity(AtyPlanHis.class, null);
    }

    //启动Activity并传递参数
    public static void toActivity(Context context, String PlanId, String PlanName, String shift) {
        Intent intent = new Intent(context, AtyPlanDesc.class);
        intent.putExtra(KEY_PLAN_NAME, PlanName);
        intent.putExtra(KEY_PLAN_ID, PlanId);
        intent.putExtra(KEY_PLAN_SHIFT, shift);
        context.startActivity(intent);
    }

    //查看照片
    @Override
    public void onClick(View v) {

    }

    private String readImage() {
        File fileDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //判断sd卡是否绑定
            //路径1：storage/sdcard/Android/data/包名/files
            //fileDir = this.getActivity().getFilesDir();
            fileDir = this.getExternalFilesDir("");
        } else {
            //手机内部存储
            //路径：data/data/包名/files
            fileDir = this.getFilesDir();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
//        String photoName = equipLists.get(equipPosSelected).getEL_ID() + itemLists.get(itemPosSelected).getITEM_ID()
//                + sdf.format(new Date()) + ".png";
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
    }

