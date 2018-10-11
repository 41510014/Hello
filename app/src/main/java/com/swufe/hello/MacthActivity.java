package com.swufe.hello;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MacthActivity extends AppCompatActivity {

    TextView score;
    TextView scoreb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_match);

        score=findViewById(R.id.score);
        scoreb=findViewById(R.id.scoreb);

    }

    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
    @Override
    protected void onRestart(){
        super.onRestart();
    }
    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onStop(){
        super.onStop();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        String scorea= (String) ((TextView)findViewById(R.id.score)).getText();
        String scoreb=(String)((TextView)findViewById(R.id.scoreb)).getText();
        outState.putString("teama_score",scorea);
        outState.putString("teamb_score",scoreb);
    }
    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        String scorea=saveInstanceState.getString("teama_score");
        String scoreb=saveInstanceState.getString("teamb_score");
        ((TextView)findViewById(R.id.score)).setText(scorea);
        ((TextView)findViewById(R.id.scoreb)).setText(scoreb);
    }

    public void btnAdd1(View btn){
        if (btn.getId()==R.id.btn1) {
            showScore(1);
        }else{
            showScoreb(1);
        }
    }
    public void btnAdd2(View btn){
        if (btn.getId()==R.id.btn2) {
            showScore(2);
        }else{
            showScoreb(2);
        }
    }
    public void btnAdd3(View btn){
        if (btn.getId()==R.id.btn3) {
            showScore(3);
        }else{
            showScoreb(3);
        }
    }
    public void btnReset(View btn){
        score.setText("0");
        scoreb.setText("0");
    }
    private void showScore(int inc){
        Log.i("show","inc="+inc);
        String oldScore=(String) score.getText();
        // score.setText(""+Integer.parseInt(oldScore)+inc);
        int newScore=Integer.parseInt(oldScore)+inc;
        score.setText(""+newScore);
    }
    private void showScoreb(int inc){
        Log.i("show","inc="+inc);
        String oldScore=(String) scoreb.getText();
        // score.setText(""+Integer.parseInt(oldScore)+inc);
        int newScore=Integer.parseInt(oldScore)+inc;
        scoreb.setText(""+newScore);
    }

}
