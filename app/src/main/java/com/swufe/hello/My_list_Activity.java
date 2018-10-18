package com.swufe.hello;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class My_list_Activity extends AppCompatActivity {

    List<String> data=new ArrayList<String>( );
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate( saveInstanceState );
        setContentView( R.id.mylist );
        ListView listView=findViewById( R.id.mylist );
       // String data[]={"111","2222"};

        for (int i=0;i<10;i++){
            data.add( "item"+i );
        }

        ListAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter( adapter );

    }
}