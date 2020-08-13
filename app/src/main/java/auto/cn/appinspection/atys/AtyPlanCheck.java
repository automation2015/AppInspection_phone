package auto.cn.appinspection.atys;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auto.cn.appinspection.R;
import auto.cn.appinspection.adapters.DropDownAdapter;
import auto.cn.appinspection.bases.BaseActivity;
import auto.cn.appinspection.commons.AppNetConfig;
import auto.cn.appinspection.commons.DbHelper;
import auto.cn.appinspection.nfc.MyNfcRecordParse;
import auto.cn.appinspection.ui.DropDownMemu;
import auto.cn.appinspection.utils.LogUtil;
import auto.cn.appinspection.utils.UIUtils;
import auto.cn.greendaogenerate.AreaList;
import auto.cn.greendaogenerate.ContentList;
import auto.cn.greendaogenerate.Equiplist;
import auto.cn.greendaogenerate.ItemList;
import auto.cn.greendaogenerate.PartList;
import butterknife.Bind;
import butterknife.OnClick;

public class AtyPlanCheck extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

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
    private String headers[] = {"设备", "部位", "项目", "内容"};
    private List<View> popViews = new ArrayList<>();

    List<Equiplist> equipLists;
    List<PartList> partLists;
    List<ItemList> itemLists;
    List<ContentList> contentLists;
    private DropDownAdapter<Equiplist> equipAdapter;
    private DropDownAdapter<PartList> partAdapter;
    private DropDownAdapter<ItemList> itemAdapter;
    private DropDownAdapter<ContentList> contentAdapter;

    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private String areaData;
    private String equipSelected;
    private String partSelected;
    private String itemSelected;
    private String contentSelected;
    private int equipPosSelected;
    private int partPosSelected;
    private int itemPosSelected;
    private int contentPosSelected;
    //ContentView组件
    private TextView tvCheckArea;
    private TextView tvCheckEquip;
    private TextView tvCheckPart;
    private TextView tvCheckItem;
    private TextView tvCheckContent;
    private TextView tvCheckStandard;
    private TextView tvCheckUnit;
    private EditText etCheckDesc;
    private EditText etCheckQuantify;
    private ToggleButton tbCheckStatus;
    private ToggleButton tbCheckQuantify;
    private FloatingActionButton fabCheckBack;
    private FloatingActionButton fabCheckForward;
    private LinearLayout llCheckStatus, llCheckQuantify;
    private String contentStandard;
    private String currenArea = "";
    private int position = 0;
    private String contentAlarmH1;
    private String quantify;

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

    @OnClick(R.id.iv_title_back)
    public void back() {
        removeAll();
        goToActivity(AtyPlanManager.class, null);
    }

    @Override
    public void initData() {
        equipLists = new ArrayList<>();
        partLists = new ArrayList<>();
        itemLists = new ArrayList<>();
        contentLists = new ArrayList<>();
        //获取数据库操作类
        dbHelper = DbHelper.getInstance(this, AppNetConfig.DB_NAME);
        //打开数据库
        dbHelper.openDb();
        //设置控制台输出sql语句，filter tag：”greenDAO”
        dbHelper.setDebug();
        //nfc检查
        nfcCheck();
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
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
        tvCheckArea = contentView.findViewById(R.id.tv_check_area);
        tvCheckEquip = contentView.findViewById(R.id.tv_check_equip);
        tvCheckPart = contentView.findViewById(R.id.tv_check_part);
        tvCheckItem = contentView.findViewById(R.id.tv_check_item);
        tvCheckContent = contentView.findViewById(R.id.tv_check_content);
        tvCheckUnit = contentView.findViewById(R.id.tv_check_unit);
        tvCheckStandard = contentView.findViewById(R.id.tv_check_standard);
        etCheckDesc = contentView.findViewById(R.id.et_check_desc);
        etCheckQuantify = contentView.findViewById(R.id.et_check_quantify);
        fabCheckBack = contentView.findViewById(R.id.fab_check_back);
        fabCheckForward = contentView.findViewById(R.id.fab_check_forward);
        tbCheckQuantify = contentView.findViewById(R.id.toggle_check_quantify);
        tbCheckStatus = contentView.findViewById(R.id.toggle_check_status);
        llCheckStatus = contentView.findViewById(R.id.ll_check_desc);
        llCheckQuantify = contentView.findViewById(R.id.ll_check_quantify);
        tbCheckStatus.setOnCheckedChangeListener(this);
        tbCheckQuantify.setOnCheckedChangeListener(this);
        fabCheckBack.setOnClickListener(this);
        fabCheckForward.setOnClickListener(this);
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popViews, contentView);

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
            case R.id.list2://equip
                equipPosSelected = position;
                equipAdapter.setCheckItem(position);
                equipSelected = equipLists.get(position).getEL_NAME();
                dropDownMenu.setTabText(position == -1 ? headers[1] : equipSelected);
                partLists.clear();
                partLists.addAll(equipLists.get(position).getParts());
                partAdapter.notifyDataSetChanged();

                itemLists.clear();
                itemLists.addAll(equipLists.get(position).getItems());
                itemAdapter.notifyDataSetChanged();
                tvCheckArea.setText(areaData);

                tvCheckEquip.setText(equipSelected);
                tvCheckEquip.setTextColor(Color.BLACK);
                dropDownMenu.closeMenu();
                break;
            case R.id.list3://part
                partPosSelected = position;
                partAdapter.setCheckItem(position);
                partSelected = partLists.get(position).getPART_NAME();
                dropDownMenu.setTabText(position == -1 ? headers[2] : partLists.get(position).getPART_NAME());
                tvCheckPart.setText(partSelected);
                tvCheckPart.setTextColor(Color.BLACK);
                dropDownMenu.closeMenu();
                break;
            case R.id.list4://item
                itemPosSelected = position;
                itemAdapter.setCheckItem(position);
                itemSelected = itemLists.get(position).getITEM_NAME();
                dropDownMenu.setTabText(position == -1 ? headers[3] : itemSelected);
                contentLists.clear();
                contentLists.addAll(itemLists.get(position).getContents());
                contentAdapter.notifyDataSetChanged();
                tvCheckItem.setText(itemSelected);
                tvCheckItem.setTextColor(Color.BLACK);
                dropDownMenu.closeMenu();
                break;
            case R.id.list5://content
                contentPosSelected = position;
                contentAdapter.setCheckItem(position);
                contentSelected = contentLists.get(position).getCONTENT_NAME();
                contentStandard = contentLists.get(position).getCONTENT_STANDARD();
                dropDownMenu.setTabText(position == -1 ? headers[4] : contentSelected);
                tvCheckContent.setText(contentSelected);
                tvCheckContent.setTextColor(Color.BLACK);
                tvCheckStandard.setText(contentStandard);
                tvCheckStandard.setTextColor(Color.BLACK);
                dropDownMenu.closeMenu();
                break;
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
                new AlertDialog.Builder(AtyPlanCheck.this)
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

    private void resolveIntent(Intent intent) {
        //读取nfc Tag数据
        readData(intent);
    }

    //读取nfc Tag数据
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
                equipLists.clear();
                processNDEFMsg(messages);

            } else {
                UIUtils.toast("不支持的卡片！", false);
            }
        } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {

        } else if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {

        } else {
        }
    }

    //解析nfc数据
    private void processNDEFMsg(NdefMessage[] messages) {
        //1.判断message是否有效
        if (messages == null || messages.length == 0) {
            Toast.makeText(this, "TAG内容为空", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < messages.length; i++) {
            //2.得到message的记录长度
            int length = messages[i].getRecords().length;
            //3.获得record数组集合
            NdefRecord[] records = messages[i].getRecords();
            for (int j = 0; j < length; j++) {
                for (NdefRecord record : records) {
                    //4.得到记录的类型，是否是RTD_TEXT类型
                    byte[] type = record.getType();
                    String recordType = Integer.toHexString(type[0]);
                    String rtdText = Integer.toHexString(NdefRecord.RTD_TEXT[0]);
                    if (recordType.equals(rtdText)) {
                        areaData = MyNfcRecordParse.parseWellKnowTextRecord(record);
                        //5.查询数据库，确认标签数据是否是该班计划区域
                        AreaList area = dbHelper.queryAreaByAreaLable(areaData);
                        if (area.getPL_AREA_LABEL().equals(areaData)) {
                            tvCheckArea.setText(areaData);
                            tvCheckArea.setTextColor(Color.BLUE);
                            //6.查询数据库equip
                            equipLists.clear();
                            partLists.clear();
                            itemLists.clear();
                            contentLists.clear();
                            equipLists.addAll(area.getEquips());
                            equipAdapter.notifyDataSetChanged();

                            //7.为数据集合和 UI组件赋值（初值）
                            partLists.addAll(equipLists.get(0).getParts());
                            itemLists.addAll(equipLists.get(0).getItems());
                            contentLists.addAll(itemLists.get(0).getContents());
                            tvCheckEquip.setText(equipLists.get(0).getEL_NAME());
                            tvCheckPart.setText(equipLists.get(0).getParts().get(0).getPART_NAME());
                            ItemList itemList = equipLists.get(0).getItems().get(0);
                            tvCheckItem.setText(itemList.getITEM_NAME());
                            tvCheckContent.setText(itemList.getContents().get(0).getCONTENT_NAME());
                            tvCheckStandard.setText(itemList.getContents().get(0).getCONTENT_STANDARD());
                            tvCheckEquip.setTextColor(Color.BLACK);
                            tvCheckItem.setTextColor(Color.BLACK);
                            tvCheckContent.setTextColor(Color.BLACK);
                            tvCheckStandard.setTextColor(Color.BLACK);
                            tvCheckPart.setTextColor(Color.BLACK);
                            tbCheckStatus.setClickable(true);
                            tbCheckQuantify.setClickable(true);
                        } else {
                            tvCheckArea.setText("无效的区域！");
                            UIUtils.toast("该区域不在本班计划之中，请重新扫描区域标签！", false);
                        }
                    } else {
                        tvCheckArea.setText("无效的标签，数据格式不符合！");
                        UIUtils.toast("NFC中的数据格式不符合，请重新输入数据！", false);
                    }
                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_check_back:
                if (TextUtils.isEmpty(areaData)) {
                    UIUtils.toast("请先扫描区域标签！", false);
                } else {
                    if (contentPosSelected > 0) {
                        contentPosSelected--;
                        setContentData(v);
                    } else {
                        contentPosSelected = 0;
                        itemPosSelected--;
                        if (itemPosSelected >= 0) {
                            setItemDatas(v);
                            setContentData(v);
                        } else {
                            itemPosSelected = 0;
                            equipPosSelected--;
                            if (equipPosSelected >= 0) {
                                setEquipDatas(v);
                                setItemDatas(v);
                                setContentData(v);
                            } else {
                                UIUtils.toast("已经没有数据了！", false);
                                contentPosSelected = 0;
                                itemPosSelected = 0;
                                equipPosSelected = 0;
                            }
                        }
                    }
                }
                break;
            case R.id.fab_check_forward:
                if (TextUtils.isEmpty(areaData)) {
                    UIUtils.toast("请先扫描区域标签！", false);
                } else {
                    if (contentPosSelected < contentLists.size() - 1) {
                        contentPosSelected++;
                        //1.判断当前选中的是否是最后一项，如果不是，当前选中content的位置+1，更新adapter，为tv设置新值；
                        setContentData(v);
                    } else if (contentPosSelected >= contentLists.size() - 1) {
                        //2.判断当前选中的是否是最后一项，如果是，当前选中item的位置+1，更新adapter，更新contentLists，为tv设置新值；
                        contentPosSelected = contentLists.size() - 1;
                        itemPosSelected++;
                        if (itemPosSelected <= itemLists.size() - 1) {
                            setItemDatas(v);
                            setContentData(v);
                        } else if (itemPosSelected >= itemLists.size()) {
                            itemPosSelected = itemLists.size() - 1;
                            equipPosSelected++;
                            if (equipPosSelected <= equipLists.size() - 1) {
                                setEquipDatas(v);
                                setItemDatas(v);
                                setContentData(v);
                            } else {
                                equipPosSelected = equipLists.size() - 1;
                                UIUtils.toast("已经是最后一项内容了！", false);
                            }
                        }
                    } else {

                    }
                }
                break;
        }
    }

    private void setEquipDatas(View v) {
        equipAdapter.setCheckItem(equipPosSelected);
        // equipAdapter.notifyDataSetChanged();
        itemLists.clear();
        itemLists.addAll(equipLists.get(equipPosSelected).getItems());
        switch (v.getId()) {
            case R.id.fab_check_back:
                itemPosSelected = itemLists.size() - 1;
                break;
            case R.id.fab_check_forward:
                itemPosSelected = 0;
                break;
        }
        partLists.clear();
        partLists.addAll(equipLists.get(equipPosSelected).getParts());
        partAdapter.notifyDataSetChanged();
        tvCheckEquip.setText(equipLists.get(equipPosSelected).getEL_NAME());
        tvCheckPart.setText(partLists.get(0).getPART_NAME());
    }

    private void setItemDatas(View v) {
        itemAdapter.setCheckItem(itemPosSelected);
        // itemAdapter.notifyDataSetChanged();
        contentLists.clear();
        contentLists.addAll(itemLists.get(itemPosSelected).getContents());
        switch (v.getId()) {
            case R.id.fab_check_back:
                contentPosSelected = contentLists.size() - 1;
                break;
            case R.id.fab_check_forward:
                contentPosSelected = 0;
                break;
        }
        //contentPosSelected = 0;
        tvCheckItem.setText(itemLists.get(itemPosSelected).getITEM_NAME());
    }

    private void setContentData(View v) {
        contentAdapter.setCheckItem(contentPosSelected);
        ContentList curContent = contentLists.get(contentPosSelected);
        tvCheckContent.setText(curContent.getCONTENT_NAME());
        tvCheckStandard.setText(curContent.getCONTENT_STANDARD());
        boolean checkWay = curContent.getCONTENT_ALARM_STYLE().equals("定性");
        if (checkWay) {
            tbCheckQuantify.setChecked(false);
            tbCheckStatus.setChecked(false);
            llCheckQuantify.setVisibility(View.GONE);
            llCheckStatus.setVisibility(View.GONE);
        } else {
            tbCheckQuantify.setChecked(true);
            llCheckQuantify.setVisibility(View.VISIBLE);
            llCheckStatus.setVisibility(View.VISIBLE);
            quantify = etCheckQuantify.getText().toString().trim();
            contentAlarmH1 = curContent.getCONTENT_ALARM_H1();
            if (TextUtils.isEmpty(quantify)) {
                new AlertDialog.Builder(AtyPlanCheck.this)
                        .setTitle("提示")
                        .setMessage("请输入量化值！")
                        .setCancelable(false)
                        .setPositiveButton("确定", null)
                        .create()
                        .show();
                contentPosSelected--;
            } else {
                if (Integer.valueOf(quantify) > Integer.valueOf(contentAlarmH1)) {
                    tbCheckStatus.setChecked(true);
                } else {
                    tbCheckStatus.setChecked(false);
                }
            }
        }
        if(tbCheckStatus.isChecked()){
            ivTitleSetting.setBackgroundResource(R.mipmap.icon_camera);
            ivTitleSetting.setVisibility(View.VISIBLE);
            String unusualDesc = etCheckDesc.getText().toString().trim();
            if (TextUtils.isEmpty(unusualDesc)) {
                new AlertDialog.Builder(AtyPlanCheck.this)
                        .setTitle("提示")
                        .setMessage("请输入异常信息！")
                        .setCancelable(false)
                        .setPositiveButton("确定", null)
                        .create()
                        .show();
                contentPosSelected--;
            } else {
                etCheckQuantify.setText("");
                etCheckDesc.setText("");
                tbCheckQuantify.setChecked(false);
                tbCheckStatus.setChecked(false);
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.toggle_check_status:
                if (isChecked) {
                    //异常
                    llCheckStatus.setVisibility(View.VISIBLE);
                    ivTitleSetting.setBackgroundResource(R.mipmap.icon_camera);
                    ivTitleSetting.setVisibility(View.VISIBLE);
                    UIUtils.toast("请填写异常信息或者拍照上传！", false);
                    String unusualDesc = etCheckDesc.getText().toString().trim();
                } else {
                    //正常
                    llCheckStatus.setVisibility(View.GONE);
                    ivTitleSetting.setVisibility(View.GONE);
                }
                break;
            case R.id.toggle_check_quantify:
                if (isChecked) {
                    //量化
                    llCheckQuantify.setVisibility(View.VISIBLE);
                } else {
                    //定性
                    llCheckQuantify.setVisibility(View.GONE);
                }
                break;
        }
    }
}
