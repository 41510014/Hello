package com.swufe.hello;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
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

public class RateListActivity extends ListActivity {
    private static final String TAG="MyAdapter";
    private String[] list_data = {"one", "two", "three", "four"};
 //   int msgWhat = 3;
    Handler handler;
    private ArrayList<HashMap<String,String>> listItems;//存放文字
    private SimpleAdapter listItemAdapter;
    private int msgWhaw=7;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> list1 = new ArrayList<String>();
        ListView listView=findViewById( R.id.mylist );
       // GridView listView=findViewById( R.id.mylist );

        for (int i = 1; i < 100; i++) {
            list1.add("item" + i);

        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_data);
        setListAdapter(adapter);
        //getListAdapter();
        listView.setEmptyView( findViewById( R.id.nodata ) );
        listView.setOnItemClickListener( (AdapterView.OnItemClickListener) this );
        getListView().setOnItemLongClickListener( (AdapterView.OnItemLongClickListener) this );


        /*listView.setAdapter( adapter );
        listView.setEmptyView( findViewById( R.id.nodata ) );
        listView.setOnItemClickListener( this );
        */
        Thread t = new Thread((Runnable) this);
        t.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==5){
                    List<String> retList=(List<String>) msg.obj;
                    ListAdapter adapter=new ArrayAdapter<String>(RateListActivity.this,android.R.layout.simple_list_item_1,retList);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };



    }


    public void run() throws IOException {
        Log.i("thread", "run.......");
        List<String> rateList = new ArrayList<String>();
        try {
            String url = "http://www.usd-cny.com/bankofchina.htm";
            Document doc = Jsoup.connect(url).get();
            Elements tbs = doc.getElementsByClass("tableDataTable");
            Element table = tbs.get(0);
            Elements tds = table.getElementsByTag("td");
            for (int i = 0; i < tds.size(); i += 5) {
                Element td = tds.get(i);
                Element td2 = tds.get(i + 3);

                String tdStr = td.text();
                String pStr = td2.text();
                rateList.add(tdStr + "=>" + pStr);

                Log.i("td", tdStr + "=>" + pStr);

            }
        } catch (MalformedURLException e) {
            Log.e("www",e.toString());
            e.printStackTrace();
        }catch (IOException e){
            Log.e("www",e.toString());
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(5);

        msg.obj=rateList;
        Log.i("thread","sendMessage......");

    }

    public void onItemClick(AdapterView<?> listview, View view,int position){
        Log.i( TAG,"onItemClick:position="+position );
        Log.i( TAG,"onItemClick:parent="+listview );

        listItems.remove( listview.getItemAtPosition( position ) );

    }

    public boolean onItemLongClick(AdapterView<?> listview, View view,int position,long id){
        return true;
    }



}
