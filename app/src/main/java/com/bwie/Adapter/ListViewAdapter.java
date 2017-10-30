package com.bwie.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.Bean.Bean;
import com.bwie.week03.R;

import java.util.List;

/**
 * Created by 董绍 on 2017/9/16.
 */

public class ListViewAdapter extends BaseAdapter {
    List<Bean.ResultsBean> list;
    Context context;

    public ListViewAdapter(List<Bean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
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
        return 0;
    }
    class  ViewHolder
    {
        TextView text;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null)
        {
            view=View.inflate(context, R.layout.listview,null);
            holder=new ViewHolder();
            holder.text= (TextView) view.findViewById(R.id.text1);
            view.setTag(view);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

           Bean.ResultsBean resultsBean=list.get(i);
           holder.text.setText(resultsBean.getDesc()+"\n"+resultsBean.getUrl());

        return view;
    }
}
