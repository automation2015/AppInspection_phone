package auto.cn.appinspection.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import auto.cn.greendaogenerate.AreaList;
import auto.cn.greendaogenerate.AreaListDao;
import auto.cn.greendaogenerate.ContentListDao;
import auto.cn.greendaogenerate.DaoMaster;
import auto.cn.greendaogenerate.DaoSession;
import auto.cn.greendaogenerate.Equiplist;
import auto.cn.greendaogenerate.EquiplistDao;
import auto.cn.greendaogenerate.ItemListDao;
import auto.cn.greendaogenerate.PartList;
import auto.cn.greendaogenerate.PartListDao;
import auto.cn.greendaogenerate.PlanList;
import auto.cn.greendaogenerate.PlanListDao;
import butterknife.Bind;
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
    private String url= AppNetConfig.GETALLPLAN + "?username=巡检丙班&rolename=电气岗位点检员"; ;
    private String url1 = AppNetConfig.GETALLPLAN + "?username=admin&rolename=系统管理员";
    private DaoMaster master;
    private DaoSession session;
    private SQLiteDatabase db;
    private PlanListDao planListDao;
    private AreaListDao areaListDao;
    private EquiplistDao equiplistDao;
    private PartListDao partListDao;
    private ItemListDao itemListDao;
    private ContentListDao contentListDao;
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
            UIUtils.toast(userBean.getUsername() + ",您好，本班没有您需要点检的工作计划。", false);
        }
        planAdapter = new CommonBaseAdapter<PlanBean>(getActivity(), mDatas, R.layout.item_fragment_plan_lv) {
            @Override
            public void convert(ViewHolder holder, PlanBean planBean) {
                //设置显示不同的计划图片
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
        //为list设置适配器
        lvFragmentPlan.setAdapter(planAdapter);
        //设置下拉刷新控件的颜色
        swiperefresh.setColorSchemeColors(Color.BLUE);
        //设置下拉刷新监听
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reConnect();
                swiperefresh.setRefreshing(false);
            }
        });
        //设置list item点击事件
        lvFragmentPlan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlanBean planBean = mDatas.get(position);
                String planId = planBean.getPLAN_ID();
                AtyPlanCheck.toActivity(getActivity(),planId);
                //((BaseActivity) getActivity()).removeCurrentActivity();
            }
        });
        //打开数据库
        openDb();
        //将联网获取的数据存入数据库
        //saveDb(mDatas);
    }
    private void openDb() {
        db = new DaoMaster.DevOpenHelper(getActivity(), "plan.db", null)
                .getReadableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        planListDao = session.getPlanListDao();
        areaListDao=session.getAreaListDao();
        equiplistDao=session.getEquiplistDao();
       partListDao=session.getPartListDao();
       itemListDao=session.getItemListDao();
       contentListDao=session.getContentListDao();
    }
    //将联网获取的数据存入数据库
    private void saveDb(List<PlanBean> mDatas) {
        if (mDatas==null) {
            return;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < mDatas.size(); i++) {

            try {
                PlanBean planData= mDatas.get(i);
                List<PlanBean.AreaListBean> areaDatas = mDatas.get(i).getAreaList();

                PlanList planList=new PlanList();
                planList.setId(Long.valueOf(planData.getId()));
                planList.setPLAN_PART_ID(planData.getPLAN_ID());
                planList.setPLAN_NAME(planData.getPLAN_NAME());
                planList.setPLAN_ORG_NAME(planData.getPLAN_ORG_NAME());

                planList.setPLAN_PART_NAME(planData.getPLAN_PART_NAME());
                planList.setPLAN_PART_ID(planData.getPLAN_PART_ID());
                planList.setPLAN_NUM(Integer.valueOf(planData.getPLAN_NUM()));
                planList.setPLAN_CYCLE_TYPE(Integer.valueOf(planData.getPLAN_CYCLE_TYPE()));

                planList.setPLAN_LAST_DATE(sdf.parse(planData.getPLAN_LAST_DATE()));
                planList.setPLAN_LAST_USER_NAME(planData.getPLAN_LAST_USER_NAME());
                planList.setPLAN_CREATE_DATE(sdf.parse(planData.getPLAN_CREATE_DATE()));
                planList.setValid_Flag(planData.getValid_Flag());

                planList.setShift(Integer.valueOf(planData.getShift()));
                planList.setCODE_NAME(planData.getCODE_NAME());

                planListDao.insert(planList);

                for (int j = 0; j <areaDatas.size(); j++) {
                    PlanBean.AreaListBean areaData = areaDatas.get(j);
                    List<PlanBean.AreaListBean.EquipListBean> equipDatas = areaData.getEquipList();
                    AreaList areaList=new AreaList();
                    areaList.setPL_AREA_ID(areaData.getPL_AREA_ID());
                    areaList.setPL_AREA_NAME(areaData.getPL_AREA_NAME());
                    areaList.setPL_AREA_LABEL(areaData.getPL_AREA_LABEL());
                    areaList.setPL_AREA_CREATE_ID(areaData.getPL_AREA_CREATE_ID());

                    areaList.setPL_AREA_CREATE_DATE(sdf.parse(areaData.getPL_AREA_CREATE_DATE()));
                    areaList.setValid_Flag(areaData.getValid_Flag());
                    areaList.setPlanId(areaData.getPlanId());

                    areaList.setPlanList(planList);
                    areaListDao.insert(areaList);
                    for (int k = 0; k <equipDatas.size() ; k++) {
                        PlanBean.AreaListBean.EquipListBean equipData = equipDatas.get(k);
                        List<PlanBean.AreaListBean.EquipListBean.PartListBean> partDatas = equipData.getPartList();
                        Equiplist equipList = new Equiplist();
                        equipList.setEL_NAME(equipData.getEL_NAME());
                        equipList.setEL_Depart_Name(equipData.getEL_Depart_Name());
                        equipList.setEL_ID(equipData.getEL_ID());
                        equipList.setEL_Type_Name(equipData.getEL_Type_Name());

                        equipList.setEL_EIS_ID(equipData.getEL_EIS_ID());
                        equipList.setEL_EIS_NAME(equipData.getEL_EIS_NAME());
                        equipList.setEL_VALID_FLAG(equipData.getEL_VALID_FLAG());
                        equipList.setPL_AREA_ID(equipData.getPL_AREA_ID());

                        equipList.setAreaList(areaList);
                        equiplistDao.insert(equipList);
                        for (int l = 0; l < partDatas.size(); l++) {
                            PlanBean.AreaListBean.EquipListBean.PartListBean partData = partDatas.get(l);
                            PartList partList = new PartList();
                           // partList.setId();

                        }

                    }
                }


            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


    }

    //FloationActionButton点击事件
    @OnClick(R.id.fab_choosemsg)
    public void fabClick(){
        PlanBean planBean = mDatas.get(0);
        String planId = planBean.getPLAN_ID();
       AtyPlanCheck.toActivity(getActivity(),planId);
        //((BaseActivity) getActivity()).removeCurrentActivity();
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
        Log.e("TAG", "onSuccess() called with: content = [" + content + "]");
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
