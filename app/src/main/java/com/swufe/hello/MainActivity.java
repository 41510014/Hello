package com.swufe.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView out;
    EditText inp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         out = findViewById(R.id.showview);
       out.setText("swufe");

        inp = findViewById(R.id.editText);
        String str = inp.getText().toString();

       // Log.i("main", "input=" + str);

        Button btn=findViewById(R.id.btn1);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.i("click","OnClick ......");

       // TextView tv = findViewById(R.id.showview);
        //EditText inp = findViewById(R.id.editText);
        String str = inp.getText().toString();

        out.setText("Holle "+str);
    }
}
