package auto.cn.appinspection.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonBaseAdapter<T> extends BaseAdapter{
	protected Context mContext;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	private int layoutId;
	public  CommonBaseAdapter(Context context,List<T> datas,int layoutId){
		this.mDatas=datas;
		this.mContext=context;
		this.layoutId=layoutId;
		mInflater=LayoutInflater.from(context);
	}

	public CommonBaseAdapter() {
	}

	@Override
	public int getCount() {
		return mDatas.size()>0?mDatas.size():0;
	}

	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public  View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder=ViewHolder.get(mContext, parent, layoutId, position, convertView);
		convert(holder,getItem(position));
		return holder.getConvertView();
	}
public abstract void convert(ViewHolder holder,T t);

}
