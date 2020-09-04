package auto.cn.appinspection.commons;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class QueryAllPlanAsync {
    private Context context;
    private ProgressDialog pd;
    private DbHelper dbHelper;
    public QueryAllPlanAsync(Context context,DbHelper helper){
        this.context=context;
        this.dbHelper=helper;
        pd=new ProgressDialog(context);
        pd.setTitle("提示");
        pd.setMessage("正在加载数据......");
    }
    public void queryAllRecord(){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pd.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pd.dismiss();
            }

            @Override
            protected String doInBackground(Void... voids) {
                return null;
            }
        }.execute();
    }
}
