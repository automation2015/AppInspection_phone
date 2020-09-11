package auto.cn.appinspection.atys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import auto.cn.appinspection.R;
import auto.cn.appinspection.adapters.CommonBaseAdapter;
import auto.cn.appinspection.adapters.ViewHolder;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.commons.Constant;
import auto.cn.appinspection.loader.PlanLoader;
import auto.cn.appinspection.utils.UIUtils;
import auto.cn.greendaogenerate.AreaList;
import auto.cn.greendaogenerate.ContentList;
import auto.cn.greendaogenerate.Equiplist;
import auto.cn.greendaogenerate.ItemList;
import auto.cn.greendaogenerate.PlanList;
import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AtyPlanHis extends BaseActivity implements LoaderManager.LoaderCallbacks<List<PlanList>>, AdapterView.OnItemClickListener {
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.lv_plan_his)
    ListView lvPlanHis;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @Bind(R.id.fab_his_upload)
    FloatingActionButton fabHisUpload;
    @Bind(R.id.pb_his)
    ProgressBar pbHis;
    private CommonBaseAdapter<PlanList> mAdapter;
    private LoaderManager loaderManager;
    private static final int LOAD_ID = 1;
    private List<PlanList> mDatas = new ArrayList<>();
    private Loader<List<PlanList>> listLoader;
    private int totalContentFinish = 0;//已经完成巡检的content数量
    private int totalItemFinish = 0;//已经完成巡检的item数量
    private int totalEquipFinish = 0;//已经完成巡检的equip数量
    private int totalAreaFinish = 0;//已经完成巡检的area数量
    private int totalPlanFinish = 0;//已经完成巡检的plan数量
    private int i = 0;
    //1.拿到okHttpClient对象；
    private OkHttpClient okHttpClient;
    public static final MediaType USERNAME = MediaType.get("text/plain; charset=utf-8");

    @Override
    protected int getLayoutId() {
        return R.layout.aty_plan_his;
    }

    @Override
    protected void initTitle() {
        tvTitle.setText("巡检历史");
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setVisibility(View.GONE);
    }

    @OnClick(R.id.iv_title_back)
    public void back() {
        removeAll();
        goToActivity(AtyPlanManager.class, null);
    }

    @Override
    public void initData() {
        okHttpClient = new OkHttpClient();
        //设置adapter
        setupListAdatper();
        //初始化Loader
        initLoader();
        //设置下拉刷新控件的颜色
        swiperefresh.setColorSchemeColors(Color.BLUE);
        //设置下拉刷新监听
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reLoader();
                swiperefresh.setRefreshing(false);
            }
        });

    }

    //设置adapter
    private void setupListAdatper() {
        mAdapter = new CommonBaseAdapter<PlanList>(this, mDatas, R.layout.item_history_lv) {

            @Override
            public void convert(ViewHolder holder, PlanList planList) {
                holder.setText(R.id.tv_plan_name, planList.getPLAN_NAME())
                        .setText(R.id.tv_plan_id, planList.getPLAN_ID())
                        .setText(R.id.tv_plan_shift, planList.getShift());
                holder.setProgessBarProgress(R.id.p_progresss, i);

            }
            //TODO 设置计划进度显示
        };
        lvPlanHis.setAdapter(mAdapter);
        lvPlanHis.setOnItemClickListener(this);
    }

    //初始化Loader
    private void initLoader() {
        loaderManager = getSupportLoaderManager();
        listLoader = loaderManager.initLoader(LOAD_ID, null, this);
    }


    @Override
    public Loader<List<PlanList>> onCreateLoader(int i, Bundle bundle) {
        pbHis.setVisibility(View.VISIBLE);
        return new PlanLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<PlanList>> loader, List<PlanList> planList) {
        mDatas.addAll(planList);
        mAdapter.notifyDataSetChanged();
        pbHis.setVisibility(View.GONE);
        if (mDatas != null && mDatas.size() > 0) {
            fabHisUpload.setVisibility(View.VISIBLE);
        } else {
            fabHisUpload.setVisibility(View.GONE);
        }
     //获得计划完成情况
        getPlanFinish();
        i = (int) (((float) totalPlanFinish / (float) mDatas.size()) * 100);
    }
    //获得计划完成情况
    private void getPlanFinish() {
        for (int i = 0; i < mDatas.size(); i++) {
            List<AreaList> areaLists = mDatas.get(i).getAreas();
            for (int j = 0; j < areaLists.size(); j++) {
                List<Equiplist> equiplists = areaLists.get(j).getEquips();
                for (int k = 0; k < equiplists.size(); k++) {
                    List<ItemList> itemLists = equiplists.get(k).getItems();
                    for (int l = 0; l < itemLists.size(); l++) {
                        List<ContentList> contentLists = itemLists.get(l).getContents();
                        for (int m = 0; m < contentLists.size(); m++) {
                            contentLists.get(m).setCONTENT_FINISH(true);
                            if (contentLists.get(m).getCONTENT_FINISH()) {
                                totalContentFinish++;
                            }
                        }
                        if (totalContentFinish == contentLists.size()) {
                            itemLists.get(l).setITEM_FINISH(true);
                            totalItemFinish++;
                        }

                    }
                    if (totalItemFinish == itemLists.size()) {
                        equiplists.get(k).setEQUIP_FINISH(true);
                        totalEquipFinish++;
                    }
                }
                if (totalEquipFinish == equiplists.size()) {
                    areaLists.get(j).setAREA_FINISH(true);
                    totalAreaFinish++;
                }
            }
            if (totalAreaFinish == areaLists.size()) {
                mDatas.get(i).setPLAN_FINISH(true);
                totalPlanFinish++;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<List<PlanList>> loader) {

    }
    //下拉刷新获取最新数据
    private void reLoader() {
        int id = listLoader.getId();
        if (id == LOAD_ID) {
            if (listLoader.isStarted()) {
                listLoader.forceLoad();
                UIUtils.toast("刷新成功,数据已经是最新数据了。", false);
            }
        } else {
            UIUtils.toast("刷新失败,请重新刷新。", false);
        }
    }

    //上传计划数据
    @OnClick(R.id.fab_his_upload)
    public void uploadPlanData() {
        if (mDatas != null && mDatas.size() > 0) {
            pbHis.setVisibility(View.VISIBLE);
            String recordJson = new Gson().toJson(mDatas);
            //2.构造Request
            Request.Builder builder = new Request.Builder();
            //3.将Request封装为Call
            //此处一定要注意添加MediaType.parse("application/json")，而不是MediaType.parse("application/json charset=utf-8"),否则报415错误
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), recordJson);
            Request request = builder.post(requestBody)
                    .url(Constant.URL_UPLOAD_RECORD)
                    .build();
            //4.执行Call
            try {
                excuteCall(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            UIUtils.toast("没有需要上传的数据！", false);
        }
    }
    //4.执行Call
    private void excuteCall(Request request) throws IOException {
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pbHis.setVisibility(View.GONE);
                        UIUtils.toast("连接服务器失败，请确认后重试！",false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string().substring(1,2);
                if(res.equals("1")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pbHis.setVisibility(View.GONE);
                            UIUtils.toast("数据已经成功上传至服务器！",false);
                        }
                    });

                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UIUtils.toast("数据上传过程异常，请重新上传！",false);
                            //tvResult.setText(res);
                        }
                    });

                }

            }
        });
    }
    //点击列表项弹出详情列表
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //1.点击跳转
        //2.传递数据
        String planName = mDatas.get(position).getPLAN_NAME();
        String planId = mDatas.get(position).getPLAN_ID();
        String shift = mDatas.get(position).getShift();
        AtyPlanDesc.toActivity(this, planId, planName, shift);
        //3.销毁页面
        //removeAll();
    }
}
