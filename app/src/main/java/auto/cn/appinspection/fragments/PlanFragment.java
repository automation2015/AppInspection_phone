package auto.cn.appinspection.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import auto.cn.appinspection.R;
import auto.cn.appinspection.adapters.CommonBaseAdapter;
import auto.cn.appinspection.adapters.ViewHolder;
import auto.cn.appinspection.atys.AtyPlanCheck;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.bases.BaseFragment;
import auto.cn.appinspection.beans.PlanBean;
import auto.cn.appinspection.beans.UserBean;
import auto.cn.appinspection.commons.AppNetConfig;
import auto.cn.appinspection.utils.LogUtil;
import auto.cn.appinspection.utils.UIUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlanFragment extends BaseFragment {
    @Bind(R.id.lv_fragment_plan)
    ListView lvFragmentPlan;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @Bind(R.id.fab_choosemsg)
    FloatingActionButton fabChoosemsg;
    private List<PlanBean> mDatas;
    private CommonBaseAdapter<PlanBean> planAdapter;
    private String urlTest = "http://api.map.baidu.com/telematics/v3/weather?location=%E6%B5%8E%E5%8D%97&output=json&ak=FkPhtMBK0HTIQNh7gG4cNUttSTyr0nzo";
    private String url = AppNetConfig.GETALLPLAN + "?username=巡检丙班&rolename=电气岗位点检员";
    private String url1 = AppNetConfig.GETALLPLAN + "?username=admin&rolename=系统管理员";


    @Override
    protected void initTitle() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_plan;
    }

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return url;
    }

    @Override
    protected void initData(String content) {
        //解析获取的数据
        mDatas = parseDatas(content);
//        Log.e("TAG", "initData() called with: content = [" + content + "]");
//        Log.e("TAG", "initData() called with: content = [" + mDatas.size() + "]");
        //mDatas.addAll(datas);
        if (mDatas == null || mDatas.size() == 0) {
            UserBean userBean = ((BaseActivity) getActivity()).readUser();
            ;
            UIUtils.toast(userBean.getUsername() + ",您好，本班没有您需要点检的工作计划。", false);
        }
        planAdapter = new CommonBaseAdapter<PlanBean>(getActivity(), mDatas, R.layout.item_fragment_plan_lv) {
            @Override
            public void convert(ViewHolder holder, PlanBean planBean) {
                switch (planBean.getPLAN_CYCLE_TYPE()) {
                    case "1":
                        holder.setImageResource(R.id.iv_plan_type, R.mipmap.icon_plan_type3);
                        break;
                    case "2":
                        holder.setImageResource(R.id.iv_plan_type, R.mipmap.icon_plan_type2);
                        break;
                    case "3":
                        holder.setImageResource(R.id.iv_plan_type, R.mipmap.icon_plan_type3);
                        break;
                    default:
                        holder.setImageResource(R.id.iv_plan_type, R.mipmap.icon_plan_type1);
                        break;
                }
                holder.setText(R.id.tv_plan_id, planBean.getPLAN_ID())
                        .setText(R.id.tv_plan_name, planBean.getPLAN_NAME());
            }
        };
        lvFragmentPlan.setAdapter(planAdapter);

        swiperefresh.setColorSchemeColors(Color.BLUE);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reConnect();
                swiperefresh.setRefreshing(false);
            }
        });
        lvFragmentPlan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlanBean planBean = mDatas.get(position);
                String planId = planBean.getPLAN_ID();
                AtyPlanCheck.toActivity(getActivity(),planId);
                ((BaseActivity) getActivity()).removeCurrentActivity();
            }
        });
    }
    //FloationActionButton点击事件
    @OnClick(R.id.fab_choosemsg)
    public void fabClick(){
        PlanBean planBean = mDatas.get(0);
        String planId = planBean.getPLAN_ID();
       AtyPlanCheck.toActivity(getActivity(),planId);
        ((BaseActivity) getActivity()).removeCurrentActivity();
    }
    //重新联网获取最新计划数据
    private void reConnect() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                LogUtil.e("onSuccess() called with: content = [" + content + "]");
                if (!TextUtils.isEmpty(content)) {
                    mDatas.clear();
                    mDatas.addAll(parseDatas(content));
                    planAdapter.notifyDataSetChanged();
                    UIUtils.toast("刷新成功,数据已经是最新数据了。", false);
                    swiperefresh.setRefreshing(false);
                } else {
                    UserBean userBean = ((BaseActivity) getActivity()).readUser();
                    UIUtils.toast(userBean.getUsername() + ",您好，本班没有您需要点检的工作计划。", false);
                }
            }
            @Override
            public void onFailure(Throwable error, String content) {
                UIUtils.toast("刷新失败,请检查你的网络连接！", false);
            }
        });
    }
    //解析获取的数据
    private List<PlanBean> parseDatas(String content) {

        if (!TextUtils.isEmpty(content.replace("[]", ""))) {
            List<PlanBean> data = new ArrayList<>();
            Gson gson = new Gson();
            // PlanBean1 planBean1 = gson.fromJson(content, PlanBean1.class);
            Type listType = new TypeToken<List<PlanBean>>() {
            }.getType();
            data = gson.fromJson(content, listType);
            return data;
        }
        return new ArrayList<>();
    }
}
