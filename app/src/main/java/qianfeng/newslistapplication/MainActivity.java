package qianfeng.newslistapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private Button loadMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = ((ListView) findViewById(R.id.lv));
        loadMore = ((Button) findViewById(R.id.loadMore));

        new MyAsyncTask().execute("http://m2.qiushibaike.com/article/list/text?page=1");
    }

    private class MyAsyncTask extends AsyncTask<String, Void, List<QiuBaiBean>> {
        @Override
        protected List doInBackground(String... params) {
            byte[] http = HttpUtils.getHttp(params[0]);
            Log.d("Debug_google:", "doInBackground: " + new String(http,0,http.length));
            String str = new String(http,0,http.length);

            return parseJSON(str);
        }

        private List parseJSON(String str)
        {
            // 开始解析
             List<QiuBaiBean> list = new ArrayList<>();
            try {
                JSONObject object = new JSONObject(str);
                JSONArray items = object.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject jsonObject = items.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String content = jsonObject.getString("content");
                    list.add(new QiuBaiBean(id,content));
                }
                return list;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<QiuBaiBean> qiuBaiBeen) {


            MyBaseAdapter myBaseAdapter = new MyBaseAdapter(MainActivity.this, qiuBaiBeen);
            lv.setAdapter(myBaseAdapter);

            // 对lv实现监听
            lv.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                        if( (firstVisibleItem + visibleItemCount) == totalItemCount)
                        {
                            loadMore.setVisibility(View.VISIBLE);
                        }else
                        {
                            loadMore.setVisibility(View.GONE);

                            // 重新测量ListView的大小并重新设置
                            lv.requestLayout();
                        }
                }
            });
        }
    }



}
