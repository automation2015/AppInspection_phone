package auto.cn.appinspection.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auto.cn.appinspection.R;
import auto.cn.appinspection.adapters.CommonBaseAdapter;
import auto.cn.appinspection.adapters.DropDownAdapter;
import auto.cn.appinspection.adapters.ViewHolder;
import auto.cn.appinspection.atys.AtyPlanCheck;
import auto.cn.appinspection.bases.BaseFragment;
import auto.cn.appinspection.commons.Constant;
import auto.cn.appinspection.commons.DbHelper;
import auto.cn.appinspection.ui.DropDownMemu;
import auto.cn.appinspection.utils.UIUtils;
import auto.cn.greendaogenerate.AreaList;
import auto.cn.greendaogenerate.PlanList;
import butterknife.Bind;

public class AreaFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.dropDownMenu)
    DropDownMemu dropDownMenu;
    //操作数据库的类
    private DbHelper dbHelper;
    //DropDownTab 内容数据
    private String headers[] = {"计划名称"};
    private List<View> popViews = new ArrayList<>();
    private List<PlanList> planLists = new ArrayList<>();
    private List<AreaList> areaLists = new ArrayList<>();
    private DropDownAdapter<PlanList> planAdapter;
    private CommonBaseAdapter<AreaList> adapter;

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_area;
    }

    @Override
    protected void initData(String content) {
        //获取数据库操作类
        dbHelper = DbHelper.getInstance(getActivity(), Constant.DB_NAME);
        //打开数据库
        dbHelper.openDb();
        //设置控制台输出sql语句，filter tag：”greenDAO”
        dbHelper.setDebug();
        //根据PlanId获取数据库数据
        planLists = dbHelper.getAllPlan();
        //初始化DropDownMenu
        initViews();
    }
    //初始化DropDownMenu
    private void initViews() {
        //计划列表
        ListView lvPlan = new ListView(getActivity());
        planAdapter = new DropDownAdapter<PlanList>(getActivity(), planLists) {
            @Override
            public void fillValue(ViewHolder holder, PlanList planList) {
                holder.getTvId().setText(planList.getPLAN_ID());
                holder.getTvName().setText(planList.getPLAN_NAME());
            }
        };
        lvPlan.setDividerHeight(0);
        lvPlan.setId(R.id.list1);
        lvPlan.setAdapter(planAdapter);
        //计划列表监听
        lvPlan.setOnItemClickListener(this);
        popViews.add(lvPlan);

        //添加contentView
        View contentView = View.inflate(getActivity(), R.layout.drop_view_contentview, null);
        //lvArea= contentView.findViewById(R.id.lv_drop_view_content);
        if (planLists != null) {

            adapter = new CommonBaseAdapter<AreaList>(getActivity(), areaLists, R.layout.item_drop_down_lv) {
                @Override
                public void convert(ViewHolder holder, final AreaList areaList) {
                    holder.setText(R.id.tv_drop_down_area, areaList.getPL_AREA_NAME());
                    holder.tvOnClick(R.id.tb_drop_down_area, new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                //TODO 为数据库添加一个标识字段
                                areaList.setAREA_NORNAL("检修");
                                long insertCount = dbHelper.insertOrReplace(areaList);
                                UIUtils.toast("检修", false);
                            } else {
                                areaList.setAREA_NORNAL("正常");
                                UIUtils.toast("正常", false);
                            }
                        }
                    });
                }
            };
        }
        //为dropDownMenu设置子view
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popViews, contentView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.list1://city
                planAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == -1 ? headers[0] : planLists.get(position).getPLAN_NAME());
                areaLists.clear();
                String plan_id = planLists.get(position).getPLAN_ID();
                List<AreaList> areaByPlanId = dbHelper.getAreaByPlanId(plan_id);
                areaLists.addAll(areaByPlanId);
                adapter.notifyDataSetChanged();

                dropDownMenu.setAreaContentView(adapter, (areaLists == null) ? View.VISIBLE : View.GONE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //FloationActionButton点击事件
                        AtyPlanCheck.toActivity(getActivity(), null);
                    }
                });
                dropDownMenu.closeMenu();
                break;

        }
    }

}
