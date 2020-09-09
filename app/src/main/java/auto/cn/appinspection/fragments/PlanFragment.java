package auto.cn.appinspection.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.bases.BaseFragment;
import auto.cn.appinspection.beans.PlanBean;
import auto.cn.appinspection.beans.UserBean;
import auto.cn.appinspection.commons.Constant;
import auto.cn.appinspection.commons.DbHelper;
import auto.cn.appinspection.utils.LogUtil;
import auto.cn.appinspection.utils.UIUtils;
import auto.cn.greendaogenerate.AreaList;
import auto.cn.greendaogenerate.ContentList;
import auto.cn.greendaogenerate.Equiplist;
import auto.cn.greendaogenerate.ItemList;
import auto.cn.greendaogenerate.PartList;
import auto.cn.greendaogenerate.PlanList;
import butterknife.Bind;

public class PlanFragment extends BaseFragment {
    @Bind(R.id.lv_fragment_plan)
    ListView lvFragmentPlan;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    private ProgressDialog pd;
    //Activity 控件 添加数据到数据库
    private ImageView ivAddDb;
    private List<PlanBean> mDatas;
    private CommonBaseAdapter<PlanBean> planAdapter;
    private String urlTest = "http://api.map.baidu.com/telematics/v3/weather?location=%E6%B5%8E%E5%8D%97&output=json&ak=FkPhtMBK0HTIQNh7gG4cNUttSTyr0nzo";
    private String url = Constant.GETALLPLAN + "?username=巡检丁班&rolename=电气岗位点检员";
    private String url1 = Constant.GETALLPLAN + "?username=admin&rolename=系统管理员";
    private DbHelper dbHelper;

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
        pd=new ProgressDialog(getActivity());
        pd.setTitle("提示");
        pd.setMessage("正在存储数据，请稍候！");
        if (content != null && content.length() != 0) {
            //解析获取的数据
            mDatas = parseDatas(content);
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
        /*
        lvFragmentPlan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlanBean planBean = mDatas.get(position);
                String planId = planBean.getPLAN_ID();
                AtyPlanCheck.toActivity(getActivity(), planId);
                //((BaseActivity) getActivity()).removeCurrentActivity();
            }
        });*/
            //初始化数据库对象
            dbHelper = DbHelper.getInstance(getActivity(), Constant.DB_NAME);
            //设置控制台输出sql语句，filter tag：”greenDAO”
            dbHelper.setDebug();
            //获取添加数据库按钮并设置点击事件
            ivAddDb = getActivity().findViewById(R.id.iv_title_setting);
            ivAddDb.setVisibility(View.VISIBLE);
            ivAddDb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //1.打开数据库
                    boolean isOpen = dbHelper.openDb();
                    //openDb();
                    //2.判断数据库是否存在
                    if (isOpen) {
                        //3.dialog弹窗提示用户
                        new AlertDialog.Builder(getActivity()).setTitle("提示")
                                .setMessage("您确定将新数据存入数据库吗？此操作将清除原有数据。")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //4.删除原有数据
                                        dbHelper.clearDb();
                                        //5.存入新数据
//                                        saveDb(mDatas);
//                                        UIUtils.toast("数据存储成功！", false);
                                        new SaveData().execute();

                                    }
                                }).setNegativeButton("取消", null).create().show();
                    }
                }
            });
        } else {
            UserBean userBean = ((BaseActivity) getActivity()).readUser();
            UIUtils.toast(userBean.getUsername() + ",您好，本班没有您需要点检的工作计划。", false);
        }
    }
    //将联网获取的数据存入数据库,异步任务
    class SaveData extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            saveDb(mDatas);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
            UIUtils.toast("数据存储成功！", false);
        }
    }

    //将联网获取的数据存入数据库
    public void saveDb(List<PlanBean> mDatas) {
        if (mDatas == null && mDatas.size() > 0) {
            UIUtils.toast("您请求的数据有误，请刷新页面后重试！", false);
            return;
        }
        //第一层循环，添加计划数据
        for (int i = 0; i < mDatas.size(); i++) {
            try {
                PlanBean planData = mDatas.get(i);
                List<PlanBean.AreaListBean> areaDatas = mDatas.get(i).getAreaList();

                PlanList planList = new PlanList();
                //planList.setId(Long.valueOf(planData.getId()));
                planList.setPLAN_ID(planData.getPLAN_ID());
                planList.setPLAN_NAME(planData.getPLAN_NAME());
                planList.setPLAN_ORG_NAME(planData.getPLAN_ORG_NAME());

                planList.setPLAN_PART_NAME(planData.getPLAN_PART_NAME());
                planList.setPLAN_PART_ID(planData.getPLAN_PART_ID());
                planList.setPLAN_NUM(Integer.valueOf(planData.getPLAN_NUM()));
                planList.setPLAN_CYCLE_TYPE(Integer.valueOf(planData.getPLAN_CYCLE_TYPE()));

                planList.setPLAN_LAST_DATE(planData.getPLAN_LAST_DATE());
                planList.setPLAN_LAST_USER_NAME(planData.getPLAN_LAST_USER_NAME());
                planList.setPLAN_CREATE_DATE(planData.getPLAN_CREATE_DATE());
                planList.setValid_Flag(planData.getValid_Flag());

                planList.setShift(planData.getShift());
                planList.setCODE_NAME(planData.getCODE_NAME());
                long planCount = dbHelper.insertOrReplace(planList);
                //planListDao.insertOrReplaceInTx(planList);
                //第二层循环，添加area数据
                for (int j = 0; j < areaDatas.size(); j++) {
                    PlanBean.AreaListBean areaData = areaDatas.get(j);
                    List<PlanBean.AreaListBean.EquipListBean> equipDatas = areaData.getEquipList();
                    AreaList areaList = new AreaList();

                    areaList.setPL_AREA_ID(areaData.getPL_AREA_ID());
                    areaList.setPL_AREA_NAME(areaData.getPL_AREA_NAME());
                    areaList.setPL_AREA_LABEL(areaData.getPL_AREA_LABEL());
                    areaList.setPL_AREA_CREATE_ID(areaData.getPL_AREA_CREATE_ID());

                    areaList.setPL_AREA_CREATE_DATE(areaData.getPL_AREA_CREATE_DATE());
                    areaList.setValid_Flag(areaData.getValid_Flag());
                    areaList.setPlanId(areaData.getPlanId());

                    areaList.setPlanList(planList);
                    //areaListDao.insertOrReplace(areaList);
                    //areaListDao.insertOrReplaceInTx(areaList);
                    long areaCount = dbHelper.insertOrReplace(areaList);
                    //第三层循环，添加equip数据
                    for (int k = 0; k < equipDatas.size(); k++) {
                        PlanBean.AreaListBean.EquipListBean equipData = equipDatas.get(k);
                        List<PlanBean.AreaListBean.EquipListBean.PartListBean> partDatas = equipData.getPartList();
                        List<PlanBean.AreaListBean.EquipListBean.ItemListBean> itemDatas = equipData.getItemList();
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
                       // equiplistDao.insertOrReplaceInTx(equipList);
                        dbHelper.insertOrReplace(equipList);

                        //第四层循环，添加part数据
                        for (int l = 0; l < partDatas.size(); l++) {
                            PlanBean.AreaListBean.EquipListBean.PartListBean partData = partDatas.get(l);
                            PartList partList = new PartList();
                            // partList.setId(Long.valueOf(partData.getId()));
                            partList.setPART_ID(partData.getPART_ID());
                            partList.setPART_BZ_ID(partData.getPART_BZ_ID());
                            partList.setPART_NAME(partData.getPART_NAME());

                            partList.setPART_CREATE_DATE(partData.getPART_CREATE_DATE());
                            partList.setValid_Flag(partData.getValid_Flag());

                            partList.setEquiplist(equipList);
                            //partListDao.insertOrReplace(partList);
                            dbHelper.insertOrReplace(partList);
                        }
                        //第五层循环，添加item数据
                        for (int m = 0; m < itemDatas.size(); m++) {
                            PlanBean.AreaListBean.EquipListBean.ItemListBean itemData = itemDatas.get(m);
                            List<PlanBean.AreaListBean.EquipListBean.ItemListBean.ContentListBean> contentDatas = itemData.getContentList();

                            ItemList itemList = new ItemList();
                            // itemList.setId(Long.valueOf(itemData.getId()));
                            itemList.setITEM_ID(itemData.getITEM_ID());
                            itemList.setITEM_PART_ID(itemData.getITEM_PART_ID());
                            itemList.setITEM_PL_BZ_ID(itemData.getITEM_PL_BZ_ID());

                            itemList.setITEM_NAME(itemData.getITEM_NAME());
                            itemList.setITEM_CREATE_DATE(itemData.getITEM_CREATE_DATE());
                            itemList.setValid_Flag(itemData.getValid_Flag());

                            itemList.setEquiplist(equipList);
                            //itemListDao.insertOrReplaceInTx(itemList);
                            dbHelper.insertOrReplace(itemList);
                            //第六层循环，添加content数据
                            for (int n = 0; n < contentDatas.size(); n++) {
                                PlanBean.AreaListBean.EquipListBean.ItemListBean.ContentListBean contentData = contentDatas.get(n);
                                ContentList contentList = new ContentList();

                                // contentList.setId(Long.valueOf(contentData.getId()));
                                contentList.setCONTENT_ID(contentData.getCONTENT_ID());
                                contentList.setCONTENT_PL_BZ_ID(contentData.getCONTENT_PL_BZ_ID());
                                contentList.setCONTENT_PART_ID(contentData.getCONTENT_PART_ID());

                                contentList.setCONTENT_ITEM_ID(contentData.getCONTENT_ITEM_ID());
                                contentList.setCONTENT_NAME(contentData.getCONTENT_NAME());
                                contentList.setCONTENT_CONTENT_TYPE(contentData.getCONTENT_CONTENT_TYPE());
                                contentList.setCONTENT_SORT(Integer.valueOf(contentData.getCONTENT_SORT()));

                                contentList.setCONTENT_WAY(contentData.getCONTENT_WAY());
                                contentList.setCONTENT_IS_USE(contentData.getCONTENT_IS_USE());
                                contentList.setCONTENT_STANDARD(contentData.getCONTENT_STANDARD());
                                contentList.setCONTENT_IS_PHOTO(contentData.getCONTENT_IS_PHOTO());

                                contentList.setCONTENT_IS_PHOTO_EXCEPTION(contentData.getCONTENT_IS_PHOTO_EXCEPTION());
                                contentList.setCONTENT_CREATE_DATE(contentData.getCONTENT_CREATE_DATE());
                                contentList.setCONTENT_ALARM_H1(contentData.getCONTENT_ALARM_H1());
                                contentList.setCONTENT_ALARM_H2(contentData.getCONTENT_ALARM_H2());

                                contentList.setCONTENT_ALARM_STYLE(contentData.getCONTENT_ALARM_STYLE());
                                contentList.setValid_Flag(contentData.getValid_Flag());
                                contentList.setCODE_NAME(contentData.getCODE_NAME());
                                contentList.setContent_finish(false);
                                contentList.setPhoto_path("未知");

                                contentList.setItemList(itemList);
                                //contentListDao.insertOrReplaceInTx(contentList);
                                dbHelper.insertOrReplace(contentList);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
