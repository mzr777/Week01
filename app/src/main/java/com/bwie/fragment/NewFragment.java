package com.bwie.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bwie.Adapter.ListViewAdapter;
import com.bwie.Bean.Bean;
import com.bwie.Database.DataBase;
import com.bwie.Utils.NewUtils;
import com.bwie.week03.R;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created by 董绍华 on 2017/9/16.
 */

public class NewFragment extends Fragment {

    PullToRefreshListView pullToRefreshListView;
    String i="";
    List<Bean.ResultsBean> list;
    ListViewAdapter adapter;
    DataBase dataBase;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        i=bundle.getString("name");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //加载布局
        View view=View.inflate(getActivity(), R.layout.newfragment,null);
        pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.pulltorsfresh);
        new MyAsynTask().execute();
        dataBase=new DataBase(getActivity());
        //上拉加载
        pullToRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                new AsyncTask<String,Integer,String>()
                {

                    @Override
                    protected String doInBackground(String... strings) {
                        String JSON_URL="http://gank.io/api/data/Android/10/"+i;

                        String jsonStr=new NewUtils().getGson(JSON_URL);
                        return jsonStr;
                    }

                    @Override
                    protected void onPostExecute(String s) {

                        Bean bean=new Gson().fromJson(s,Bean.class);
                        List<Bean.ResultsBean> rlist=bean.getResults();
                        list.addAll(rlist);
                        adapter.notifyDataSetChanged();
                    }
                }.execute();
            }
        });
        //下拉刷新
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new MyAsynTask().execute();
              //  pullToRefreshListView.onRefreshComplete();//停止刷新
            }
        });

        return view;
    }

    public class MyAsynTask extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String JSON_URL="http://gank.io/api/data/Android/10/"+i;

            String jsonStr=new NewUtils().getGson(JSON_URL);
            Log.i("TAG",jsonStr);
            return jsonStr;
        }

        @Override
        protected void onPostExecute(String s) {

            Bean bean=new Gson().fromJson(s,Bean.class);
            list=bean.getResults();
            for (int i=0;i<list.size();i++)
            {

                Bean.ResultsBean resultsBean=list.get(i);
                //添加到数据库
                dataBase.add(resultsBean.get_id(),resultsBean.getCreatedAt(),resultsBean.getDesc(),resultsBean.getPublishedAt(),resultsBean.getSource(),resultsBean.getType(),resultsBean.getUrl(),resultsBean.getWho());

            }
            adapter=new ListViewAdapter(list,getActivity());
            pullToRefreshListView.setAdapter(adapter);

        }
    }

}
