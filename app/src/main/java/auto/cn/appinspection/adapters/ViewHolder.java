package auto.cn.appinspection.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import auto.cn.appinspection.ui.RoundProgress;

public class ViewHolder {
	private SparseArray<View> mViews;
	private View mConvertView;
	private int mPosition;
	private Context mContext;
	public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
		this.mPosition = position;
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		mConvertView.setTag(this);
		this.mContext=context;
	}

	public View getConvertView() {
		return mConvertView;
	}
/*
 * 
 */
	public static ViewHolder get(Context context, ViewGroup parent, int layoutId, int position, View convertView) {
		if (convertView == null) {
			return new ViewHolder(context, parent, layoutId, position);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}
/*
 * 通过viewId获取控件
 */
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	public ViewHolder setText(int viewId, String text) {
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}

	public ViewHolder setImageResource(int viewId, int resId) {
		ImageView iv = getView(viewId);
		iv.setImageResource(resId);
		return this;
	}

	public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
		ImageView iv = getView(viewId);
		iv.setImageBitmap(bm);
		return this;
	}
	public ViewHolder setImageUrl(int viewId, Uri uri) {
		ImageView iv = getView(viewId);
		Picasso.with(mContext).load(uri).into(iv);
		return this;
	}

	public ViewHolder setTextColor(int viewId, int colorRes) {
		TextView tv = getView(viewId);
		tv.setTextColor(colorRes);
		return this;
	}
	public ViewHolder tvOnClick(int viewId,CompoundButton.OnCheckedChangeListener listener){
		ToggleButton tb=getView(viewId);
		tb.setOnCheckedChangeListener(listener);
		return this;
	}
	public ViewHolder setProgessBarProgress(int viewId,int progress){
		RoundProgress rp=getView(viewId);
		rp.setProgress(progress);
		return this;
	}
	public ViewHolder setBackground(int viewId,int color){
		RoundProgress rp=getView(viewId);
		//rp.setProgress(progress);
		return this;
	}
}
