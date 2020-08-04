package auto.cn.appinspection.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import auto.cn.appinspection.R;
import auto.cn.appinspection.adapters.BadgeViewAdapter;
import auto.cn.appinspection.adapters.CommonBaseAdapter;
import auto.cn.appinspection.atys.AtyLogin;
import auto.cn.appinspection.atys.AtyPlan;
import auto.cn.appinspection.atys.AtyPlanManager;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.bases.BaseFragment;
import auto.cn.appinspection.beans.UserBean;
import auto.cn.appinspection.utils.UIUtils;
import butterknife.Bind;

public class HomeFragment extends BaseFragment implements OnItemClickListener {
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.gv)
    GridView gv;
    private int[] icon = {R.mipmap.icon_plan_manager, R.mipmap.icon_hidden_manage, R.mipmap.icon_maintainance_manage, R.mipmap.icon_other_manage};
    private String[] iconName = {"计划管理", "隐患管理", "检修管理", "其他管理"};
    private BadgeViewAdapter adapter;
    //private CommonBaseAdapter<>
    private List<Map<String, Object>> dataList;
    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        adapter = new BadgeViewAdapter(UIUtils.getContext(), dataList);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(this);
    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.GONE);
        tvTitle.setText("首页");
        ivTitleSetting.setVisibility(View.GONE);
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    /**
     * 获取gridView的数据
     * @return
     */
    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("iconId", icon[i]);
            map.put("iconTitle", iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserBean user = ((BaseActivity) this.getActivity()).readUser();
        String username = user.getUsername();
        switch (position) {
            case 0:
                if(TextUtils.isEmpty(username)){
                  doLogin();}
                else{
                ((BaseActivity) this.getActivity()).goToActivity(AtyPlanManager.class,null);
                }
                break;
            case 1:
                if(TextUtils.isEmpty(username)){
                    doLogin();}
                else{
                UIUtils.toast(position+"",false);
                }
                break;
            case 2:
                if(TextUtils.isEmpty(username)){
                    doLogin();}
                else{
                    UIUtils.toast(position+"",false);
                }
                break;
            case 3:
                if(TextUtils.isEmpty(username)){
                    doLogin();}
                else{
                    UIUtils.toast(position+"",false);
                }
        }

    }

    private void doLogin() {
        new AlertDialog.Builder(this.getActivity())
                .setTitle("提示")
                .setMessage("您还没有登录哦！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //UIUtils.toast("进入登录页面",false);
                        ((BaseActivity) HomeFragment.this.getActivity()).goToActivity(AtyLogin.class, null);
                    }
                })
                .setCancelable(false)
                .show();
    }
}
