package com.example.agammahajan.otp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    public TextView textView ;
    public long result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        Spooling();

    }

    private void Algo(){

        DateFormat d = new SimpleDateFormat("yyMMddHHmm");
        String da = d.format(Calendar.getInstance().getTime());

        int tt = Integer.parseInt(da);


        String test= Integer.toHexString(tt);

        String temp="";
        String mykey = "secret";

        int c=5;
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec secret = new SecretKeySpec(mykey.getBytes(),"HmacSHA1");
            mac.init(secret);
            byte[] digest = mac.doFinal(test.getBytes());
            String enc = new String(digest);

            for (byte b : digest) {
                if(c>=0){
                    temp=temp+String.format("%02x", b);
                    c--;
                }
                else break;

            }

            result=Long.parseLong(temp,16);
            result=result%100000;
            System.out.println(result);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(Long.toString(result));
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void Spooling(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Algo();
            }
        },0,1000);
    }



}
