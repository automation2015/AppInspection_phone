package auto.cn.appinspection.atys;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import auto.cn.appinspection.R;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.nfc.MyNDEFMsgGet;
import auto.cn.appinspection.nfc.MyNfcRecordParse;
import auto.cn.appinspection.utils.LogUtil;
import auto.cn.appinspection.utils.UIUtils;
import butterknife.Bind;
import butterknife.OnClick;

public class AtyNfcFunc extends BaseActivity {
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.et_nfc_text)
    EditText etNfcText;
    @Bind(R.id.btn_nfc_write)
    Button btnNfcWrite;
    @Bind(R.id.tv_nfc_type)
    TextView tvNfcType;
    @Bind(R.id.tv_nfc_data)
    TextView tvNfcData;

    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private AlertDialog alertDialog;
    private String payload;
    private NdefMessage ndefMessage;
    private boolean isWrite = false;

    @Override
    protected int getLayoutId() {
        return R.layout.aty_nfc_func;
    }

    @Override
    protected void initTitle() {
        tvTitle.setText("NFC读写数据");
        ivTitleBack.setVisibility(View.VISIBLE);
        ivTitleSetting.setVisibility(View.GONE);
    }

    @OnClick(R.id.iv_title_back)
    public void back() {
        removeCurrentActivity();
    }

    @Override
    public void initData() {
        //check auto.cn.common.auto.cn.common.nfc
        nfcCheck();
        //init auto.cn.common.auto.cn.common.nfc
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        etNfcText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                payload = s.toString();
                LogUtil.e("afterTextChanged() called with: payload = [" + payload + "]");
            }
        });


    }

    //Todo 动态权限获取
    //写入事件监听
    @OnClick({R.id.btn_nfc_write})
    public void writeDataClick() {
        if (TextUtils.isEmpty(etNfcText.getText())) {
            UIUtils.toast("请先输入需要写入NFC中的数据！", false);
        } else {
            //enableForegroundDispatch();
            AlertDialog.Builder builder = new AlertDialog.Builder(AtyNfcFunc.this);
            builder.setTitle("写入NFC数据")
                    .setMessage("请靠近NFC标签！")
                    .setPositiveButton("取消", null)
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            isWrite = true;
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            disableForegroundDispatch();
                            alertDialog.dismiss();
                        }
                    });
            alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }
    }

    //获取tag
    private void resolveIntent(Intent intent) {

           // writeData(intent);

            readData(intent);

    }

    //写入Tag数据
    private void writeData(Intent intent) {
        String action = intent.getAction();
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (supportedTechs(tag.getTechList())) {
            //ndefMessage = MyNDEFMsgGet.getNdefMsg_RTD_URI(payload, (byte) 0x01, false);
            if (isWrite) {
                if (payload != null) {
                    ndefMessage = MyNDEFMsgGet.getNdefMsg_RTD_TEXT(payload, true, false);

                    new WriteTask(this, ndefMessage, tag).execute();
                    isWrite = false;
                } else {
                    UIUtils.toast("请先填写数据！", false);
                }
            }
        } else {
            UIUtils.toast("不支持的卡片！", false);
        }
    }

    //读取Tag数据
    private void readData(Intent intent) {
            String action = intent.getAction();
            if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
                NdefMessage[] messages = null;
                //获取标签对象
                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                if (supportedTechs(tag.getTechList())) {
                    //获取ndef消息数组
                    Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                    if (rawMsgs != null) {
                        messages = new NdefMessage[rawMsgs.length];
                        for (int i = 0; i < rawMsgs.length; i++) {
                            messages[i] = (NdefMessage) rawMsgs[i];
                        }
                    } else {
                        //未知的标签
                        byte[] empty = new byte[]{};
                        NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
                        NdefMessage msg = new NdefMessage(new NdefRecord[]{record});
                        messages = new NdefMessage[]{msg};
                    }
                    //tvTitle.setText("Scan a TAG!");
                    //解析
                    processNDEFMsg(messages);
                } else {
                    UIUtils.toast("不支持的卡片！", false);
                }
            } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {

            } else if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {

            } else {

            }
        }

        private void processNDEFMsg (NdefMessage[]messages){
            if (messages == null || messages.length == 0) {
                Toast.makeText(this, "TAG内容为空", Toast.LENGTH_SHORT).show();
                return;
            }
            for (int i = 0; i < messages.length; i++) {
                int length = messages[i].getRecords().length;
                NdefRecord[] records = messages[i].getRecords();
                for (int j = 0; j < length; j++) {
                    for (NdefRecord record : records) {
                        //parseRTDUriRecord(record);
                        String s = MyNfcRecordParse.parseWellKnowTextRecord(record);
                        tvNfcData.setText(s);
                    }
                }
            }
        }

    //检测tag的数据类型
    private boolean supportedTechs(String[] techList) {
        boolean isSupport = false;
        for (String s : techList) {
            LogUtil.e("supportedTechs() called with: s = [" + s + "]");
            if (s.equals("android.nfc.tech.MifareClassic")) {
                isSupport = false;
            } else if (s.equals("android.nfc.tech.MifareUltralight")) {
                isSupport = false;
            } else if (s.equals("android.nfc.tech.Ndef")) {
                isSupport = true;
            } else if (s.equals("android.nfc.tech.NdefA")) {
                isSupport = false;
            }
            //...
            else {
                isSupport = false;
            }
        }
        return isSupport;
    }

    //检测nfc功能
    private void nfcCheck() {
        mAdapter = NfcAdapter.getDefaultAdapter(this);//检测手机是否支持nfc
        if (mAdapter == null) {
            Toast.makeText(this, "设备没有nfc", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (!mAdapter.isEnabled()) {//检测手机的nfc功能是否打开
                new AlertDialog.Builder(AtyNfcFunc.this)
                        .setTitle("提示")
                        .setMessage("您的设备还未开启NFC功能，前往设置菜单打开NFC功能！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);//跳转到打开nfc设置界面
                                startActivity(intent);
                                return;
                            }
                        }).setNegativeButton("取消", null)
                        .show();
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        resolveIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        enableForegroundDispatch();
    }

    @Override
    protected void onPause() {
        super.onPause();
        disableForegroundDispatch();
        isWrite = false;
    }

    //使能前台标签调度系统
    private void enableForegroundDispatch() {
        if (mAdapter != null)
            mAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
    }

    //取消前台标签调度系统
    private void disableForegroundDispatch() {
        if (mAdapter != null) mAdapter.disableForegroundDispatch(this);
    }

    //写入数据
    static class WriteTask extends AsyncTask<Void, Void, Void> {
        Activity activity = null;
        NdefMessage msg = null;
        Tag tag = null;
        String text = null;

        WriteTask(Activity host, NdefMessage msg, Tag tag) {
            this.activity = host;
            this.msg = msg;
            this.tag = tag;
        }

        @Override
        protected Void doInBackground(Void... params) {
            int size = msg.toByteArray().length;
            Ndef ndef = Ndef.get(tag);
            if (ndef == null) {
                NdefFormatable formatable = NdefFormatable.get(tag);
                if (formatable != null) {
                    try {
                        formatable.connect();
                        formatable.format(msg);
                    } catch (IOException e) {
                        text = "Failed to connect Tag!";
                        LogUtil.e("doInBackground() called with: params = [" + params
                                + "],Failed to connect Tag!");
                        e.printStackTrace();
                    } catch (FormatException e) {
                        text = "Failed to format Tag!";
                        LogUtil.e("doInBackground() called with: params = [" + params
                                + "],Failed to format Tag!");
                        e.printStackTrace();
                    } finally {
                        if (formatable != null) {
                            try {
                                formatable.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    text = "NDEF not support your tag!";
                    LogUtil.e("doInBackground() called with: params = [" + params
                            + "],NDEF not support your tag");
                }
            } else {
                try {
                    ndef.connect();
                    if (!ndef.isWritable()) {
                        text = "Tag read only!";
                        LogUtil.e("doInBackground() called with: params = [" + params
                                + "],Tag read only");
                    } else if (ndef.getMaxSize() < size) {
                        text = "Tag is too small!";
                        LogUtil.e("doInBackground() called with: params = [" + params
                                + "],Tag is too small!");
                    } else {
                        ndef.writeNdefMessage(msg);
                    }
                } catch (IOException e) {
                    text = "Fail to connect Tag!";
                    LogUtil.e("doInBackground() called with: params = [" + params
                            + "],Fail to connect Tag!");
                    e.printStackTrace();
                } catch (FormatException e) {
                    text = "Fail to write NdefMessage Tag!";
                    LogUtil.e("doInBackground() called with: params = [" + params
                            + "],Fail to write NdefMessage Tag!");
                    e.printStackTrace();
                } finally {
                    try {
                        ndef.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (text != null) {
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
            }
            //activity.finish();
        }
    }
}
