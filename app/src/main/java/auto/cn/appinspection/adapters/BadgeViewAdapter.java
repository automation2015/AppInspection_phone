package auto.cn.appinspection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import java.util.Map;

import auto.cn.appinspection.R;

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
        holder.ivIcon.setImageResource(iconId);
        //holder.badge.setBadgeNumber(position+1);
        return convertView;
    }
    class ViewHolder{
        TextView tvTitle,tvBadge;
        ImageView ivIcon;
        //Badge badge;
        public ViewHolder(View view){
            tvTitle=view.findViewById(R.id.tv_title);
            ivIcon=view.findViewById(R.id.iv_icon);
           // badge=new QBadgeView(context).bindTarget(view.findViewById(R.id.tv_badge));
        }

    }
}
