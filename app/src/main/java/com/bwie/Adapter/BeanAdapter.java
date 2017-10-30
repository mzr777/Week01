package com.bwie.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.Bean.ListBean;
import com.bwie.week03.R;

import java.util.List;

/**
 * Created by 董绍华 on 2017/9/17.
 */

public class BeanAdapter extends BaseAdapter {

    Context context;
    List<ListBean> list;

    public BeanAdapter(Context context, List<ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    class  ViewHolder
    {
       ImageView img;
        TextView name;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null)
        {
            //获取自定义视图
            view=View.inflate(context, R.layout.layout,null);
            holder=new ViewHolder();
            holder.img= (ImageView) view.findViewById(R.id.layout_img);
            holder.name= (TextView) view.findViewById(R.id.layout_text);

            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        ListBean listBean=list.get(i);
        holder.img.setImageResource(listBean.getImg());
        holder.name.setText(listBean.getName());
        return view;
    }
}
