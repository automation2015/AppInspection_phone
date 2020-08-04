package auto.cn.appinspection.atys;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auto.cn.appinspection.R;
import auto.cn.appinspection.adapters.CommonBaseAdapter;
import auto.cn.appinspection.adapters.DropDownAdapter;
import auto.cn.appinspection.adapters.ViewHolder;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.commons.AppNetConfig;
import auto.cn.appinspection.commons.DbHelper;
import auto.cn.appinspection.ui.DropDownMemu;
import auto.cn.appinspection.utils.UIUtils;
import auto.cn.greendaogenerate.AreaList;
import auto.cn.greendaogenerate.ContentList;
import auto.cn.greendaogenerate.Equiplist;
import auto.cn.greendaogenerate.ItemList;
import auto.cn.greendaogenerate.PartList;
import auto.cn.greendaogenerate.PlanList;
import butterknife.Bind;
import butterknife.OnClick;

public class AtyPlanCheck extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.dropDownMenu)
    DropDownMemu dropDownMenu;
    //操作数据库的类
    private DbHelper dbHelper;

    //private String headers[] = {"计划名称", "区域", "设备", "部位", "项目", "内容"};
    private String headers[] = {"设备", "部位", "项目", "内容"};
    private List<View> popViews = new ArrayList<>();
    List<AreaList> areaLists = new ArrayList<>();
    List<Equiplist> equipLists = new ArrayList<>();
    List<PartList> partLists = new ArrayList<>();
    List<ItemList> itemLists = new ArrayList<>();
    List<ContentList> contentLists = new ArrayList<>();
    private DropDownAdapter<PlanList> planAdapter;
    //private DropDownAreaAdapter areaAdapter;
    private DropDownAdapter<Equiplist> equipAdapter;
    private DropDownAdapter<PartList> partAdapter;
    private DropDownAdapter<ItemList> itemAdapter;
    private DropDownAdapter<ContentList> contentAdapter;
    private int[] imagIds = {R.mipmap.icon_shangang, R.mipmap.icon_shangang, R.mipmap.icon_other_manage, R.mipmap.icon_shangang, R.mipmap.icon_other_manage};
    private CommonBaseAdapter<AreaList> adapter;
    ListView lvArea;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_plan_check;
    }

    @Override
    protected void initTitle() {
        tvTitle.setText("设备点巡检");
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setBackgroundResource(R.mipmap.icon_camera);
        ivTitleSetting.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
//        Intent intent = getIntent();
//        String planId = intent.getExtras().getString(AppNetConfig.KEY_PLANID);
//        Log.e("TAG", "initData() called" + planId);
//        tv.setText(planId);
        //获取数据库操作类
        dbHelper = DbHelper.getInstance(this, AppNetConfig.DB_NAME);
        //打开数据库
        dbHelper.openDb();
        //设置控制台输出sql语句，filter tag：”greenDAO”
        dbHelper.setDebug();
        //获取nfc数据 TODO

        //初始化DropDownMenu
        initViews();
    }
    //初始化DropDownMenu
    private void initViews() {
        //设备列表
        ListView lvEquip = new ListView(this);
        lvEquip.setDividerHeight(0);
        lvEquip.setId(R.id.list2);

        equipAdapter = new DropDownAdapter<Equiplist>(this, equipLists) {
            @Override
            public void fillValue(ViewHolder holder, Equiplist equiplist) {
                holder.getTvId().setVisibility(View.GONE);
                holder.getTvName().setText(equiplist.getEL_NAME());
            }
        };

        lvEquip.setAdapter(equipAdapter);
        //设备监听
        lvEquip.setOnItemClickListener(this);
        //部位列表
        ListView lvPart = new ListView(this);
        lvPart.setDividerHeight(0);
        lvPart.setId(R.id.list3);
        partAdapter = new DropDownAdapter<PartList>(this, partLists) {
            @Override
            public void fillValue(ViewHolder holder, PartList partList) {
                holder.getTvId().setVisibility(View.GONE);
                holder.getTvName().setText(partList.getPART_NAME());
            }
        };

        lvPart.setAdapter(partAdapter);
        //部位监听
        lvPart.setOnItemClickListener(this);
        //项目列表
        ListView lvItem = new ListView(this);
        lvItem.setDividerHeight(0);
        lvItem.setId(R.id.list4);
        itemAdapter = new DropDownAdapter<ItemList>(this, itemLists) {
            @Override
            public void fillValue(ViewHolder holder, ItemList itemList) {
                holder.getTvId().setVisibility(View.GONE);
                holder.getTvName().setText(itemList.getITEM_NAME());
            }
        };

        lvItem.setAdapter(itemAdapter);
        //项目监听
        lvItem.setOnItemClickListener(this);
        //内容列表
        ListView lvContent = new ListView(this);
        lvContent.setDividerHeight(0);
        lvContent.setId(R.id.list5);
        contentAdapter = new DropDownAdapter<ContentList>(this, contentLists) {
            @Override
            public void fillValue(ViewHolder holder, ContentList contentList) {
                holder.getTvId().setVisibility(View.GONE);
                holder.getTvName().setText(contentList.getCONTENT_NAME());
            }
        };
        lvContent.setAdapter(contentAdapter);
        //内容监听
        lvContent.setOnItemClickListener(this);
        popViews.add(lvEquip);
        popViews.add(lvPart);
        popViews.add(lvItem);
        popViews.add(lvContent);
        //添加contentView
        View contentView = View.inflate(this, R.layout.drop_view_check_contentview, null);
        //lvArea= contentView.findViewById(R.id.lv_drop_view_content);
        adapter = new CommonBaseAdapter<AreaList>(this, areaLists, R.layout.item_drop_down_lv) {
            @Override
            public void convert(ViewHolder holder, AreaList areaList) {
                holder.setText(R.id.tv_drop_down_area, areaList.getPL_AREA_NAME());
                holder.tvOnClick(R.id.tb_drop_down_area, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            UIUtils.toast("检修", false);
                        } else {
                            UIUtils.toast("正常", false);
                        }
                    }
                });
            }
        };
//        ImageView contentView = new ImageView(this);
//        contentView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
//        contentView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popViews, contentView);
    }

    @OnClick(R.id.iv_title_back)
    public void back() {
        removeAll();
        goToActivity(AtyPlanManager.class, null);
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

            case R.id.list2://city
                equipAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == -1 ? headers[1] : equipLists.get(position).getEL_NAME());
                //dropDownMenu1.setImageResource(imagIds[1]);
                dropDownMenu.closeMenu();
                break;
            case R.id.list3://city
                partAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == -1 ? headers[2] : partLists.get(position).getPART_NAME());
                //dropDownMenu1.setImageResource(imagIds[2]);
                dropDownMenu.closeMenu();
                break;
            case R.id.list4://city
                itemAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == -1 ? headers[3] : itemLists.get(position).getITEM_NAME());
                // dropDownMenu1.setImageResource(imagIds[3]);
                dropDownMenu.closeMenu();
                break;
            case R.id.list5://city
                contentAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == -1 ? headers[4] : contentLists.get(position).getCONTENT_NAME());
                // dropDownMenu1.setImageResource(imagIds[4]);
                dropDownMenu.closeMenu();
                break;
        }
    }

}
