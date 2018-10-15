package com.swufe.hello;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RateListActivity2 extends ListActivity implements Runnable{

    Handler handler;
    private ArrayList<HashMap<String,String>> listItems;//存放文字
    private SimpleAdapter listItemAdapter;
    private int msgWhaw=7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLisrView();
        this.setListAdapter(listItemAdapter);

        Thread t = new Thread((Runnable) this);
        t.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==msgWhaw){
                    List<HashMap<String,String>> retList=(List<HashMap<String,String>>) new ArrayList<HashMap<String, String>>();
                    SimpleAdapter adapter=new SimpleAdapter(RateListActivity2.this,retList,
                            R.layout.list_item,
                            new String[]{"ItemTitle","ItemDetail"},
                            new int[]{R.id.itemTitle,R.id.itemDetail});
                   // ListAdapter adapter=new ArrayAdapter<String>(RateListActivity2.this,android.R.layout.simple_list_item_1,retList);
                    setListAdapter(adapter);
                    Log.i("handle","rese list...");
                }
                super.handleMessage(msg);
            }
        };
    }

    public void run(){
        Log.i("thread", "run.......");
        boolean marker=false;
        List<HashMap<String,String>> rateList = new ArrayList<HashMap<String, String>>();
        try {
            String url = "http://www.usd-cny.com/bankofchina.htm";
            Document doc = Jsoup.connect(url).get();
            Elements tbs = doc.getElementsByClass("tableDataTable");
            Element table = tbs.get(0);
            Elements tds = table.getElementsByTag("td");
            for (int i = 6; i < tds.size(); i += 6) {
                Element td = tds.get(i);
                Element td2 = tds.get(i + 3);

                String tdStr = td.text();
                String pStr = td2.text();
                HashMap<String,String> map=new HashMap<String, String>();
                map.put("ItemTitle",tdStr);
                map.put("ItemDetail",pStr);
                rateList.add(map);
            }
            marker=true;
        } catch (MalformedURLException e) {
            Log.e("www",e.toString());
            e.printStackTrace();
        }catch (IOException e){
            Log.e("www",e.toString());
            e.printStackTrace();
        }

        Message msg=handler.obtainMessage();
        msg.what=msgWhaw;
        if (marker){msg.arg1=1;}
        else{msg.arg1=0;}

        msg.obj=rateList;
        Log.i("thread","sendMessage......");

    }

    private void initLisrView(){
        listItems=new ArrayList<HashMap<String, String>>();
        for (int i=0;i<10;i++){
            HashMap<String,String> map=new HashMap<String, String>();
            map.put("ItemTitle","Rate:"+i);//标题文字
            map.put("ItemDetail","Detail:"+i);//详情描述
            listItems.add(map);
        }

        listItemAdapter=new SimpleAdapter(this,listItems,
                R.layout.list_item,
                new String[]{"ItemTitle","ItemDetail"},
                new int[]{R.id.itemTitle,R.id.itemDetail});
    }

}
