package auto.cn.appinspection.atys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auto.cn.appinspection.R;
import auto.cn.appinspection.adapters.CommonBaseAdapter;
import auto.cn.appinspection.adapters.DropDownListAdapter;
import auto.cn.appinspection.adapters.ViewHolder;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.beans.PlanBean;
import auto.cn.appinspection.commons.AppNetConfig;
import auto.cn.appinspection.commons.DbHelper;
import auto.cn.appinspection.ui.DropDownMemu;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static auto.cn.appinspection.ui.DropDownMemu.*;

public class AtyPlanCheck extends BaseActivity implements AdapterView.OnItemClickListener {
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.dropDownMenu)
    DropDownMemu dropDownMenu;
    private DbHelper dbHelper;

   // private String headers[] = {"计划名称", "区域", "设备", "部位", "项目", "内容"};
    private String headers[] = {"计划名称"};
    private List<View> popViews = new ArrayList<>();
    private List<PlanBean> mDatas = new ArrayList<>();
    private DropDownListAdapter planAdapter;
    private int[] imagIds = {R.mipmap.icon_shangang};
    @Override
    protected int getLayoutId() {
        return R.layout.aty_plan_check;
    }

    @Override
    protected void initTitle() {
        tvTitle.setText("设备点巡检");
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String planId = intent.getExtras().getString(AppNetConfig.KEY_PLANID);
        Log.e("TAG", "initData() called" + planId);

        tv.setText(planId);
        dbHelper=DbHelper.getInstance(this,AppNetConfig.DB_NAME);
        //初始化DropDownMenu
        PlanBean planBean = new PlanBean();
        planBean.setPLAN_ID("jh0001");
        mDatas.add(planBean);
        initViews();

    }

    //初始化DropDownMenu
    private void initViews() {
        ListView lvPlan = new ListView(this);
        planAdapter = new DropDownListAdapter(this,mDatas);
        lvPlan.setDividerHeight(0);
        lvPlan.setId(R.id.list1);
        lvPlan.setAdapter(planAdapter);

        lvPlan.setOnItemClickListener(this);
        popViews.add(lvPlan);
        ImageView contentView = new ImageView(this);
        contentView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        contentView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popViews, contentView);
    }
    @OnClick(R.id.iv_title_back)
    public void back() {
        removeAll();
        goToActivity(AtyPlan.class, null);
    }
    //启动Activity并传递参数
    public static void toActivity(Context context, String planId) {
        Intent intent = new Intent(context, AtyPlanCheck.class);
        intent.putExtra(AppNetConfig.KEY_PLANID, planId);
        context.startActivity(intent);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.list1://city
                planAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position==-1?headers[0]:mDatas.get(position).getPLAN_ID());
                dropDownMenu.setImageResource(imagIds[0]);
                dropDownMenu.closeMenu();
                break;
    }
}}
