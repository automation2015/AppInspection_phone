package auto.cn.appinspection.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import auto.cn.appinspection.R;
import auto.cn.appinspection.adapters.CommonBaseAdapter;


public class DropDownMemu extends LinearLayout {
    //顶部菜单布局
    private LinearLayout tabMenuView;
    //底部容器 包含内容区域 遮罩区域 菜单弹出区域
    private FrameLayout containerView;
    //内容区域
    private View contentView;
    //遮罩区域
    private View maskView;
    //菜单弹出区域
    private FrameLayout popupMenuViews;

    //分割线颜色
    private int dividerColor = 0xffcccccc;
    //文本选中颜色
    private int textSelectedColor = 0xff890c85;
    //文本未被选中颜色
    private int textUnSelectedColor = 0xff111111;
    //遮罩颜色
    private int maskColor = 0x88888888;
    //菜单背景颜色
    private int menuBackgroundColor = 0xffffffff;
    //水平分割线颜色
    private int underlineColor = 0xffcccccc;
    //字体大小
    private int menuTextSize = 14;
    //tab选中图标
    private int menuSelectdIcon;
    //tab未选中图标
    private int menuUnSelectdIcon;
    //菜单项被选中位置，初始没有菜单被选中，记为-1
    private int currentTabPosition = -1;

    public DropDownMemu(Context context) {
        super(context, null);
    }

