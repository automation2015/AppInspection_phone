package auto.cn.appinspection.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CheckHistoryFragment extends BaseFragment {

    @Bind(R.id.lv_fragment_plan)
    ListView lvFragmentPlan;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @Bind(R.id.fab_choosemsg)
    FloatingActionButton fabChoosemsg;

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
        return R.layout.fragment_check_history;
    }

    @Override
    protected void initData(String content) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
