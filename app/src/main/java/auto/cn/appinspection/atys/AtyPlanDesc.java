package auto.cn.appinspection.atys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import auto.cn.appinspection.R;
import auto.cn.appinspection.adapters.CommonBaseAdapter;
import auto.cn.appinspection.adapters.ViewHolder;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.beans.ContentDescBean;
import auto.cn.appinspection.commons.Constant;
import auto.cn.appinspection.commons.DbHelper;
import auto.cn.appinspection.utils.UIUtils;
import auto.cn.greendaogenerate.AreaList;
import auto.cn.greendaogenerate.ContentList;
import auto.cn.greendaogenerate.Equiplist;
import auto.cn.greendaogenerate.ItemList;
import auto.cn.greendaogenerate.PartList;
import auto.cn.greendaogenerate.PlanList;
import butterknife.Bind;
import butterknife.OnClick;

public class AtyPlanDesc extends BaseActivity {

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
    //    private List<AreaList> areaLists = new ArrayList<>();
//    private List<Equiplist> equipLists = new ArrayList<>();
//    private List<PartList> partLists = new ArrayList<>();
//    private List<ItemList> itemLists = new ArrayList<>();
    //private List<ContentList> contentLists = new ArrayList<>();
    private List<ContentDescBean> contentDescLists = new ArrayList<>();
    private CommonBaseAdapter<ContentDescBean> mAdapter;
    private DbHelper dbHelper;
    private String planId;
    private ProgressDialog pd;
    private boolean querySuccess;

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
        //设置下拉刷新控件的颜色
        swiperefresh.setColorSchemeColors(Color.BLUE);
        //设置下拉刷新监听
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

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
        mAdapter = new CommonBaseAdapter<ContentDescBean>(this, contentDescLists, R.layout.item_plan_desc) {
            @Override
            public void convert(ViewHolder holder, ContentDescBean contentDescBean) {
                if (!contentDescBean.areaIsMaintenance()) {
                    holder.setBackground(R.id.ll_desc, Color.rgb(255, 255, 255));
                    holder.setText(R.id.tv_desc_content, contentDescBean.getContent())
                            .setText(R.id.tv_desc_standard, contentDescBean.getStandard())

                            .setText(R.id.tv_desc_status, contentDescBean.getContentStatus())
                            .setText(R.id.tv_desc_area, contentDescBean.getAreaName())
                            .setText(R.id.tv_desc_area_status, "正常")
                            .setText(R.id.tv_desc_equip, contentDescBean.getEquipName())
                            .setText(R.id.tv_desc_part, contentDescBean.getPartName())
                            .setText(R.id.tv_desc_item, contentDescBean.getItemName());
                    //设置温度显示
                    if(!contentDescBean.getCheckWay().equals("114")){
                        holder.setVisiable(R.id.ll_desc_temprature,View.GONE);

                    }else{
                        holder.setVisiable(R.id.ll_desc_temprature,View.VISIBLE);
                        holder.setText(R.id.tv_desc_temprature,contentDescBean.getTemperature());
                    }
                    //设置查看异常照片
                    if(contentDescBean.getContentStatus().equals("异常")){
                        holder.setVisiable(R.id.btn_desc_photo,View.VISIBLE);

                        //holder.setImageUrl(R.id.iv_desc_photo,"");
                    }else{
                        holder.setVisiable(R.id.btn_desc_photo,View.GONE);
                    }

                } else {
                    holder.setBackground(R.id.ll_desc, Color.rgb(255, 255, 200));
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
        lvPlanDesc.setAdapter(mAdapter);
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
                PlanList planList = dbHelper.queryPlanById(planId);
                if (planList != null) {
                    List<AreaList> areaLists = planList.getAreas();
                    for (int i = 0; i < areaLists.size(); i++) {
                        List<Equiplist> equiplists = areaLists.get(i).getEquips();
                        for (int j = 0; j < equiplists.size(); j++) {
                            List<PartList> partLists = equiplists.get(j).getParts();
                            List<ItemList> itemLists = equiplists.get(j).getItems();
                            for (int m = 0; m < partLists.size(); m++) {
                                for (int k = 0; k < itemLists.size(); k++) {
                                    List<ContentList> contentLists = itemLists.get(k).getContents();
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

}
