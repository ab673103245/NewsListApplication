package qianfeng.newslistapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/1 0001.
 */
public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<QiuBaiBean> list;

    public MyBaseAdapter(Context context, List<QiuBaiBean> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.list_item,parent,false);
            holder = new ViewHolder();
            holder.tv = ((TextView) convertView.findViewById(R.id.tv));
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(list.get(position).getContent());


        return convertView;
    }

    class ViewHolder
    {
        TextView tv;
    }
}