    public DropDownMemu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropDownMemu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        //读取attrs中定义的属性值
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.dropDownMenu);
        underlineColor = a.getColor(R.styleable.dropDownMenu_underlineColor, underlineColor);
        dividerColor = a.getColor(R.styleable.dropDownMenu_dividerColor, dividerColor);
        textSelectedColor = a.getColor(R.styleable.dropDownMenu_textSelectdColor, textSelectedColor);
        textUnSelectedColor = a.getColor(R.styleable.dropDownMenu_textUnSelectdColor, textUnSelectedColor);
        menuBackgroundColor = a.getColor(R.styleable.dropDownMenu_menuBackgroundColor, menuBackgroundColor);
        maskColor = a.getColor(R.styleable.dropDownMenu_maskColor, maskColor);
        menuTextSize = a.getDimensionPixelSize(R.styleable.dropDownMenu_menuTextSize, menuTextSize);
        menuSelectdIcon = a.getResourceId(R.styleable.dropDownMenu_menuSelectedIcon, menuSelectdIcon);
        menuUnSelectdIcon = a.getResourceId(R.styleable.dropDownMenu_menuUnSelectedIcon, menuUnSelectdIcon);
        a.recycle();
        initViews(context);
    }

    private void initViews(Context context) {
        //创建顶部菜单指示
        tabMenuView = new LinearLayout(context);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        tabMenuView.setOrientation(HORIZONTAL);
        tabMenuView.setLayoutParams(lp);
        addView(tabMenuView, 0);
        //创建底部下划线
        View underlineView = new View(context);
        underlineView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, dp2Px(1.0f)));
        underlineView.setBackgroundColor(underlineColor);
        addView(underlineView, 1);
        //初始化containerView
        containerView = new FrameLayout(context);
        containerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(containerView, 2);
    }

    private int dp2Px(float value) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, dm);
    }

    /**
     * 初始化DropDownMenu 显示具体内容
     */
    public void setDropDownMenu(List<String> tabTexts, List<View> popuViews, View contentView) {
        this.contentView = contentView;
        //如果字符集合与弹出窗口数量不一致，抛出错误
        if (tabTexts.size() != popuViews.size()) {
            throw new IllegalArgumentException("tabTexts.size() should be equal popuview.size().");
        }
        //循环加入文本标签
        for (int i = 0; i < tabTexts.size(); i++) {
            addTab(tabTexts, i);
        }
        //下部加入内容视图
        containerView.addView(contentView, 0);
        //创建遮罩视图
        maskView = new View(getContext());
        maskView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        maskView.setBackgroundColor(maskColor);
        maskView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
            }
        });
        maskView.setVisibility(View.GONE);
        //把遮罩层加入视图
        containerView.addView(maskView, 1);
        //创建弹出窗口视图
        popupMenuViews = new FrameLayout(getContext());
        popupMenuViews.setVisibility(View.GONE);
        for (int i = 0; i < popuViews.size(); i++) {
            popuViews.get(i).setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
            popupMenuViews.addView(popuViews.get(i), i);
        }
        //把弹出窗口加入视图
        containerView.addView(popupMenuViews, 2);
    }

    //添加菜单视图中的子视图
    public void addTab(List<String> tabTexts, int index) {
        final TextView tab = new TextView(getContext());
        tab.setSingleLine();
        tab.setEllipsize(TextUtils.TruncateAt.END);
        tab.setGravity(Gravity.CENTER);
        tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
        tab.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f));
        tab.setTextColor(textUnSelectedColor);
        tab.setCompoundDrawablesWithIntrinsicBounds(null, null,
                getResources().getDrawable(menuUnSelectdIcon), null);
        tab.setText(tabTexts.get(index));
        tab.setPadding(dp2Px(5), dp2Px(12), dp2Px(5), dp2Px(12));
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchMenu(tab);
            }
        });
        tabMenuView.addView(tab);
        //添加分割线
        if (index < tabTexts.size() - 1) {
            View view = new View(getContext());
            view.setLayoutParams(new LayoutParams(dp2Px(0.5f), LayoutParams.MATCH_PARENT));
            view.setBackgroundColor(dividerColor);
            tabMenuView.addView(view);
        }
    }

    /**
     * 切换菜单
     *
     * @param targeView
     */
    private void switchMenu(View targeView) {
        for (int i = 0; i < tabMenuView.getChildCount(); i += 2) {
            if (targeView == tabMenuView.getChildAt(i)) {
                if (currentTabPosition == i) {//关闭菜单
                    closeMenu();
                } else {//弹出菜单
                    if (currentTabPosition == -1) {//初始状态
                        popupMenuViews.setVisibility(View.VISIBLE);
                        popupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_in));
                        maskView.setVisibility(View.VISIBLE);
                        maskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_in));
                        popupMenuViews.getChildAt(i / 2).setVisibility(View.VISIBLE);
                    } else {
                        popupMenuViews.setVisibility(View.VISIBLE);
                    }
                    currentTabPosition = i;
                    ((TextView) tabMenuView.getChildAt(i)).setTextColor(textSelectedColor);
                    ((TextView) tabMenuView.getChildAt(i)).setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(menuSelectdIcon), null);
                    // popupMenuViews.setVisibility(View.GONE);
                }
            } else {
                ((TextView) tabMenuView.getChildAt(i)).setTextColor(textUnSelectedColor);
                ((TextView) tabMenuView.getChildAt(i)).setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(menuUnSelectdIcon), null);
                popupMenuViews.getChildAt(i / 2).setVisibility(View.GONE);
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if (currentTabPosition != -1) {
            ((TextView) tabMenuView.getChildAt(currentTabPosition)).setTextColor(textUnSelectedColor);
            ((TextView) tabMenuView.getChildAt(currentTabPosition)).setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(menuUnSelectdIcon), null);
            popupMenuViews.setVisibility(View.GONE);
            popupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_out));
            maskView.setVisibility(View.GONE);
            maskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_out));
            currentTabPosition = -1;
        }
    }

    /**
     * 改变tab文字
     *
     * @param text
     */
    public void setTabText(String text) {
        if (currentTabPosition != -1) {
            //((TextView)((ViewGroup)tabMenuView.getChildAt(currentTabPosition)).getChildAt(0)).setText(text+"  ");
            TextView tv = (TextView) tabMenuView.getChildAt(currentTabPosition);
            tv.setText(text);
        }
    }

    public void setTabClickable(boolean clickable) {
        for (int i = 0; i < tabMenuView.getChildCount(); i = i + 2) {
            tabMenuView.getChildAt(i).setClickable(clickable);
        }
    }

    public void setListView(CommonBaseAdapter adapter, int tvVisiable, OnClickListener listener) {
        if (currentTabPosition != -1) {
            RelativeLayout rl = (RelativeLayout) containerView.getChildAt(0);
            if (rl != null) {
                ListView lv = rl.findViewById(R.id.lv_drop_view_content);
                TextView tv = rl.findViewById(R.id.tv_mes);
                tv.setVisibility(tvVisiable);
                FloatingActionButton fab = rl.findViewById(R.id.fab_scan);
                fab.setVisibility(VISIBLE);
                fab.setOnClickListener(listener);
                lv.setAdapter(adapter);
                tv.setVisibility(tvVisiable);
            }
        }
    }
    /**
     public void setImageResource(int imagId) {
     if (currentTabPosition != -1) {
     ImageView iv = (ImageView) containerView.getChildAt(0);
     iv.setBackgroundResource(imagId);
     }
     }
     */
    /**
     * DropDownMenu是否处于可见状态
     *
     * @return
     */
    public boolean isShowing() {
        return currentTabPosition != -1;
    }
}
