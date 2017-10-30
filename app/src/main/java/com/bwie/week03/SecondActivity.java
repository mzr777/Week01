package com.bwie.week03;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.bwie.Adapter.BeanAdapter;
import com.bwie.Bean.ListBean;
import com.bwie.fragment.NewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 董绍华
 */
public class SecondActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ListView listView;
    List<ListBean> listBeen;
    List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

         initView();
    }
    //初始化控件获取id
    public void initView()
    {
        drawerLayout= (DrawerLayout) findViewById(R.id.drawablelayout);
        tabLayout= (TabLayout) findViewById(R.id.tabs);
        viewPager= (ViewPager) findViewById(R.id.vp_view);
        listView= (ListView) findViewById(R.id.left_drawer);
        //适配数据
       listBeen=new ArrayList<>();
       listBeen.add(new ListBean(R.mipmap.a,"搜索"));
       listBeen.add(new ListBean(R.mipmap.b,"收藏"));
       listBeen.add(new ListBean(R.mipmap.c,"消息"));
       listBeen.add(new ListBean(R.mipmap.d,"离线"));
       listBeen.add(new ListBean(R.mipmap.e,"活动"));
       BeanAdapter beanAdapter=new BeanAdapter(this,listBeen);
       listView.setAdapter(beanAdapter);

        //相互绑定
        tabLayout.setupWithViewPager(viewPager);
        //添加头部数据
        list.add("头条");
        list.add("社会");
        list.add("国内");
        list.add("国际");
        list.add("娱乐");
        list.add("体育");
        list.add("军事");
        list.add("科技");
        list.add("财经");
        list.add("时尚");

        setViewPager();
    }
      //设置ViewPager适配器
    public void setViewPager()
    {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }

            @Override
            public Fragment getItem(int position) {

                //NewFragment对象
                NewFragment fragment=new NewFragment();
                //Bundle对象
                Bundle  bundle=new Bundle();

                if (list.get(position).equals("头条")) {
                    bundle.putString("name", "1");
                } else if (list.get(position).equals("社会")) {
                    bundle.putString("name", "2");
                } else if (list.get(position).equals("国内")) {
                    bundle.putString("name", "3");
                } else if (list.get(position).equals("国际")) {
                    bundle.putString("name", "4");
                } else if (list.get(position).equals("娱乐")) {
                    bundle.putString("name", "5");
                } else if (list.get(position).equals("体育")) {
                    bundle.putString("name", "6");
                } else if (list.get(position).equals("军事")) {
                    bundle.putString("name", "7");
                } else if (list.get(position).equals("科技")) {
                    bundle.putString("name", "8");
                } else if (list.get(position).equals("财经")) {
                    bundle.putString("name", "9");
                } else if (list.get(position).equals("时尚")) {
                    bundle.putString("name", "10");
                }

                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

    }
}
