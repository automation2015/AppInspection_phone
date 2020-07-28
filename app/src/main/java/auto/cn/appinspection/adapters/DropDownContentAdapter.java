package auto.cn.appinspection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import auto.cn.appinspection.R;
import auto.cn.greendaogenerate.ContentList;
import auto.cn.greendaogenerate.ItemList;

public  class DropDownContentAdapter extends BaseAdapter {
    private Context context;
    private List<ContentList> list;
    private int checkItemPosition = -1;

    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }

    public DropDownContentAdapter(Context context, List<ContentList> datas) {
        this.context = context;
        this.list = datas;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_drop_down_list, null, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        fillValue(position, holder);
        return convertView;
    }
    public void fillValue(int position, ViewHolder holder) {
        ContentList contentList = list.get(position);
        holder.tvId.setText(contentList.getCONTENT_ID());
        holder.tvName.setText(contentList.getCONTENT_NAME());
        if (checkItemPosition != -1) {
            if (checkItemPosition == position) {
                holder.tvId.setTextColor(context.getResources().getColor(R.color.drop_down_selected));
                holder.tvName.setTextColor(context.getResources().getColor(R.color.drop_down_selected));
                holder.tvName.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.mipmap.icon_drop_down_selected), null);
                holder.tvName.setCompoundDrawablePadding(420);
               // holder.tvId.setBackgroundResource(R.color.check_bg);
            } else {
                holder.tvId.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));
                holder.tvName.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));
                holder.tvName.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                //holder.tvId.setBackgroundResource(R.color.white);
            }
        }
    }

    class ViewHolder {
        TextView tvId,tvName;
        ViewHolder(View view) {
            tvId = view.findViewById(R.id.tv_plan_id);
            tvName=view.findViewById(R.id.tv_plan_name);
        }
    }
}
