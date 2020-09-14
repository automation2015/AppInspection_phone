package auto.cn.appinspection.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;
import java.util.Map;

import auto.cn.appinspection.R;
import auto.cn.appinspection.utils.BitmapUtils;
import auto.cn.appinspection.utils.UIUtils;

public class BadgeViewAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> mDatas;
    public BadgeViewAdapter(Context context, List<Map<String, Object>> data){
        this.context=context;
        this.mDatas=data;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.item_home_gridview,null);
            holder=new ViewHolder(convertView);
           convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        Map<String, Object> dataMap = mDatas.get(position);
        String iconTitle = (String)dataMap.get("iconTitle");
        int iconId =(int) dataMap.get("iconId");
        holder.tvTitle.setText(iconTitle);

        Picasso.with(context).load(iconId).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {//下载以后的内存中的bitmap对象
                Bitmap bitmap =makeIcon(source);
                return bitmap;
            }

            @Override
            public String key() {
                //此方法没有什么作用，但是要保证返回值不为null，否则报错
                return "";
            }
        }).into( holder.ivIcon);
       // holder.ivIcon.setImageResource(iconId);
        //holder.badge.setBadgeNumber(position+1);
        return convertView;
    }
    //处理图片的方法：压缩、圆形
    private Bitmap makeIcon(Bitmap source) {
        //压缩处理
        Bitmap bitmap = BitmapUtils.zoom(source, UIUtils.dp2px(62),
                UIUtils.dp2px(62));
        //圆形处理
        bitmap = BitmapUtils.circleBitmap(bitmap);
        //回收bitmap资源
        source.recycle();
        return bitmap;
    }
    class ViewHolder{
        TextView tvTitle,tvBadge;
        ImageView ivIcon;
        //Badge badge;
        public ViewHolder(View view){
            tvTitle=view.findViewById(R.id.tv_title);
            ivIcon=view.findViewById(R.id.iv_icon);
            tvBadge=view.findViewById(R.id.tv_badge);
           // badge=new QBadgeView(context).bindTarget(view.findViewById(R.id.tv_badge));
        }

    }
}
