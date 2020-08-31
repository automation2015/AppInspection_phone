package auto.cn.appinspection.atys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import auto.cn.appinspection.R;
import auto.cn.appinspection.adapters.CommonBaseAdapter;
import auto.cn.appinspection.adapters.ViewHolder;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.loader.PlanLoader;
import auto.cn.appinspection.utils.UIUtils;
import auto.cn.greendaogenerate.PlanList;
import butterknife.Bind;
import butterknife.OnClick;

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
    private CommonBaseAdapter<PlanList> mAdapter;
    private LoaderManager loaderManager;
    private static final int LOAD_ID = 1;
    private List<PlanList> mDatas = new ArrayList<>();
    private Loader<List<PlanList>> listLoader;

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
        return new PlanLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<PlanList>> loader, List<PlanList> planList) {
        mDatas.addAll(planList);
        mAdapter.notifyDataSetChanged();
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
        //TODO
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
