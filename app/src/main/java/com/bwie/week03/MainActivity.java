package com.bwie.week03;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.Bean.ImgBean;
import com.bwie.Bean.ImgBean1;
import com.bwie.Utils.NewUtils;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 董绍华
 */
public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL="https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10";
    ViewPager pager;
    TextView  text;
    LinearLayout linearLayout;
    List<ImageView> imglist=new ArrayList<>();
    List<View>     viewList=new ArrayList<>();
    List<ImgBean1.NewslistBean> str;
    int imgcount=0;
    int viewcount=0;
   // int count=0;
    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            pager.setCurrentItem(imgcount);
            viewList.get(viewcount).setBackgroundResource(R.drawable.shape1);
            viewList.get(imgcount%viewList.size()).setBackgroundResource(R.drawable.shape2);
            viewcount=imgcount%viewList.size();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }
    //初始化
    public void initView()
    {
        pager= (ViewPager) findViewById(R.id.pager);
        text= (TextView) findViewById(R.id.textview);
        linearLayout= (LinearLayout) findViewById(R.id.linear);

        //点击跳转
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建Intent对象
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);

                startActivity(intent);
            }
        });

        //异步线程
        new AsyncTask<String,Integer,String>()
        {

            @Override
            protected String doInBackground(String... strings) {
                String JsonStr=new NewUtils().getGson(JSON_URL);
                return JsonStr;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                //解析Gson串
                ImgBean1 imgBean=new Gson().fromJson(s,ImgBean1.class);
                //得到图片链接数组
                str=imgBean.getNewslist();

                getImg();//加载图片
                getYu();//加载小圆点
                pager.setAdapter(new MyAdapter());//ViewPager设置适配器
                pager.setCurrentItem(3000);
                viewList.get(0).setBackgroundResource(R.drawable.shape1);
                //定时器发送
                Timer timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        imgcount=pager.getCurrentItem()+1;
                        handler.sendEmptyMessage(0);
                    }
                },2000,3000);

            }
        }.execute();


    }
    //加载图片
     public void getImg()
     {
         for (int i=0;i<str.size();i++)
         {
             //创建ImageView
             ImageView imag=new ImageView(MainActivity.this);

             ImageLoader.getInstance().displayImage(str.get(i).getPicUrl(),imag);
             imglist.add(imag);//将图片添加到缓存
         }
     }
    //加载小圆点
       public void getYu() {
           for (int i = 0; i < imglist.size(); i++) {
               View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_layout, null);
               View v = view.findViewById(R.id.view);
               viewList.add(v);
               linearLayout.addView(view);
           }
       }

    public  class MyAdapter extends PagerAdapter
    {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView img=imglist.get(position%imglist.size());
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(img);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
