package auto.cn.appinspection.fregments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import auto.cn.appinspection.R;
import auto.cn.appinspection.atys.AtyAbout;
import auto.cn.appinspection.atys.AtyUserRegist;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.bases.BaseFragment;
import auto.cn.appinspection.commons.AppNetConfig;
import auto.cn.appinspection.utils.UIUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MoreFragment extends BaseFragment {

    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.tv_more_regist)
    TextView tvMoreRegist;
    @Bind(R.id.tv_more_reset)
    TextView tvMoreReset;
    @Bind(R.id.tv_more_phone)
    TextView tvMorePhone;
    @Bind(R.id.rl_more_contact)
    RelativeLayout rlMoreContact;
    @Bind(R.id.tv_more_share)
    TextView tvMoreShare;
    @Bind(R.id.tv_more_about)
    TextView tvMoreAbout;
    @Bind(R.id.tv_more_feedback)
    TextView tvMoreFeedback;

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
        //用户注册
        userResgist();
        //联系客服
        contactService();
        //提交反馈意见
        commitFeedback();
        //分享软件
        shareSoftwareMsg();
        //软件说明
        aboutInspection();
    }

    public void initTitle() {
        ivTitleBack.setVisibility(View.GONE);
        tvTitle.setText("更多");
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_more;
    }

    //用户注册
    private void userResgist() {
        tvMoreRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) MoreFragment.this.getActivity()).goToActivity(AtyUserRegist.class, null);
            }
        });
    }
    //软件说明
    private void aboutInspection() {
        tvMoreAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) MoreFragment.this.getActivity()).goToActivity(AtyAbout.class, null);
            }
        });
    }
    //分享软件
    private void shareSoftwareMsg() {
        tvMoreShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="\"世界上最遥远的距离，是我在if里你在else里，似乎一直相伴又永远分离；\"\n" +
                        "\" 世界上最痴心的等待，是我当case你是switch，或许永远都选不上自己；\"\n" +
                        "\" 世界上最真情的相依，是你在try我在catch。无论你发神马脾气，我都默默承受，静静处理。到那时，再来期待我们的finally。\"";

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, s);
                startActivity(Intent.createChooser(intent, "设备点巡检"));
            }
        });

    }
    //联系客服
    private void contactService() {
        rlMoreContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MoreFragment.this.getActivity())
                        .setTitle("联系客服")
                        .setMessage("是否现在联系客服：0531-776920678")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //获取手机号码
                                String phone = tvMorePhone.getText().toString().trim();
                                //使用隐式意图，启动系统拨号应用界面
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:" + phone));
                                if (ActivityCompat.checkSelfPermission(MoreFragment.this.getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                MoreFragment.this.getActivity().startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }
    private String department = "不明确";
    //提交反馈意见
    private void commitFeedback() {
        tvMoreFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提供一个View
                View view = View.inflate(MoreFragment.this.getActivity(), R.layout.view_feedback, null);
                final RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg_feedback);
                final EditText et_feedback_content = (EditText) view.findViewById(R.id.et_feedback_content);

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton rb = (RadioButton) rg.findViewById(checkedId);
                        department = rb.getText().toString();
                    }
                });

                new AlertDialog.Builder(MoreFragment.this.getActivity())
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //获取反馈的信息
                                String content = et_feedback_content.getText().toString();
                                //联网发送反馈信息
                                AsyncHttpClient client = new AsyncHttpClient();
                                //TODO 联网发送信息
                                String url = "";
                                RequestParams params = new RequestParams();
                                params.put("department", department);
                                params.put("content", content);
                                client.post(url, params, new AsyncHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(String content) {
                                        UIUtils.toast("发送反馈信息成功", false);
                                    }

                                    @Override
                                    public void onFailure(Throwable error, String content) {
                                        UIUtils.toast("发送反馈信息失败", false);
                                    }
                                });
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }
}
