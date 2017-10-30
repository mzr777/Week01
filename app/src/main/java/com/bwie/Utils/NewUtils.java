package com.bwie.Utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 董绍华 on 2017/9/16.
 * 工具类
 */

public class NewUtils {

     //获取网络Gson串
    public String getGson(String uri)
    {
        URL url;
        HttpURLConnection httpURLConnection;
        String str="";
        try
        {
            url=new URL(uri);//得到链接
            httpURLConnection= (HttpURLConnection) url.openConnection();//打开链接
            int rescode=httpURLConnection.getResponseCode();//得到回传值
            if(rescode==200)
            {
                InputStream in=httpURLConnection.getInputStream();//得到输入流
                byte[] by=new byte[1024];
                int len=0;
                while ((len=in.read(by))!=-1)
                {
                    str+=new String(by,0,len);
                }
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return  str;
    }
}
